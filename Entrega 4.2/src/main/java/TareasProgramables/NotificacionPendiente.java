package TareasProgramables;

import Repositorios.RepoEntidades;
import Repositorios.RepoEstablecimientos;
import ServiciosExternos.Notifcaciones.NotificacionJavaMail;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class NotificacionPendiente {

  public static void main(String[] args) throws SchedulerException {
    NotificacionPendiente proceso = new NotificacionPendiente();
    proceso.comenzar();

  }

  public void comenzar() throws SchedulerException{
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

    scheduler.start();

    JobDetail job = newJob(NotificacionDeIncidente.class)
        .withIdentity("notificacion-incidente")
        .build();
    SimpleTrigger trigger =  newTrigger().withIdentity("trigger-notificacion")
        .startNow()
        .withSchedule(simpleSchedule().withIntervalInMinutes(15).repeatForever())
        .build();

    scheduler.scheduleJob(job, trigger);
  }

  public static class NotificacionDeIncidente implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      RepoEstablecimientos.instance().todos().forEach(establecimiento -> establecimiento.notificarInteresados());
      RepoEntidades.instance().todos().forEach(entidad -> entidad.notificarInteresados());
    }
  }
}
