package sk.seky.resteasy.quartz.job;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lsekerak on 26. 3. 2016.
 */
public final class JobRegistry {
    private Set<JobDescription> descriptions;

    public JobRegistry() {
        this.descriptions = new HashSet<>();
    }

    public void register(JobDescription desc) {
        descriptions.add(desc);
    }

    public void register(Class klazz) {
        for (Method method : klazz.getMethods()) {
            CronJob cronJob = method.getAnnotation(CronJob.class); // TODO: pozor na dedenie
            if (cronJob != null) {
                JobDescription desc = new JobDescription(klazz, method, cronJob);
                descriptions.add(desc);
            }
        }
    }

    public Set<JobDescription> getDescriptions() {
        return descriptions;
    }
}
