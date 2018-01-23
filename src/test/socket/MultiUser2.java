package test.socket;

import java.net.*;  
import java.io.*;  
public class MultiUser2 extends Thread{  
	private Socket client;  
	public MultiUser2(Socket c){  
	  this.client=c;  
	}  
	public void run(){  
	  try{    
	   BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));  
	   PrintWriter out=new PrintWriter(client.getOutputStream());  
	    //Mutil User but can't parallel  
	    while(true){  
	     String str=in.readLine();  
	     System.out.println(str);  
	     out.println("has receive....");  
	     out.flush();  
	     if(str.equals("end"))  
	      break;  
	    }  
	   client.close();    
	   }catch(IOException ex){  
	   }finally{  
	      
	   }  
	} 
}
