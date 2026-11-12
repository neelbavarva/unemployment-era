import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);

        }

        out.flush();
        out.close();
    }

    // shuffle before sorting so anti-quicksort hacks can't make it O(n²)
    static void safeSort(int[] a) {
        Random rnd = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        Arrays.sort(a);
    }

    static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }

        String nextLine() throws IOException {
            st = null;
            return br.readLine();
        }
    }
}