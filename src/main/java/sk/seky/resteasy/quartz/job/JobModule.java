package sk.seky.resteasy.quartz.job;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by lsekerak on 26. 3. 2016.
 */
public class JobModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JobRegistry.class).in(Singleton.class);
    }
}
