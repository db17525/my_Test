package test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiUser implements Runnable {
	private Socket client;

	public MultiUser(Socket c) {
		this.client = c;
	}
	
	@Override
	public void run() {
		try{
			InputStream is = client.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			OutputStream os = client.getOutputStream();
			PrintWriter out = new PrintWriter(os);
			
			while (true) {
				//String str = br.readLine();
				
				byte[] buf=new byte[1024];  
                int len=is.read(buf);  
                String str=new String(buf, 0, len); 
                
				System.out.println(str+"***server*syso*****");
				out.println("receive...."+str);
				out.flush();
				if(str.equals("end")){
					out.close();
					os.close();
					br.close();
					isr.close();
					is.close();
					client.close();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}/*finally{
			if(client!=null){
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
	}
	
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(5678);
		while (true) {
			MultiUser mu = new MultiUser(server.accept());
			Thread th = new Thread(mu);
			th.start();
		}
		//boolean isConnection=socket.isConnected() && !socket.isClosed();   //判断当前是否处于连接
	}
}
