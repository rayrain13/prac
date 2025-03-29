package lanqiao;
import java.util.*;

public class Main {
    static int[][] mp = new int [5][5];
    static int ans;
    public static void main(String[] args) {
    	dfs(0,0,0);
    	System.out.print(ans);
    }
    
    static void dfs(int sum,int h,int b) {
    	if(sum == 25) {
    		if(h==13&&b==12) {
    			for(int i =0;i<5;i++) {
    				int s = mp[i][0]+mp[i][1]+mp[i][2]+mp[i][3]+mp[i][4];
    				if(s==5||s==0) return;
    			}
    			for(int i =0;i<5;i++) {
    				int s = mp[0][i]+mp[1][i]+mp[2][i]+mp[3][i]+mp[4][i];
    				if(s==5||s==0) return ;
    			}
    			int s  = mp[0][0]+mp[1][1]+mp[2][2]+mp[3][3]+mp[4][4];
    			int p  = mp[0][4]+mp[1][3]+mp[2][2]+mp[3][1]+mp[4][0];
    			if(s==5||s==0||p==5||p==0) return ;
    			ans++;
    		}
    		return ;
    	}
    	int x = sum/5;
    	int y = sum%5;
    	mp[x][y] = 1;
    	dfs(sum+1,h+1,b);
    	mp[x][y] = 0;
    	dfs(sum+1,h,b+1);
    }
}

