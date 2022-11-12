package telran.multithreading;

public class ThreadsRaceAppl {

	public static void main(String[] args) throws InterruptedException {

		ApplThread t = null;
		for (int i = 0; i < 6; i++) {
			t = new ApplThread(10);
			t.start();
		}
		for (int i = 0; i < 6; i++) {
			t.join();
		}
	}
}
