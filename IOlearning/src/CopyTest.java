import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class CopyTest {
@Test
    public void test1(){
    String path="E:\\IDEcode\\file";
    String new_name="demo1.txt";
    String pathname=path+new_name;
    File file=new File(pathname);
    if(!file.exists()){
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建失败");
        }
    }
    else{
        System.out.println("already have");
    }
}

}
