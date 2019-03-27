package org.mvnsearch;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * grpc auto configuration
 *
 * @author linux_china
 */
@Configuration
public class GrpcAutoConfiguration {
    @Bean
    @GRpcGlobalInterceptor
    public ServerInterceptor globalInterceptor() {
        return new ServerInterceptor() {
            @Override
            public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
                System.out.println("intercepting");
                //gRPC over HTTP2 https://github.com/grpc/grpc/blob/master/doc/PROTOCOL-HTTP2.md
                // ServerCall includes all information from client
                return next.startCall(call, headers);
            }
        };
    }
}
