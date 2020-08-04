package thread2;

public class ThreadPoolMain2 {
    public static void main(String[] args) {
        Task t1 = new Task();
        Task t2 = new Task();
        MyThreadPool myThreadPool = new MyThreadPool(3);
        myThreadPool.execute(t1);
        myThreadPool.execute(t2);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        myThreadPool.shutdown();
    }
}
