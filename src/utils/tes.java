package utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.alibaba.druid.sql.visitor.functions.Substring;

public class tes{
	//http://www.haoservice.com/docs/3
	public static void main(String[] args) throws IOException {
		/*String sURL = "http://api.haoservice.com/api/viplbs?requestdata={'celltowers':["
				+ "{'cell_id':'37934','lac':'4526','mcc':'460','mnc':'0','signalstrength':'-60'},"
				+ "{'cell_id':'47637','lac':'4526','mcc':'460','mnc':'0','signalstrength':'-60'},"
				+ "{'cell_id':'56935','lac':'4167','mcc':'460','mnc':'0','signalstrength':'-60'}],'mnctype':'gsm'}&type=2&key=4613693da88f417fabcab980aa18898b";
		URL l_url = new URL(sURL); 
		HttpURLConnection l_connection = (HttpURLConnection)l_url.openConnection(); 
		l_connection.connect(); 
		InputStream l_urlStream = l_connection.getInputStream(); 

		BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream,"GBK")); 
		String sCurrentLine = ""; 
		String sTotalString = ""; 
		while((sCurrentLine = l_reader.readLine()) != null){ 
			sTotalString+=sCurrentLine; 
		}
		System.out.println(sTotalString);*/
		//System.out.println(URLDecoder.decode(a,"utf-8"));
		System.out.println(URLDecoder.decode("%B9%F0%BA%CF%D3%E600688","gbk"));
		System.out.println(URLEncoder.encode("桂防渔00822", "gbk"));
		System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		/*System.out.println(URLEncoder.encode("海南岛东南部渔场", "gbk"));
		String aa = "海南岛东南部渔场-2017-04-21.xls";
		System.out.println(aa.substring(aa.indexOf(".xls")-10,aa.indexOf(".xls")));
		System.out.println("109° 46′ 53”".substring(0, "109° 46′ 53”".indexOf("′")+1)+"E");*/
		/*String[] ids = "abc,de".split(",");
		System.out.println(ids. );*/
		List<String> st = new ArrayList<String>();
		st.add("123");
		st.add("45");
		long s = 45;
		System.out.println(st.contains(s+""));
		System.out.println(st.size());
		clearLis(st);
		System.out.println(st.size());
		
		System.out.println(Utils.GetSecFrom1970("2017-10-15 00:00:00"));
		System.out.println(Utils.GetSecFrom1970("2017-11-14 00:00:00"));
		System.out.println("13371685890a".substring(0,11));
		System.out.println(91/10);
	}
	
	public static void clearLis(List<String> li){
		li.clear();
	}
}