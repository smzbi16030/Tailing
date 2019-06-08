package com.rabhareit.tailing.repository;

import com.rabhareit.tailing.entity.TemporaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporaryAccountRepository extends JpaRepository<TemporaryAccount, Long> {
  public TemporaryAccount findByUsername(String username);

  public Optional<TemporaryAccount> findById(Long id);

  public void deleteById(Long id);
}
