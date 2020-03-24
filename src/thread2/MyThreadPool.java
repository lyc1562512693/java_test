package thread2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {
    private final int THREAD_POOL_NUM = 5;
    private LinkedList<Task> taskList = new LinkedList<>();
    private List<Worker> workerList = new ArrayList<>();

    public MyThreadPool(){
        initWorker(THREAD_POOL_NUM);
    }
    public MyThreadPool(int num){
        if(num<=THREAD_POOL_NUM){
            initWorker(num);
        }else{
            System.out.println("该线程池最大允许添加的线程数为：" +THREAD_POOL_NUM);
        }
    }
    public void execute(Task task){
        synchronized (taskList) {
            taskList.add(task);
            taskList.notify();
        }
    }
    public void shutdown(){
        synchronized (taskList){
            //taskList.notifyAll();//这条语句写在这里表示阻塞中的任务执行完再结束线程
            for(Worker worker : workerList){
                worker.shutdown();
            }
            taskList.notifyAll();//这条语句写在这里表示阻塞中的任务不执行完就结束线程
        }

        System.out.println("成功结束线程池内的所有线程");
    }
    public void initWorker(int num){
        for(int i = 0;i< num;i++){
            Worker worker = new Worker();
            Thread thread = new Thread(worker,"worker" + num);
            thread.start();
            workerList.add(worker);
        }
    }
    public void addWorker(int num){
        if((workerList.size() + num) > THREAD_POOL_NUM){
            initWorker(THREAD_POOL_NUM - workerList.size());
        }else{
            initWorker(num);
        }
    }
    class Worker implements Runnable{
        private Boolean running = true;
        @Override
        public void run() {
            while(running){
                Task task = null;
                synchronized (taskList){
                    while(taskList.isEmpty()){
                        try {
                            taskList.wait();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        if(!running){
                            break;
                        }
                    }
                    if(!taskList.isEmpty()){
                        task = taskList.removeFirst();
                    }
                }
                if(task != null){
                    task.run();
                }
            }
        }
        public void shutdown(){
            this.running = false;
        }
    }
}
