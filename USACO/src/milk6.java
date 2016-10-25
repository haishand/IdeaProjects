import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

/*
 ID: cyrano63
 LANG: JAVA
 TASK: milk6
 */
public class milk6 {
	private static final int INF = 2000000;
	private BufferedReader fin;
	private PrintWriter fout;
	private int N;
	private int M;
	private long[][] C, R;
	private boolean[] visited;
	private int[] dist;
	private boolean[] color;
	int edge[][] = new int[1001][3];

	public milk6() {
		try {
			fin = new BufferedReader(new FileReader("milk6.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter(
					"milk6.out")));
			C = new long[40][40];
			R = new long[40][40];
			visited = new boolean[40];
			dist = new int[40];
			color = new boolean[40];

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new milk6().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
/*		nAns = ans.size();
		System.out.println(MIN + " " + nAns);
		if (nAns != 0) {
			for (int i = 0; i < nAns; i++) {
				System.out.println(ans.get(i));
			}
		}*/

		nAns = ans.size();
		fout.println(MIN + " " + nAns);
		if (nAns != 0) {
			for (int i = 0; i < nAns; i++) {
				fout.println(ans.get(i));
			}

		}
		fout.close();
	}

	int nAns;
	Vector<Integer> ans = new Vector<Integer>();
	long MIN;
	private int[] rank = new int[1001];

	private void solve() {

		long max = dinic(1, N);
		MIN = 0;
		
		for(int i=1; i<=M; i++) {
			rank [i] = i;
		}
		for(int a=1; a<M; a++) {
			for(int b=a+1; b<=M; b++) {
				if(edge[a][0] < edge[b][0]) {
					int temp = rank[a];
					rank[a] = rank[b];
					rank[b] = temp;
				}
			}
		}
		for (int i = 1; i <= M; i++) {
			int v = edge[rank[i]][1];
			int u = edge[rank[i]][2];
			int c = edge[rank[i]][0];

			if(R[v][u] != 0) continue;
			for(int a=1; a<=N; a++) {
				for(int b=1; b<=N; b++) {
					R[a][b] = C[a][b];
				}
			}

			R[v][u] -= c;
			long f = dinic(1, N);
			if (max - f == c) {
				MIN += c;
				ans.add(rank[i]);
				C[v][u] -= c;
				max = f;
			} 
		}
		Collections.sort(ans);
	}

	private void findCut(int v) {
		color[v] = true;
		for (int u = 1; u <= N; u++) {
			if (!color[u] && R[v][u] > 0) {
				findCut(u);
			}
		}
	}

	private int dinic(int S, int T) {
		int max = 0;
		for (;;) {
			Arrays.fill(visited, false);
			bfs(S);
			long f = dfs(S, T, INF);
			if (f == 0)
				break;
			max += f;
		}
		return max;
	}

	private void bfs(int s) {
		Arrays.fill(dist, -1);
		Queue<Integer> q = new LinkedList<Integer>();

		dist[s] = 0;
		q.add(s);
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int u = 1; u <= N; u++) {
				if (dist[u] == -1 && R[v][u] > 0) {
					dist[u] = dist[v] + 1;
					q.add(u);
				}
			}
		}

	}

	private long dfs(int v, int T, long f) {
		if (v == T)
			return f;
		visited[v] = true;
		for (int u = 1; u <= N; u++) {
			if (!visited[u] && R[v][u] > 0) {
				long ff = dfs(u, T, Math.min(f, R[v][u]));
				if (ff > 0) {
					R[v][u] -= ff;
					R[u][v] += ff;
					return ff;
				}
			}
		}
		return 0;
	}

	private void input() {
		Scanner in = new Scanner(fin);
		N = in.nextInt();
		M = in.nextInt();
		for (int i = 0; i < M; i++) {
			int s = in.nextInt();
			int e = in.nextInt();
			int c = in.nextInt();
			C[s][e] += c;
			R[s][e] += c;
			edge[i + 1][0] = c;
			edge[i + 1][1] = s;
			edge[i + 1][2] = e;
		}

		in.close();
	}

}
