package me.whiteship.java8to11;

public class CompletableFuture {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        MyThread myThread = new MyThread();
        System.out.println("Hello");


    }

    static class  MyThread extends Thread {
        public void run() {
            System.out.println("hello : " + Thread.currentThread().getName());
        }
    }


}
