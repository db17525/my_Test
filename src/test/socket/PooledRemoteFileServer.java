package test.socket;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
 * PooledRemoteFileServer是一个多线程、池化的Socket服务器。它能够在指定的端口
 * 监听来自客户端的连接请求，同时它限定了允许同时连接的数目。
 * 
 * 在服务器端，服务器启动时创建指定定数量的后台处理类实例，这些实例实际上是实现了
 * Runnable接口的类，它们在创建完成后将立刻唤起，不停地监控来自客户端的连接。
 * 
 * 另一方面，服务器在创建线程之后，将在指定的端口监听。一旦有客户端连接上，立刻将
 * 该连接交给后台在等待的线程去处理，接着立刻返回继续在端口监听。这样的话后台线程
 * 的处理将不会造成前端服务器监听的阻塞。
 * </pre>
 */
public class PooledRemoteFileServer {

	/** The max connections. */
	protected int maxConnections;

	/** The listen port. */
	protected int listenPort;

	/** The server socket. */
	protected ServerSocket serverSocket;

	/**
	 * Instantiates a new pooled remote file server.
	 * 
	 * @param aListenPort
	 *            the a listen port
	 * @param maxConnections
	 *            the max connections
	 */
	public PooledRemoteFileServer(int aListenPort, int maxConnections) {
		listenPort = aListenPort;// 监听端口
		this.maxConnections = maxConnections;// 最大同时连接
	}

	/**
	 * 初始化池：每次创建一个Runnable实例，然后创建线程对象
	 */
	public void setUpHandlers() {
		for (int i = 0; i < maxConnections; i++) {
			PooledConnectionHandler currentHandler = new PooledConnectionHandler();
			// 线程启动后将一直监控Socket队列，以轮询的方式
			// 监控是否有新的客户端请求到来，如果有的话则取
			// 出处理，无的话则继续等待直至请求到来
			new Thread(currentHandler, "Handler" + i).start();
		}
	}

	/**
	 * 接收客户端连接
	 */
	public void acceptConnections() {
		try {
			ServerSocket server = new ServerSocket(listenPort, 5);
			Socket incomingConnection = null;
			while (true) {
				incomingConnection = server.accept();
				handleConnection(incomingConnection);
			}
		} catch (BindException be) {
			System.out.println("");
		} catch (IOException ioe) {
			System.out.println("" + listenPort);
		}
	}

	/**
	 * 在池中处理Socket请求
	 * 
	 * @param connectionToHandle
	 *            the connection to handle
	 */
	protected void handleConnection(Socket connectionToHandle) {
		PooledConnectionHandler.processRequest(connectionToHandle);
	}

	public static void main(String args[]) {
		PooledRemoteFileServer server = new PooledRemoteFileServer(1001, 3);
		// 初始化线程池
		server.setUpHandlers();
		// 开始在指定端口等待到来的请求
		server.acceptConnections();
	}
}