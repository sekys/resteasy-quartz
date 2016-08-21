package sk.seky.resteasy.quartz;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.quartz.*;
import sk.seky.resteasy.quartz.job.JobDescription;

/**
 * Created by Seky on 21. 3. 2016.
 * QUartz Job , ktory prevola Rest metodu.
 */
public final class MethodDirectCallJob implements Job {
    public static final String DEFINITION_OF_JOB = "DEFINITION_OF_JOB";

    @Inject
    private Injector injector;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String method = dataMap.getString(DEFINITION_OF_JOB);
            SchedulerContext cx = context.getScheduler().getContext();
            JobDescription description = (JobDescription) cx.get(method);
            Object instance = injector.getInstance(description.getKlass());
            description.getMethod().invoke(instance, new Object[]{});
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}

