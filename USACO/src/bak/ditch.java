package bak;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
ID: cyrano63
LANG: JAVA
TASK: ditch
 */
public class ditch {
	private static final int MAXN = 201;
	private static final int INF = 10000000;

	int[][] G;
	int N, M;
	int MAX;
	int level[];
//	int iter[];
	private BufferedReader fin;
	private PrintWriter fout;

	public ditch() {
		G = new int[MAXN][MAXN];
		level = new int[MAXN];
//		iter = new int[MAXN];
		try {
			fin = new BufferedReader(new FileReader("ditch.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ditch().run();
	}

	private void run() {
/*		Scanner in = new Scanner(System.in);
		int S, E, C;
		while (in.hasNext()) {
			N = in.nextInt();
			M = in.nextInt();

			for (int i = 0; i < N; i++) {
				S = in.nextInt();
				E = in.nextInt();
				C = in.nextInt();
				G[S][E] += C;
				G[E][S] = 0;
			}

			solve(1, M);
			output();
		}
		in.close();*/
		input();
		solve(1, M);
		output();
	}

	private void output() {
//		System.out.println(MAX);
		fout.println(MAX);
		fout.close();
	}

	private void solve(int s, int t) {
		MAX = 0;
		while (true) {
			bfs(s);
			if (level[t] < 0)
				break;
//			Arrays.fill(iter, 1);
			int f = dfs(s, t, INF);
			if (f == 0)
				break;
			MAX += f;
		}
	}

	private void bfs(int s) {
		Arrays.fill(level, -1);
		Queue<Integer> q = new LinkedList<Integer>();
		level[s] = 0;
		q.add(s);
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int i = 1; i <= M; i++) {
				if (G[v][i] > 0 && level[i] < 0) {
					level[i] = level[v] + 1;
					q.add(i);
				}
			}
		}
	}

	private int dfs(int v, int t, int f) {
		if (v == t)
			return f;
		for (int i = 1; i <= M; i++) {
			if (G[v][i] > 0 && level[v] + 1 == level[i]) {
				int d = dfs(i, t, Math.min(f, G[v][i]));
				if (d > 0) {
					G[v][i] -= d;
					G[i][v] += d;
					return d;
				}
			}
		}
		return 0;
	}

	private void input() {
		Scanner in = new Scanner(fin);
		int S, E, C;
		N = in.nextInt();
		M = in.nextInt();

		for (int i = 0; i < N; i++) {
			S = in.nextInt();
			E = in.nextInt();
			C = in.nextInt();
			G[S][E] += C;
		}
	}

}
