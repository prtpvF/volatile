import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        myThread.shutdown();

}
/* основная проблема которая может возникнуть при работе двух потоков с одной и более переменной
* когеренция кешей
* Её суть заключается в том, что читающий поток может закешировать переменную, тогда значения в ней обновляться не будут
* */

}
class MyThread extends Thread {
    private volatile boolean running = true;   // volatile гарантирует когерентность кешей(переменная не кешируется для каждого ядра)
    public void run(){      //volatile нужно применять при ситуации, когда два потока работаю с одними и теми же переменными(пишут и читают)
        while(running){
            System.out.println("hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void shutdown(){
        this.running=false;
    }
}