package test.dataimport;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
    static int count = 0;
    static Timer timer = new Timer();
    
    public static void main(String[] args) {
    	startTimer();
    	//stopTimer();
    }
    
    //启动定时器
    public static void startTimer() {
    	TimerTask task = new TimerTask() {
            public void run() {
                ++count;
                System.out.println("时间=" + new Date() + " 执行了" + count + "次");
                Insert_Data.zx();
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的10:09:00执行，
        calendar.set(year, month, day, 10, 9, 00);
        Date date = calendar.getTime();
        System.out.println(date);
        
        int period = 60 * 1000;
        //每天的date时刻执行task，每隔2分钟重复执行
        timer.schedule(task, date, period);
        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
    }
    
    //停止定时器
    public static void stopTimer() {
    	System.out.println("定时器停止了");
        timer.cancel();
    }
}