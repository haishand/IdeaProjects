import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/*
 ID: cyrano63
 LANG: JAVA
 TASK: lgame
 */
public class lgame {
	private BufferedReader fin;
	private BufferedReader fin2;
	private PrintWriter fout;

	public lgame() {
		try {
			fin = new BufferedReader(new FileReader("lgame.in"));
			fin2 = new BufferedReader(new FileReader("lgame.dict"));
			fout = new PrintWriter(new BufferedWriter(new FileWriter(
					"lgame.out")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new lgame().run();
	}

	private void run() {
		input();
		solve();
		output();
	}

	int maxRank = 0;
	
	private void output() {
		
		 System.out.println(maxRank); for(int i=0; i<res.size(); i++) {
		 System.out.println(res.get(i)); }
		 
		fout.println(maxRank);
		for (int i = 0; i < res.size(); i++) {
			fout.println(res.get(i));
		}
		fout.close();
	}

	private void solve() {
		if (foundWholeWord) {
			maxRank = letterRank;
			for (int i = 0; i < dict.size(); i++) {
				for (int j = i + 1; j < dict.size(); j++) {
					String w1 = dict.get(i);
					String w2 = dict.get(j);
					int r1 = calcRank(w1);
					int r2 = calcRank(w2);
					if (r1 + r2 == letterRank && containSameLetters(w1 + w2)) {
						res.add(w1 + " " + w2);
					}
				}
			}
		}else {
			// 1d
			maxRank = 0;
			for(int i=0; i<dict.size(); i++) {
				String w = dict.get(i);
				int r = calcRank(w);
				if (r>maxRank && containSameLetters(w)) {
					maxRank = r;
				}
			}
			for(int i=0; i<dict.size(); i++) {
				String w = dict.get(i);
				int r = calcRank(w);
				if(r == maxRank && containSameLetters(w)) {
					res.add(w);
				}
			}
			// 2d
			for (int i = 0; i < dict.size(); i++) {
				for (int j = i + 1; j < dict.size(); j++) {
					String w1 = dict.get(i);
					String w2 = dict.get(j);
					int r1 = calcRank(w1);
					int r2 = calcRank(w2);
					if (r1 + r2 > maxRank && containSameLetters(w1 + w2)) {
						maxRank = r1 + r2;
						res.clear();
					}
				}
			}
			for (int i = 0; i < dict.size(); i++) {
				for (int j = i + 1; j < dict.size(); j++) {
					String w1 = dict.get(i);
					String w2 = dict.get(j);
					int r1 = calcRank(w1);
					int r2 = calcRank(w2);
					if (r1 + r2 == maxRank && containSameLetters(w1 + w2)) {
						res.add(w1 + " " + w2);
					}
				}
			}
		}
		
		Collections.sort(res);
	}

	int[] letterFreq = new int[26];
	String letter;
	int letterRank;
	int[] rank = { 2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2,
			4, 6, 6, 7, 5, 7 };
	Vector<String> dict = new Vector<String>();
	Vector<String> res = new Vector<String>();
	boolean foundWholeWord = false;

	private void input() {
		Scanner in = new Scanner(fin);
		letter = in.next();
		letterRank = 0;
		for (int i = 0; i < letter.length(); i++) {
			++letterFreq[letter.charAt(i) - 'a'];
			letterRank += rank[letter.charAt(i) - 'a'];
		}
		in.close();

		int r = 0;
		Scanner in2 = new Scanner(fin2);
		String word;
		while (!(word = in2.next()).equals(".")) {
//			if(word.equals("hub")) {
//				System.out.println("ooo");
//			}
			r = calcRank(word);
			if (contain(word)) {
				dict.add(word);
				if (r == letterRank && containSameLetters(word)) {
					foundWholeWord = true;
					res.add(word);
				}
			}
		}
	}

	private boolean contain(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (letterFreq[word.charAt(i) - 'a'] == 0)
				return false;
		}
		return true;
	}

	private boolean containSameLetters(String word) {
		int freq[] = new int[26];
		for (int i = 0; i < word.length(); i++) {
			++freq[word.charAt(i) - 'a'];
		}
		for(int i=0; i<word.length(); i++) {
			int j = word.charAt(i) - 'a';
			if(freq[j] != 0) {
				if(freq[j] > letterFreq[j]) 
					return false;
			}
		}
		return true;
	}

	private int calcRank(String word) {
		int r = 0;
		for (int i = 0; i < word.length(); i++) {
			r += rank[word.charAt(i) - 'a'];
		}
		return r;
	}

}
