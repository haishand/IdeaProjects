import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;

/*
ID: cyrano63
LANG: JAVA
TASK: fence6
 */

class Segment {
	int len;
	Vector<Integer> h;
	Vector<Integer> t;
	
	public Segment() {
		h = new Vector<Integer>();
		t = new Vector<Integer>();
	}
};

public class fence6 {
	static final int MAXN = 101;
	static final int MAX_ITEMS_NUM = 9;
	
	BufferedReader fin;
	PrintWriter fout;
	
	int N;
	Segment[] seg = new Segment[MAXN];
	int minP;
	
	public fence6() {
		try {
			fin = new BufferedReader(new FileReader("fence6.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new fence6().run();
	}

	private void run() {
		input();
		
		for(int i=0; i<MAXN; i++)
			visited[i] = false;
		
		for(int i=1; i<=N; i++) {
//			for(int k=0; k<MAXN; k++)
//				visited[k] = false;
			visited[i] = true;
			for(int j=0; j<seg[i].t.size(); j++) {
				solve(i, i, seg[i].t.get(j), seg[i].len);
			}
			visited[i] = false;
		}
		
		output();
	}

	private void output() {
//System.out.println(minP);		
		fout.println(minP);
		fout.close();
		
//		debug();
	}

	private void debug() {
		for(int i=1; i<=N; i++) {
			if(ansFlag[i]) {
				System.out.println(i);
			}
		}
	}

	boolean[] ansFlag = new boolean[MAXN];
	boolean[] visited = new boolean[MAXN];
	private void solve(int s, int last, int c, int perim) {
		if(perim > minP) return;	// pruning optimization
		if (visited[c]) return;
		
		/*
		 * Please don't forget to reset the flag(visited/perim) you have set in each return path.
		 * DEBUG TIPS for DP problem: trace all the elem on the solution path to find if it's correct.
		 * 
		 */
		visited[c] = true;
		perim += seg[c].len;
		if (seg[s].h.contains(c)) {
			if (perim < minP) {
				minP = perim;
				
				for(int i=1; i<=N; i++) {
					ansFlag[i] = visited[i];
				}
			}
		}
		else {
			boolean fromH = seg[c].h.contains(last);
			if (fromH) {
				for(int i=0; i<seg[c].t.size(); i++) {
					solve(s, c, seg[c].t.get(i), perim);
				}
			}else {
				for(int i=0; i<seg[c].h.size(); i++) {
					solve(s, c, seg[c].h.get(i), perim);
				}
			}
		}
		perim -= seg[c].len;
		visited[c] = false;

	}

	private void input() {
		try {
			N = Integer.parseInt(fin.readLine());

			minP  = 0;
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(fin.readLine());
				int s = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				int N1 = Integer.parseInt(st.nextToken());
				int N2 = Integer.parseInt(st.nextToken());
				seg[s] = new Segment();
				seg[s].len = l;
				minP += l;
				st = new StringTokenizer(fin.readLine());
				for(int j=0; j<N1; j++) {
					seg[s].h.add(Integer.parseInt(st.nextToken()));
				}
				st = new StringTokenizer(fin.readLine());
				for(int j=0; j<N2; j++) {
					seg[s].t.add(Integer.parseInt(st.nextToken()));
				}
			}
			fin.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
