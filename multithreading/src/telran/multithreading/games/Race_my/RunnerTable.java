package telran.multithreading.games.Race_my;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

public class RunnerTable extends Thread {
	private Race race;
	private int runnerId;
	static int place;
	static Instant startTime;
	private static final Object mutex = new Object();

	public RunnerTable(Race race, int runnerId) {
		this.race = race;
		this.runnerId = runnerId;
	}

	@Override
	public void run() {
		int sleepRange = race.getMaxSleep() - race.getMinSleep() + 1;
		int minSleep = race.getMinSleep();
		int distance = race.getDistance();
		place = 1;
		startTime = Instant.now();
		IntStream.range(0, distance).forEach(i -> {
			try {
				sleep((long) (minSleep + Math.random() * sleepRange));
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});
		synchronized (mutex) {
			System.out.printf("%-10s%-15s%s%n", place++, runnerId, ChronoUnit.MILLIS.between(startTime, Instant.now()));
		}
	}
}