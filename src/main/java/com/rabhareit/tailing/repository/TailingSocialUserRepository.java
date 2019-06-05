package com.rabhareit.tailing.repository;

import com.rabhareit.tailing.entity.TailingSocialUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TailingSocialUserRepository extends JpaRepository<TailingSocialUser,String> {
  TailingSocialUser findByUserId(String id);
  TailingSocialUser findOneByUserId(String id);
}
