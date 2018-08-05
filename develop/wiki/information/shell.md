## 常用命令

### 批量杀死进程
```
 /**批量kill nodejs 进程*/
 ps -ef | grep node | awk '{print $2}' | xargs kill -9
```