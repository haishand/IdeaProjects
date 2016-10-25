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
 TASK: race3
 */
public class race3 {
	private static final int MAXN = 60;
	private BufferedReader fin;
	private PrintWriter fout;
	private boolean[][] g;
	int N;
	Vector<Integer> unvoid;
	Vector<Integer> split;
	boolean visited[];

	public race3() {
		try {
			fin = new BufferedReader(new FileReader("race3.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter(
					"race3.out")));
			g = new boolean[MAXN][MAXN];
			visited = new boolean[MAXN];
			unvoid = new Vector<Integer>();
			split = new Vector<Integer>();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new race3().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
		/*
		 * System.out.print(unvoid.size()); for(int i=0; i<unvoid.size(); i++) {
		 * System.out.print(" "); System.out.print(unvoid.get(i)); }
		 * System.out.println(); System.out.print(midv.size()); for(int i=0;
		 * i<midv.size(); i++) { System.out.print(" ");
		 * System.out.print(midv.get(i)); } System.out.println();
		 */
		fout.print(unvoid.size());
		for (int i = 0; i < unvoid.size(); i++) {
			fout.print(" ");
			fout.print(unvoid.get(i));
		}
		fout.println();
		fout.print(split.size());
		for (int i = 0; i < split.size(); i++) {
			fout.print(" ");
			fout.print(split.get(i));
		}
		fout.println();
		fout.close();
	}

	boolean color[] = new boolean[MAXN];

	private void solve() {

		for (int i = 1; i < N - 1; i++) {
			Arrays.fill(visited, false);
			visited[i] = true;
			if (!dfs(0)) {
				unvoid.add(i);
			}
		}

		for (Integer i : unvoid) {
			Arrays.fill(color, false);
			dfs2(i);
			Arrays.fill(visited, false);
			visited[i]=true;
			if (dfs3(0)) {
				split.add(i);
			}
		}
	}

	private boolean dfs3(int v) {
		visited[v] = true;
		if (color[v])
			return false;
		for (int u = 0; u < N; u++) {
			if (!visited[u] && g[v][u]) {
				if (!dfs3(u)) {
					return false;
				}
			}
		}
		return true;
	}

	private void dfs2(int v) {
		color[v] = true;
		for (int u = 0; u < N; u++) {
			if (!color[u] && g[v][u]) {
				dfs2(u);
			}
		}
	}

	private boolean dfs(int v) {
		visited[v] = true;
		if (v == N - 1)
			return true;
		for (int u = 0; u < N; u++) {
			if (!visited[u] && g[v][u]) {
				if (dfs(u)) {
					return true;
				}
			}
		}
		return false;
	}

	private void input() {
		Scanner in = new Scanner(fin);
		for (int i = 0; i < MAXN; i++) {
			Arrays.fill(g[i], false);
		}
		int v, u;
		v = 0;
		for (;;) {
			u = in.nextInt();
			if (u == -1)
				break;
			if (u == -2) {
				++v;
				continue;
			}
			g[v][u] = true;
		}
		N = v;
	}

}
