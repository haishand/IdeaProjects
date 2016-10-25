/**
 * Created by wang-tong on 16/10/6.
 */
public class MergeSort {
    public static void main(String[] args) {
        new MergeSort().run();
    }

    int A[] = {5,4,1,8,7,2,6,3};
//    int A[] = {5, 4};

    void merge(int p, int q, int r) {
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

        i=0; j=0;
        for(k=p; k<=r; k++) {
            if(L[i]<R[j]) {
                A[k] = L[i];
                ++i;
            }else {
                A[k] = R[j];
                ++j;
            }
        }
    }

    void sort(int low, int high) {
        if(low<high) {
            int mid = (low+high)/2;
            sort(low, mid);
            sort(mid+1, high);
            merge(low, mid, high);
        }
    }

    void print() {
        for(int i=0; i<A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    private void run() {
        print();
        sort(0, A.length-1);
        print();
    }
}
