package test.singleton;

//一般单例都是五种写法。懒汉，恶汉，双重校验锁，枚举和静态内部类。
//单例模式	双重校验锁
public class Singleton {
	private volatile static Singleton singleton;

	private Singleton() {
	}

	public static Singleton getSingleton() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
