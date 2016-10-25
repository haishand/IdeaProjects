import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/*
ID: cyrano63
LANG: JAVA
TASK: snail
 */
public class snail {
    private static final int MAXN = 200;
    private BufferedReader fin;
    private PrintWriter fout;
    private int N, B;
    private boolean[][] map = new boolean[MAXN][MAXN];    // true: can pass, false: barrier
    private boolean[][] visited = new boolean[MAXN][MAXN];
    private static final int dx[] = {-1, 0, 1, 0};
    private static final int dy[] = {0, 1, 0, -1};
    int[][] step = new int[MAXN][MAXN];

    public snail() {
        try {
            fin = new BufferedReader(new FileReader("snail.in"));
            fout = new PrintWriter(new BufferedWriter(new FileWriter("snail.out")));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new snail().run();
    }

    private void run() {
        input();
        solve();
        output();
    }

    int MAX;

    private void solve() {
        MAX = 0;

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                visited[x][y] = false;
            }
        }

        for (int i = 0; i < 4; i++) {
            visited[1][1] = true;
            dfs(1, 1, i, 1);
        }
    }

    private void output() {
        System.out.println(MAX);
        fout.println(MAX);
        fout.close();
    }

    private void dfs(int x, int y, int dir, int n) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (visited[nx][ny]) {
            if (n > MAX) {
                MAX = n;
//                return;
            }
            return;
        }

        if (map[nx][ny]) {
            visited[nx][ny] = true;
//            step[nx][ny] = n;
            dfs(nx, ny, dir, n + 1);
//            step[nx][ny] = 0;
            visited[nx][ny] = false;
        }else {
            boolean blocked = true;
            for (int i = 0; i < 4; i++) {
                if (i == dir) continue;
                nx = x + dx[i];
                ny = y + dy[i];
                if (map[nx][ny] && !visited[nx][ny]) {
                    blocked = false;
                    visited[nx][ny] = true;
//                    step[nx][ny] = n;
                    dfs(nx, ny, i, n + 1);
//                    step[nx][ny] = 0;
                    visited[nx][ny] = false;
                }
            }
            if (blocked) {
                if (n > MAX) {
                    MAX = n;
//                    debug();

                }
            }
        }

    }

    private void debug() {
        System.out.println("==============" + MAX);
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(visited[i][j]) {
                    System.out.print(step[i][j]);
                }
                else {
                    System.out.print(map[i][j]?".":"#");
                }
            }
            System.out.println();
        }
    }

    private void input() {
        Scanner in = new Scanner(fin);

        //TODO
        N = in.nextInt();
        B = in.nextInt();

        for (int i = 0; i <= N+1; i++) {
            Arrays.fill(map[i], true);
        }

        String s;
        for (int i = 0; i < B; i++) {
            s = in.next();
            int y= s.charAt(0) - 'A' + 1;
            int x = Integer.parseInt(s.substring(1));
            map[x][y] = false;
        }

        // set N/S/W/E walls
        for (int i = 0; i <= N + 1; i++) {
            map[0][i] = false;
            map[N + 1][i] = false;
            map[i][0] = false;
            map[i][N + 1] = false;
        }
        in.close();
    }

}

