package integration;

import api.InputConfig;
import java.util.List;
import java.util.ArrayList;

public class TestInputConfig implements InputConfig {
    private final List<Integer> numbers;
    
    public TestInputConfig(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }
    
    public TestInputConfig(int[] numbers) {
        this.numbers = new ArrayList<>();
        for (int num : numbers) {
            this.numbers.add(num);
        }
    }
    
    @Override
    public int[] getNumbers() {
        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }
    
    public List<Integer> getNumbersList() {
        return new ArrayList<>(numbers);
    }
}