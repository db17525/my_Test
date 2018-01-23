package test.allen;

import net.sf.json.JSONObject;

public class Test05 {
	public static void printForJson(Object o)
	  {
	    JSONObject json = JSONObject.fromObject(o);
	    System.out.println(json);
	  }
	public static void main(String[] args) {
		Integer aa =1;
		printForJson(aa);
	}
}
