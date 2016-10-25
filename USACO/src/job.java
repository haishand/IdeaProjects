import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
ID: cyrano63
LANG: JAVA
TASK: job
 */
public class job {
	private static final int INF = (int)1e9;
	private static final int MAXM = 40;
	private static final int MAXN = 2000;
	private BufferedReader fin;
	private PrintWriter fout;
	private int N;
	private int M1;
	private int M2;
	private int[] a;
	private int[] b;
	private int ANS1;
	private int[] cost;
	private int ANS2;
	private int[] costA;	//costA[i]: i物品单纯在A机器加工所需用时
	private int[] costB;	//costB[i]: i物品单纯在B机器加工所需用时

	public job() {
		try {
			fin = new BufferedReader(new FileReader("job.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
			a = new int[MAXM];
			b = new int[MAXM];
			cost = new int[MAXM];
			costA = new int[MAXN];
			costB = new int[MAXN];
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new job().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
System.out.println(ANS1 + " " + ANS2);		
		fout.println(ANS1 + " " + ANS2);
		fout.close();
	}

	private void solve() {
		ANS1 = 0;
		for(int i=0; i<M1; i++) {
			cost[i] = a[i];
		}
		for(int i=0; i<N; i++) {
			int min = INF, minj = 0;
			for(int j=0; j<M1; j++) {
				if(cost[j]<min) {
					min = cost[j];
					minj = j;
				}
			}
			costA[i] = cost[minj];
			ANS1 = Math.max(ANS1, min);
			cost[minj] += a[minj];
		}
		
		for(int i=0; i<M2; i++) {
			cost[i] += b[i];
		}
		ANS2 = 0;
		for(int i=0; i<N; i++) {
			int min = INF, minj = 0;
			for(int j=0; j<M2; j++) {
				if(cost[j]<min) {
					min = cost[j];
					minj = j;
				}
			}
			costB[i] = cost[minj];
			ANS2 = Math.max(ANS2, min);
			cost[minj] += b[minj];
		}
		
//		ANS2=0;
//		for(int i=0; i<N; i++) {
//			ANS2 = Math.max(ANS2, costA[i] + costB[N-i-1]);
//		}
	}

	private void input() {
		Scanner in = new Scanner(fin);
		N = in.nextInt();
		M1 = in.nextInt();
		M2 = in.nextInt();
		for(int i=0; i<M1; i++) {
			a[i] = in.nextInt();
		}
		for(int i=0; i<M2; i++) {
			b[i] = in.nextInt();
		}
		
	}

}

