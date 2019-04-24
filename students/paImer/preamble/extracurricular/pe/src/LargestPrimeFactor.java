/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * <p>
 * What is the largest prime factor of the number 600851475143 ?
 */
public class LargestPrimeFactor {
    public static void main(String[] args) {
        long num = 600851475143L;
        long divisor = 3;
        while (num > 1) {
            if ((num % divisor) == 0) {
                num = num / divisor;
            } else {
                divisor += 2;
            }
        }
        System.out.println(divisor);
    }
}
