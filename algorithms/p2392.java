import java.util.*;

public class Main {
    
    static Deque<int[]> q = new ArrayDeque<int[]>();
    static int[] arr = new int [4];
    static int[][] times = new int[4][20];
    static int left,right;
    static int min;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    	for(int i =0;i<4;i++) {
        	arr[i] = sc.nextInt();      	
        }
    	for(int i =0;i<4;i++) {
    		for(int j=0;j<arr[i];j++) {
    			times[i][j]=sc.nextInt();
    		}
    	}
    	int ans =0;
    	for(int i=0;i<4;i++) {
    		left = 0;
    		right = 0;
    		min = Integer.MAX_VALUE;
    		dfs(i,0);
    		ans+=min;
    	}
    	System.out.print(ans);
    }
    static void dfs(int i,int j) {
    	if(j>=arr[i]) {
    		min = Math.min(min,Math.max(left, right));
    		return ;
    	}
    	left+=times[i][j];
    	dfs(i,j+1);
    	left-=times[i][j];
    	
    	right+=times[i][j];
    	dfs(i,j+1);
    	right-=times[i][j];
    }
    
}