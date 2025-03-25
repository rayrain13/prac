package lanqiao;
import java.util.*;


public class Main {
	
	static int[] arr=new int[10010];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();    
        for(int i =1;i<=n;i++){
            arr[i] = sc.nextInt();
        }
        for(int i = 1;i<=n;i++){
            arr[i] = arr[i-1]+arr[i];
        }
        int m = sc.nextInt();
        for(int i = 1;i<=m;i++){
            int l = sc.nextInt();
            int r = sc.nextInt();
            int ans = arr[r]-arr[l-1];
            System.out.println(ans);
        }
        
        sc.close();
    }	
}