package roman.torsten.sample.bankservice.gsonrpc;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import roman.torsten.sample.bankservice.api.TransferRequest;
import roman.torsten.sample.bankservice.api.TransferResult;

/**
 * Author: Roman Torsten
 * Date: 24.04.2017
 * Time: 16:02
 */
public interface JsonBankService {
    TransferResult transfer(@JsonRpcParam("request") TransferRequest request);
}
