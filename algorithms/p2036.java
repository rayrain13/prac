import java.util.*;


public class Main {
	static int n;
	static int[] a ;
	static int[] b ;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		a = new int[n+1];
		b = new int[n+1];
		for(int i=1;i<=n;i++) {
			a[i] = sc.nextInt();
			b[i] = sc.nextInt();
		}
		dfs(1,1,0);
		System.out.print(ans);
	}
	static void dfs(int step,int s,int bi) {
		if(step>n) {
			if(s==1&&bi==0) {
				return ;
			}
			ans = Math.min(Math.abs(s-bi),ans);
			return ;
		}
		dfs(step+1,s*a[step],bi+b[step]);
		dfs(step+1,s,bi);
	}
}