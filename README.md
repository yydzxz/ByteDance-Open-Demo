# ByteDance-Open-Demo
- 该demo为[ByteDanceOpen SDK](https://github.com/yydzxz/ByteDanceOpen)用法示例

### 申请账号
- 根据[字节跳动开放平台文档](https://bytedance.feishu.cn/docs/doccnYmtnRy6APhKiTfYgW#)指引，申请好账号后，将自己的appid等数据填入
`application-dev.yml`
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200714-122557%402x.png)

### 启动redis
- `access_token`等数据都是保存在redis中，所以需要一个redis服务

- 为了方便使用，项目中提供了一个默认的`redis.conf`, 只修改了两个配置:

  ```bash
  # 把protected-mode改为了no
  protected-mode no
  # 注释了bind
  # bind 127.0.0.1 ::1
  ```
- 本项目使用的redis客户端是[redisson](https://github.com/redisson/redisson)，可以在`application-dev.yml`指定redis的配置文件。
#### 
- 如果想要使用jedis，可以自己实现一个`IByteDanceRedisOps`
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200715-144937%402x.png)


### 启动项目
- 启动项目后需要等待字节跳动服务器将ticket推送过来后（一般10分钟以内），才能进行后续的授权等api调用。如果一直没有推送，请确认推送地址是否配置正确
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200714-130942%402x.png)

### 接口测试
- 请确定ticket已经推送过来了。如果ticket推送过来，日志中会打印"MsgTypeTicketHandler 开始处理消息: xxxx"

#### 模版管理
[获取第三方应用的草稿](http://127.0.0.1:8080/bytedance/template/draft/list)
[获取第三方应用的所有模版](http://127.0.0.1:8080/bytedance/template/list)
