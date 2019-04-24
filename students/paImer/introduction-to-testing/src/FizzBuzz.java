class FizzBuzz {
    String fizzBuzz(int i) {
        String result = "";
        result += i % 3 == 0 ? "Fizz" : "";
        result += i % 5 == 0 ? "Buzz" : "";
        return "".equals(result) ? "" + i : result;
    }
}
