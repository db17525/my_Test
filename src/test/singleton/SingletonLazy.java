package test.singleton;

//单例模式  懒汉式（加上线程安全）
/**
 * 这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading， 但是，遗憾的是，效率很低，99%情况下不需要同步。
 */
public class SingletonLazy {
	private static SingletonLazy instance;

	private SingletonLazy() {
	}

	public static synchronized SingletonLazy getInstance() {
		if (instance == null) {
			instance = new SingletonLazy();
		}
		return instance;
	}
}
