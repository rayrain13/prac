package lanqiao;
import java.util.*;

public class Main {
	static int n,m ;
	static int[] path = new int[50];
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	n = sc.nextInt();
    	m = sc.nextInt();
    	dfs(0,0);
    	sc.close();
    }
    static void dfs(int step,int startIndex) {
    	if(step == m) {
    		for(int i = 1;i<=m;i++) {
    			System.out.print(path[i]);
    		}
    		System.out.println();
    		return ;
    	}
    	for(int i = startIndex + 1;i<=n;i++) {
    		path[step+1]=i;
    		dfs(step+1,i);
    		path[step+1] = 0;
    	}
    }
    
}


