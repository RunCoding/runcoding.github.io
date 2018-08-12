## 系统相关
### mac批量删除.DS_Store 文件
```sh
find ~/projects -name ".DS_Store" -exec rm -r "{}" \;
```

### 批量kill node
```sh 
ps -ef | grep node | awk '{print $2}' | xargs kill -9
-- 删除node 及其子进程
```

## idea

