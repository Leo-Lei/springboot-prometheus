package com.leibangzhu.prometheus;

import io.prometheus.client.Histogram;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HistogramController {

    private static Random random = new Random();

    static final Histogram requestLatency = Histogram.build()
            .name("histogram_requests_latency_seconds").help("Request latency in seconds.").register();

    @RequestMapping("/histogram")
    public String endpoint() throws Exception {
        Integer i = random.nextInt(1000);
        Histogram.Timer requestTimer = requestLatency.startTimer();
        try {
            // Your code here.
            Thread.sleep(i);
        } finally {
            requestTimer.observeDuration();
        }
        return i.toString();
    }
}
