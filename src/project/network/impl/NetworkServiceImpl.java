package project.network.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import api.implementations.NetworkAPI;
import io.grpc.stub.StreamObserver;
import network.api.ComputationRequest;
import network.api.ComputationResponse;
import network.api.Delimiter;
import project.network.grpc.NetworkProto;
import project.network.grpc.NetworkServiceGrpc;
import shared.stuff.ApiStatus;
import shared.stuff.Resource;
import shared.stuff.ResourceType;

public class NetworkServiceImpl
    extends
      NetworkServiceGrpc.NetworkServiceImplBase {

  private final NetworkAPI networkAPI;

  public NetworkServiceImpl() {
    this.networkAPI = new NetworkAPI();
  }

  @Override
  public void compute(NetworkProto.ComputationRequest protoReq,
      StreamObserver<NetworkProto.ComputationResponse> responseObserver) {

    try {
      // --- Input resource ---
      NetworkProto.Resource protoIn = protoReq.hasInputResource()
          ? protoReq.getInputResource()
          : null;
      Resource inputResource = null;
      if (protoIn != null) {
        ResourceType rtype = fromProtoResourceType(protoIn.getType());
        if (rtype == ResourceType.CUSTOM) {
          List<BigInteger> data = new ArrayList<>();
          for (String s : protoIn.getDataList()) {
            data.add(new BigInteger(s));
          }
          inputResource = new Resource(rtype, data);
        } else {
          inputResource = new Resource(rtype, protoIn.getUri());
        }
      }

      // --- Output resource ---
      NetworkProto.Resource protoOut = protoReq.hasOutputResource()
          ? protoReq.getOutputResource()
          : null;
      Resource outputResource = null;
      if (protoOut != null) {
        ResourceType rtype = fromProtoResourceType(protoOut.getType());
        outputResource = new Resource(rtype, protoOut.getUri());
      }

      // --- Delimiter ---
      Delimiter delim = Delimiter.defaultDelimiter();
      if (protoReq.hasDelimiter() && !protoReq.getDelimiter().isEmpty()) {
        switch (protoReq.getDelimiter().trim()) {
          case "," :
            delim = Delimiter.COMMA;
            break;
          case ";" :
            delim = Delimiter.SEMICOLON;
            break;
          case "|" :
            delim = Delimiter.PIPE;
            break;
          case ":" :
            delim = Delimiter.COLON;
            break;
          default :
            delim = Delimiter.defaultDelimiter();
        }
      }

      // --- Build domain request and compute ---
      ComputationRequest domainReq = new ComputationRequest(inputResource,
          outputResource, delim);
      ComputationResponse domainResp = networkAPI.compute(domainReq);

      // --- Build proto response ---
      NetworkProto.ComputationResponse.Builder respBuilder = NetworkProto.ComputationResponse
          .newBuilder();

      // Status
      ApiStatus status = domainResp.getStatus();
      if (status != null) {
        try {
          respBuilder.setStatus(NetworkProto.ApiStatus.valueOf(status.name()));
        } catch (IllegalArgumentException e) {
          respBuilder.setStatus(NetworkProto.ApiStatus.UNKNOWN);
        }
      } else {
        respBuilder.setStatus(NetworkProto.ApiStatus.UNKNOWN);
      }

      // Results
      List<BigInteger> results = domainResp.getResults();
      if (results != null) {
        List<String> resultStrings = new ArrayList<>();
        for (BigInteger bi : results) {
          resultStrings.add(bi.toString());
        }
        respBuilder.addAllResults(resultStrings);
      }

      // Message
      respBuilder.setMessage(
          domainResp.getMessage() != null ? domainResp.getMessage() : "");

      responseObserver.onNext(respBuilder.build());
      responseObserver.onCompleted();

    } catch (Exception e) {
      e.printStackTrace();
      responseObserver.onError(e);
    }
  }

  private ResourceType fromProtoResourceType(
      NetworkProto.ResourceType protoType) {
    switch (protoType) {
      case FILE :
        return ResourceType.FILE;
      case CUSTOM :
        return ResourceType.CUSTOM;
      case DATABASE :
        return ResourceType.DATABASE;
      case STREAM :
        return ResourceType.STREAM;
      default :
        return ResourceType.FILE;
    }
  }
}
