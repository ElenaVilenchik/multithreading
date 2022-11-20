package telran.multithreading.games.Race_my;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Runner extends Thread {

	private int nSteps;
	public static Map<String, Integer> timesRes = new HashMap<String, Integer>();

	public Runner(int nSteps) {
		this.nSteps = nSteps;
	}

	@Override
	public void run() {
		Random rand = new Random();
		Instant startTime = Instant.now();
		for (int i = 0; i < nSteps; i++) {
			System.out.printf("%s started... \n", Runner.currentThread().getName());
			try {
				sleep(rand.nextInt(4) + 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("%s stopped... \n", Runner.currentThread().getName());
		}
		int time = (int) ChronoUnit.MILLIS.between(startTime, Instant.now());
		System.out.printf("%s finish time...%s \n", time, Runner.currentThread().getName());
		timesRes.putIfAbsent(Runner.currentThread().getName(), time);
	}

//	public static void resWinner() {
//		Entry<String, Double> min = Collections.min(timesRes.entrySet(), Comparator.comparing(Entry::getValue));
//		List<String> isMin = timesRes.entrySet()
//				.stream()
//				.filter(e -> e.getValue().equals(min.getValue()))
//				.map(Map.Entry::getKey)
//				.sorted()
//				.collect(Collectors.toList());
//		System.out.printf("\n%sCongratulations winner!!! \n"," ".repeat(10));
//		for (String r : isMin) {
//			System.out.printf("Congratulations winner to %s with time %s!!! \n", r, min.getValue());
//		}
//	}
	
	public static void resultsTtable() {
		Map<String, Integer> timesSorted = timesRes.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue())
	              .collect(Collectors
	                          .toMap(Map.Entry::getKey,
	                                 Map.Entry::getValue,
	                                 (e1, e2) -> e1,
	                                 LinkedHashMap::new));
		
		System.out.printf("\nplace%s racer number%s time\n", " ".repeat(7), " ".repeat(11));
		int i = 1;
		for (Entry<String, Integer> r : timesSorted.entrySet())
			System.out.println(i++ + " ".repeat(15) 
					+ r.getKey().substring(r.getKey().indexOf("-") + 1) + " ".repeat(20)
					+ r.getValue());
	}
}
