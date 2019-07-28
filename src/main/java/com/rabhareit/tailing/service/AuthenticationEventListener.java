package com.rabhareit.tailing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.Component;

/**
 * Sample Class.
 */

@Component
public class AuthenticationEventListener {
  private static final Logger log = LoggerFactory.getLogger(AuthenticationEventListener.class);

  @EventListener
  public void handleBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
    log.info("Bad credentials event is detected. username:{}", event.getAuthentication().getName());
  }

  @EventListener
  public void handleDisabledEvent(AuthenticationFailureDisabledEvent event) {
    log.info("Disabled event is detected. username:{}", event.getAuthentication().getName());
  }

  @EventListener
  public void handleLockedEvent(AuthenticationFailureLockedEvent event) {
    log.info("Locked event is detected. username:{}", event.getAuthentication().getName());
  }

  @EventListener
  public void handleExpiredEvent(AuthenticationFailureExpiredEvent event) {
    log.info("Expired event is detected. username:{}", event.getAuthentication().getName());
  }

  @EventListener
  public void handleCredentialsExpiredEvent(AuthenticationFailureCredentialsExpiredEvent event) {
    log.info("Credentials expired event is detected. username:{}", event.getAuthentication().getName());
  }

  @EventListener
  public void handleServiceExceptionEvent(AuthenticationFailureServiceExceptionEvent event) {
    log.info("Service exception event is detected. username:{}", event.getAuthentication().getName());
  }

    /**
     * 認証済みユーザーの認証情報はデフォルトではセッションに保存される。格納された認証情報はリクエストごとに
     * SecurityContextPersistenceFilterクラスによってSecurityContextHolderクラスに格納され、
     * 同一スレッド内であればどこからでもアクセスできる。
     *
     *
     * 監査ログ（「いつ」「誰が」「どのデータに」「どのようなアクセスをしたか」の「誰か」）
     *
     *   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     *
     *   String userUuid = null;
     *   if (auth.getPrincipal() instanceof UserDetails) {
     *     UserDetails userDetails = TailingUserDetails.class.cast(auth.getPrincipal());
     *     userUuid = userDetails.getAccount().getUuid();
     *   }
     *
     *   デフォルトでは認証情報をスレッドローカルの変数に格納しているため、リクエストを受けたスレッドと同じスレッドであれば
     *   どこからでもアクセスできてしまう。便利な半面、認証情報を必要とするクラスがSecurityContextHolderに直接依存しており、
     *   乱用するとコンポーネントの「疎結合性」を低下させる原因となる。
     */


}
