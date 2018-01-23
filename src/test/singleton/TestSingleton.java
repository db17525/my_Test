package test.singleton;


public class TestSingleton {
	
	private volatile static TestSingleton aa;
	private TestSingleton(){
		
	}
	public static TestSingleton getSingleton(){
		if(aa == null){
			synchronized (TestSingleton.class) {
				if(aa == null){
					aa = new TestSingleton();
				}
			}
		}
		return aa;
	}
	
	
	/*private static TestSingleton aa = new TestSingleton();
	private TestSingleton(){
		
	};
	public static TestSingleton getSingleton(){
		return aa;
	}*/
	/*private static TestSingleton aa;
	private TestSingleton(){
		
	};
	public  static synchronized TestSingleton getSingleton(){
		if(null == aa){
			aa = new TestSingleton();
		}
		return aa;
	}*/
	/*private volatile static TestSingleton aa;
	private TestSingleton(){
		
	}
	public static TestSingleton getSingleton(){
		if(null == aa){
			synchronized(TestSingleton.class){
				if(null == aa){
					aa = new TestSingleton();
				}
			}
		}
		return aa;
	}*/
}
