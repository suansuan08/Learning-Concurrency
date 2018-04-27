package com.javaman.concurrency.interupt;

/**
 * @author pengzhe
 * @date 2018/4/8 22:13
 * @description
 */

public class InterruptedExample {

    public static void main(String[] args) {
        InterruptedExample interruptedExample = new InterruptedExample();
        interruptedExample.start();
    }

    public void start() {
        MyThread myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(3000);
            myThread.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class MyThread extends Thread {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("test");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupt");
                    //抛出InterruptedException后中断标志被清除，标准做法是再次调用interrupt恢复中断
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("stop");
        }

        public void cancel() {
           interrupt();
        }
    }
}


