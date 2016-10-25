import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by wang-tong on 16/10/6.
 */
public class CountInversions {
    BufferedReader fin;

    public static void main(String[] args) {
        new CountInversions().run();
    }

    int A[];
    
    public CountInversions() {
        A = new int[100000];
        try {
            fin = new BufferedReader(new FileReader("IntegerArray.txt"));
            Scanner in = new Scanner(fin);
            int i = 0;
            while(in.hasNext()) {
                A[i++] = in.nextInt();
            }
//            System.out.println(i);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    
//    int A[] = {5,4,1,8,7,2,6,3};
//    int A[] = {5, 4, 3, 2, 1};

    long countSplitInv(int p, int q, int r) {
        long res = 0;
        int n1 = q-p+1;
        int n2 = r-q;
        int L[] = new int[n1+2];
        int R[] = new int[n2+2];
        int i,j,k;

        for(i=0; i<n1; i++) {
            L[i] = A[p+i];
        }
        for(j=0; j<n2; j++) {
            R[j] = A[q+j+1];
        }
        L[i] = Integer.MAX_VALUE;
        R[j] = Integer.MAX_VALUE;

        i=0; j=0; res = 0;
        for(k=p; k<=r; k++) {
            if(L[i]<R[j]) {
                A[k] = L[i];
                ++i;
            }else {
                A[k] = R[j];
                ++j;
                res += n1-i;
            }
        }
        return res;
    }

    long countInv(int low, int high) {
        if(low<high) {
            int mid = (low+high)/2;
            long x = countInv(low, mid);
            long y = countInv(mid+1, high);
            long z = countSplitInv(low, mid, high);
            return x+y+z;
        }
        return 0;
    }


    private void run() {
        System.out.println(countInv(0, A.length-1));
    }
}
