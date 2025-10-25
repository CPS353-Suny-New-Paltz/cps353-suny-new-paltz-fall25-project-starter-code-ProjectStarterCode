package project.conceptualapi;

public class PowerDigitSumController implements ComputeControllerAPI {

    @Override
    public ComputeResponse compute(ComputeRequest request) {
        try {
            int base = request.getBase();
            int exponent = request.getExponent();

            // Validate input
            if (base <= 0 || exponent <= 0) {
                return new ComputeResponse(
                        "Invalid input: base and exponent must be positive integers.",
                        ComputeStatus.FAIL
                );
            }

            // Compute base^exponent
            long power = 1;
            for (int i = 0; i < exponent; i++) {
                power *= base;
            }

            // Compute digit sum
            String numberStr = Long.toString(power);
            int sum = 0;
            for (char c : numberStr.toCharArray()) {
                sum += (c - '0');
            }

            // Return + successful response
            return new ComputeResponse(String.valueOf(sum), ComputeStatus.SUCCESS);

        } catch (Exception e) {
            return new ComputeResponse(
                    "Error during computation: " + e.getMessage(),
                    ComputeStatus.ERROR
            );
        }
    }
}
