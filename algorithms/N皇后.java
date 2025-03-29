import java.util.*;



public class Main {
    static int n,ans;
    static int step;
    static int[]path  =new int [15];
    static int[] vis = new int [15];
    
    
    static boolean check(int x) {
    	for(int i =1;i<x;i++) {
    		if(Math.abs(x-i)==Math.abs(path[x]-path[i])) {
    			return false;
    		}
    	}
    	return true;
    }
    
    static void dfs(int step) {
    	if(step == n) {
    		ans++;
    		return;
    	}
    	for(int i = 1;i<=n;i++) {
    		if(vis[i] == 0) {
    			vis[i] =1;
    			path[step+1] = i;
    			if(check(step+1)) {
    				dfs(step+1);
    			}
    			path[step+1] = 0;
    			vis[i]=0;
    		}
    	}
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dfs(0);
        System.out.print(ans);
        sc.close();
    }
}