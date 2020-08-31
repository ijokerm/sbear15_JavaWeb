package rekonw;


public class ThreadInterrupt {
    private static class  MyRunnable implements Runnable{
        //为何要用volatile锁这个isQuit
        private  volatile boolean isQuit=false;
        //通过共享的标记isQuit来进行沟通
        @Override
        public void run() {
            while(!isQuit){
                System.out.println(Thread.currentThread().getName()+":别管我，我忙着转账呢");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName()+"差点误了大事");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        MyRunnable target=new MyRunnable();
        Thread thread=new Thread(target,"李四");
        System.out.println(Thread.currentThread().getName()+"李四开始转账");
        thread.start();
        Thread.sleep(10*1000);
        System.out.println(Thread.currentThread().getName()+"对方是骗子，李四终止交易");
        target.isQuit=true;
    }
}
