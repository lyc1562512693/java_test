package thread;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class MyThreadPool {

	public static final int DEFAULT_THREAD_POOL_NUM = 5;
	
	public LinkedList<Task> tasks = new LinkedList<>();
	
	public List<Worker> workers = new ArrayList<>();

	public int workerNum;
	
	public MyThreadPool(){
		initWorker(DEFAULT_THREAD_POOL_NUM);
	}
	public MyThreadPool(int num) throws Exception{
		if(num<DEFAULT_THREAD_POOL_NUM){
			initWorker(num);
		}else{
			throw new Exception("�����������Ϸ���Ӧ����0��"+DEFAULT_THREAD_POOL_NUM+"֮��");
		}
	}
	//��ӹ���������
	public void execute(Task task){
		synchronized(tasks){
			tasks.add(task);
			tasks.notify();		
		}
	}
	//�ر������߳�
	public void shutDown(){
		synchronized(tasks){			
			for(Worker w : workers){
				w.shutDown();
			}
			tasks.notifyAll();
		}
		
	}
	//���ָ�������Ĺ������߳�
	public void addWorkers(int num){
		if(workers.size()+num>DEFAULT_THREAD_POOL_NUM){
			num = DEFAULT_THREAD_POOL_NUM;
		}
		initWorker(num);
	}
	public void initWorker(int num){
		for(int i = 1;i<=num;i++){
			Worker w = new Worker();
			Thread t = new Thread(w,"工作者线程"+i);
			t.start();
			workers.add(w);
		}
		System.out.println("当前线程池已初始化了"+num+"个工作者线程,剩余"+(DEFAULT_THREAD_POOL_NUM-num)+"个线程可加入");
	}
	//�ڲ��๤����
	class Worker implements Runnable{
		public boolean running = true;
		public Worker(){
			super();
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(running){	
				if(!running){
					break;
				}
				Task task = null;
				synchronized(tasks){//��Ҫ��ִ��run������һ���ᵽsychronized�����棬��Ȼ�ᵼ�¸��߼���ɵ��̵߳��ˣ�����Ҫ�ѿ��ܻ����̰߳�ȫ����ķŵ�sychronized����			
					while(tasks.isEmpty()){
						try {
							tasks.wait(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(!running){
							break;
						}
					}
					if(!tasks.isEmpty()){
						task = tasks.removeFirst();
						
					}
				}
				if(task!=null){				
					task.run(Thread.currentThread().getName());
				}
			}
		}
		public void shutDown(){
			running = false;
		}		
	}
	
}
