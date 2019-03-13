package org.mvnsearch;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.mvnsearch.application.ReactiveAccountServiceGrpcImpl;

/**
 * Reactor services server
 *
 * @author linux_china
 */
public class ReactorServicesServer {

    public static void main(String[] args) throws Exception {
        // Start the server
        Server server = ServerBuilder.forPort(50051).addService(new ReactiveAccountServiceGrpcImpl()).build().start();
        server.awaitTermination();
    }
}
