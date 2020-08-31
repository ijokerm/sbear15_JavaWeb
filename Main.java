import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String n1=sc.next();
        String n2=sc.next();

        n1=n1.replace("","-");
        String[] first=n1.split("-");
        n2=n2.replace("","-");
        String[] second=n2.split("-");
        for(int i=0;i<first.length;i++){
            for(int j=0;j<second.length;j++){
                if(first[i].equals(second[j])){
                    n1=n1.replace(first[i],second[j]);
                }
            }
        }
    }
}
