## **Java世界面试问题**
### **Java基础**

#### _String、StringBuffer、StringBuilder 的区别_

#### _== 和 equals 的区别_
_对象直接比较_ , `对象`==`对象`,代码可以这么写，但是一般会返回false,除非@Override equals method。

#### _简述一下你了解的设计模式。_ 
 三类（创建型[对类的实例化过程的抽象化]、结构型[描述如何将类或对象结合在一起形成更大的结构]、行为型[对在不同的对象之间划分责任和算法的抽象化]）共23种设计模式，包括：<br>
 Abstract Factory（抽象工厂模式），Builder（建造者模式），Factory Method（工厂方法模式），Prototype（原始模型模式），<br>
 Singleton（单例模式）；Facade（门面模式），Adapter（适配器模式），Bridge（桥梁模式），Composite（合成模式），<br>
 Decorator（装饰模式），Flyweight（享元模式），Proxy（代理模式）；Command（命令模式），Interpreter（解释器模式），<br>
 Visitor（访问者模式），Iterator（迭代子模式），Mediator（调停者模式），Memento（备忘录模式），Observer（观察者模式），<br>
 State（状态模式），Strategy（策略模式），Template Method（模板方法模式）， Chain Of Responsibility（责任链模式）。 <br>
`面试被问到关于设计模式的知识时，可以拣最常用的作答，例如：` 
 - 工厂模式：工厂类可以根据条件生成不同的子类实例，这些子类有一个公共的抽象父类并且实现了相同的方法，但是这些方法针对不同的数据进行了不同的操作（多态方法）。<br>
 当得到子类的实例后，开发人员可以调用基类中的方法而不必考虑到底返回的是哪一个子类的实例。 <br>
 - 代理模式：给一个对象提供一个代理对象，并由代理对象控制原对象的引用。实际开发中，按照使用目的的不同，<br>
   代理可以分为：远程代理、虚拟代理、保护代理、Cache代理、防火墙代理、同步化代理、智能引用代理。 
 - 适配器模式：把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起使用的类能够一起工作。<br> 
 - 模板方法模式：提供一个抽象类，将部分逻辑以具体方法或构造器的形式实现，然后声明一些抽象方法来迫使子类实现剩余的逻辑。<br>
   不同的子类可以以不同的方式实现这些抽象方法（多态实现），从而实现不同的业务逻辑。 <br>
 除此之外，还可以讲讲上面提到的门面模式、桥梁模式、单例模式、装潢模式（Collections工具类和I/O系统中都使用装潢模式）等，反正基本原则就是拣自己最熟悉的、用得最多的作答，以免言多必失。

### **Java线程**

#### _线程的几种状态_

#### _线程池的配置参数_

### **Java 虚拟机**
#### _虚拟机怎么调优_

#### _GC安全点(GC会产生停顿)_

#### _内存泄漏_

### **Spring**

#### _AOP的理解_

#### _IOC的理解_

#### _Spring MVC 框架理解(源代码)_

### **IO**
#### [OIO、NIO、AIO差异](http://www.ylzx8.cn/zhuantikaifa/ai/1014032.html)

### **中间件**
#### _MQ的理解_
##### _RocketMQ的原理与实践_
  - 原理与实践：http://www.jianshu.com/p/453c6e7ff81c
  - RocketMQ原理解析：http://blog.csdn.net/column/details/learningrocketmq.html
  - 阿里中间件团队博客：http://jm.taobao.org/2017/01/12/rocketmq-quick-start-in-10-minutes/
  
#### _RPC_
#####  Netty的工作原理
#####  Dubbo的工作原理
https://dubbo.gitbooks.io/dubbo-dev-book/
https://dubbo.gitbooks.io/dubbo-user-book/
https://dubbo.gitbooks.io/dubbo-admin-book/ 
http://dubbo.io

### **缓存**
#### _Redis的理解_
