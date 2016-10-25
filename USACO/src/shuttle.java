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
TASK: shuttle
 */
public class shuttle {
	static final int MAXN = 30;
	
	private BufferedReader fin;
	private PrintWriter fout;
	private int N;
	private int state[];	// 1:white, 2:black, 0:hole

	private int STATE_LEN;
	static int GOAL_STATE[];
	
	public shuttle() {
		try {
			fin = new BufferedReader(new FileReader("shuttle.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new shuttle().run();
	}

	private void run() {
		input();
		solve2(0, N+1);
		output();
	}

	private void output() {
		for(int i=0; i<ntrace-1; i++) {
			System.out.print(ans[i]);		
			if(i!=0 && (i==19 || (i+1)%20 == 0)) {
				System.out.println();
			}else {
				System.out.print(" ");
			}
		}
		System.out.println(ans[ntrace-1]);

		for(int i=0; i<ntrace-1; i++) {
			fout.print(ans[i]);		
			if(i!=0 && (i==19 || (i+1)%20 == 0)) {
				fout.println();
			}else {
				fout.print(" ");
			}
		}
		fout.println(ans[ntrace-1]);
		fout.close();
	}

	int trace[] = new int[200000];
	int ntrace = 200000;
	private boolean solve(int d, int holePos) {
		
		if (same(state, GOAL_STATE)) {
//			System.out.println(Arrays.toString(trace));
			ntrace = d;
			return true;
		}
		if(holePos-2>=1 && holePos-2<=STATE_LEN && state[holePos-2] ==1 && state[holePos-2] != state[holePos-1]) {
			swap(holePos-2, holePos);
			trace[d] = holePos-2;
			if(solve(d+1,  holePos-2)) return true;
			swap(holePos, holePos-2);
		}
		if(holePos-1>=1 && holePos-1<=STATE_LEN && state[holePos-1] == 1) {
			swap(holePos-1, holePos);
			trace[d] = holePos-1;
			if(solve(d+1, holePos-1)) return true;
			swap(holePos, holePos-1);
		}
		
		if(holePos+1>=1 && holePos+1<=STATE_LEN && state[holePos+1] == 2) {
			swap(holePos+1, holePos);
			trace[d] = holePos+1;
			if(solve(d+1, holePos+1)) return true;
			swap(holePos, holePos+1);
		}

		if(holePos+2>=1 && holePos+2<=STATE_LEN && state[holePos+2] ==2 && state[holePos+2] != state[holePos+1]) {
			swap(holePos+2, holePos);
			trace[d] = holePos+2;
			if(solve(d+1, holePos+2)) return true;
			swap(holePos, holePos+2);
		}
		return false;
	}

	int ans[] = new int[20000];
	private void solve2(int d, int holePos) {
		
		if (same(state, GOAL_STATE)) {
//			System.out.println(Arrays.toString(trace));
			if(d<ntrace) {
				ntrace = d;
				for(int i=0; i<ntrace; i++) {
					ans[i] = trace[i];
				}
			}
			return;
		}
		if(holePos-2>=1 && holePos-2<=STATE_LEN && state[holePos-2] ==1 && state[holePos-2] != state[holePos-1]) {
			swap(holePos-2, holePos);
			trace[d] = holePos-2;
			solve2(d+1,  holePos-2);
			swap(holePos, holePos-2);
		}
		if(holePos-1>=1 && holePos-1<=STATE_LEN && state[holePos-1] == 1) {
			swap(holePos-1, holePos);
			trace[d] = holePos-1;
			solve2(d+1, holePos-1);
			swap(holePos, holePos-1);
		}
		
		if(holePos+1>=1 && holePos+1<=STATE_LEN && state[holePos+1] == 2) {
			swap(holePos+1, holePos);
			trace[d] = holePos+1;
			solve2(d+1, holePos+1);
			swap(holePos, holePos+1);
		}

		if(holePos+2>=1 && holePos+2<=STATE_LEN && state[holePos+2] ==2 && state[holePos+2] != state[holePos+1]) {
			swap(holePos+2, holePos);
			trace[d] = holePos+2;
			solve2(d+1, holePos+2);
			swap(holePos, holePos+2);
		}
		return;
	}
	
	private boolean same(int[] state1, int[] state2) {
		for(int i=1; i<=STATE_LEN; i++){
			if(state1[i] != state2[i]) {
				return false;
			}
		}
		return true;
	}

	private void swap(int i, int holePos) {
		int t = state[i];
		state[i] = state[holePos];
		state[holePos] = t;
	}

	private void input() {
		Scanner in = new Scanner(fin);
		N = in.nextInt();
		STATE_LEN = 2*N + 1;
		in.close();
		
		init();
	}

	private void init() {
		state = new int[MAXN];
		GOAL_STATE = new int[MAXN];
		
		for(int i=1; i<=N; i++) {
			state[i] = 1;
			GOAL_STATE[i] = 2;
		}
		state[N+1] = 0;
		for(int i=N+2; i<=2*N+1; i++) {
			state[i] = 2;
			GOAL_STATE[i] = 1;
		}
		
	}

}

