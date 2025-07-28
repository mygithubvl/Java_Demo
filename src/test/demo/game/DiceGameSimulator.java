package test.demo.game;

import java.util.Random;
import java.util.stream.IntStream;

public final class DiceGameSimulator {
	
	private static int numberOfDice = 5;
    private static int diceSides = 6;
    private static int numberOfSimulations = 100;

    public static void main(String[] args) {
    	
    	Random random = new Random();
        long start = System.nanoTime();
        
        int[] frequencyNums = new int[numberOfDice * diceSides + 1];
        
        IntStream.range(0, numberOfSimulations).forEach(i -> {
            int total = IntStream.range(0, numberOfDice)
                                 .map(j -> random.nextInt(diceSides) + 1)
                                 .sum();
            frequencyNums[total]++;
        });

        long endTime = System.nanoTime();

        for (int total = numberOfDice; total <= numberOfDice * diceSides; total++) {
            int count = frequencyNums[total];
            double estProbability = (double) count / numberOfSimulations;
            System.out.printf("Total %d occurs %.2f occurred %.1f times.%n", total, estProbability, (double) count);
        }

        double duration = (endTime - start) / 1_000_000_000.0;
        System.out.printf("Simulation time: %.2f seconds.%n", duration);
    }
}
