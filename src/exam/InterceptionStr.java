package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;

public class InterceptionStr {
	
	static String ss;
	static int n;

	public static void main(String[] args) throws IOException {
		System.out.println("请输入字符串：");
		Scanner scStr = new Scanner(System.in);
		ss = scStr.next();
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//ss = br.readLine();
		System.out.println(ss+"---请输入字节数：");
		Scanner scNum = new Scanner(System.in);
		n = scNum.nextInt();
		Intercept(ss, n);
	}
	//"[\u4e00-\u9fa5]"
	public static void Intercept(String scStr, int scNum){
		char[] strs = scStr.toCharArray();
		String m = "[\u4e00-\u9fa5]";
		String result = "";
		int j = 0;
		/*for (char c : strs) {
			if(String.valueOf(c).matches(m)){
				j+=2;
				if(j<=scNum){
					result += String.valueOf(c);
				}else{
					System.out.println(result);
					j = 2;
					result = String.valueOf(c);
				}
			}else{
				j+=1;
				if(j<=scNum){
					result += String.valueOf(c);
				}else{
					System.out.println(result);
					j = 1;
					result = String.valueOf(c);
				}
			}
		}
		System.out.println(result);*/
		
		for (char c : strs) {
			if(String.valueOf(c).matches(m)){
				j+=2;
			}else{
				j+=1;
			}
			if(j<scNum){
				System.out.print(c);
			}
			if(j==scNum){
				System.out.print(c);
				System.out.println();
			}
			if(j>scNum){
				j=0;
				System.out.println();
			}
		}
	}
}
