## 性能分析

## jps （输出jvm虚拟机进程信息）
-q （输出进程Id）
-m （main函数启动参数）
-l（输出主类的全名，如jar)
```bash
$ jps -l
1965 com.runcoding.Application
```
-v（输出虚拟机进程启动时所带的Jvm参数）
```bash
1964 Launcher -Xmx700m -Djava.awt.headless=true -Djava.endorsed.dirs="" -Djdt.compiler.useSingleThread=true -Dpreload.project.path=/Users/runningghost/projects/soft_develop/github/runcoding.github.io -Dpreload.config.path=/Users/runningghost/Library/Preferences/IntelliJIdea2017.2/options -Dcompile.parallel=false -Drebuild.on.dependency.change=true -Djava.net.preferIPv4Stack=true -Dio.netty.initialSeedUniquifier=-8553886555547395819 -Dfile.encoding=UTF-8 -Djps.file.types.component.name=FileTypeManager -Duser.language=zh -Duser.country=CN -Didea.paths.selector=IntelliJIdea2017.2 -Didea.home.path=/Applications/IntelliJ IDEA.app/Contents -Didea.config.path=/Users/runningghost/Library/Preferences/IntelliJIdea2017.2 -Didea.plugins.path=/Users/runningghost/Library/Application Support/IntelliJIdea2017.2 -Djps.log.dir=/Users/runningghost/Library/Logs/IntelliJIdea2017.2/build-log -Djps.fallback.jdk.home=/Applications/IntelliJ IDEA.app/Contents/jdk/Contents/Home/jre -Djps.fallback.jdk.version=1.8.0_152-release -Dio.netty.noUnsafe=true -Djava.io.tmpdir=/Users/runningghost/Lib
```

## jstat（虚拟机运行监控）
例如:（jstat -gcutil ）gc的统计新老代
```bash
$ jstat -gcutil 1965
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT   
 95.22   0.00  99.08  36.97  97.84  95.59     18    0.285     2    0.104    0.389
 /** YGC (Minor gc Young GC) 18次，总耗时 0.285s*/
 /** FGC (Full GC) 2次，总耗时 0.104 s*/
 /* GCt 总耗时*/
```

## jinfo (java 配置信息工具)
```bash
jinfo 1965
Attaching to process ID 1965, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.111-b14
Java System Properties:

java.runtime.name = Java(TM) SE Runtime Environment
java.vm.version = 25.111-b14
sun.boot.library.path = /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib
gopherProxySet = false
java.vendor.url = http://java.oracle.com/
java.vm.vendor = Oracle Corporation
path.separator = :
java.rmi.server.randomIDs = true
file.encoding.pkg = sun.io
java.vm.name = Java HotSpot(TM) 64-Bit Server VM
sun.os.patch.level = unknown
sun.java.launcher = SUN_STANDARD
user.country = CN
user.dir = /Users/runningghost/projects/soft_develop/github/runcoding.github.io
java.vm.specification.name = Java Virtual Machine Specification
PID = 1965
java.runtime.version = 1.8.0_111-b14
java.awt.graphicsenv = sun.awt.CGraphicsEnvironment
os.arch = x86_64
java.endorsed.dirs = /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/endorsed
org.jboss.logging.provider = slf4j
visualvm.id = 11145166608052
line.separator = 

java.io.tmpdir = /var/folders/8h/pjxlhbtj6215h5b440f4qjkh0000gn/T/
java.vm.specification.vendor = Oracle Corporation
os.name = Mac OS X
sun.jnu.encoding = UTF-8
java.library.path = /Users/runningghost/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.
spring.beaninfo.ignore = true
java.specification.name = Java Platform API Specification
java.class.version = 52.0
sun.management.compiler = HotSpot 64-Bit Tiered Compilers
os.version = 10.13.3
btrace.port = 55847
user.home = /Users/runningghost
user.timezone = Asia/Shanghai
java.awt.printerjob = sun.lwawt.macosx.CPrinterJob
file.encoding = UTF-8
java.specification.version = 1.8
user.name = runningghost
java.vm.specification.version = 1.8
sun.arch.data.model = 64
sun.java.command = com.runcoding.Application
java.home = /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre
user.language = zh
java.specification.vendor = Oracle Corporation
user.language.format = en
awt.toolkit = sun.lwawt.macosx.LWCToolkit
java.vm.info = mixed mode
java.version = 1.8.0_111
java.ext.dirs = /Users/runningghost/Library/Java/Extensions:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/ext:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java
sun.boot.class.path = /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/sunrsasign.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/classes
java.awt.headless = true
java.vendor = Oracle Corporation
file.separator = /
java.vendor.url.bug = http://bugreport.sun.com/bugreport/
sun.io.unicode.encoding = UnicodeBig
sun.cpu.endian = little
sun.cpu.isalist = 

VM Flags:
Non-default VM flags: -XX:CICompilerCount=3 -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:MaxNewSize=715653120 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=44564480 -XX:OldSize=89653248 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseParallelGC 
Command line:  -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:55835,suspend=y,server=n -Dvisualvm.id=11147471070820 -Dvisualvm.id=11145166608052 -Dfile.encoding=UTF-8
```

## jmap(生产堆快照文件)
```bash
$ jmap -dump:format=b,file=/Users/runningghost/Downloads/rundump.bin 1965
Dumping heap to /Users/runningghost/Downloads/rundump.bin ...
Heap dump file created

```

## jstack（用来生成虚拟机线程快照信息，分析线程死锁停顿等）
例如: jstack 2419 
-F （用来生成不被响应时强制生成线程的快照）
-m （堆栈信息）
-l （打印锁信息）
```bash
>$ jstack -l 1965
2018-03-18 16:45:22
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.111-b14 mixed mode):

"Thread-38" #64 daemon prio=9 os_prio=31 tid=0x00007fc8c7ab7800 nid=0x9c23 waiting on condition [0x000070000eb73000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
"Thread-13" #33 daemon prio=9 os_prio=31 tid=0x00007fc8c7809000 nid=0x9e03 runnable [0x000070000f89a000]
   java.lang.Thread.State: RUNNABLE
	at java.net.PlainSocketImpl.socketAccept(Native Method)
```
 
## 类加载冲突（在JVM 启动时加上-verbose:class 可以看到class是从哪个jar中引进来的）

### BTrace
 @see http://calvin1978.blogcn.com/articles/btrace1.html
```java
/* BTrace Script Template */
import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
    
    @TLS 
    static long beginTime;  
    
    @OnMethod(clazz="com.runcoding.controller.OrderController",method="createOrder",location=@Location(Kind.RETURN))
	public static void func(com.runcoding.model.po.order.OrderPo order , @Return com.runcoding.model.po.order.OrderPo result
                        ,@Duration long durationL ){
        println(strcat(strcat("方法执行时间s:",str(timeMillis()-beginTime)),"ms"));  
        println(strcat("方法执行时间:", str(durationL)));
        println(strcat("当前执行线程名称:", Threads.name(currentThread())));
        println(strcat("方法请求:", str(order)));
        println(strcat("方法结果:", str(result)));
    }
}
```