package rekonw;
import java.util.Random;

public class ThreadLearning {
    private static class MyThread extends Thread{
        @Override
        public void run() {
            Random random =new Random();
            //打印出线程名称
            while(true){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        MyThread thread1=new MyThread();
        MyThread thread2=new MyThread();
        MyThread thread3=new MyThread();

        thread1.start();
        thread2.start();
        thread3.start();

        Random random=new Random();

        while(true){
            System.out.println(Thread.currentThread().getName());
            //随机停止10秒 0-9
            try {
                Thread.sleep(10);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
