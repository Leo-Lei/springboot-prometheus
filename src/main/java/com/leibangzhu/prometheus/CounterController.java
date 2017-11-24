package com.leibangzhu.prometheus;

import io.prometheus.client.Counter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class CounterController {

    private static Random random = new Random();

    private static final Counter requestTotal = Counter.build()
            .name("my_sample_counter")
            .labelNames("status")
            .help("A simple Counter to illustrate custom Counters in Spring Boot and Prometheus").register();

    @RequestMapping("/counter")
    public String endpoint() {
        if (random.nextInt(2) > 0) {
            requestTotal.labels("success").inc();
            return "success";
        } else {
            requestTotal.labels("error").inc();
            return "error";
        }
    }
}
