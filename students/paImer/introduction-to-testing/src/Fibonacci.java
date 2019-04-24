class Fibonacci {
    int fib(int i) {
        if (i < 1) {
            return -1;
        }
        if (i == 1 || i == 2) {
            return 1;
        }
        return fib(i - 1) + fib(i - 2);
    }
}
