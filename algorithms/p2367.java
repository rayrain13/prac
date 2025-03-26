import java.util.*;



public class Main {
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int[] temp = new int [n+5];
        int[] arr = new int[n+5];
        for(int i =1;i<=n;i++) {
        	arr[i] = sc.nextInt();
        	temp[i] = arr[i] - arr[i-1];
        }
        
        
        for(int i=0;i<p;i++) {
        	int l =sc.nextInt();
        	int r = sc.nextInt();
        	int add= sc.nextInt();
        	temp[l] +=add;
        	temp[r+1]-= add;
        }
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++) {
        	temp[i]+=temp[i-1];
        	min = Math.min(min, temp[i]);
        }
        System.out.print(min);
    }
}
