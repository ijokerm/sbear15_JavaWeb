package rekonw;

public class StringUse {
    public static void main(String[] args) {
       StringBuilder s=new StringBuilder(6);
        System.out.println(s.capacity());
        System.out.println(s.length());
        String s1="reborn";
        System.out.println(s1);
        StringBuilder sb=new StringBuilder(s1);
        System.out.println("---String的一些常用方法");
        System.out.println(sb);
        System.out.println(sb.append("a"));
        System.out.println(s1.substring(3));
        System.out.println(s1.charAt(3));
        System.out.println(s1.compareTo("restart"));
        //结果如果为负整数那么如果String对象按字典顺序排列在参数字符串之前；为零则字符串相 等
        System.out.println(s1.contains("born"));
        System.out.println(s1.toString());
        System.out.println(s1.toUpperCase());
        System.out.println(s1.toCharArray());
  //      System.out.println("人面不知何处去，");
    //    System.out.println("桃花依旧笑春风");



    }
}
