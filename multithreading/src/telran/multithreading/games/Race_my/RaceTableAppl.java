package telran.multithreading.games.Race_my;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;
import java.util.Locale;
import java.util.stream.IntStream;

import telran.view.*;

public class RaceTableAppl {

	private static final int MAX_THREADS = 20;
	private static final int MIN_DISTANCE = 10;
	private static final int MAX_DISTANCE = 1000;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);
	}

	private static Item[] getItems() {
		Item[] res = { Item.of("Start new game", RaceTableAppl::startGame), Item.exit() };
		return res;
	}

	static void startGame(InputOutput io) {
		int nThreads = io.readInt("Enter number of the runners", "Wrong number of the runners", 2, MAX_THREADS);
		int distance = io.readInt("Enter distance", "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE);
		Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
		RunnerTable[] runners = new RunnerTable[nThreads];
		System.out.printf("%-10s%-15s%-10s%n", "place", "racer number", "time");
		startRunners(runners, race);
		joinRunners(runners);
	}

	private static void joinRunners(RunnerTable[] runners) {
		IntStream.range(0, runners.length).forEach(i -> {
			try {
				runners[i].join();
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});
	}

	private static void startRunners(RunnerTable[] runners, Race race) {
		IntStream.range(0, runners.length).forEach(i -> {
			runners[i] = new RunnerTable(race, i + 1);
			runners[i].start();
		});
	}
}
