# RESTEasy - Quartz
Thin wrapper atop Quartz. 
Project is example of RESTEasy - Quartz - Guice integration

Inpired by Google App engine (GAE) cron job system.
Main point of  GAE's cron job system is cron.xml file. This file define jobs endpoints and define timer for each job.
With this tool you can create similar cron job system, without GAE and without XML file.
Simply put annotation to your resource method.

Example:
```
@GET
@Path("/checkTemperature")
@Produces(MediaType.APPLICATION_JSON)
@CronJob(cron="0 0/5 10-23 * * ?")
public void checkTemperature() {
    LOGGER.debug("runned");
    thermometer.check();
	...
}
```

## Job runners
ClientAPIJob - Create and send HTTP request to the server
MethodDirectCallJob - Call resource method by Java reflection
 
## Job/Scheduler configuration
Configuration is defined in "quartz.properties".
More info at [Quartz documentation](http://www.quartz-scheduler.org/documentation/quartz-2.x/configuration/).

