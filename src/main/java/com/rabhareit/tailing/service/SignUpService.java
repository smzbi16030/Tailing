package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TailingSocialUser;
import com.rabhareit.tailing.repository.TailingSocialUserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.social.connect.UserProfile;

import javax.transaction.Transactional;
import java.util.Objects;

public class SignUpService {

  TailingSocialUserRepository socialUserRepository;

  //@Transactional
  public TailingSocialUser createUser(UserProfile userProfile) {
    String userId = "";
    boolean userIdDecided = false;

    while (!userIdDecided) {
      userId = RandomStringUtils.randomAlphanumeric(8);
      TailingSocialUser user = socialUserRepository.findOneByUserId(userId);
      if (Objects.isNull(user)) {
        userIdDecided = true;
      }
    }

    TailingSocialUser newUser = new TailingSocialUser();
    newUser.setUserId(userId);
    newUser.setTailingId(Long.parseLong(RandomStringUtils.randomNumeric(32)));
    newUser.setScreenName(userProfile.getUsername());
    newUser.setEncodedPasswd(RandomStringUtils.randomAlphanumeric(32));
    TailingSocialUser savedUser = socialUserRepository.save(newUser);
    return savedUser;
  }
}
