
public class NimGame {
    public boolean canWinNim(int n) {
        if (n==1||n==2||n==3) return true;
        boolean a,b,c,d;
        a = true;
        b = true;
        c = true;
        d=false;
        for (long i=4; i<=n; i++) {
            d = !(a&&b&&c);	//ֻҪ��һ��Ӯ�Ŀ��ܾ���
            a=b;
            b=c;
            c=d;
            System.out.println(i + ":" + d);
        }
        return d;
        
    }
    
    public static void main(String[] args) {
    	NimGame game = new NimGame();
    	System.out.println(game.canWinNim(100));
    }
}
