package roman.torsten.sample.bankservice.jsonrpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.TransferStatus;

@AutoJsonRpcServiceImpl
@Service
public class JsonBankService implements JsonRpcBankService {
    @Override
    public TransferResult transfer(TransferRequest request) {
        System.out.println("Send response to " + request);
        return new TransferResult(TransferStatus.Success, "json ok!");
    }
}
