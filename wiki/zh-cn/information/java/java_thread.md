## 线程

### 创建线程

-   继承Thread类,并重写了 run() 方法。
-   实现Runnable接口,并重写了 run() 方法。

### 线程的5种状态
- 新建状态（New）:线程对象被创建后，就进入了新建状态。eg，Thread = new Thread();
- 就绪状态（Runnable）:又称可执行状态。线程对象创建后调用了start() 方法启动线程
- 运行状态（Running）:线程获取CPU进行执行。只能从就绪状态进入运行状态。
- 阻塞状态（Blocked）:因某种原因放弃CPU使用权，暂时停止运行。原因有三种：<br>
等待阻塞 --> 通过调用线程的wait()或sleep()或join()方法，让线程等待某工作完成。<br>
同步阻塞 --> 线程在获取synchronized同步锁失败（被其他线程占用）<br>
其他阻塞 --> 通过调用线程的发出了I/O请求时，线程会进入同步阻塞状态<br>
- 死亡状态（Dead）:线程执行完了或异常退出了run()方法。结束生命周期<br>
<img src="/dist/java/thread_run.png" >

### 同步操作
- CountDownLatch 通常用来使主线程等待其他线程执行完再执行所用到
- ReentrantLock 并发时，它增加了一些synchronized没有的方法，更方便管理
- Synchronized 简单的同步就用这个吧

### interrupt相关
- 当某个线程调用了interrupt()方法后，相当于给该线程打上了一个中断标志，如果线程正好处于阻塞状态，会直接抛出InterruptedException 异常
- interrupted()方法用来检测“当前线程”的中断状态，且会将中断状态标志清除。
- isInterrupted()方法用来检测“this”的中断状态，且不会改变线程的状态标志。<br>

获取线程终端状态 要用Thread.currentThread().isInterrupted() 比较标准
 

  