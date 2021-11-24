# gRPC compile with Reactor code generation
grpc-compile:
   mvn protobuf:compile

# build project
build: grpc-compile
   mvn -DskipTests clean package

# testing with evans
testing:
   evans

grpc-docs:
   mkdir -p doc
   protoc --doc_out=./doc --doc_opt=html,index.html src/main/proto/*.proto

services:
   grpcurl -plaintext localhost:50052 list

curl:
   grpcurl -plaintext -d '{"id": 1234}' localhost:50052 org.mvnsearch.service.AccountService/findAccount

setup:
   go get -u github.com/pseudomuto/protoc-gen-doc/cmd/protoc-gen-doc
   