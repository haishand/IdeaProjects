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
TASK: template
 */
public class template {
	private BufferedReader fin;
	private PrintWriter fout;

	public template() {
		try {
			fin = new BufferedReader(new FileReader("template.in"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new template().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	private void output() {
//System.out.println();		
		fout.println();
		fout.close();
	}

	private void solve() {
	}

	private void input() {
		Scanner in = new Scanner(fin);
		
		//TODO
		
		in.close();
	}

}

