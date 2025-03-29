public import java.util.*;



public class Main {
    static int [] node  = new int[10];
    static int [] ans = new int[10];
    static int [] vis = new int [10];
    static int n = 9;
    static int cnt = 0;
    static boolean check() {
    	int tmp = node[1] + node[2] + node[3];
        if (tmp != node[4] + node[5] + node[6]) return false;
        if (tmp != node[7] + node[8] + node[9]) return false;
        if (tmp != node[1] + node[4] + node[7]) return false;
        if (tmp != node[2] + node[5] + node[8]) return false;
        if (tmp != node[3] + node[6] + node[9]) return false;
        if (tmp != node[1] + node[5] + node[9]) return false;
        if (tmp != node[3] + node[5] + node[7]) return false;
        return true;
    }
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int i =1;i<=n;i++) {
			node[i] = sc.nextInt();
			vis[node[i]] = 1;	//标记已经填充过的数字
		}
		dfs(0);
		
		if (cnt == 1) {
            // 输出找到的唯一解
            for (int i = 1; i <= 3; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            for (int i = 4; i <= 6; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            for (int i = 7; i <= 9; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("Too Many");
        }
        sc.close();
	}
	static void dfs(int step) {
		if(step == n) {
			if(check()) {
				cnt++;
				for(int i =1;i<=n;i++) {
					ans[i] = node[i];
				}
			}
			return;
		}
		
		if(node[step+1]!=0) {
			dfs(step+1);
			return;
		}
		
		for(int i = 1;i<=n;i++) {
			if(vis[i]==0) {
				vis[i]=1;
				node[step+1] = i;
				dfs(step+1);
				node[step+1] = 0;
				vis[i] = 0;
			}
		}
		
	}
} {
    
}
