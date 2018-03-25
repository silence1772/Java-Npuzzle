package application;

/**
 * 利用多线程，提供计时方法
 * 
 * @author wuzewei
 * @version 0.1
 */
public class Timer implements Runnable {

	private Thread t;
	/** 暂停标识 */
	private boolean suspended = false;
	/** 计数 */
	private int cnt;
	/** 游戏UI */
	private GameUI gameUI;

	/**
	 * 构造函数， 初始化变量
	 * 
	 * @param gameUI
	 *            游戏UI
	 */
	public Timer(GameUI gameUI) {
		cnt = 0;
		this.gameUI = gameUI;
	}

	/**
	 * 线程run方法，每隔一秒更新计数器实现计时
	 */
	@Override
	public void run() {
		try {
			while (true) {
				if (!suspended) {
					cnt++;
					Thread.sleep(1000);
					gameUI.setTimes(this.getTimes());
				}
				// 此处有问题，必须有输出语句才能在暂停恢复后重新执行
				System.out.print("");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 线程start方法
	 */
	public void start() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	/**
	 * 计时暂停
	 */
	public void suspend() {
		suspended = true;
	}

	/**
	 * 计时恢复
	 */
	synchronized void resume() {
		suspended = false;
		notify();
	}

	/**
	 * 获取计数的时间格式表示
	 * 
	 * @return 格式化的时间字符串
	 */
	public String getTimes() {
		return (String.format("%02d", (this.cnt / 60)) + ":" + String.format("%02d", (this.cnt % 60)));
	}

	/**
	 * 计数归零
	 */
	public void reset() {
		this.cnt = 0;
	}
}
