import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

/*
 ID: cyrano63
 LANG: JAVA
 TASK: starry
 */

public class starry {
	public static final int MAXH = 200, MAXW = 200;
	private static final int INF = (int) 1e9;
	private BufferedReader fin;
	private PrintWriter fout;
	private int W;
	private int H;
	private char[][] map;
	int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
	Vector<Cluster> clusters = new Vector<Cluster>();
//	HashMap<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();
	char mark = 'a';
	Queue<Point> queue = new LinkedList<Point>();
	
	class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	};

	class Cluster {
		int h, w;
		char mark;
		boolean[][] m;

		public Cluster() {
		}

		public Cluster(int h, int w, char mark, boolean[][] m) {
			this.h = h;
			this.w = w;
			this.mark = mark;
			this.m = m;
		}

		
		@Override
		public int hashCode() {
			int res = 37*h + w;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(m[i][j]) res = res*31 + 37*i+j;
				}
			}
			return res;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (obj == this)
				return true;
			if (obj.getClass() != getClass()) {
				return false;
			}
			Cluster c = (Cluster) obj;
			if (c.h != h || c.w != w)
				return false;
			if(obj.hashCode() != this.hashCode()) return false;
//			for (int i = 0; i < c.h; i++) {
//				for (int j = 0; j < c.w; j++) {
//					if (c.m[i][j] != m[i][j])
//						return false;
//				}
//			}

			return true;
		}

	};

	public starry() {
		try {
			fin = new BufferedReader(new FileReader("starry.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter(
					"starry.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new starry().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
/*		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}*/
		 System.out.println();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				fout.print(map[i][j]);
			}
			fout.println();
		}
		// fout.println();
		fout.close();
	}

	private void solve() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] == '1') {
					Point p = new Point(i, j);
					floodfill(p);
				}
			}
		}
	}

	private void floodfill(Point start) {
		queue.clear();
		queue.add(start);
		int minX = start.x, minY = start.y, maxX = 0, maxY = 0;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.x > maxX)
				maxX = p.x;
			if (p.x < minX)
				minX = p.x;
			if (p.y > maxY)
				maxY = p.y;
			if (p.y < minY)
				minY = p.y;
			map[p.x][p.y] = '2'; // flag it
			for (int i = 0; i < 8; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				if (map[nx][ny] == '1') {
					Point np = new Point(nx, ny);
					queue.add(np);
//						System.out.println(nx + "," + ny);
					map[nx][ny] = '2'; // flag it
				}
			}
		}

		Cluster c = new Cluster();
		c.h = maxX - minX + 1;
		c.w = maxY - minY + 1;
		boolean[][] m = new boolean[c.h][c.w];
		c.m = m;

		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				c.m[i - minX][j - minY] = (map[i][j] == '2' ? true : false);
			}
		}

/*		boolean Found = false;
		char ch = mark;
		Cluster cl = clusters.get(c.hashCode());
		if(cl != null) {
			Found = true;
			ch = cl.mark;
		}
		if (!Found) {
			c.mark = mark;
			for (int i = 1; i <= 8; i++) {
				Cluster cc = trans(c, i);
				cc.mark = c.mark;
				clusters.put(cc.hashCode(), cc);
			}
			++mark;
		}*/
		
		boolean found = false;
		char ch = mark;
		for (Cluster cl : clusters) {
			if (sameCluster(c, cl)) {
				found = true;
				ch = cl.mark;
				break;
			}
		}

		if (!found) {
			c.mark = mark;
			for (int i = 1; i <= 8; i++) {
				Cluster cc = trans(c, i);
				cc.mark = c.mark;
				clusters.add(cc);
			}
			++mark;
		}

		// flag with char
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				if (map[x][y] == '2') {
					map[x][y] = ch;
				}
			}
		}
	}

	/*
	 * public boolean sameCluster(Cluster c1, Cluster c2) { for (int i = 1; i <=
	 * 8; i++) { Cluster t = trans(c2, i); if (c1.equals(t)) { return true; } }
	 * return false; }
	 */

	public boolean sameCluster(Cluster c1, Cluster c2) {
		if (c1.equals(c2)) {
			return true;
		}
		return false;
	}

	public Cluster trans(Cluster c, int type) {
		Cluster res = c;

		switch (type) {
		case 1:
			res = c;
			break;
		case 2:
			res = rotate(c);
			break;
		case 3:
			res = rotate(rotate(c));
			break;
		case 4:
			res = rotate(rotate(rotate(c)));
			break;
		case 5:
			res = flip(rotate(rotate(c)));
			break;
		case 6:
			res = flip(rotate(c));
			break;
		case 7:
			res = flip(c);
			break;
		case 8:
			res = flip(rotate(rotate(rotate(c))));
			break;
		}

		return res;
	}

	public Cluster rotate(Cluster c) {
		Cluster res = new Cluster();
		res.h = c.w;
		res.w = c.h;
		res.m = new boolean[res.h][res.w];
		for (int i = 0; i < c.h; i++) {
			for (int j = 0; j < c.w; j++) {
				res.m[j][res.w - i - 1] = c.m[i][j];
			}
		}
		return res;
	}

	public Cluster flip(Cluster c) {
		Cluster res = new Cluster();
		res.h = c.h;
		res.w = c.w;
		res.m = new boolean[res.h][res.w];
		for (int i = 0; i < c.h; i++) {
			for (int j = 0; j < c.w; j++) {
				res.m[i][j] = c.m[i][c.w - j - 1];
			}
		}
		return res;
	}

	private void input() {
		Scanner in = new Scanner(fin);

		// TODO
		W = in.nextInt();
		H = in.nextInt();
		map = new char[H][W];
		String line;
		for (int i = 0; i < H; i++) {
			line = in.next();
			for (int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		in.close();
	}

}
