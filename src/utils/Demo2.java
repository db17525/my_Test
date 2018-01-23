package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class Demo2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		String data = "Java过滤器实现Gzip压缩实例源码教程。Gzip是若干种文件压缩程序的简称,我们这里来实现通过javaee中的filter实现对响应数据的压缩，高效的传到客户端，那么是怎么通过Gzip压缩实现的将数据压缩后客户端的，对步骤进行分析Java过滤器实现Gzip压缩实例源码教程。Gzip是若干种文件压缩程序的简称,我们这里来实现通过javaee中的filter实现对响应数据的压缩，高效的传到客户端，那么是怎么通过Gzip压缩实现的将数据压缩后客户端的，对步骤进行分析Java过滤器实现Gzip压缩实例源码教程。Gzip是若干种文件压缩程序的简称,我们这里来实现通过javaee中的filter实现对响应数据的压缩，高效的传到客户端，那么是怎么通过Gzip压缩实现的将数据压缩后客户端的，对步骤进行分析Java过滤器实现Gzip压缩实例源码教程。Gzip是若干种文件压缩程序的简称,我们这里来实现通过javaee中的filter实现对响应数据的压缩，高效的传到客户端，那么是怎么通过Gzip压缩实现的将数据压缩后客户端的，对步骤进行分析Java过滤器实现Gzip压缩实例源码教程。Gzip是若干种文件压缩程序的简称,我们这里来实现通过javaee中的filter实现对响应数据的压缩，高效的传到客户端，那么是怎么通过Gzip压缩实现的将数据压缩后客户端的，对步骤进行分析Java过滤器实现Gzip压缩实例源码教程。Gzip是若干种文件压缩程序的简称,我们这里来实现通过javaee中的filter实现对响应数据的压缩，高效的传到客户端，那么是怎么通过Gzip压缩实现的将数据压缩后客户端的，对步骤进行分析";
		System.out.println(StringUtils.center("压缩前" + data.getBytes().length,50,"=="));
		
		//以下代码完成将String类型压缩到byte[]中
		ByteArrayOutputStream bout = new ByteArrayOutputStream(); 
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(data.getBytes());
		gout.flush();
		gout.close();
		
		//取出压缩后的数据
		byte[] buf = bout.toByteArray();
		System.out.println("压缩后" + buf.length);
		
		//将压缩后的数据输出到浏览器
		response.setHeader("content-encoding","gzip");
		response.setHeader("content-length",buf.length+"");
		
		//服务端以字节方式输出
		response.getOutputStream().write(buf);
	}
}