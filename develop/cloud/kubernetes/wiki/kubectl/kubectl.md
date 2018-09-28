## 使用Kubernetes发布应用
### create

```sh
$ kubectl create -f nginx-deployment.yaml
deployment.apps/nginx-deployment created
```
[yaml文件](yaml/nginx-deployment.yaml ':include :type=code')

查看运行结果

### get
```sh
$ kubectl get pods -l app=nginx

-l参数，即获取所有匹配 app: nginx 标签的 Pod
NAME                                     READY     STATUS    RESTARTS   AGE
nginx-deployment-75675f5897-gqhl8        1/1       Running   0          10m
nginx-deployment-75675f5897-tr4cx        1/1       Running   0          10m
可以看到有连个节点在执行
```

### describe
 使用 kubectl describe 命令，查看运行细节

```sh
$ kubectl describe pod nginx-deployment-75675f5897-gqhl8

Name:           nginx-deployment-75675f5897-gqhl8
Namespace:      default
Node:           docker-for-desktop/192.168.65.3
Start Time:     Fri, 28 Sep 2018 21:19:57 +0800
Labels:         app=nginx
                pod-template-hash=3123191453
Annotations:    <none>
Status:         Running
IP:             10.1.0.77
Controlled By:  ReplicaSet/nginx-deployment-75675f5897


Events:
  Type    Reason                 Age   From                         Message
  ----    ------                 ----  ----                         -------
  Normal  Scheduled              19m   default-scheduler            Successfully assigned nginx-deployment-75675f5897-gqhl8 to docker-for-desktop
  Normal  SuccessfulMountVolume  19m   kubelet, docker-for-desktop  MountVolume.SetUp succeeded for volume "default-token-5g96z"
  Normal  Pulling                19m   kubelet, docker-for-desktop  pulling image "nginx:1.7.9"
  Normal  Pulled                 19m   kubelet, docker-for-desktop  Successfully pulled image "nginx:1.7.9"
  Normal  Created                19m   kubelet, docker-for-desktop  Created container
  Normal  Started                19m   kubelet, docker-for-desktop  Started container
在 Kubernetes 执行的过程中，对 API 对象的所有重要操作，都会被记录在这个对象的 Events 里，并且显示在 kubectl describe 指令返回的结果中。
```

### replace
使用replace，更新yaml

```sh
$ kubectl replace -f nginx-deployment.yaml
deployment.apps/nginx-deployment replaced

```

### apply
推荐使用apply来部署，忽略使create还是replace。
```sh
$ kubectl apply  -f nginx-deployment.yaml
deployment.apps/nginx-deployment configured

```

### exec
使用 kubectl exec 指令，进入到这个 Pod 当中（即容器的 Namespace 中）查看这个 Volume 目录
```sh
$ kubectl exec -it nginx-deployment-7b5fff6d7d-zx7xm /bin/bash
root@nginx-deployment-7b5fff6d7d-zx7xm:/usr/share/nginx/html# ls
index.html #拷贝到宿主机的/Users/xukai/Downloads/k8s目录下，在容器中就能看到

```

### delete
使用delete 从Kubernetes集群中删除部署的这个Nginx Deployment

```sh
$ kubectl delete  -f nginx-deployment.yaml
deployment.apps "nginx-deployment" deleted

``