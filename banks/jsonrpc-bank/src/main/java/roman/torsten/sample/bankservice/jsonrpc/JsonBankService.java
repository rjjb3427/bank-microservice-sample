package roman.torsten.sample.bankservice.jsonrpc;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;
import roman.torsten.sample.bankservice.api.TransferStatus;

@JsonRpcService("/jsonBankService")
@AutoJsonRpcServiceImpl
@Service
public class JsonBankService {
    public TransferResult transfer(@JsonRpcParam(value = "request") TransferRequest request) {
        System.out.println("Send response to " + request);
        return new TransferResult(TransferStatus.Success, "json ok!");
    }
}
