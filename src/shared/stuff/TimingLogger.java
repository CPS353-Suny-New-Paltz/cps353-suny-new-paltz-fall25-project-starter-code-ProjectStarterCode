package shared.stuff;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple timing logging utility. we use a single timing logger for the compute
 * method and each section of the method we wrap gets its own entry in each hash
 * map. It will compute the average time of various sections of the compute
 * method so we can more easily find perfoamnce issues
 */
public class TimingLogger {

  // total time (in nano) for each section, so each section adds to this time
  private static final Map<String, Long> totalTimes = new HashMap<>();

  // number of times each section runs (to compute avg)
  private static final Map<String, Integer> counts = new HashMap<>();

  // start
  public static long startSection(String label) {
    return System.nanoTime();
  }

  // end- update total time, increment count
  public static void endSection(String label, long startTime) {
    long endTime = System.nanoTime();
    long duration = endTime - startTime;

    // add to totalTimes
    long currentTotal = totalTimes.getOrDefault(label, 0L);
    totalTimes.put(label, currentTotal + duration);

    // increment
    int currentCount = counts.getOrDefault(label, 0);
    counts.put(label, currentCount + 1);
  }

  // displays all data
  public static void printStats() {
    System.out.println("------ Timing Stats ------");
    for (String label : totalTimes.keySet()) {
      long totalNano = totalTimes.get(label);
      int count = counts.get(label);
      double totalMillis = totalNano / 1_000_000.0; // convert nanoseconds →
                                                    // milliseconds
      double avgMillis = totalMillis / count;

      System.out.println("Section: " + label);
      System.out.println("  Total time: " + totalMillis + " ms");
      System.out
          .println("  Average time: " + avgMillis + " ms (" + count + " runs)");
    }
    System.out.println("--------------------------");
  }

}