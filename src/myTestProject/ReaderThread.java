package myTestProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ReaderThread implements Runnable{

  protected BlockingQueue<String> blockingQueue = null;

  public ReaderThread(BlockingQueue<String> blockingQueue){
    this.blockingQueue = blockingQueue;     
  }

 // @Override
  public void run() {
    BufferedReader br = null;
    try {
    	
    		System.out.println("This is reader--> "+Thread.currentThread().getName());
    	
            br = new BufferedReader(new FileReader(new File("input2.txt")));
            String buffer = null;
            while((buffer=br.readLine())!=null){
                blockingQueue.put(buffer);
                System.out.println("Queue size after put : "+blockingQueue.size());
            }
            blockingQueue.put("EOF");  //When end of file has been reached

         } catch (FileNotFoundException e) {

            e.printStackTrace();
         } catch (IOException e) {

            e.printStackTrace();
         } catch(InterruptedException e) {

        } finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

  }

}
