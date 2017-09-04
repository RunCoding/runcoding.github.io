package com.runcoding.learn.java8.lambda;

import com.runcoding.learn.java8.lambda.worker.WorkerInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Java8Lambda {

    public static void main(String[] args) {
        /**List 遍历*/
        Java8Lambda.forEachToLambda();
        /**使用@FunctionalInterface*/
        Java8Lambda.workerToLambda();
        /**线程*/
        Java8Lambda.threadToLambda();
    }

    public static void forEachToLambda(){
        System.out.println("forEachToLambda(),使用lambda以前写发");
        List<Integer> list = Arrays.asList(1,6);
        for (Integer n : list) {
            System.out.println(n);
        }
        System.out.println("forEachToLambda(),使用 -> 的 Lambda 表达式");
        list.forEach(n -> System.out.println(n));

        System.out.println("forEachToLambda(),使用 :: 的 Lambda 表达式\n");
        list.forEach(System.out::println);

        System.out.print("forEachToLambda() 输出大于 5 的数字：");
        evaluate(list, (n) -> n > 5);

        System.out.print("forEachToLambda() Stream ");
        list.stream().map((x) -> x*x).forEach(System.out::println);


        System.out.println("forEachToLambda() printKeyAndValue \n ");
        Consumer<Integer> printKeyAndValue =   Java8Lambda::printKeyAndValue;
        list.forEach(printKeyAndValue);

    }


    private static void printKeyAndValue(Integer integer){
        System.out.println("forEachToLambda() Integer: " + integer+"\n");
    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.print(n + " ");
            }
        }
        System.out.println();
    }

    public static void workerToLambda(){
        WorkerInterface work = new WorkerInterface() {
            @Override
            public void doWork() {
                System.out.println("workerToLambda() 通过匿名内部类调用");
            }
        };
        work.doWork();
        System.out.println("workerToLambda(),使用 -> 的 Lambda 表达式");
        ((WorkerInterface) () -> System.out.println("通过 Lambda 表达式调用\n")).doWork();

    }

    public static  void threadToLambda(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadToLambda() Thread run() ");
            }
        }).start();

        new Thread(() -> System.out.println("threadToLambda() Lambda Thread run() ")).start();

    }


}
