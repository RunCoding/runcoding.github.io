package com.runcoding.learn.string;

/**
 * @Author: xukai
 * @Email: runcoding@163.com
 * @Created Time: 2017/9/4 21:56
 * @Description
 **/
public class TestString {

    public static void main(String[] args) {

        String s3 = "s";
        String s4 = "s";
        System.out.println(s3==s4); //true

        String s5 = "RunningCoding";
        String s6 = "Running"+"Coding";
        System.out.println(s5==s6); //true

        String s1 = new String("s");
        String s2 = new String("s");
        System.out.println(s1==s2); //false
        System.out.println(s1.intern()==s2.intern()); //true

        /***
         * String s = “s” 是常量池中创建一个对象”s”，所以是true。
         * 而String s = new String（”s”）在堆上面分配内存创建一个String对象，栈放了对象引用。
         */
    }
}
