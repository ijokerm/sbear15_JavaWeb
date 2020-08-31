import java.util.ArrayList;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int N=sc.nextInt();
        ArrayList as=new ArrayList();
        int x=0;
        for(int i=0;i<N;i++){
            as.add(sc.nextInt());
            x^=as.indexOf(i);
        }
        System.out.println(N);
        System.out.println(as);

        int index=0;
        for(int i=0;i<32;i++){
            if(((x>>i)&1)==1){
                index=i;
                break;
            }
        }
        int n1=0;
        int n2=0;
        for(int i=0;i<N;i++){
            if(((as.indexOf(i)>>index)&1)==1){
                n1=as.indexOf(i);
            }else{
                n2=as.indexOf(i);
            }
        }
        if(n1<n2){
            System.out.println(n1+" "+n2);
        }else{
            System.out.println(n2+" "+n1);
        }
    }

}
