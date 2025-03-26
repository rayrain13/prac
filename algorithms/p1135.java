import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 读取输入
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[] nums = new int[n + 1];  // 楼层从1开始编号
        boolean[] vis = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
        }
        
        // BFS初始化
        Queue<Integer> q = new LinkedList<>();  // 存储楼层
        Queue<Integer> steps = new LinkedList<>(); // 存储对应步数
        q.add(a);
        steps.add(0);
        vis[a] = true;
        
        // BFS搜索
        while (!q.isEmpty()) {
            int cur = q.poll();
            int step = steps.poll();
            
            if (cur == b) {
                System.out.println(step);
                return;
            }
            
            // 尝试下楼
            int down = cur - nums[cur];
            if (down > 0 && !vis[down]) {
                vis[down] = true;
                q.add(down);
                steps.add(step + 1);
            }
            
            // 尝试上楼
            int up = cur + nums[cur];
            if (up <= n && !vis[up]) {
                vis[up] = true;
                q.add(up);
                steps.add(step + 1);
            }
        }
        
        // 无法到达
        System.out.println(-1);
    }
}
