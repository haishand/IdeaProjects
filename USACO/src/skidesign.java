import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: cyrano63
LANG: JAVA
TASK: skidesign
 */

public class skidesign {
	static final int MAXN = 1000;
	BufferedReader fin;
	PrintWriter fout;
	int	N;
	int[] h;
	int min = Integer.MAX_VALUE;
	
	public skidesign() {
		try {
			fin = new BufferedReader(new FileReader("skidesign.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
			h = new int[MAXN];
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new skidesign().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
		fout.println(min);
		fout.close();
	}

	private void solve() {
		int t;
		for (int i=0; i<100-17+1; i++) {
			t = 0;
			for (int j=0; j<N; j++) {
				if (h[j]<i) {
					t += (i-h[j])*(i-h[j]);
				}
				if (h[j]>17+i) {
					t += (h[j]-17-i)*(h[j]-17-i);
				}
			}
			if (t<min) min = t;
		}
	}

	private void input() {
		try {
			N = Integer.parseInt(fin.readLine());
			for(int i=0; i<N; i++) {
				h[i] = Integer.parseInt(fin.readLine());
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
