package thread;

public class Task {
    public void run(String name) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(name + "线程执行了第" + i + "个任务");
        }
    }
}
