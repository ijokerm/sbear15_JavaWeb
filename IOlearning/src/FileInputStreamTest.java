import org.junit.Test;

import java.io.*;
import java.io.IOException;

public class FileInputStreamTest {

    @Test
    public void test1(){
        try {
            System.out.println(System.getProperty("user.dir"));
            File f = new File("src/test1.txt");
            System.out.println(f.exists());
            FileInputStream fis = new FileInputStream(f);
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len=fis.read(bytes)) != -1){
                String s = new String(bytes, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        try {
            System.out.println(System.getProperty("user.dir"));
            File f = new File("src/test1.txt");
            System.out.println(f.exists());
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len=bis.read(bytes)) != -1){
                String s = new String(bytes, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        try {
            System.out.println(System.getProperty("user.dir"));
            File f = new File("src/test1.txt");
            System.out.println(f.exists());
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s;
            while((s=br.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        try {
            System.out.println(System.getProperty("user.dir"));
            File f = new File("src/test1.txt");
            System.out.println(f.exists());
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("1=123\n");
            bw.write("唐诗");
            bw.newLine();
            bw.write("宋词");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
