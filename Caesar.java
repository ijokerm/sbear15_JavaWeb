import java.util.Scanner;

public class Caesar {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
            String res=encrypt(str,3);
            System.out.println("加密后密文："+res);
            String out=decrypt(str,3);
            System.out.println("将该密文解密后明文:"+out);
    }

    //加密
    public static char encrypt(char ch,int k){
        //ch 字母；k密钥
        int code;
        int c=ch-'a';
        if(c+k>'z'){
            code=c+k-26;
        }else{
            code=c+k;
        }
        return (char)(code%26+'a');
    }
    public static String encrypt(String str,int k){
        char[]chs=str.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char c:chs){
            sb.append(encrypt(c,k));
        }
        return sb.toString();
    }
    //解密
    public static char decrypt(char ch,int k){
        int code;
        int c=ch-'a';
        if(c-k<'a'){
            code=c-k+26;
        }else{
            code=c-k;
        }
        return (char)(code%26+'a');
    }
    public static String decrypt(String str,int k){
        char[]chs=str.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char c:chs){
            sb.append(decrypt(c,k));
        }
        return sb.toString();
    }
}
