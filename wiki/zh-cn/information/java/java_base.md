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
  
### 集合

### GC

### 设计模式

### 线程&并发

### 算法