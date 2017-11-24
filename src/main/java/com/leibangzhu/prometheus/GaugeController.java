package com.leibangzhu.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class GaugeController {

    private static Random random = new Random();

    private static final Gauge inprogressRequests = Gauge.build()
            .name("inprogress_requests")
            .help("Inprogress requests.").register();

    @RequestMapping("/gauge")
    public String endpoint() {
        Integer i = random.nextInt(10);
        inprogressRequests.set(i);
        return i.toString();
    }

}
