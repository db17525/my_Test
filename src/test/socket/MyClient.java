package test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
	static Socket server;
	public static void main(String[] args) throws UnknownHostException, IOException {
		server = new Socket(InetAddress.getLocalHost(), 5678);
		InputStream is = server.getInputStream();//从服务程序获得输入流读传送来的信息
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader in = new BufferedReader(isr);
		
		OutputStream os = server.getOutputStream();//获得输出流来发送消息
		PrintWriter out = new PrintWriter(os);
		
		InputStreamReader iss = new InputStreamReader(System.in);
		BufferedReader wt = new BufferedReader(iss);
		
		while (true) {
			String str = wt.readLine();
			out.println(str+"****println*****");
			out.flush();
			if(str.equals("end")){
				out.close();
				os.close();
				wt.close();
				iss.close();
				in.close();
				isr.close();
				is.close();
				server.close();
				break;
			}
			System.out.println(in.readLine()+"****syso***");
		}
	}
}
