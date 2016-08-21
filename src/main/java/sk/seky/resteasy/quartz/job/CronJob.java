package sk.seky.resteasy.quartz.job;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lsekerak on 25. 2. 2016.
 * http://jersey.576304.n2.nabble.com/A-sample-FreeMarkerProvider-td2450898.html
 * http://www.citytechinc.com/us/en/blog/2008/12/spring_jax-rs_free.html
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CronJob {
	String cron();
}
