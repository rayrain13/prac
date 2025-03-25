package algorithms;

import java.util.*;


public class Main {
    static final int N = 5000010;
    static int[] a = new int[N];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int k = 1; k <= T; k ++) {
            int n = sc.nextInt(), m = n + 1 >> 1;
            String s = sc.next();
            for (int i = 1; i <= n; i ++) a[i] = a[i - 1] + Integer.parseInt(s.charAt(i - 1) + "");
            int res = 0;
            for (int i = m; i <= n; i ++) res = Math.max(res, a[i] - a[i - m]);
            System.out.println("Case #" + k + ": " + res);
        }
    }
}