cd ../
## 删除旧文档
rm -rf wiki
rm -rf learn-java
## 复制新文档
cp -r ../../wiki  ./
cp -r ../../learn-java  ./

## 构建相关依赖
gitbook install
## 编译生成文件
make

## 将编译完,文件拷贝到gitbook 仓库
cp -r  _book/.  ../../../runcodingbook

## 删除零时文件
rm -rf _book
rm -rf wiki
## 提交文档
cd ../../../runcodingbook && git add . && git commit -a -m 'Update doc' && git push