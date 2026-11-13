public class Recursion {

    // Fibonacci Number
    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // Reverse String
    public void reverseString(char[] s) {
        reverseString(s, 0, s.length - 1);
    }

    private void reverseString(char[] s, int i, int j) {
        if (i >= j) return;
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        reverseString(s, i + 1, j - 1);
    }

    // Pow(x, n)
    public double myPow(double x, int n) {
        double temp = myPowHelper(x, Math.abs(n));
        return n < 0 ? (1 / temp) : temp;
    }

    private double myPowHelper(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double myPowHelper = myPowHelper(x, n / 2);
        return n % 2 == 0 ? (myPowHelper * myPowHelper) : (x * myPowHelper * myPowHelper);
    }

}
