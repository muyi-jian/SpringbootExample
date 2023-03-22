



### SpringBoot之quartz

#### 概述

**Job**
Job是一个任务接口，开发者定义自己的任务须实现该接口，并重写execute(JobExecutionContext context)方法.

Job中的任务有可能并发执行，例如任务的执行时间过长，而每次触发的时间间隔太短，则会导致任务会被并发执行。

为了避免出现上面的问题，可以在Job实现类上使用@DisallowConcurrentExecution,保证上一个任务执行完后，再去执行下一个任务

**JobDetail**
JobDetail是任务详情。

包含有：任务名称，任务组名称，任务描述、具体任务Job的实现类、参数配置等等信息

可以说JobDetail是任务的定义，而Job是任务的执行逻辑。

**Trigger**
Trigger是一个触发器,定义Job执行的时间规则。

主要触发器:SimpleTrigger,CronTrigger,CalendarIntervalTrigger,DailyTimeIntervalTrigger。

SimpleTrigger:从某一个时间开始，以一定的时间间隔来执行任务,重复多少次。

CronTrigger: 适合于复杂的任务，使用cron表达式来定义执行规则。

CalendarIntervalTrigger:指定从某一个时间开始，以一定的时间间隔执行的任务,时间间隔比SimpleTrigger丰富

DailyTimeIntervalTrigger:指定每天的某个时间段内，以一定的时间间隔执行任务。并且它可以支持指定星期。

所有的Trigger都包含了StartTime和endTime这两个属性，用来指定Trigger被触发的时间区间。

所有的Trigger都可以设置MisFire策略.

MisFire策略是对于由于系统奔溃或者任务时间过长等原因导致Trigger在应该触发的时间点没有触发.

并且超过了misfireThreshold设置的时间(默认是一分钟，没有超过就立即执行)就算misfire(失火)了。

这个时候就该设置如何应对这种变化了。激活失败指令(Misfire Instructions)是触发器的一个重要属性

**Scheduler**
调度器，主要是用来管理Trigger、JobDetail的。

Scheduler可以通过组名或者名称来对Trigger和JobDetail来进行管理

一个Trigger只能对应一个Job，但是一个Job可以对应多个Trigger.

Scheduler 有两个实现类：RemoteScheduler、StdScheduler。但它是由SchdulerFactory创建的。

SchdulerFactory是个接口，它有两个实现类：StdSchedulerFactory、DirectSchedulerFactory

Quartz提供了相应的Builder方便我们进行构造。

**JobBuilder**
这个主要方便我们构建任务详情，常用方法：

withIdentity(String name, String group):配置Job名称与组名

withDescription(String jobDescription): 任务描述

requestRecovery(): 出现故障是否重新执行，默认false

storeDurably(): 作业完成后是否保留存储，默认false

usingJobData(String dataKey, String value): 配置单个参数key

usingJobData(JobDataMap newJobDataMap): 配置多个参数，放入一个map

setJobData(JobDataMap newJobDataMap): 和上面类似，但是这个参数直接指向newJobDataMap,直接设置的参数无效

**TriggerBuilder**
这个主要方便我们构建触发器，常用方法：

withIdentity(String name, String group)： 配置Trigger名称与组名

withIdentity(TriggerKey triggerKey)： 配置Trigger名称与组名

withDescription(String triggerDescription)： 描述

withPriority(int triggerPriority)： 设置优先级，默认是：5

startAt(Date triggerStartTime)： 设置开始时间

startNow()： 触发器立即生效

endAt(Date triggerEndTime)： 设置结束时间

withSchedule(ScheduleBuilder<SBT> schedBuilder)： 设置调度builder,下面的builder就是

**SimpleScheduleBuilder**
几种触发器类型之一，最简单常用的。常用方法：

repeatForever()：指定触发器将无限期重复

withRepeatCount(int triggerRepeatCount)：指定重复次数，总触发的次数=triggerRepeatCount+1

repeatSecondlyForever(int seconds)：每隔seconds秒无限期重复

repeatMinutelyForever(int minutes)：每隔minutes分钟无限期重复

repeatHourlyForever(int hours)：每隔hours小时无限期重复

repeatSecondlyForever()：每隔1秒无限期重复

repeatMinutelyForever()：每隔1分钟无限期重复

repeatHourlyForever()：每隔1小时无限期重复

withIntervalInSeconds(int intervalInSeconds)：每隔intervalInSeconds秒执行

withIntervalInMinutes(int intervalInMinutes)：每隔intervalInMinutes分钟执行

withIntervalInHours(int intervalInHours)：每隔intervalInHours小时执行

withMisfireHandlingInstructionFireNow()：失火后的策略为：MISFIRE_INSTRUCTION_FIRE_NOW

**CronScheduleBuilder**
算是非常常用的了，crontab 表达式，常用方法：

cronSchedule(String cronExpression)：使用cron表达式

简单的一笔

**CalendarIntervalScheduleBuilder**
常用方法：

inTimeZone(TimeZone timezone)：设置时区

withInterval(int timeInterval, IntervalUnit unit)：相隔多少时间执行，单位有：毫秒、秒、分、时、天、周、月、年

withIntervalInSeconds(int intervalInSeconds)：相隔秒

withIntervalInWeeks(int intervalInWeeks)：相隔周

withIntervalInMonths(int intervalInMonths)：相隔月

等等方法

**DailyTimeIntervalScheduleBuilder**
withInterval(int timeInterval, IntervalUnit unit)：相隔多少时间执行，单位有：秒、分、时，其他单位的不支持会报错

withIntervalInSeconds(int intervalInSeconds)：相隔秒

withIntervalInMinutes(int intervalInMinutes)：相隔分

withIntervalInHours(int intervalInHours)：相隔时

onDaysOfTheWeek(Set<Integer> onDaysOfWeek)：将触发器设置为在一周的指定日期触发。取值范围可以是1-7，1是星期天，2是星期一…

onDaysOfTheWeek(Integer ... onDaysOfWeek)：和上面一样，3是星期二…7是星期六

onMondayThroughFriday()：每星期的周一导周五触发

onSaturdayAndSunday()：每星期的周六周日触发

onEveryDay()：每天触发

withRepeatCount(int repeatCount)：重复次数，总的重复次数=1 (at start time) + repeatCount

startingDailyAt(TimeOfDay timeOfDay)：触发的开始时间

endingDailyAt(TimeOfDay timeOfDay)：触发的结束时间

#### 简单实现

pom

```xml
 <dependency>
 	<groupId>org.springframework.boot</groupId>
	 <artifactId>spring-boot-starter-quartz</artifactId>
 </dependency>
```

代码：

编写 Job 任务类

```java
@Slf4j
public class SimpleTsak extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("这是一个简单的Quartz计划任务");
    }
}


```

编写配置类

```java

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
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ? *"))
                .build();
    }
}
```

启动之后可以看到输出

![image-20230224194047110](F:\学习笔记\Notes\content\Java\Spring系列\SpringbootExample\image\image-20230224194047110.png)

#### springboot 整合quartz