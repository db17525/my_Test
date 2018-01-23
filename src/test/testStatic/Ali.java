package test.testStatic;

public class Ali {
	//顺序执行 ------------>静态修饰无论如何只会被装载一次.
	public static int k=0;					//二、k=0;
	public static Ali t1 = new Ali("t1");  	//三、new Ali("t1")
	public static Ali t2 = new Ali("t2");  	//七、new Ali("t2");
	public static int i = print("i");  	   	//十一、静态变量与静态构造块优先执行,但是得按照先后次序(在代码中的)
	public static int n = 99;  			   	//十二、n=99;
	static   								//十三、静态构造块无论如何也只能被执行一次.与静态变量之间按照先后次序决定。
	{  
		print("静态块");  
	}  
	public static int print(String str)  //九下、(合并九上)	//十二、静态方法不执行
	{  
		/*静态方法如果不被调用不会主动执行.*/
		System.out.println((++k)+":"+str+"   i="+i+"    n="+n);  
		++n;  
		return ++i;  
	} 
	//------------------------------------\\
	//-------------以上为static-------------\\
	//------------------------------------\\
	public int j = print("j");				//四、非静态元素定义与非静态方法块按上下顺序执行	//八、非静态变量执行是new多少次就执行多少次的		//十五、再次执行,这是来自main里面的呼唤
	
	{  //五、非静态构造块优先构造器执行，但与非静态变量按上下先后顺序		//九、非静态构造块优先构造器执行，但与非静态变量按上下先后顺序		//十六、这是来自main里面的呼唤
		print("构造块");  
	} 
	public Ali(String str)  //六、构造器		//十、构造器		//十七、这儿执行完毕之后就不会再执行了.
	{  
		System.out.println( (++k) + ":"+str+"   i="+i+"    n="+n);  
		++i;
		++n;  
	}
	//------------------------------------\\
	//-------------以上非static-------------\\
	//------------------------------------\\
	public static void main(String[] args) {//一、大约步骤--装载static修饰的	
		new Ali("init ... ");				//十四、执行 new Ali("init ... ");
	}
}
