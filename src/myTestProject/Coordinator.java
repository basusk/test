package myTestProject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.Executor;


public class Coordinator {

  public static void main(String[] args) {

    BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
    ExecutorService readExecutors = Executors.newFixedThreadPool(5);
    ExecutorService writeExecutors = Executors.newFixedThreadPool(5);
    ReaderThread read = new ReaderThread(queue);
    WriterThread write = new WriterThread(queue);

    readExecutors.submit(read);
    writeExecutors.submit(write);
    readExecutors.shutdown();
    writeExecutors.shutdown();
    
    try {
		readExecutors.awaitTermination(10, TimeUnit.MINUTES);
		System.out.println("Reading done");
		writeExecutors.awaitTermination(50, TimeUnit.MINUTES);
		System.out.println("Writing done");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
  }
 
}