###  HBase 的应用场景
  致谢：https://www.ibm.com/developerworks/cn/analytics/library/ba-cn-bigdata-hbase/index.html
- HBase 则非常适合用来进行大数据的实时查询，例如 Facebook 用 HBase 进行消息和实时的分析。
- HBase 的部署来说，需要 Zookeeper 的帮助（Zookeeper，是一个用来进行分布式协调的服务，这些服务包括配置服务，维护元信息和命名空间服务）。
- HBase 本身只提供了 Java 的 API 接口，并不直接支持 SQL 的语句查询。<br>
  如果想要在 HBase 上使用 SQL，则需要联合使用 Apache Phonenix，或者联合使用 Hive 和 HBase。<br>
  如果集成使用 Hive 查询 HBase 的数据，则无法绕过 MapReduce，那么实时性还是有一定的损失。<br>
  Phoenix 加 HBase 的组合则不经过 MapReduce 的框架，因此当使用 Phoneix 加 HBase 的组成，实时性上会优于 Hive 加 HBase 的组合


