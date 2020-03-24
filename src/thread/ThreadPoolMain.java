package thread;

public class ThreadPoolMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Task task1 = new Task();
		Task task2 = new Task();
		MyThreadPool m = null;
		try {
			m = new MyThreadPool(3);
		}catch(Exception e){
			System.out.println("您设定的线程池大小超过允许的最大大小");
			e.printStackTrace();
		}
		m.execute(task1);
		m.execute(task2);
		try {
			Thread.sleep(4000);
		}catch(Exception e){
			e.printStackTrace();
		}
		m.shutDown();
		
	}

}
