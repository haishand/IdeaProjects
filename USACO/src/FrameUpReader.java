import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class FrameUpReader {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("frameup.txt"));
			
			String line;
			String A = "final String[] A = {";
			String B = "final String[] B = {";
			String C = "final String[] C = {";
			String D = "final String[] D = {";
			String E = "final String[] E = {";
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "   ");
				String a = st.nextToken();
				String b = st.nextToken();
				String c = st.nextToken();
				String d = st.nextToken();
				String e = st.nextToken();
				A += "\"" + a + "\",";
				B += "\"" + b + "\",";
				C += "\"" + c + "\",";
				D += "\"" + d + "\",";
				E += "\"" + e + "\",";
			}
			System.out.println(A);
			System.out.println(B);
			System.out.println(C);
			System.out.println(D);
			System.out.println(E);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
