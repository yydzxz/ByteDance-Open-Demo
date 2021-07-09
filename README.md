# ByteDance-Open-Demo
- [字节跳动小程序第三方接入文档](https://microapp.bytedance.com/docs/zh-CN/mini-app/thirdparty/API/update)
- 该demo为[ByteDanceOpen SDK](https://github.com/yydzxz/ByteDanceOpen)用法示例. 大部分参数和url跟字节跳动open api保持相同, 方便对照文档快速理解使用, 剩余的按照RESTful命名风格
- 提供了docker启动方式，包含redis和ngrok服务
- 当前页面上的图片如果裂了，可以[尝试修改host](https://github.com/labuladong/fucking-algorithm/issues/271#issuecomment-620470589),如果还不行那么就需要科学上网o(￣ヘ￣o＃)，或者clone后查看images/gifs文件夹下对应的图片
- 如果觉得用起来还不错╰(●’◡’●)╮，希望能够**star🌟**支持一下~~~
- 如果有接口没有及时更新，可以给我提issue或者PR，着急的话也可以通过sdk暴露的接口自己实现(详情见下面的[其他注意事项](#其他注意事项))。

## 申请账号
- 先在[字节跳动小程序管理后台](https://microapp.bytedance.com)注册一个账号。
- [字节跳动小程序管理后台](https://microapp.bytedance.com)账号自带一个[字节跳动第三方平台](https://open.microapp.bytedance.com)账号（登录小程序管理后台后，进入第三方平台直接就是登录状态），创建一个第三平台后，将第三方平台的相关数据填入
`application-dev.yml`
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200714-122557%402x.png?raw=true)
    
## 配置ip白名单
  - 把[本机外网ip](https://www.ipaddress.com/)配置到[字节跳动第三方平台](https://open.microapp.bytedance.com)的白名单
  - ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200717-210903%402x.png?raw=true)

## 启动项目
### 方式1: docker启动方式
#### 1. 进入项目目录
  ```bash
    cd /yourpath/bytedance-open-demo
  ```
#### 2. 打包jar
  ```bash
    mvn clean install -DskipTests
  ```
#### 3. 在**docker-compose.yml**中填入第三方平台的信息
#### 4. 通过docker启动所需服务: redis,ngrok以及本项目
  ```bash
    docker-compose up --build
  ```
#### 5. 获取本机外网地址: 查询ngrok提供的外网地址
  
  ##### Mac
  ```bash
  curl $(docker port bytedance-open-demo_ngrok_1 4040)/api/tunnels
  ```
  ##### Windows
   - [点击查看外网地址](http://127.0.0.1:4040/api/tunnels)
  
两种方式都可以从**public_url**中找到所需的外网地址

##### 6. 配置 授权发起页域名,授权接收URL,消息与事件接收URL
- 使用从上一步获取的外网地址, 去[字节跳动第三方平台](https://open.microapp.bytedance.com)配置以下信息:
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/2431599530574_.pic_hd.jpg?raw=true)
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/2441599533989_.pic_hd.jpg?raw=true)

### 方式2: 普通启动方式
#### 1. 启动redis
- `access_token`等数据都是保存在redis中，所以需要一个redis服务

- 为了方便使用，项目中提供了一个默认的`redis.conf`, 位于`docker/redis/redis.conf`, 只修改了两个配置:

  ```bash
  # 把protected-mode改为了no
  protected-mode no
  # 注释了bind
  # bind 127.0.0.1 ::1
  ```
- 本项目使用的redis客户端是[redisson](https://github.com/redisson/redisson)，可以直接使用。如果想修改redis连接相关配置，可以在`application-dev.yml`指定redis的配置文件。

- 如果想要使用jedis，可以自己实现一个`IByteDanceRedisOps`

#### 2. 获取本机外网地址: 内网穿透
  - 如果没有公网地址，那么需要使用内网穿透工具。比如[ngrok](https://ngrok.com/)
  - 如果使用ngrok，下载好后，根据不同的操作系统，在命令行执行
    ```bash
      # MacOS
      ngrok http 8080
      # Windows
      ngrok.exe http 8080
    ```
    就能得到一个映射到8080端口（项目默认启动端口）的外网地址。
    
#### 3. 配置 授权发起页域名,授权接收URL,消息与事件接收URL
- [同上](#6-配置-授权发起页域名授权接收url消息与事件接收url)

#### 4. 启动本项目    
- 启动项目后需要等待字节跳动服务器将ticket推送过来后（一般10分钟以内），才能进行后续的授权等api调用。如果一直没有推送，请到[字节跳动第三方平台](https://open.microapp.bytedance.com)，选择自己创建的第三方平台确认推送地址是否配置正确
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200714-130942%402x.png?raw=true)


## 任选一种方式启动项目后，进行接口测试
- 请确定ticket已经推送过来了。如果ticket推送过来，日志中会打印"MsgTypeTicketHandler 开始处理消息: xxxx"

  ### 网页授权
  - 先去[字节跳动小程序管理后台](https://microapp.bytedance.com/app/applist)创建一个小程序
  - 然后去[字节跳动第三方平台](https://open.microapp.bytedance.com/tplist)将刚才创建的小程序的appid添加到【授权测试小程序列表】，以便测试
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200717-210508%402x.png?raw=true)
  
  - 浏览器中输入授权地址: [https://你的公网地址或者ngrok生成的公网地址/bytedance/v1/auth/goto_auth_url_show]()
  ### 模版管理
  - [获取第三方平台的草稿列表](http://127.0.0.1:8080/bytedance/v1/template/draft/list)
  - [获取第三方平台的模版列表](http://127.0.0.1:8080/bytedance/v1/template/list)
  
## 事件推送处理
  - 可以在`EventHandlerConfiguration`配置用于处理相应事件的handler
  
## 单元测试
1. [启动项目](#启动项目)

2. [进行小程序授权](#网页授权)

3. 配置`src/test/resources/application-unittest.yml`中的
```yml
bytedance:
  open:
    componentAppId: ""
    componentSecret: ""
    componentToken: ""
    componentAesKey: ""
```

4. 将刚才授权的小程序appid配置到`src/test/resources/application-unittest.yml`中的
```yml
test-app-info:
  appid: ""
```
5. 运行单元测试


## 其他注意事项
  - 加解密异常 java.security.InvalidKeyException: Illegal key size
    - 因为秘钥长度超过128位,在某些jdk版本中默认只支持128位以下的秘钥
    - 解决方案为使用无限制的策略文件替换 $JAVA_HOME\jre\lib\security 下的策略文件
    - 下载地址
      - jdk8 [https://www.oracle.com/java/technologies/javase-jce8-downloads.html](https://www.oracle.com/java/technologies/javase-jce8-downloads.html)
  - 目前字节跳动的[字节跳动开放平台文档](https://bytedance.feishu.cn/docs/doccnYmtnRy6APhKiTfYgW#)还在不断更新，我也会根据他的更新不断新增接口。如果有接口没有及时更新，可以给我提issue或者PR，着急的话也可以通过sdk暴露的接口自己实现。
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/1407E96CAA9184803B3BF7D53A80649E.jpg?raw=true)