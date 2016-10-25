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
TASK: stall4
 */

public class stall4 {
	private static final int MAXM = 250;
	private static final int MAXN = 250;
	BufferedReader fin;
	PrintWriter fout;
	
	int N, M;
	int NM;
	
	boolean G[][];
	int ANS;
	boolean used[];
	int x[], y[];
	
	public stall4() {
		try {
			G = new boolean[MAXN][MAXM];
			used = new boolean[MAXN];
			x = new int[MAXN];
			y = new int[MAXN];
			fin = new BufferedReader(new FileReader("stall4.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new stall4().run();
	}

	private void run() {
		input();
		solve();
		output();
	}
	
	private void output() {
//System.out.println(ANS);		
		fout.println(ANS);
		fout.close();
	}
	
	private void solve() {
		ANS = 0;
		Arrays.fill(x, -1);
		Arrays.fill(y, -1);
		for(int i=1; i<=N; i++){
			if(x[i] == -1) {
				Arrays.fill(used, false);
				if(dfs(i)){
					++ANS;
				}
			}
		}
	}
	
	private boolean dfs(int v) {
		used[v] = true;
		for(int i=1; i<=M; i++) {
			if(G[v][i]) {
				int k = y[i];
				if(k<0 || !used[k] && dfs(k)) {
					x[v] = i;
					y[i] = v;
					return true;
				}
			}
		}
		return false;
	}
	
	
	private void input() {
		Scanner in = new Scanner(fin);
		
		N = in.nextInt();
		M = in.nextInt();
		
//		NM = Math.max(N, M);
		
		init();
		
		for(int i=1; i<=N; i++) {
			int S = in.nextInt();
			for(int j=0; j<S; j++) {
				int k = in.nextInt();
				G[i][k] = true;
//				G[k][i] = true;	// 注意二分图左右是不一样的，假如1（左）->2（右）有条匹配边，不代表2（左）->1（右）有匹配边
			}
		}
		
		in.close();
	}
	
	private void init() {
		for(int i=0; i<=N; i++) {
			Arrays.fill(G[i], false);
		}
	}

}
