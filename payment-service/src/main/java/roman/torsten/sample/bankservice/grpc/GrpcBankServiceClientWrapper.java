package roman.torsten.sample.bankservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.TransferStatus;

import java.util.concurrent.TimeUnit;

public class GrpcBankServiceClientWrapper {
    private final ManagedChannel channel;
    private final BankServiceGrpc.BankServiceBlockingStub blockingStub;

    public GrpcBankServiceClientWrapper(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true));
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    GrpcBankServiceClientWrapper(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public TransferResult transfer(TransferRequest origin) {
        GrpcTransferRequest request = GrpcTransferRequest.newBuilder()
                .setAccountId(origin.getAccountId())
                .setMoney(GrpcTransferRequest.Money.getDefaultInstance().newBuilderForType().setAmount(origin.getMoney().getAmount()).setCurrency(origin.getMoney().getCurrency()).build())
                .setOperation(GrpcTransferRequest.Operation.valueOf(origin.getOperation().name()))
                .build();
        GrpcTransferResult response;
        try {
            response = blockingStub.transfer(request);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
            throw new RuntimeException(e);
        }
        return new TransferResult(TransferStatus.valueOf(response.getStatus().name()), response.getId());
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        GrpcBankServiceClientWrapper client = new GrpcBankServiceClientWrapper("localhost", 50051);
    }
}
