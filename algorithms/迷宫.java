package main;
import java.util.Scanner;

public class Main {
	static int[][] visited = new int[9][9];
	static int[][] z = new int[9][9];
	static int n,m,t,sx,sy,fx,fy;
	static int ans = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		t = sc.nextInt();
		sx = sc.nextInt();
		sy = sc.nextInt();
		fx = sc.nextInt();
		fy = sc.nextInt();
		for(int i=0;i<=t;i++) {
			int b = sc.nextInt();
			int c = sc.nextInt();
			z[b][c] = 1;
		}
		dfs(sx,sy);
		System.out.println(ans);
	}
	static void dfs(int x,int y) {
		if(x<1||y<1||x>n||x>m) {
			return ;
		}
		if(z[x][y]==1) {
			return;
		}
		if(visited[x][y]==1) {
			return;
		}
		if(x==fx&&y==fy) {
			ans++;
			return ;
		}
		visited[x][y]=1;
		dfs(x-1,y);
		dfs(x+1,y);
		dfs(x,y-1);
		dfs(x,y+1);
		visited[x][y]=0;
	}
}
