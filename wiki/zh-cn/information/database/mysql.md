## 表结构优化

### 引擎
- 需要用事务用 InnoDB。
- 对查询效率要求高用MyISAM。

### 建表原则
- 推荐使用int(Unix Timestamp)，避免使用datetime和timestamp<br>

  `a.` 以整形存储时间戳，建立索引之后，查询速度快，条件范围搜索可以使用使用between，不能使用mysql提供的时间函数。<br>
  `b.` timestamp(1970-01-01 08:00:01到2038-01-19 11:14:07)

- 避免使用外键<br>  
  
  
  
  