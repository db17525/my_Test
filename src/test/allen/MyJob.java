package test.allen;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
public class MyJob implements Job {
 /**
  * 需要定时调度的方法
  */
 public void execute(JobExecutionContext arg0) throws JobExecutionException {
  
  System.out.println("我是被定时调度的方法啊，当前时间为："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()));
 }
}