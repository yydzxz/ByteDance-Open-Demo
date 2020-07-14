# ByteDance-Open-Demo
该demo为ByteDanceOpen SDK用法示例

### 申请账号
根据[字节跳动开放平台文档](https://bytedance.feishu.cn/docs/doccnYmtnRy6APhKiTfYgW#)指引，申请好账号后，将自己的appid等数据填入
application-dev.yml
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200714-122557%402x.png)

### 启动redis
为了方便使用，项目中提供了一个默认的redis.conf, 只修改了两个配置
```bash
# 把protected-mode改为了no
protected-mode no
# 注释了bind
# bind 127.0.0.1 ::1
```
本项目使用的redis客户端是redisson，可以在application-dev.yml指定redis的配置文件。如果想要使用jedis，可以自己实现一个IByteDanceRedisOps
最后启动项目, 接着需要等待字节跳动服务器将ticket推送过来后，才能进行后续的授权等api调用。如果一直没有推送，请确认推送地址是否配置正确
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200714-130942%402x.png)
