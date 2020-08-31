import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hill {
    List a;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String clear=sc.nextLine();
        int n=clear.length();

        encry(n,clear);
    }

    public static void encry(int n,String text){
        int[] result=new int[n];//密文对应数组；
        int[] origin=new int[n];//原密码对应数组；
        ArrayList<List>list=new ArrayList<>();//加密矩阵
        for(int i=0;i<n;i++){
            list.add(new Hill(n).a);
        }
        System.out.println(list);//输出加密书组
        for(int t=0;t<n;t++){
            origin[t]=((int)text.charAt(t)-96);
        }
        for(int j=0;j<n;j++){
            for(int k=0;k<n;k++){
                int sum=0;
                for(int i=0;i<list.get(k).size();i++){
                    sum+=((int)list.get(k).size())*origin[i];//矩阵相乘
                }
                result[k]=sum%26;
            }
        }
        for(int q=0;q<n;q++){
            System.out.println((char)(result[q]+96));//结果数组
        }
    }
    public Hill(int n){//数组属性随机赋值
        a=new ArrayList();
        for(int i=0;i<n;i++){
            a.add((int)(Math.random()*27));
        }

    }
}
