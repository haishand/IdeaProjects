import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
ID: cyrano63
LANG: JAVA
TASK: milk4
 */
public class milk4 {
    private BufferedReader fin;
    private PrintWriter fout;
    private int Q;
    private int P;
    private int[] pail = new int[1000];
    private int nAns = Integer.MAX_VALUE;
    private int[] count = new int[1000];
    //	private Vector<Integer> ans = new Vector<Integer>();
    int ans[] = new int[1000];
    int temp[] = new int[1000];
    int Depth;
    boolean Found = false;

    public milk4() {
        try {
            fin = new BufferedReader(new FileReader("milk4.in"));
            fout = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new milk4().run();
    }

    private void run() {
        input();
        solve();
        output();
    }

    private void output() {
        System.out.println(Found);
        System.out.print(Depth);
        for (int i = 0; i < Depth; i++) {
            System.out.print(" " + ans[i]);
        }
        System.out.println();
/*		for(Integer e : ans) {
            System.out.print(" " + e);
		}
		System.out.println();*/

        fout.print(Depth);
        for (int i = 0; i < Depth; i++) {
            fout.print(" " + ans[i]);
        }
        fout.println();
        fout.close();
    }

    void IDDFS() {
        for (Depth = 1; Depth <= P; Depth++) {
            Arrays.fill(ans, 10000);
            dfs(0, Depth, 0, 0);
            if (Found) break;
        }
    }

    void dfs(int d, int maxDep, int v, int use) {
        if (use == maxDep) {
            if (v == Q) {
                for (int i = 0; i < maxDep; i++) {
                    ans[i] = temp[i];
                }
                Found = true;
            }
            return;
        } else {
            if (d >= P || pail[d] > ans[use]) return;
            temp[use] = pail[d];
            for (int i = 1; i * pail[d] <= Q; i++) {
                dfs(d, maxDep, v + i * pail[d], use + 1);
            }
            dfs(d + 1, maxDep, v, use);
        }
    }

/*	void dfs(int d, int v) {
        if(v == Q) {
			Vector<Integer> temp = new Vector<Integer>();
			int cnt = 0;
			for(int i=0; i<P; i++) {
				if(count[i] !=0) {
					++cnt;
					temp.add(pail[i]);
				}
			}
			if(cnt <= nAns) {
				nAns = cnt;
				if(ans.isEmpty() || (ans.size() > temp.size())) {
					ans = temp;
				}else {
					for (int i = 0; i < Math.min(ans.size(), temp.size()); i++) {
						if (temp.get(i) < ans.get(i)) {
							ans = temp;
							break;
						}
					}
				}

			}
			return;
		}
		for(int i=0; i<P; i++) {
			if(pail[i] + v <= Q) {
				++count[i];
				dfs(d+1, v+pail[i]);
				--count[i];
			}
		}
	}*/

    private void solve() {
        Arrays.fill(count, 0);
//		dfs(0, 0);
        Arrays.sort(pail, 0, P);
        IDDFS();
    }

    int[] DP = new int[1000000];

/*    private void solve2() {
        Arrays.sort(pail, 0, P);
        Arrays.fill(DP, 0);
        for (int i = 0; i < P; i++) {
            DP[pail[i]] = 1;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int v = pail[0]; v <= Q; v++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < P; i++) {
                if (v - pail[i] < 0) continue;
                if (min > DP[v - pail[i]] + 1) {
                    if (map.get(pail[i]) == null) {
                        min = DP[v - pail[i]] + 1;
                        map.put(pail[i], 1);
                    } else {
                        min = DP[v - pail[i]];
                    }
                }
            }
            DP[v] = min;
        }
        System.out.println(DP[Q]);
    }*/

    private void input() {
        Scanner in = new Scanner(fin);

        //TODO
        Q = in.nextInt();
        P = in.nextInt();
        for (int i = 0; i < P; i++) {
            pail[i] = in.nextInt();
        }
    }
}