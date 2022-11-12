package telran.multithreading;

public class ApplThread extends Thread {
	private static final long RANDOM_NUMBER = (int) (Math.random() * 4 + 2);
	private int nSteps;
	static int a = -1;

	public ApplThread(int nSteps) {
		this.nSteps = nSteps;
	}

	@Override
	public void run() {
		for (int i = 0; i < nSteps; i++) {
			System.out.printf("%s started... \n", Thread.currentThread().getName());
			try {
				sleep(RANDOM_NUMBER);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("%s stopped... \n", Thread.currentThread().getName());
		}
		a++;
		if (a == 0) {
			System.out.printf("Congratulations to %s winner!!! \n", Thread.currentThread().getName());
		} else
			System.out.printf("Congratulations to %s finish!!! \n", Thread.currentThread().getName());
	}
}
