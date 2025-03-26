package lanqiao;
import java.util.*;


public class Main {
	
	static int N = 5010;
	static int cnt; //记录满足的情况个数
	static int[][] arr = new int [N][10];
	static int[] temp = new int [10];
	static int n=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		dfs(0,0);
		if(cnt == 0) {
			System.out.print(0);
		}
		else {
			System.out.println(cnt);
			for(int i =0;i<cnt;i++) {
				for(int j = 0 ;j<10;j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
		}
		sc.close();
	}
	private static void dfs(int type,int sum){
		if(type == 10) {
			if(sum == n) {
				
				for(int i = 0;i<10;i++) {
					arr[cnt][i] = temp[i];
				}
				cnt++;
			}
			return ;
		}
		for(int i=1;i<=3;i++) {
			temp[type]=i;
			dfs(type+1,sum+i);
		}
		
	}
}

