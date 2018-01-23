package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.util.WebUtils;

public class downLoadTest01 {

	public void download(HttpServletRequest request, HttpServletResponse response,String fileUrl,String fileName) {
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			// 客户使用保存文件的对话框：
			//fileName = WebUtils.encodeFileName(request, fileName).replaceAll("\\+", "%20");
			fileUrl = new String(fileUrl.getBytes("utf-8"), "utf-8");
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 通知客户文件的MIME类型：
			bis = new java.io.BufferedInputStream(new java.io.FileInputStream(
					(fileUrl)));
			bos = new java.io.BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			int i = 0;

			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
				i++;
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bis = null;
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bos = null;
			}
		}

	}

}
