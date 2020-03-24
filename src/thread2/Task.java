package thread2;

public class Task {
    public void run(){
        for(int i = 0;i < 10; i++){
            System.out.println(Thread.currentThread().getName() + "线程执行了第" + i + "个任务");
        }
    }
}
