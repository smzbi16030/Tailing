package com.rabhareit.tailing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/config")
public class AdminController {

    @Value("${app.tailing-consumer-key}")
    private String tailingConsumerKey;

    @Value("${app.tailing-consumer-secret}")
    private String tailingConsumerSecret;

    @Value("${app.tailing-access-token}")
    private String tailingAccessToken;

    @Value("${app.tailing-access-token-secret}")
    private String tailingAccessTokenSecret;

    @Autowired
    private JdbcTemplate jdbc;

    @RequestMapping("/stream/stop")
    public String streamStop() {
        return "";
    }

    @RequestMapping("/stream")
    public String administratorConfiguration() throws TwitterException {
        Configuration configuration = new ConfigurationBuilder()
            .setOAuthConsumerKey(tailingConsumerKey)
            .setOAuthConsumerSecret(tailingConsumerSecret)
            .setOAuthAccessToken(tailingAccessToken)
            .setOAuthAccessTokenSecret(tailingAccessTokenSecret)
            .build();
        Twitter twitter = new TwitterFactory(configuration).getInstance();
        TwitterStream twStream = new TwitterStreamFactory(configuration).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onStatus(Status status) {
                if (status.getText().startsWith("RT")) {
                    // MEMO リツイートは表示しない
                    return;
                } else {
                    // 確認用
                    String target = status.getUser().getScreenName();
                    System.out.println("id = " + status.getId() + ", screenName = " + target + ", text = " + status.getText());
                    System.out.println(" -> tweet to @" + target);

                    try {
                        StringBuffer buffer = new StringBuffer();
                        List<Map<String,Object>> allTask = jdbc.queryForList("select * from task_model where ownerid = ?",target);
                        allTask.stream().forEach( (task) -> buffer.append(task.get("title") + " : " + task.get("deadline") + "まで" + System.lineSeparator()) );
                        twitter.updateStatus("@" + target + System.lineSeparator() + buffer.toString());
                    } catch(NullPointerException npe) {
                        npe.printStackTrace();
                    } catch(TwitterException te) {
                        te.printStackTrace();
                    }
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                // MEMO ステータスの削除通知 (delete)
                // MEMO 通知に従って保存領域から該当ツイートを削除しなければならない
                // MEMO ツイート主が削除したツイートは自分のデータベースからも削除しなければならない。
                // TODO ユーザーが消し去ったツイートがDBに残ってないことを明示できたら
                System.out.println("!-----[onDeletionNotice]-----");
            }

            @Override
            public void onScrubGeo(long lat, long lng) {
                // MEMO 位置情報の削除通知 (scrub_geo)
                // MEMO 通知に従って保存領域から該当ツイートの位置情報を削除しなければならない
                // MEMO ツイート主が削除した位置情報は自分のデータベースからも削除しなければならない。
                // TODO ユーザーが消し去った位置情報がDB残ってないことを明示できたら
                System.out.println("!-----[onScrubGeo]-----");
            }

            @Override
            public void onTrackLimitationNotice(int i) {
                // MEMO 制限通知 (limit)
                // MEMO 速度制限の上限を超えたために取得できなかったツイートが存在する
                // TODO リアルタイム性が重視なのでツイートしなくてもいいかもしれないが、エラーが継続してたら知らせる仕組みを作る
                System.out.println("!-----[onTrackLimitationNotice]-----");
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                // NOTE 速度低下警告 (warning)
                // TODO メールするか放置か
                System.out.println("!-----[onStallWarning]-----");
            }

            @Override
            public void onException(Exception excptn) {
                // MEMO その他エラー
                // TODO ストリームを複数開いた,API上限,短時間に同じ内容のツイートなど,分岐できたら
                System.out.println("!-----[onException]-----");
                excptn.printStackTrace();
            }
        };

        // MEMO ストリームの開始
        twStream.addListener(listener);


        FilterQuery filter = new FilterQuery();
        filter.follow(new long[]{826175150391320576l});
        twStream.filter(filter);

        return "adConfig";
    }

}
