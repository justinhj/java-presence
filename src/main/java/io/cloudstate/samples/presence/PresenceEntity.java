package io.cloudstate.samples.presence;

import java.util.Arrays;
import java.util.Iterator;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import io.cloudstate.javasupport.crdt.*;
import cloudstate.samples.chat.presence.grpc.*;

/** Presence service is a CRDT entity that takes care of an OrSet of a users presence. */
@CrdtEntity
public class PresenceEntity {

  private final Vote presence;

  public PresenceEntity(Vote presence) {
    this.presence = presence;

  }


  /**
   * Connect a user, to make their presence active.
   *
   * This is a streamed call. As long as a user (id given by the entity id) is connected
   * to it, they are considered to be online.
   *
   * Here we use a Vote CRDT, which if at least one node votes is true, will be true.
   * So when the user connects, we invoke the connect() method (which we have defined
   * by enriching the CRDT in onStateSet), which will manage our vote accordingly.
   *
   * When they disconnect, the onStreamCancel callback is invoked, and we disconnect,
   * removing our vote if this is the last connection to this CRDT.
   */

  @CommandHandler
  public Source<Empty, NotUsed> connect(User user, CommandContext ctx) {

    //presence.

    return null;
  }

}
