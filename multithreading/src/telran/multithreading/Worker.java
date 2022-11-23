package telran.multithreading;

public class Worker extends Thread {
	static Object resourse1 = new Object();
	static Object resourse2 = new Object();
	static Object resourse3 = new Object();
	static int nRuns = 1;

	void f1()  {
		synchronized (resourse1) {
			synchronized (resourse2) {
				synchronized (resourse3) {
					try {
						resourse3.wait(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						interrupted();
					}
				}
			}
		}
	}

	void f2() {
		synchronized (resourse1) {
			synchronized (resourse3) {
				try {
					resourse1.wait(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					interrupted();
	
				}
			}
		}
	}

	void f3() {
		synchronized (resourse2) {
			synchronized (resourse3) {
				try {
					resourse2.wait(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					interrupted();
				}
			}
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < nRuns; i++) {
			f1();
			f2();
			f3();
		}
	}
}
