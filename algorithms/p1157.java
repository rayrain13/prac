import java.util.Scanner;

public class Main {
    static int r, n;
    static int[] a = new int[100]; // 数组下标从1开始使用

    // 深度优先搜索（DFS）生成组合数
    static void dfs(int k) {
        if (k > r) {
            for (int i = 1; i <= r; i++) {
                System.out.printf("%3d", a[i]); // 场宽为3，右对齐输出
            }
            System.out.println();
            return;
        }
        for (int i = a[k - 1] + 1; i <= n; i++) {
            a[k] = i;
            dfs(k + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        r = scanner.nextInt();
        a[0] = 0; // 初始化，保证第一次从i=1开始选
        dfs(1);
    }
}