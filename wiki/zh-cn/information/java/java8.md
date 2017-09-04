## Lambda表达式使用
```java
public class Java8Lambda {
 
     public static void main(String[] args) {
            List<Apple> inventory = Arrays.asList( new Apple(50, "green"),new Apple(100, "red"));
            inventory.forEach(Java8Lambda::getApple);
            /**
             apple = [green]
             apple = [red]
             * */
     }

    
    public static void getApple(Apple apple) {
            System.out.println("apple = [" + apple.getColor() + "]");
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

}
 class Apple {

    private int weight = 0;

    private String color = "";

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

@FunctionalInterface
public interface WorkerInterface {

    void doWork();

    /**不能存在两个方法*/
    //void doMoreWork();
}
```
## Java8 Date中的使用
```java
public class Java8DateUtils {

    private static Logger  logger = LoggerFactory.getLogger(Java8DateUtils.class);

    public static void main(String[] args) {
        /**显示日期*/
        Java8DateUtils.localDate();
        /**显示时间*/
        Java8DateUtils.localDateTime();
        /**某一个时间点的时间戳*/
        Java8DateUtils.instant();
    }

    /**显示日期*/
    public static void localDate(){
        logger.info("-----start localDate() ----");
        LocalDate date = LocalDate.now();
        logger.info("默认格式日期：{}",date);//2017-09-04
        logger.info("BASIC_ISO_DATE格式日期：{}",date.format(DateTimeFormatter.BASIC_ISO_DATE));//20170904
        logger.info("ISO_LOCAL_DATE格式日期：{}",date.format(DateTimeFormatter.ISO_LOCAL_DATE));//2017-09-04

        logger.info("今天的开始日期：{}",date.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));//2017-09-04 00:00:00
        logger.info("-----end localDate() ----\n");
    }

    /**显示时间*/
    public static  void localDateTime(){
        logger.info("-----start localDateTime() ----");
        LocalDateTime date = LocalDateTime.now();
        logger.info("默认格式时间：{}",date);//2017-09-04T14:09:29.288
        logger.info("BASIC_ISO_DATE格式日期：{}",date.format(DateTimeFormatter.BASIC_ISO_DATE));//20170904
        logger.info("ISO_LOCAL_DATE格式日期：{}",date.format(DateTimeFormatter.ISO_LOCAL_DATE));//2017-09-04
        logger.info("yyyy-MM-dd HH:mm:ss格式日期：{}",date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));//2017-09-04 14:09:29
        logger.info("-----end localDateTime() ----\n");
    }

    /**某一个时间点的时间戳*/
    public static void  instant(){
        logger.info("-----start instant() ----");
        Instant date = Instant.now();
        logger.info("默认格式时间：{}",date.toString());//2017-09-04T06:09:29.289Z
        logger.info("当前时间加5s：{}",date.plus(5, ChronoUnit.SECONDS));//2017-09-04T06:09:34.289Z
        logger.info("减去1000毫秒数：{}",date.minusMillis(1000));//2017-09-04T06:09:28.289Z
        logger.info("是否在当前时间之前：{}",date.isBefore(Instant.now()));//true(可以会相同)
        logger.info("是否在当前时间之后：{}",date.isAfter(Instant.now())); //false
        logger.info("compareTo比较大小（当前Instant大，返回正数；相等，返回0；否则，返回负数）：{}",Instant.now().compareTo(date));//3000000
        logger.info("从1970-01-01 00:00:00开始的秒数：{}",date.getEpochSecond());//1504505369
        logger.info("从1970-01-01 00:00:00开始的毫秒数：{}",date.toEpochMilli());//1504505369289
        logger.info("-----end instant() ----\n");
    }

}

```

