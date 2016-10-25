import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/*
ID: cyrano63
LANG: JAVA
TASK: buylow
 */
public class buylow {
	private static final int MAXN = 6000;
	private BufferedReader fin;
	private PrintWriter fout;
	private int N;
	private int[] price;
	private int[] dp;
	private int ANS1;
	private BigInteger ANS2;
	private BigInteger[] num;

	public buylow() {
		try {
			fin = new BufferedReader(new FileReader("buylow.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
			price = new int[MAXN];
			dp = new int[MAXN];
			num = new BigInteger[MAXN];
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new buylow().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
//System.out.println(ANS1 + " " + ANS2);		
		fout.println(ANS1 + " " + ANS2);
		fout.close();
	}

	private void solve() {
		// calc max buy-in times
		int max = 0;
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			num[i] = BigInteger.ONE;
			for(int j=0; j<i; j++) {
				if (price[i]<price[j]) {
					if (dp[i]<dp[j]+1) {
						dp[i] = dp[j]+1;
						num[i] = num[j];
					}else if(dp[i] == dp[j]+1) {
						num[i] = num[i].add(num[j]);
					}
				}
			}
			
			for(int j=0; j<i; j++) {
				if(price[i]==price[j] && dp[i]==dp[j]) {
					num[i] = BigInteger.ZERO;
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		ANS1 = max;
		ANS2 = BigInteger.ZERO;
		for(int i=0; i<N; i++) {
			if(dp[i] == ANS1) {
				ANS2 = ANS2.add(num[i]);
			}
		}
		
	}

	private void input() {
		Scanner in = new Scanner(fin);
		N = in.nextInt();
		for(int i=0; i<N; i++) {
			price[i] = in.nextInt();
		}
		in.close();
	}

}

