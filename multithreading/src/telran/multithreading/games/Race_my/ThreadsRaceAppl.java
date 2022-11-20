package telran.multithreading.games.Race_my;

import java.util.stream.IntStream;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class ThreadsRaceAppl {
	private static final int MAX_THREADS = 20;
	private static final int MIN_DISTANCE = 10;
	private static final int MAX_DISTANCE = 1000;

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = { Item.of("Start new game", ThreadsRaceAppl::startGame), Item.exit() };
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);
	}

	static void startGame(InputOutput io) {
		int nThreads = io.readInt("Enter number of the runners", "Wrong number of the runners", 2, MAX_THREADS);
		int distance = io.readInt("Enter distance", "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE);
		Runner[] t = new Runner[nThreads];

		IntStream.range(0, t.length).forEach(i -> {
			t[i] = new Runner(distance);
			t[i].start();
			try {
				t[i].join();
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});
		Runner.resultsTtable();
	}
}
