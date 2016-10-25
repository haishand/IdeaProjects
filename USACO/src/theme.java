import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/*
ID: cyrano63
LANG: JAVA
TASK: theme
 */
public class theme {
	private static final int MAXN = 5010;
	private BufferedReader fin;
	private PrintWriter fout;
	private int N;
	private int[] T;
	private int[] TT;
	private int[][] LCS;
	private int ANS;

	public theme() {
		try {
			fin = new BufferedReader(new FileReader("theme.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("theme.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new theme().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
		System.out.println(ANS+1<5?0:ANS+1);	
		fout.println(ANS+1<5?0:ANS+1);
		fout.close();
	}

	private void lcs() {
		ANS = 0;
		for(int i=1; i<N; i++) {
			for(int j=i; j<N; j++) {
				int k = i%2;
				if(TT[i] == TT[j]) {
					if(LCS[1-k][j-1]+1<j-i) {
						LCS[k][j] = LCS[1-k][j-1]+1;	// LCS[i][j]=LCS[i-1][j-1]+1
					}
					else {
						LCS[k][j] = j-i-1;

					}
					/*if(LCS[1-k][j-1] + 1 > LCS[k][j]) {
						LCS[k][j] = Math.min(LCS[1-k][j-1] + 1, j-i-1);
					}*/
				}else
					LCS[k][j] = 0;
				if(LCS[k][j] > ANS) {
					ANS = LCS[k][j];
				}
			}
		}

//		System.out.println(ANS);
	}
	
	private void solve() {
		lcs();
	}

	private void input() {
		Scanner in = new Scanner(fin);
		
		//TODO
		N = in.nextInt();
		T = new int[N+1];
		TT = new int[N+1];	// N-1
		LCS = new int[2][N+1];	// N-1
		
		for(int i=1; i<=N; i++) {
			T[i] = in.nextInt();
		}
		
		// transform
		for(int i=1; i<N; i++) {
			TT[i] = T[i+1] - T[i];
			System.out.print(TT[i] + " ");
		}
		System.out.println();
		
		in.close();
	}

}

