package com.jpabooks.service;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class PriceSchdule {
    Logger log = LoggerFactory.getLogger(PriceSchdule.class);
     @Scheduled(initialDelay = 2000,fixedRate=2000)
    //@Scheduled(cron = "${interval-in-cron }")
    //@Scheduled(cron = "@daily")
     @SchedulerLock(name = "bookcomputePrice")
    @Async
    public void computePrice() throws InterruptedException {
        Thread.sleep(4000);
        log.info("compute price " + LocalDateTime.now());
    }
}
