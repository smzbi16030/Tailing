package com.rabhareit.tailing.service;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class CompletionListenerImpl implements DatabaseReference.CompletionListener {
  @Override
  public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
    //TODO なんかいい感じに
    System.out.println(databaseError+"¥n"+databaseReference);
  }
}
