/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * <p>
 * What is the 10,001st prime number?
 */
public class TenThousandAndFirstPrime {
    public static void main(String[] args) {
        int counter = 1;
        int num = 3;
        while (counter < 10001) {
            if (isPrime(num)) {
                counter += 1;
            }
            num += 2;
        }
        System.out.println(num - 2);
    }

    private static boolean isPrime(long num) {
        if ((num % 2) == 0) {
            return false;
        }
        for (int i = 3; i <= Math.pow(num, 0.5) + 1; i += 2) {
            if ((num % i) == 0) {
                return false;
            }
        }
        return true;
    }
}