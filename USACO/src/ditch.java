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
TASK: ditch
 */
public class ditch {
	class Edge {
		int to, cap, rev;

		public Edge(int to, int cap, int rev) {
			super();
			this.to = to;
			this.cap = cap;
			this.rev = rev;
		}
	}

	private static final int MAXN = 201;

	private static final int INF = 10000000;

	Vector<Edge> G[];
	int N, M;
	int MAX;
	boolean used[];

	private BufferedReader fin;

	private PrintWriter fout;

	public ditch() {
		G = new Vector[MAXN];
		used = new boolean[MAXN];
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

	private void addEdge(int from, int to, int cap) {
		G[from].add(new Edge(to, cap, G[to].size()));
		G[to].add(new Edge(from, 0, G[from].size() - 1));
	}

	public static void main(String[] args) {
		new ditch().run();
	}

	private void run() {
		input();
		solve(1, M);
		output();
	}

	private void output() {
//System.out.println(MAX);		
		fout.println(MAX);
		fout.close();
	}

	private void solve(int s, int t) {
		MAX = 0;
		while (true) {
			Arrays.fill(used, false);
			int f = dfs(s, t, INF);
			if (f == 0)
				break;
			MAX += f;
		}
	}

	private int dfs(int s, int t, int f) {
		if (s == t)
			return f;
		used[s] = true;
		for (int i = 0; i < G[s].size(); i++) {
			Edge e = G[s].get(i);
			if (!used[e.to] && e.cap > 0) {
				int d = dfs(e.to, t, Math.min(f, e.cap));
				if (d > 0) {
					e.cap -= d;
					G[e.to].get(e.rev).cap += d;
					return d;
				}
			}
		}
		return 0;
	}

	private void input() {
		Scanner in = new Scanner(fin);
		int S, E, C;
		for (int i = 1; i < MAXN; i++) {
			G[i] = new Vector<Edge>();
		}
		N = in.nextInt();
		M = in.nextInt();

		for (int i = 0; i < N; i++) {
			S = in.nextInt();
			E = in.nextInt();
			C = in.nextInt();
			addEdge(S, E, C);
		}
	}

}
