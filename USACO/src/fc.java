import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/*
 ID: cyrano63
 LANG: JAVA
 TASK: fc
 */
public class fc {
	static final double EPS = 0.001f;

	private int N = 0;

	private BufferedReader fin;
	private PrintWriter fout;
	double LEN;

	class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	};

	Point P[] = new Point[10000];
	Point CH[] = new Point[1000];
	int nCH;

	boolean leftBottomMost(Point a, Point b) {
		return (a.y < b.y) || (a.y == b.y && a.x < b.x);
	}

	double cross(Point o, Point a, Point b) {
		return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
	}

	double distance1(Point a, Point b) {
		return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	}

	double distance(Point a, Point b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	boolean far(Point o, Point a, Point b) {
		return distance1(o, a) > distance1(o, b);
	}

	void jarvisMarch() {
		int start = 0;
		for (int i = 1; i < N; i++) {
			if (leftBottomMost(P[start], P[i])) {
				start = i;
			}
		}

		nCH = 0;
		CH[nCH] = P[start];

		for (nCH = 1;; ++nCH) {
			int next = start;
			for (int i = 0; i < N; i++) {
				double c = cross(CH[nCH - 1], P[i], P[next]);
				if (c > 0 || c == 0 && far(CH[nCH - 1], P[i], P[next])) {
					next = i;
				}
			}
			if (next == start)
				break;
			CH[nCH] = P[next];
		}
	}

	void jarvisMarch2() {
		int start = 0;
		for (int i = 1; i < N; i++) {
			if (leftBottomMost(P[start], P[i])) {
				start = i;
			}
		}

		nCH = 0;
		int next = start;
		do {
			CH[nCH++] = P[next];
			next = start;
			for (int i = 0; i < N; i++) {
				double c = cross(CH[nCH - 1], P[i], P[next]);
				if (c > 0 || c == 0 && far(CH[nCH - 1], P[i], P[next])) {
					next = i;
				}
			}
		} while (next != start);
	}

	public fc() {
		try {
			fin = new BufferedReader(new FileReader("fc.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new fc().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println(df.format(LEN));
		fout.println(df.format(LEN));
		fout.close();
	}

	private void solve() {
		jarvisMarch2();

		LEN = 0;
		int i = 0;
		for (int j = 1; j < nCH; j++) {
			LEN += distance(CH[j], CH[i]);
			i = j;
		}
		LEN += distance(CH[nCH - 1], CH[0]);
	}

	private void input() {
		Scanner in = new Scanner(fin);

		N = in.nextInt();
		for (int i = 0; i < N; i++) {
			double x = in.nextDouble();
			double y = in.nextDouble();
			Point p = new Point(x, y);
			P[i] = p;
		}

		in.close();
	}

}
