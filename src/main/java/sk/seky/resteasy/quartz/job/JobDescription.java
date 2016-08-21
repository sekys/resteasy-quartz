package sk.seky.resteasy.quartz.job;

import java.lang.reflect.Method;

/**
 * Created by lsekerak on 26. 3. 2016.
 */

public class JobDescription {
    private final Class klass;
    private final Method method;
    private final CronJob timer;

    public JobDescription(Class klass, Method method, CronJob timer) {
        this.klass = klass;
        this.method = method;
        this.timer = timer;
    }

    public Class getKlass() {
        return klass;
    }

    public Method getMethod() {
        return method;
    }

    public CronJob getTimer() {
        return timer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        JobDescription that = (JobDescription) o;

        if (klass != null ? !klass.equals(that.klass) : that.klass != null)
            return false;
        return !(method != null ? !method.equals(that.method) : that.method != null);

    }

    @Override
    public int hashCode() {
        int result = klass != null ? klass.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }

    public String getId() {
        return klass.getName() + "." + method.getName();
    }
}
