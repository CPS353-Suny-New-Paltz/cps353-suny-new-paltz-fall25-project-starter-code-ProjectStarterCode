package visualization;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PrimeChartGenerator {

    public static void generatePieChart(List<Integer> numbers, String outputFilePath) {
        if (numbers == null || numbers.isEmpty()) {
            System.err.println("No numbers provided for chart generation.");
            return;
        }

        int primeCount = 0;
        int nonPrimeCount = 0;

        for (int num : numbers) {
            if (num < 0) {
            	continue;
            }

            if (isPrime(num)) {
                primeCount++;
            } else {
                nonPrimeCount++;
            }
        }

        if (primeCount + nonPrimeCount == 0) {
            System.err.println("No valid numbers to generate chart.");
            return;
        }

        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("Primes", primeCount);
        dataset.setValue("Non-Primes", nonPrimeCount);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Prime vs Non-Prime Numbers",
                dataset,
                true,
                true,
                false
        );

        // Save chart as PNG
        try {
            ChartUtils.saveChartAsPNG(new File(outputFilePath), chart, 600, 400);
            System.out.println("Chart saved to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error saving chart: " + e.getMessage());
        }
    }

    private static boolean isPrime(int n) {
        if (n < 2) {
        	return false;
        }
        if (n == 2) {
        	return true;
        }
        if (n % 2 == 0) {
        	return false;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) {
            	return false;
            }
        }
        return true;
    }
}
