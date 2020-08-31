package rekonw;

public class ThreadAdvantage {
    private static final long count =10_000_000;

    public static void main(String[] args) throws InterruptedException{
        //使用并发
        concurrency();

        //使用串行
        serials();
    }
    private static void concurrency() throws InterruptedException{
        long begin=System.nanoTime();
       //利用一个线程来计算a的值
        Thread thread=new Thread(new Runnable(){
            @Override
            public void run() {
                int a=0;
                for(int j=0;j<count;j++){
                    a--;
                }
            }
        });
        thread.start();
       //主线程内计算b的值
        int b=0;
        for(int j=0;j<count;j++){
            b--;
        }
       //等待thread线程运行结束
        thread.join();
       //统计耗时
       long end=System.nanoTime();
       double ms=(end-begin)*1.0/1000/1000;
        System.out.printf("并发：%f 毫秒%n",ms);
    }

    private static void serials(){
        long begin=System.nanoTime();
        int a=0;
        for(int j=0;j<count;j++){
            a--;
        }
        int b=0;
        for(int j=0;j<count;j++){
        b--;
        }
         //统计耗时
        long end=System.nanoTime();
        double ms=(end-begin)*1.0/1000/1000;
        System.out.printf("串行：%f 毫秒%n",ms);
    }
}
