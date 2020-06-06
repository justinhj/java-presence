package io.cloudstate.samples.presence;

// import java.util.Arrays;
// import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

// import akka.NotUsed;
// import akka.stream.javadsl.Source;
import io.cloudstate.javasupport.crdt.*;
import cloudstate.samples.chat.presence.grpc.*;

/** Presence service is a CRDT entity that takes care of an OrSet of a users presence. */
@CrdtEntity
public class PresenceEntity {

  private Vote presence = null;
  private int users = 0;

  public PresenceEntity(Vote presence) {
    this.presence = presence;
    System.out.println("Created entity");

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

  private Optional<Vote>changeHandler(SubscriptionContext subcription) {
    System.out.println("change!");

    return Optional.empty();
  }

  private Consumer<StreamCancelledContext> cancelHandler = a -> {
    users -= 1;
    if (users == 0) {
      presence.vote(false);
    }

    System.out.println("cancel! users " + users);
  };

  @CommandHandler
  public Empty connect(StreamedCommandContext<Vote> ctx) {

    if(ctx.isStreamed()) {
      ctx.onChange(s -> changeHandler(s));
      ctx.onCancel(cancelHandler);

      users += 1;
      if (users ==1) {
        presence.vote(true);
      }

      System.out.println("users = " + users);

    }
    else {
      System.out.println("not streamed");
      ctx.fail("Call to connect must be streamed");
    }
    return Empty.getDefaultInstance();
  }

}
