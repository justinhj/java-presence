package io.cloudstate.samples.presence;

import cloudstate.samples.chat.presence.grpc.*;
import io.cloudstate.javasupport.*;

public class Main {

	public static void main(String[] args) throws Exception {

    new CloudState()
        .registerCrdtEntity(
            PresenceEntity.class,
						PresenceGrpc.getDescriptor().findServiceByName("Presence"))
				.start()
        .toCompletableFuture()
        .get();
	}
}
