package lanqiao;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] alpha = new int[26];
		String s = sc.next();
		for(int i=0;i<s.length();i++) {
			int pos = s.charAt(i)-'A';
			alpha[pos]++;
		}
		int max = 0;
		for(int i=0;i<26;i++) {
			if(alpha[i]>max) {
				max = alpha[i];
			}
			
		}
		for(int i =0;i<26;i++) {
			if(alpha[i]==max) {
				char c =(char)(i+'A');
				System.out.print(c);
			}
		}
	}
}