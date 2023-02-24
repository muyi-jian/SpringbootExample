package spring.boot.quartz.config;


import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.boot.quartz.job.SimpleTsak;

@Configuration
public class TaskConfig {

   @Bean
    public JobDetail simpleJobDetail111(){
       //SimpleTsak.class 具体任务类
       return JobBuilder.newJob(SimpleTsak.class)
               //给 JobDetail 起一个 id, 不写也会自动生成唯一的 TriggerKey
               .withIdentity("simpleTask")
               .usingJobData("job simple","simple job")
               //即使没有Trigger关联时也不删除该Jobdetail
               .storeDurably()
               .build();
    }

    @Bean
    public CronTrigger simpleTrigger111(){
        return TriggerBuilder.newTrigger()
                .forJob(simpleJobDetail111())
                .withIdentity("simpleTrigger")
                .usingJobData("simple Trigger","trigger simple")
                //cron 表达式设置每隔 5 秒执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ? *"))
                .build();
    }
}
