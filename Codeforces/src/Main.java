import java.io.*;
import java.util.*;

public class Main {

    // ----- Codeforces Round 1134 (Div. 4) ----- //

    // A. Three Piles
    static boolean threePiles(int a, int b, int c) {
        int arr[] = new int[]{a, b, c};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] != 1) {
                return false;
            }
        }
        return true;
    }

    // B. Stubborn Pen
    static int stubbornPen(int n, String s) {
        int res = 0;
        char pen = 'R';

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != pen) {
                pen = s.charAt(i);
                res++;
            }
            pen = pen == 'R' ? 'B' : 'R';

        }

        return res;
    }

    // C. Books on a Shelf
    static void booksOnAShelf(int n, int[] h, PrintWriter out) {
        if (n == 1) {
            out.println(1);
            printArray(out, h);
        } else {
            Arrays.sort(h);
            int arr[] = new int[n];
            int left = 0;
            int right = n - 1;
            int index = 0;
            boolean visible[] = new boolean[n];

            while (left <= right && index < n) {
                arr[left++] = h[index++];
               if(left <= right && index < n){
                   arr[right--] = h[index++];
               }
            }

            int totalCount = 0;
            int prev = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                if (prev < arr[i]) {
                    visible[i] = true;
                    prev = arr[i];
                }
            }

            prev = Integer.MIN_VALUE;
            for (int i = n - 1; i >= 0; i--) {
                if (prev < arr[i]) {
                    visible[i] = true;
                    prev = arr[i];
                }
            }
            for(int i=0;i<n;i++){
                if(visible[i]){
                    totalCount++;
                }
            }

            out.println(totalCount);
            printArray(out, arr);
        }
    }

    // D. Letters Down
    static int lettersDown(int n, String s) {
        char[] targetString = new char[n];
        char[] givenString = s.toCharArray();
        int left = 0;
        int right = n-1;

        while(left<=right){
            char leftChar = givenString[left];
            char rightChar = givenString[right];
            if(leftChar==rightChar){
                targetString[left] = leftChar;
                targetString[right] = rightChar;
            } else {
                if(leftChar-'a'>rightChar-'a'){
                    targetString[left] = rightChar;
                    targetString[right] = rightChar;
                } else {
                    targetString[left] = leftChar;
                    targetString[right] = leftChar;
                }
            }
            left++;
            right--;
        }

        int total = 0;
        boolean visited[] = new boolean[26];
        for(int i=0;i<n;i++){
            if(givenString[i]!=targetString[i]){
                for(int j=givenString[i]-'a';j>targetString[i]-'a';j--){
                   if(!visited[j]){
                       visited[j] = true;
                       total++;
                   }
                }
            }
        }

        return total;
    }

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        out.flush();
        out.close();
    }

    static void printArray(PrintWriter out, int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        out.println(sb);
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
    }
}
