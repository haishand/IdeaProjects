import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: cyrano63
LANG: JAVA
TASK: wormhole
 */
public class wormhole {

	
	private static BufferedReader fin;
	private static PrintWriter fout;
	private static final int MAXN = 15;
	static int[] x = new int[MAXN], y = new int[MAXN];
	static int ans;
	static int[] pair = new int[MAXN];
	private static int N;
	private static int[] next_on_right = new int[MAXN];
	
	public static void main(String[] args) throws IOException {
		fin = new BufferedReader(new FileReader("wormhole.in"));
		fout = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		
		input();
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				if (x[i]>x[j] && y[i] == y[j]) {
					if (next_on_right[j] == 0 || x[i]<x[next_on_right[j]])  {
						next_on_right[j] = i;
					}
				}
			}
		}
		ans = solve();
		output();
	}

	private static void output() {
		fout.println(ans);
		fout.close();
	}

	private static int solve() {
		int i, j, count = 0;
		for (i=1; i<=N; i++) {
			if (pair[i] == 0) break;
		}
		
		if (i>N) {
			if (cycle_existed()) {
				return 1;
			}
			else {
				return 0;
			}
		}
		for (j=1; j<=N; j++) {
			if (i!=j && pair[j] == 0) {
				pair[i] = j; pair[j] = i;
				count += solve();
				pair[i] = pair[j] = 0;
			}
		}
		
		return count;
	}

	private static boolean cycle_existed() {
		int pos=0, start, count;
		for (start = 1; start <= N; start++) {
			pos = start;
			for (count = 0; count<N; count++) {
				pos = next_on_right[pair[pos]];
			}
			if (pos != 0) return true;
		}
		
		return false;
	}

	private static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(fin.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(fin.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
	}

}
