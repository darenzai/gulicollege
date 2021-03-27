package com.atguigu.staservice.schedule;


import com.atguigu.staservice.service.StatisticsDailyService;
import com.atguigu.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //每隔五米执行一次z


    @Scheduled(cron = "0 0 1 * * ?")
    public  void task2(){

        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        statisticsDailyService.registerCount(day);
    }

}
