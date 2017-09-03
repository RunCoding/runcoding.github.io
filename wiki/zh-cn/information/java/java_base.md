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

### 集合

### GC

### 设计模式

### 线程&并发

### 算法