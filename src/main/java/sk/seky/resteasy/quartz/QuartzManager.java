package sk.seky.resteasy.quartz;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import sk.seky.resteasy.quartz.job.JobDescription;
import sk.seky.resteasy.quartz.job.JobRegistry;

/**
 * Created by Seky on 21. 3. 2016.
 * Vyriesene dependencie pre Quartz
 */
public final class QuartzManager {

    private final Injector injector;
    private final JobRegistry jobRegistry;

    @Inject
    public QuartzManager(Injector injector, JobRegistry jobRegistry) {
        this.injector = injector;
        this.jobRegistry = jobRegistry;
    }

    public void start() throws SchedulerException {
        // Nastav scheduler
        Scheduler scheduler = new StdSchedulerFactory("quartz.properties").getScheduler();
        scheduler.setJobFactory(new GuiceJobFactory(injector));

        register(scheduler, jobRegistry);
        scheduler.start();
    }

    private void register(Scheduler scheduler, JobRegistry registry) throws SchedulerException {
        for (JobDescription desc : registry.getDescriptions()) {
            register(scheduler, desc);
        }
    }

    private void register(Scheduler scheduler, JobDescription desc) throws SchedulerException {
        ScheduleBuilder schb = CronScheduleBuilder.cronSchedule(desc.getTimer().cron());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(desc.getId(), "RestEasy")
                .withSchedule(schb)
                .build();
        JobDetail detail = JobBuilder.newJob(MethodDirectCallJob.class)
                .withIdentity(desc.getId(), "RestEasy")
                .usingJobData(MethodDirectCallJob.DEFINITION_OF_JOB, desc.getId())
                .build();

        scheduler.getContext().put(desc.getId(), desc);
        scheduler.scheduleJob(detail, trigger);
    }
}
