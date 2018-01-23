package test.socket;

import java.io.*;  
import java.net.*;  
public class MyServer2 {  
	public static void main(String[] args)throws IOException{  
		  ServerSocket server=new ServerSocket(5678);  
		  while(true){  
		   //transfer location change Single User or Multi User  
		   MultiUser2 mu=new MultiUser2(server.accept());  
		   mu.start();  
		  }  
		}  
	}
