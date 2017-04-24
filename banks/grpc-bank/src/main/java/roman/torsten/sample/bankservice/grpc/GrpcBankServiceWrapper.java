package roman.torsten.sample.bankservice.grpc;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import roman.torsten.sample.bankservice.api.Money;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;

@Component
public class GrpcBankServiceWrapper extends BankServiceGrpc.BankServiceImplBase {

    @Autowired
    private GrpcBankService bankService;

    @Override
    public void transfer(GrpcTransferRequest grpcRequest, StreamObserver<GrpcTransferResult> responseObserver) {
        try {
            TransferRequest request = new TransferRequest(grpcRequest.getAccountId(), new Money(grpcRequest.getMoney().getAmount(), grpcRequest.getMoney().getCurrency()), TransferRequest.Operation.valueOf(grpcRequest.getOperation().name()));
            TransferResult result = bankService.transfer(request);
            responseObserver.onNext(GrpcTransferResult.newBuilder().setStatus(GrpcTransferResult.Status.valueOf(result.getStatus().name())).setId(result.getId()).build());
            responseObserver.onCompleted();
        } catch(Exception e) {
            responseObserver.onError(e);
            throw e;
        }
    }
}
