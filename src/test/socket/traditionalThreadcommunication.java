package test.socket;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class traditionalThreadcommunication {
	public static void main(String[] args) {
		//定义一个线程池，里面存在多个线程
		ExecutorService threadPool = Executors.newCachedThreadPool();
		//把线程放到threadPool里面
	/*	Thread t1=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Timer().schedule(new TimerTask(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("boming!"+Thread.currentThread().getName());
					}
				}, 1000,1000);
			}
			
		});
		threadPool.execute(t1);*/
		//在execute方法里面直接重构runnable方法，比较简单，而且和上面的一样
		threadPool.execute(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//使用传统的方法定义定时器Timer并调用schedule方法实现定时器功能
				new Timer().schedule(new TimerTask(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("boming!"+Thread.currentThread().getName());
					}
				}, 1000,1000);
			}
			
		});
		
		//使用newScheduledThreadPool线程池定义定时器，最大允许线程数为3,使用四个线程，最后一个线程等待完成后调用第一个完成的任务的线程
		ScheduledExecutorService schedulePool=Executors.newScheduledThreadPool(3);
		schedulePool.scheduleAtFixedRate( new Runnable(){

			@Override
			public void run() {
				System.out.println("this is "+Thread.currentThread().getName());
			}
			
		}, 1, 2, TimeUnit.SECONDS);
		schedulePool.scheduleAtFixedRate( new Runnable(){

			@Override
			public void run() {
				System.out.println("this is "+Thread.currentThread().getName());
			}
			
		}, 1, 2, TimeUnit.SECONDS);
		schedulePool.scheduleAtFixedRate( new Runnable(){

			@Override
			public void run() {
				System.out.println("this is "+Thread.currentThread().getName());
			}
			
		}, 1, 2, TimeUnit.SECONDS);
		schedulePool.scheduleAtFixedRate( new Runnable(){

			@Override
			public void run() {
				System.out.println("this is "+Thread.currentThread().getName());
			}
			
		}, 1, 2, TimeUnit.SECONDS);
		schedulePool.scheduleAtFixedRate( new Runnable(){

			@Override
			public void run() {
				System.out.println("this is "+Thread.currentThread().getName());
			}
			
		}, 1, 2, TimeUnit.SECONDS);
		
		
		
		//简单使用一个线程的newScheduleTreadPool
		/*Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable(){
		
			@Override
			public void run() {
				System.out.println("boming2!"+Thread.currentThread().getName());
				
			}
			
		},3, 2, TimeUnit.SECONDS);*/

		
		while(true){
			try {
				new Thread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(new Date().getSeconds());
		}
	}
}

