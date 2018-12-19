package com.example.springboot_traing.global;

import com.example.springboot_traing.service.PrettyService;
import com.example.springboot_traing.service.RedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 22:18
 */
@Component
public class Crontab {

    @Autowired
    PrettyService prettyService;

    @Autowired
    RedisService redisService;

    private static final Logger logger = LogManager.getLogger();


    /*
     * 每周一中午12点执行
     */
    @Scheduled(cron = "0 0 12 ? * MON")
    public void crawlPretty() {
        logger.info("start crawl pretty ...");
        List<String> urls = prettyService.crawlPretty(1);
        urls.forEach(url -> logger.info("pretty url: {}", url));
        logger.info("crawl pretty end ...");
    }

    /*
     * 每天凌晨0点放一张图片到redis中
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void pushPrettyToRedis() {
        logger.info("push pretty image to redis start ...");
        prettyService.findFirstByValidOrderByIdAsc(1).ifPresent(pretty -> {
            redisService.set(redisService.PRETTY, pretty.getImage());
            pretty.setValid((byte) 0);
            prettyService.save(pretty);
            logger.info("push pretty image to redis: {}", pretty.getImage());
        });
    }


}
