package rs.raf.demo.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class MyCronService {


    public CronTrigger getCronExpression(String time) {
        String[] ss = time.split(":");
        int year = Integer.parseInt(ss[0]);
        int month = Integer.parseInt(ss[1]);
        int day = Integer.parseInt(ss[2]);
        int hour = Integer.parseInt(ss[3]);
        int minute = Integer.parseInt(ss[4]);
        LocalDateTime localDateTime = LocalDateTime.of(year,month,day,hour,minute);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Date date = Date.from(zonedDateTime.toInstant());
        String cronExpression = String.format("0 %d %d %d %d ?", localDateTime.getMinute(), localDateTime.getHour(), localDateTime.getDayOfMonth(), localDateTime.getMonthValue());
        CronTrigger cronTrigger = new CronTrigger(cronExpression);
        return cronTrigger;
    }


//    public String getCronExpression(String time) {
//        System.out.println(time);
//        if(time.equals("time")) {
//            return null;
//        }
//        String[] ss = time.split(":");
//        int year = Integer.parseInt(ss[0]);
//        int month = Integer.parseInt(ss[1]);
//        int day = Integer.parseInt(ss[2]);
//        int hour = Integer.parseInt(ss[3]);
//        int minute = Integer.parseInt(ss[4]);
//        LocalDateTime localDateTime = LocalDateTime.of(year,month,day,hour,minute);
//        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
//        Date date = Date.from(zonedDateTime.toInstant());
//        String cronExpression = String.format("0 %d %d %d %d ?", localDateTime.getMinute(), localDateTime.getHour(), localDateTime.getDayOfMonth(), localDateTime.getMonthValue());
//        return cronExpression;
//    }


}


