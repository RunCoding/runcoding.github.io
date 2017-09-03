## java基础
### 语法

#### 8种基本类型
<table>
  <thead>
    <tr>
      <th colspan="2">类型</th><th>占用空间(字节）</th><th>包装类型</th><th>取值范围</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td rowspan="5">整数</td> <td>byte</td><td>1</td><td>Byte</td><td>-2<sup>7</sup> ~ 2<sup>7</sup>-1，即 -128 ~ 127</td>
    </tr>
    <tr>
      <td>char</td><td>2</td><td>Character</td><td></td>
    </tr>
    <tr>
      <td>short</td><td>2</td><td>Short</td><td></td>
    </tr>
    <tr>
      <td>int</td><td>4</td><td>Integer</td><td></td>
    </tr>
    <tr>
      <td>long</td><td>8</td><td>Long</td><td></td>
    </tr>
    <tr>
      <td rowspan="2">浮点数</td><td>float</td><td>4</td><td>Float</td><td></td>
    </tr>
    <tr>
      <td>double</td><td>8</td><td>Double</td><td></td>
    </tr>
    <tr>
      <td colspan="2">boolean</td><td>?</td><td>Boolean</td><td></td>
    </tr>
  </tbody>
</table>
#### 修饰符
- 访问限定修饰符

|修饰符        | 同类  |同包   |同包(不同包子类)|所有类|
| :----       | :--- | :---- |:---   |:---- | 
| `private`   |   √  |       |       |       | 
| `default(friendly)`   |   √  |    √  |       |       |  
| `protected` |   √  |    √  |    √  |       | 
| `public`    |   √  |    √  |    √  |    √  |

- 类修饰符

  - `abstract`  —— 将一个类声明为抽象类，没有实现的方法，需要子类提供方法实现。
  - `final`     —— 将一个类生命为最终（即非继承类），表示他不能被其他类继承。

- 成员变量修饰符

  - `final` —— 最终修饰符，指定此变量的值不能变。
  - `static`（静态修饰符）   —— 指定变量被所有对象共享，即所有实例都可以使用该变量。变量属于这个类。
  - `transient`（过度修饰符）—— 指定该变量是系统保留，暂无特别作用的临时性变量。
  - `volatile`（易失修饰符） —— 指定该变量可以同时被几个线程控制和修改。

- 方法修饰符 

  - `final` —— 指定该方法不能被重载。
  - `static` —— 指定不需要实例化就可以激活的一个方法。
  - `synchronize` —— 同步修饰符，在多个线程中，该修饰符用于在运行前，对他所属的方法加锁，以防止其他线程的访问，运行结束后解锁。
  - `native` —— 本地修饰符。指定此方法的方法体是用其他语言在程序外部编写的。

##### static 详解
   static -  表示“全局”或者“静态”的意思 ，用来修饰成员变量和成员方法，也可以形成静态static代码块。<br>
   只要这个类被加载，Java虚拟机就能根据类名在运行时数据区的方法区内定找到。   
  - `static变量`<br> `在对象之间共享值时`和`方便访问变量时`
  - `静态方法`：<br> 静态方法可以直接通过类名调用，任何的实例也都可以调用。
  - `static代码块`：<br>
    JVM加载类时会执行这些静态的代码块，如果static代码块有多个，JVM将按照它们在类中出现的先后顺序依次执行它们，每个代码块只会被执行一次。<br>
**static 例子：**<br>

```java
public class Car {

    static {
        System.out.println(" Car static");
    }

    public Car() {
        System.out.println(" Car()");
    }
}

public class Bus extends  Car {

    static {
        System.out.println(" Bus static");
    }

    private static  String  carColor = getCarColor();

    static String getCarColor(){
        System.out.println(" Bus getCarColor()");
        return "red";
    }

    public Bus() {
        System.out.println(" Bus()");
    }

    public static void MyStatic(){
        System.out.println(" MyStatic()");
    }

    public static void main(String[] args) {
        Bus.MyStatic();
        new Bus();
        System.out.println("----------");
        System.out.println(" My carColor = "+carColor);
        carColor = "purple";
        Bus bus = new Bus();
        System.out.println(" My carColor = "+bus.carColor);
    }
}
/***
* 输出结果：
 Car static
 Bus static
 Bus getCarColor()
 MyStatic()
 Car()
 Bus()
----------
 My carColor = red
 Car()
 Bus()
 My carColor = purple
* */
```
 _说明：_  
  1. static 静态代码块和静态变量只执行一次。
  2. 谁在前，先执行谁。<br>
     a. super类先执行，再执行子类。<br>
     b. 静态代码块和静态变量,谁先谁执行。
  3. 静态变量共享一份，注意修改。   
  
#### 面向对象的三大特性
`封装`、`继承`、`多态`
  
#### Java 对象引用

   - `强引用`:类似Object obj = new Object() 这类引用,只要强引用还在,垃圾收集器就永远不会回收被引用的对象;
   - `软引用`:用来描述一些还有用但并未必须的对象。内存溢出异常之前,会把这些对象列入回收范围之内进行二次回收。如果回收后还没有足够的内存这回OOM;
   - `弱引用`:用来描述非必须的对象。若引用关联的对象只能活到下一次垃圾回收之前;
   - `虚引用`:唯一目的对象被回收时收到一个系统通知  

#### JVM如何加载字节码文件？

虚拟机把描述类的数据从Class文件加载到内存，并对数据进行校验、转换解析和初始化，最终形成可被虚拟机直接使用的Java类型，这就是虚拟机的类加载机制。<br>

Java语言中类的加载、连接和初始化过程都是在程序运行期间完成的，领Java具备高度的灵活性。<br>

_类加载的过程_：`加载、连接（验证、准备、解析）、初始化`。<br>

`加载`： 通过一个类的名字获取此类的二进制字节流（PS：不限于从文件中读取）；<br>
        将这个字节流代表的静态存储结构转换为方法区的运行时结构（由具体的虚拟机自己定义）；<br>
       在内存中生成一个java.lang.Class对象，作为方法区这个类的各种数据结构的访问入口。<br>
  
`验证`：文件格式验证、元数据验证（语义分析，类与类的继承关系等）、字节码验证（数据流和控制流分析）、符号引用验证（对类自身以外的信息进行匹配校验）<br>
`准备`：正式为类变量分配内存并设置初始值，这里类变量指的是被static修饰的变量。例外：如果类字段是常量，则在这里会被初始化为表达式指定的值。<br>
`解析`：将常量池内的符号引用替换为直接引用。<br>
   - 符号引用：类似于OS中的逻辑地址；<br>
   - 直接引用：类似于OS中的物理地址，直接指向目标的指针、相对偏移量或一个能间接定位到目标的句柄。<br>
   
`初始化`：真正开始执行类中定义的Java程序代码；初始化用于执行Java类的构造方法。类初始化的过程是不可逆的，如果中间一步出错，则无法执行下一步。<br>


### 集合

### GC
 - <a href='/#/information/java/java_gc?id=jvm%e5%86%85%e5%ad%98%e6%a8%a1%e5%9e%8b'>`JVM内存模型`</a>
 - <a href='/#/information/java/java_gc?id=java%e8%bf%90%e8%a1%8c%e6%97%b6%e6%95%b0%e6%8d%ae%e5%8c%ba'>`Java运行时数据区`</a>
 - <a href='/#/information/java/java_gc?id=%e5%9e%83%e5%9c%be%e6%94%b6%e9%9b%86%e7%ae%97%e6%b3%95'>`垃圾收集算法`</a>
 - <a href='/#/information/java/java_gc?id=%e8%99%9a%e6%8b%9f%e6%9c%ba%e5%8f%82%e6%95%b0%e8%ae%be%e7%bd%ae'>
 `虚拟机参数设置`
 </a>
 - <a href='/#/information/java/java_gc?id=gc%e5%ae%89%e5%85%a8%e7%82%b9gc%e4%bc%9a%e4%ba%a7%e7%94%9f%e5%81%9c%e9%a1%bf'>
 `GC安全点(GC会产生停顿)`
 </a>
### 设计模式

### 线程&并发
 - <a href='/#/information/java/java_thread?id=%e5%88%9b%e5%bb%ba%e7%ba%bf%e7%a8%8b'>`创建线程`</a>
 - <a href='/#/information/java/java_thread?id=%e7%ba%bf%e7%a8%8b%e7%9a%845%e7%a7%8d%e7%8a%b6%e6%80%81'>`线程的5种状态`</a>
 - <a href='/#/information/java/java_thread?id=%e5%90%8c%e6%ad%a5%e6%93%8d%e4%bd%9c'>`同步操作`</a>
 - <a href='/#/information/java/java_thread?id=interrupt%e7%9b%b8%e5%85%b3'>`interrupt相关`</a>
 
### 算法
<a href='https://itimetraveler.github.io/2017/07/18/%E5%85%AB%E5%A4%A7%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95%E6%80%BB%E7%BB%93%E4%B8%8Ejava%E5%AE%9E%E7%8E%B0/'>
**`八大排序算法总结与java实现`**
</a>
