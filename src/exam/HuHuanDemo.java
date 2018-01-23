package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HuHuanDemo {

	public static void main(String[] args) {
		int i = 1;
		//List<String> sTrs = new ArrayList<String>();
		String[] sTrs = new String[4];
		while (i<5) {
			System.out.print("请输入第"+i+"个整数:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Pattern pt = Pattern.compile("\\d+");
			//System.out.println(mt.matches());
			//System.out.println(mt.find());
			String a;
			try {
				a = br.readLine();
				Matcher mt = pt.matcher(a);
				if(!mt.matches()){
					System.out.println("输入格式错误，请重新输入：");
				}else{
					System.out.println(" 你输入的字符为："+ a);
					sTrs[i-1]=a;
					i++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("你输入的数组是：");
		for (int j = 0; j < sTrs.length; j++) {
			System.out.print(sTrs[j]);
		}
		System.out.println("\n排序后的数组是：");
		sTrs = DaoXu(sTrs);
		for (int j = 0; j < sTrs.length; j++) {
			System.out.print(sTrs[j]);
		}
	}
	
	public static String[] DaoXu(String[] strs){
		Collections.reverse(Arrays.asList(strs));
		return strs;
	}
}
