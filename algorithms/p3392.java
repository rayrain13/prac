package algorithms;

public class Main {
    static int n, m;
    static char[][] arr = new char[55][55]; // 保存每行的颜色
    static int ans = Integer.MAX_VALUE;     // 初始化为最大值

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        
        // 读取输入数据
        for (int i = 1; i <= n; i++) {
            String line = scanner.next();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = line.charAt(j - 1);
            }
        }
        
        // 枚举所有可能的 i 和 j
        for (int i = 1; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                int sum = 0;
                
                // 检查第 1~i 行，如果不是 'W'，则 sum++
                for (int k = 1; k <= i; k++) {
                    for (int g = 1; g <= m; g++) {
                        if (arr[k][g] != 'W') sum++;
                    }
                }
                
                // 检查第 i+1~j 行，如果不是 'B'，则 sum++
                for (int k = i + 1; k <= j; k++) {
                    for (int g = 1; g <= m; g++) {
                        if (arr[k][g] != 'B') sum++;
                    }
                }
                
                // 检查第 j+1~n 行，如果不是 'R'，则 sum++
                for (int k = j + 1; k <= n; k++) {
                    for (int g = 1; g <= m; g++) {
                        if (arr[k][g] != 'R') sum++;
                    }
                }
                
                // 更新最小值
                if (sum < ans) {
                    ans = sum;
                }
            }
        }
        
        System.out.println(ans);
    }
}