import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String string=scanner.nextLine();
        String result="";
        int count=0;
        char []arr=string.toCharArray();
        for(int i=0;i<arr.length;i++){
            if('0'<=arr[i]&&'9'>=arr[i]){
                count=1;
                int index=i;
                for(int j=i+1;j<arr.length;j++){

                    if('0'<=arr[j] && '9'>= arr[j]){
                        count++;
                        index =j;
                    }else {
                        break;
                    }
                }if(count>result.length()){
                    result = string.substring(i,index+1);
                }
            }else {
                continue;
            }

        }
        System.out.println(result);
    }
}
