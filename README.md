# Cloudstate samples

## Chat Presence service - Java implementation

This is an implementation of the presence service, which is part of Lightbend Cloudstate Chat sample.

The user code is written in Java and uses a 100% Java toolchain (no Scala tools needed).

### Preparing to build

Install Maven

http://maven.apache.org/download.cgi

### Build the package and Docker image

```
mvn package
mvn io.fabric8:docker-maven-plugin:0.26.1:build
```

### Push the image

```
mvn io.fabric8:docker-maven-plugin:0.26.1:push
```

### Testing

Cloudstate deployment requires your code to run in a container. The following steps will allow you test the presence service.

In a terminal run the Cloudstate side-car:

`docker run -it --rm --network mynetwork --name cloudstate -p 9000:9000 cloudstateio/cloudstate-proxy-dev-mode -Dcloudstate.proxy.user-function-port=8080 -Dcloudstate.proxy.user-function-interface=java-presence`

Open a second terminal to run the presence user function container (built as above).

`docker run -it --rm --name java-presence --network mynetwork justinhj/java-presence`

In a third terminal window simulate a user connecting to the service with grpcurl.

`grpcurl -plaintext -d '{"name": "Alice"}' localhost:9000 cloudstate.samples.chat.presence.Presence/Connect`

Repeat as many times as you feel like, watch the user count increase in the debug output of the second terminal.

Now open a terminal to test the monitor call.

`grpcurl -plaintext -d '{"name": "Alice"}' localhost:9000 cloudstate.samples.chat.presence.Presence/Monitor`

