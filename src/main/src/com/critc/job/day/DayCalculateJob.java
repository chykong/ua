package com.critc.job.day;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.critc.job.service.DayCalculateService;

/**
 * 每日要完成的工作
 *
 * @author 孔垂云
 */
@Component
@Lazy(false)
public class DayCalculateJob {
    private static Logger jobLog = LoggerFactory.getLogger("jobLog");
    @Autowired
    private DayCalculateService dayCalculateService;

    /**
     * 每十秒计算
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void calOrderAutoAccept() {
        jobLog.info("开始自动计算");
    }

}
