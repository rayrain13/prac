public import java.util.*;

public class Main {
    // 四个方向：下、上、右、左
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int bfs(int n, int m, int[][] mp, int x1, int y1, int x2, int y2) {
        // 初始化距离数组，-1 表示未访问
        int[][] dis = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dis[i], -1);  // 初始化距离为 -1
        }

        // 如果起点或终点为障碍物，则无法到达
        if (mp[x2][y2] == 0) {
            return -1;
        }

        // BFS 队列
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x1, y1});
        dis[x1][y1] = 0;  // 起点的距离为 0

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];

            // 探索四个方向
            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i], yy = y + dy[i];
                // 如果新的坐标合法且未访问且不是障碍物
                if (xx >= 1 && xx <= n && yy >= 1 && yy <= m && mp[xx][yy] == 1 && dis[xx][yy] == -1) {
                    dis[xx][yy] = dis[x][y] + 1;  // 更新距离
                    q.offer(new int[]{xx, yy});  // 将新的坐标加入队列
                }
            }
        }

        return dis[x2][y2];  // 返回到达终点的最短路径长度
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 输入地图的行列数
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // 初始化地图
        int[][] mp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                mp[i][j] = sc.nextInt();  // 输入地图的每一格
            }
        }

        // 输入起点和终点坐标
        int x1 = sc.nextInt(), y1 = sc.nextInt();
        int x2 = sc.nextInt(), y2 = sc.nextInt();

        // 调用 BFS 求解最短路径
        int result = bfs(n, m, mp, x1, y1, x2, y2);

        // 输出结果
        System.out.println(result);
        
        sc.close();
    }
} {
    
}
