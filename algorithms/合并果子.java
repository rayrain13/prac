import java.util.*;


public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int n = sc.nextInt();
		int res=0;
		for(int i =0;i<n;i++) {
			pq.offer(sc.nextInt());
		}
		while(pq.size()>1) {
			int a = pq.poll();
			int b = pq.poll();
			res +=a+b;
			pq.offer(res);
		}
		System.out.print(pq.peek());
	}
}