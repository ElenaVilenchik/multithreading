package telran.multithreading;

public class ThreadsRaceAppl {

	private static final int NUMBER_OF_THREADS = 5;
	private static final int DISTANCE = 10;

	public static void main(String[] args) throws InterruptedException {
		ApplThread t = null;
		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			t = new ApplThread(DISTANCE);
			t.start();
		}
		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			t.join();
		}
	}
}
