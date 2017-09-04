package com.runcoding.learn.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Java8LambdaList {

    public static void main(String[] args) {
        Java8LambdaList.forEach();
        Java8LambdaList.collectSet();
    }


    public static void forEach(){
        List<String> list = Arrays.asList("1one", "two", "three", "4four");
        list.stream()// 1.得到容器的Steam
            .filter(str -> Character.isDigit(str.charAt(0)))// 2.选出以数字开头的字符串
            .forEach(System.out::println);// 3.输出字符串 forEach(str -> System.out.println(str))
        /** 1one 4four  */
    }


    public static void collectSet(){
        List<String> list = Arrays.asList("1one", "two", "three", "4four");
        Set<String> newSet =  list.stream()// 1.得到容器的Stream
                                   .filter(str -> !Character.isDigit(str.charAt(0)))// 2.选出不以数字开头的字符串
                                   .map(String::toUpperCase)// 3.转换成大写形式
                                   .collect(Collectors.toSet());// 4.生成结果集
        System.out.println(newSet);
        /**[TWO, THREE]*/
    }


}
