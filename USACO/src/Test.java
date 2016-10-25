import java.util.Arrays;

class Cluster {
	int h, w;
	int [][] m;
	public Cluster() {}
	public Cluster(int h, int w, int[][] m) {
		super();
		this.h = h;
		this.w = w;
		this.m = m;
	}
};

public class Test {

	
	
/*	static void swap(int[] state, int i, int holePos) {
		int t = state[i];
		state[i] = state[holePos];
		state[holePos] = t;
	}
	
	public static void main(String[] args) {
		int state[] = new int[3];
		state[0] = 1;
		System.out.println(Arrays.toString(state));
		swap(state, 0, 1);
		System.out.println(Arrays.toString(state));
	}*/

	public static Cluster rotate(Cluster c) {
		Cluster res = new Cluster();
		res.h = c.w; 
		res.w = c.h;
		res.m = new int[res.h][res.w];
		for(int i=0; i<c.h; i++) {
			for(int j=0; j<c.w; j++) {
				res.m[j][res.w-i-1] = c.m[i][j];
			}
		}
		return res;
	}
	
	public static Cluster flip(Cluster c) {
		Cluster res = new Cluster();
		res.h = c.h;
		res.w = c.w;
		res.m = new int[res.h][res.w];
		for(int i=0; i<c.h; i++) {
			for(int j=0; j<c.w; j++) {
				res.m[i][j] = c.m[i][c.w-j-1];
			}
		}
		return res;
	}
	
	public static void debug(Cluster c) {
		for(int i=0; i<c.h; i++) {
			System.out.println(Arrays.toString(c.m[i]));
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int[][] m = {
				{1,1,1,0},
				{1,0,0,1},
				{1,0,0,0},
		};
		
		Cluster c = new Cluster(3, 4, m);
		debug(c);
//		Cluster c1 = rotate(c);
//		Cluster c2 = rotate(c1);
		Cluster c1 = rotate(c);
		Cluster c2 = flip(c1);
		debug(c2);
	}
}
