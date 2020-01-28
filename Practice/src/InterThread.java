
class Q
{
	int num;
	boolean vst=false;
	public synchronized void put(int num) {
		while(vst) {
			try{wait();}catch(Exception e) {}
		}
		System.out.println("Put : "+num);
		this.num=num;
		vst=true;
		notify();
	}
	public synchronized void get() {
		while(!vst) {
			try{wait();}catch(Exception e) {}
		}
		System.out.println("Get : "+ num);
		vst=false;
		notify();
	}
}
class Producer implements Runnable{
	 Q q;

	public Producer(Q q) {
		this.q = q;
		Thread t =new Thread(this,"producer");
		t.start();
	}
	public void run() {
		int i=0;
		while(true) {
			q.put(i++);
			try {Thread.sleep(10);}catch(Exception e) {}
		}
	} 
}
class Consumer implements Runnable{
	 Q q;

	public Consumer(Q q) {
		this.q = q;
		Thread t =new Thread(this,"consumer");
		t.start();
	}
	public void run() {
		while(true) {
			q.get();
			try {Thread.sleep(1000);}catch(Exception e) {}
		}
	}
	 
}
public class InterThread {

	public static void main(String[] args) {
		Q q= new Q();
		new Producer(q);
		new Consumer(q);

	}

}
