/**
 * A palindromic number reads the same both ways.
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * <p>
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class PalindromicNumbers {
    public static void main(String[] args) {
        int currentHighest = 0;
        for (int i = 999; i > 100; i--) {
            for (int j = 999; j > 100; j--) {
                int product = i * j;
                if (product > currentHighest && isPalindrome(product)) {
                    currentHighest = product;
                    break;
                }
            }
        }
        System.out.println(currentHighest);
    }

    private static boolean isPalindrome(int integer) {
        String intStr = String.valueOf(integer);
        return intStr.equals(new StringBuilder(intStr).reverse().toString());
    }
}