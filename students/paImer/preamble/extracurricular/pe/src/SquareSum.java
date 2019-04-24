/**
 * The sum of the squares of the first ten natural numbers is,
 * <p>
 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is,
 * <p>
 * (1 + 2 + ... + 10)^2 = 55^2 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
 * <p>
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class SquareSum {
    public static void main(String[] args) {
        int sum = 0;
        int squareSum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
            squareSum += i * i;
        }
        int result = (sum * sum) - squareSum;
        System.out.println(result);
    }
}
