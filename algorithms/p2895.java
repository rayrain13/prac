
// WA
import java.util.*;


public class Main {
	static int n;
	static int[][] mp = new int[310][310];
	static Queue<int[]> q = new LinkedList<>();
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[][]vis = new int[310][310];
	static int step;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for(int i =1;i<=n;i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int t = sc.nextInt();
			mp[x][y] = t;
		}
		int result = bfs(0,0);
		System.out.print(result);
		
	}
	static int bfs(int x,int y) {
		q.offer(new int[]{x,y});
		vis[0][0] = 1;
		while(!q.isEmpty()) {
			q.poll();
			for(int i =0;i<4;i++) {
				int xx = x+dx[i];
				int yy = y+dy[i];
				step++;
				if(xx<0||yy<0||xx>n||yy>n) continue;
				if(mp[xx][yy]==0) return step;
				if(vis[xx][yy]==1&&step<mp[xx][yy]) {
					q.offer(new int[] {xx,yy});
					vis[xx][yy]=1;
				}
			}
		}
		return -1;
	}
	
}