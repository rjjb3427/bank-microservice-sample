package roman.torsten.sample.bankservice.jsonrpc;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;

@JsonRpcService("/jsonBankService")
public interface JsonRpcBankService {
    TransferResult transfer(@JsonRpcParam(value = "request") TransferRequest request);
}
