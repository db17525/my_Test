package test.singleton;



//单例模式  饿汉式 ------------主要用这个
/**
 * 这种方式基于classloder机制避免了多线程的同步问题，不过，instance在类装载时就实例化，虽然导致类装载的原因有很多种，
 * 在单例模式中大多数都是调用getInstance方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候
 * 初始化instance显然没有达到lazy loading的效果。
 */

/**
 * 这个静态实例是方法内创建的，就是说你在调用方法的时候，他先看这个实例有没有被创建，没有就先创建再返回，有就直接返回。
 * 也就是说，对于调用者来说，调用方法永远会返回这一个实例。static属性是为了全局唯一
 */

/**
 * 首先你要明白static 是在什么时候初始化的，其设计意图是是什么，单例 就是我们运行的当前虚拟机中有且只有一个需要的对象，不存在重复。
 * static 是给类静态成员变量使用的，属于类的属性，一般是一些常量之类的东西，从加载上来说对于类和对象之间，在类加载到内存时候静态
 * 成员变量就存在了，而对象还不存在，另外 静态方法只能调用静态方法和静态变量这个你也应该知道，如果全部搞成静态方法那么意味着其他
 * 成员变量都要是静态的，很不方便，如果一天不要单例了也不容易扩展，很麻烦。
 */
public class SingletonHungry {
	private static SingletonHungry instance = new SingletonHungry();

	private SingletonHungry() {
	}

	public static SingletonHungry getInstance() {
		return instance;
	}
}
