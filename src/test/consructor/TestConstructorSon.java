package test.consructor;


/*顺序是这样得，首先生成B就得先生成A，所以调用A的构造器，输出AAA,然后调用方法dosomething,注意：A的该方法被B覆盖，而你生成的是B的对象，
所以它调用B的方法，由于BVAR目前没有给定值，所以自动初始化为0；然后生成B对象，先初始化变量BVAR，然后调用构造器输出BBB，然后调用方法，
这时BVAR已初始化，所以输出BVAR＝2222，而对象A中变量AVAR由于没有调用对象A的方法dosomething，所以其值为0，则输出0
注意：初始化顺序，当继承时，先生成超类对象，生成对象时，先生成静态变量，然后是一般变量，然后调用构造器！当所有超类对象生成后，
生成本对象，顺序一样！ 当方法被覆盖时，调用目前对象的方法！这得注意。*/
public class TestConstructorSon extends  TestConstructorFather{
	public int Bvar = 2222;
	public TestConstructorSon() {
		System.out.println("BBB");
		doSomething();
		System.out.println("Avar=" + Avar);
	}
	public void doSomething() {
		System.out.println("Bvar=" + Bvar);
	}
	public static void main(String[] args) {
		new TestConstructorSon();
	}
}
