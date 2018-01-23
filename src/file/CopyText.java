package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import pinyin.GetPinyin;

public class CopyText{
	public static void main(String[] args) throws Exception {
	    FileReader fr = new FileReader("d:\\组织机构.txt");
	    BufferedReader br = new BufferedReader(fr);
	    StringBuffer buf = new StringBuffer();
	     
	    FileWriter fw = new FileWriter("d:\\to.txt");
	    BufferedWriter bw = new BufferedWriter(fw);
	
	    String line = null;
	    //br.readLine();
	     
	    while((line = br.readLine()) != null){
	        buf.append(GetPinyin.getPingYin(line));
	        buf.append("\r\n");
	    }
	
	    //buf.append("END");
	    bw.write(buf.toString());
	    bw.close();
	    br.close();
}
}