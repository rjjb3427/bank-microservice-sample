package roman.torsten.sample.bankservice;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TransactionIdGenerator {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd:mmss");
    private static final AtomicInteger counter = new AtomicInteger(1);
    private static final int NODE_ID = 1;

    public String generate() {
        return LocalDateTime.now().format(formatter) + "-" + NODE_ID + "." + counter.getAndIncrement();
    }
}
