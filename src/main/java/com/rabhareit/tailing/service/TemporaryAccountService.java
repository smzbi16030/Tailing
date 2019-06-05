package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TemporaryAccount;
import com.rabhareit.tailing.repository.TemporaryAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemporaryAccountService {

  @Autowired
  TemporaryAccountRepository repo;

  public Optional<TemporaryAccount> getAccountById(Long id) {
    return repo.findById(id);
  }

  public void saveNewAccount(TemporaryAccount account) {
    System.err.printf("username: %s (id: %s ) is saved.",account.getUsername(),account.getId());
    repo.saveAndFlush(account);
  }

  public void deleteById(Long id) {
    repo.deleteById(id);
  }
}
