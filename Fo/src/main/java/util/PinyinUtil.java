package util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PinyinUtil {
    private static final String CHINESE_PATTERN="[\\u4E00-\\u9FA5]";

    private static final HanyuPinyinOutputFormat FORMAT = new HanyuPinyinOutputFormat();

    static {
        //设置小写
        FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //不带音调
        FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //V 绿
        FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    public static boolean containsChinese(String name){
        return name.matches(".*"+CHINESE_PATTERN+".*");
    }

    public static String[] get(String name) {
        String[] result = new String[2];
        //拼接字符串
        //StringBuilder线程不安全，效率高 StringBuffer反之
        StringBuilder pinyin = new StringBuilder();//全拼
        StringBuilder pinyinFirst = new StringBuilder();//首字母
        for (char c : name.toCharArray()) {
            try {
                String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c, FORMAT);
                if (pinyins == null || pinyins.length == 0) {
                    //append 添加
                    pinyin.append(c);
                } else {
                    pinyin.append(pinyins[0]);
                    pinyinFirst.append(pinyins[0].charAt(0));
                }
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
                pinyin.append(c);
                pinyinFirst.append(c);
            }
        }
        result[0] = pinyin.toString();
        result[1] = pinyinFirst.toString();
        return result;
    }

    public static String[][] get(String name,boolean fullSpell){
        char[] chars=name.toCharArray();
        String[][]result=new String[chars.length][];
        for(int i=0;i<chars.length;i++){
            try {
                String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(chars[i], FORMAT);
                if(pinyins==null||pinyins.length==0){
                    result[i]= new String[]{String.valueOf(chars[i])};
                } else{//拼音首字母
                    result[i]=unique(pinyins,fullSpell);
                }
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
                result[i]= new String[]{String.valueOf(chars[i])};
            }
        }
        return result;
    }
    //字符串数组去重
    public static String[]unique(String[]array,boolean fullSpell){
        Set<String>set=new HashSet<>();
        for(String s:array){
            if(fullSpell){
                set.add(s);
            }else{
                set.add(String.valueOf(s.charAt(0)));
            }
        }
       return set.toArray(new String[set.size()]);
    }

    public static String[] compose(String[][] pinyinArray){
        if(pinyinArray==null || pinyinArray.length==0){
            return null;
        }else if(pinyinArray.length==1){
            return pinyinArray[0];
        }else {
            for (int i = 1; i < pinyinArray.length; i++) {
                pinyinArray[0] = compose(pinyinArray[0], pinyinArray[i]);
            }
            return pinyinArray[0];
        }
    }
    //合并
    public static String[] compose(String[] pinyins1,String[] pinyins2){
        String[] result=new String[pinyins1.length*pinyins2.length];
        for(int i=0;i<pinyins1.length;i++){
            for(int j=0;j<pinyins2.length;j++){
                result[i*pinyins2.length+j]=pinyins1[i]+pinyins2[j];
            }
        }
        return result;
    }
//自己完成
    public static void main(String[] args) {
        System.out.println("abc".matches("a[bc]c"));
//        System.out.println(Arrays.toString(get("上进一点")));
//        System.out.println(Arrays.toString(get("上进A一点")));
//        //方法套方法
//        System.out.println(Arrays.toString(
//                compose(get("和长和",true))));
//        //先get 再compose
//        System.out.println(Arrays.toString(
//                compose(get("和长和",true))));
    }
}