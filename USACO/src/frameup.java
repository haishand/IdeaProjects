import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 ID: cyrano63
 LANG: JAVA
 TASK: frameup
 */
public class frameup {
	private BufferedReader fin;
	private PrintWriter fout;
	private int H;
	private int W;
	private String[] frame = new String[40];

	class Rect {
		int minX, minY, maxX, maxY;
	};

	Rect rect[] = new Rect[30];
	private boolean[][] G = new boolean[30][30];

	public frameup() {
		try {
			fin = new BufferedReader(new FileReader("frameup.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter(
					"frameup.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new frameup().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
		// System.out.println(ans);
		// fout.println();
		fout.close();
	}

	private void solve() {
		buildGraph();
		
		topoSort(0);

	}

	int[] ans = new int[30];
	private boolean[] used = new boolean[30];

	private void topoSort(int d) {
		if (d == vlist.size()) {
/*			for(int i=0; i<vlist.size(); i++) {
				char ch = (char) (ans[i] + 'A');
				System.out.print(ch);
			}
			System.out.println();*/
			
			for(int i=0; i<vlist.size(); i++) {
				char ch = (char) (ans[i] + 'A');
				fout.print(ch);
			}
			fout.println();
			return;
		}

		for (int v : vlist) {
			if (used [v])
				continue;
			boolean found = true;
			for (int u : vlist) {
				if (G[u][v]) {
					found = false;
					break;
				}
			}
			if (found) {
				ans[d] = v;
				used[v] = true;
				boolean t[][] = new boolean[30][30];
				for (int u : vset) {
					t[v][u] = G[v][u];
					G[v][u] = false;
				}
				topoSort(d+1);
				for (int u : vset) {
					G[v][u] = t[v][u];
				}
				used[v] = false;
			}
		}

		/*
		 * // find start int n = vset.size(); do { int v = findSV(); ans.add(v);
		 * --n; for(int u : vset) { G[v][u] = false; }
		 * 
		 * // System.out.println(v + ":"); // for(int v1 : vset) { // for(int u1
		 * : vset) { // System.out.print(G[v1][u1] + " "); // } //
		 * System.out.println(); // }
		 * 
		 * }while(n!=0);
		 */

	}

/*	private int findSV() {
		for (int v : vset) {
			if (ans.contains(v))
				continue;
			boolean Found = true;
			for (int u : vset) {
				if (G[u][v]) {
					Found = false;
					break;
				}
			}
			if (Found)
				return v;
		}
		return -1;
	}*/

	private void buildGraph() {
		for (int v : vset) {
			int minX = rect[v].minX;
			int minY = rect[v].minY;
			int maxX = rect[v].maxX;
			int maxY = rect[v].maxY;

			int u;
			for (int x = minX; x <= maxX; x++) {
				u = frame[x].charAt(minY) - 'A';
				if (u != v) {
					G[v][u] = true;
				}

				u = frame[x].charAt(maxY) - 'A';
				if (u != v) {
					G[v][u] = true;
				}
			}

			for (int y = minY; y <= maxY; y++) {
				u = frame[minX].charAt(y) - 'A';
				if (u != v) {
					G[v][u] = true;
				}

				u = frame[maxX].charAt(y) - 'A';
				if (u != v) {
					G[v][u] = true;
				}
			}
		}

	}

	Set<Integer> vset = new HashSet<Integer>();
	List<Integer> vlist;
	
	private void input() {
		Scanner in = new Scanner(fin);

		H = in.nextInt();
		W = in.nextInt();
		for (int i = 0; i < 26; i++) {
			rect[i] = new Rect();
			rect[i].minX = H;
			rect[i].minY = W;
			rect[i].maxX = 0;
			rect[i].maxY = 0;

		}
		for (int x = 0; x < H; x++) {
			String s = in.next();
			for (int y = 0; y < s.length(); y++) {
				char ch = s.charAt(y);
				if (ch == '.')
					continue;
				vset.add(ch - 'A');
				if (x < rect[ch - 'A'].minX) {
					rect[ch - 'A'].minX = x;
				}
				if (y < rect[ch - 'A'].minY) {
					rect[ch - 'A'].minY = y;
				}
				if (x > rect[ch - 'A'].maxX) {
					rect[ch - 'A'].maxX = x;
				}
				if (y > rect[ch - 'A'].maxY) {
					rect[ch - 'A'].maxY = y;
				}
			}
			frame[x] = s;
		}

		vlist = new ArrayList<Integer>(vset);
		Collections.sort(vlist);
		in.close();
	}

}
