/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * <p>
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class SmallestMultiple {
    public static void main(String[] args) {
        int factor = 11;
        int product = 1;
        while (factor != 20) {
            if ((product % factor) == 0) {
                factor += 1;
            } else {
                factor = 1;
                product += 1;
            }
        }
        System.out.println(product);
    }
}
