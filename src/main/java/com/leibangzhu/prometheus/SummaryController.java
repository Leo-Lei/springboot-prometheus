package com.leibangzhu.prometheus;

import io.prometheus.client.Summary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SummaryController {

    private static Random random = new Random();

    private static final Summary receivedBytes = Summary.build()
            .name("summary_requests_size_bytes").help("Request size in bytes.").register();
    private static final Summary requestLatency = Summary.build()
            .name("summary_requests_latency_seconds").help("Request latency in seconds.").register();

    @RequestMapping("/summary")
    public String endpoint() throws Exception {
        Integer i = random.nextInt(1000);
        Summary.Timer requestTimer = requestLatency.startTimer();
        try {
            // Your code here.
            Thread.sleep(i);
        } finally {
            receivedBytes.observe(i);
            requestTimer.observeDuration();
        }
        return i.toString();
    }
}
