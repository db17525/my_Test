package test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(5678);
		while(true){
			
			Socket client = server.accept();
			InputStream is = client.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			
			OutputStream os = client.getOutputStream();
			PrintWriter out = new PrintWriter(os);
			while (true) {
				String str = in.readLine();
				System.out.println("服务端打印。。"+str);
				//System.out.println(str);
				out.println("已接收到。。。"+str);
				out.flush();
				if(str.equals("end")){
					client.close();
					break;
				}
			}
			
		}
	}
}
