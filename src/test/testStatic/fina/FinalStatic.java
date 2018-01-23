package test.testStatic.fina;

import java.util.Random;

public class FinalStatic {
	/*只打印出了8，证明类并没有初始化。也就是说编译器很智能的、在编译的时候自己就能算出4+4是8，是一个固定的数字。
	      没有什么未知的因素在里面*/
	//public static final int A = 4 + 4 ;
	
	/*在静态final变量在编译时不定的情况下。如果客户程序这个时候访问了该类的静态变量，那就会对类进行初始化，
	      所以尽量静态final变量尽量没什么可变因素在里面1，否则性能会有所下降。*/
	public static final int A = 4 + new Random().nextInt(10) ;     
    
    static {     
        System.out.println("如果执行了，证明类初始化了……");     
    } 

}
