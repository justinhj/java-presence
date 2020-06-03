package io.cloudstate.samples.friends;

import java.util.Arrays;
import java.util.Iterator;

import io.cloudstate.javasupport.crdt.*;
import cloudstate.samples.chat.friends.grpc.*;

/** Friends service is a CRDT entity that takes care of an OrSet of a users friends. */
@CrdtEntity
public class FriendsEntity {

  private final ORSet<Friend> friends;

  public FriendsEntity(ORSet<Friend> friends) {
    this.friends = friends;
  }

  @CommandHandler
  public Empty add(FriendRequest req) {
    friends.add(req.getFriend());
    return Empty.getDefaultInstance();
  }

  @CommandHandler
  public FriendsList getFriends(User user) {
    Iterator<Friend> fi = friends.iterator();
    FriendsList.Builder b = FriendsList.newBuilder();
    for(Friend f: friends) {
      b.addFriends(f);
    }

    return b.build();
  }

  @CommandHandler
  public Empty remove(FriendRequest req) {
    friends.remove(req.getFriend());
    return Empty.getDefaultInstance();
  }
}
