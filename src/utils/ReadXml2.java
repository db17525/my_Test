package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class ReadXml2 {
	public static List getContext(String html) {  
        List resultList = new ArrayList();  
        Pattern p = Pattern.compile(">([^</]+)</");//正则表达式 commend by danielinbiti  
        Matcher m = p.matcher(html );//  
        while (m.find()) {  
            resultList.add(m.group(1));//  
        }  
        return resultList;  
    }  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        String a = "<doc>abc</doc><title>3232</title> <doc>只要内容</doc>";  
        List list = getContext(a);  
        System.out.println(list);  
    }  
}
