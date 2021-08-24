# springboot_Mytemplate

> 项目依赖
* jdk1.8
* maven 3
* mysql
* redis

> 代码生成
* 配置generator.properties 和 generatorConfig.xml
* 运行top.zang.mbg.Generator

> 项目启动
* 使用idea等IDE工具加载该项目
* 运行MainApplication即可

> 接口文档
* http://localhost:8080/swagger-ui/index.html#/

> 项目环境搭建

* [环境搭建参考](http://www.macrozheng.com/#/deploy/mall_deploy_docker_compose)
* windows环境docker-compose方式搭建 mysql、redis、rabbitmq、mongodb、es
```
version: '3'
services:
  mysql:
    image: mysql:5.7
    container_name: mysql
    command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: 123456 #设置root帐号密码
    ports:
      - 3306:3306
    volumes:
      - /e/docker/mysql/data/db:/var/lib/mysql #数据文件挂载
      - /e/docker/mysql/data/conf:/etc/mysql/conf.d #配置文件挂载
      - /e/docker/mysql/log:/var/log/mysql #日志文件挂载
  redis:
    image: redis:5
    container_name: redis
    command: redis-server --appendonly yes
    volumes:
      - /e/docker/redis/data:/data #数据文件挂载
    ports:
      - 6379:6379
  nginx:
    image: nginx:1.10
    container_name: nginx
    volumes:
      - /e/docker/nginx/nginx.conf:/etc/nginx/nginx.conf #配置文件挂载
      - /e/docker/nginx/html:/usr/share/nginx/html #静态资源根目录挂载
      - /e/docker/nginx/log:/var/log/nginx #日志文件挂载
    ports:
      - 80:80
  rabbitmq:
    image: rabbitmq:3.7.15-management
    container_name: rabbitmq
    volumes:
      - /e/docker/rabbitmq/data:/var/lib/rabbitmq #数据文件挂载
      - /e/docker/rabbitmq/log:/var/log/rabbitmq #日志文件挂载
    ports:
      - 5672:5672
      - 15672:15672
  elasticsearch:
    image: elasticsearch:7.6.2
    container_name: elasticsearch
    environment:
      - "cluster.name=elasticsearch" #设置集群名称为elasticsearch
      - "discovery.type=single-node" #以单一节点模式启动
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m" #设置使用jvm内存大小
    volumes:
      - /e/docker/elasticsearch/plugins:/usr/share/elasticsearch/plugins #插件文件挂载
      - /e/docker/elasticsearch/data:/usr/share/elasticsearch/data #数据文件挂载
    ports:
      - 9200:9200
      - 9300:9300
  logstash:
    image: logstash:7.6.2
    container_name: logstash
    environment:
      - TZ=Asia/Shanghai
    volumes:
      - /e/docker/logstash/logstash.conf:/usr/share/logstash/pipeline/logstash.conf #挂载logstash的配置文件
    depends_on:
      - elasticsearch #kibana在elasticsearch启动之后再启动
    links:
      - elasticsearch:es #可以用es这个域名访问elasticsearch服务
    ports:
      - 4560:4560
      - 4561:4561
      - 4562:4562
      - 4563:4563
  kibana:
    image: kibana:7.6.2
    container_name: kibana
    links:
      - elasticsearch:es #可以用es这个域名访问elasticsearch服务
    depends_on:
      - elasticsearch #kibana在elasticsearch启动之后再启动
    environment:
      - "elasticsearch.hosts=http://es:9200" #设置访问elasticsearch的地址
    ports:
      - 5601:5601
  mongo:
    image: mongo:4.2.5
    container_name: mongo
    volumes:
      - /e/docker/mongo/db:/data/db #数据文件挂载
    ports:
      - 27017:27017
```

*  启动命令
```
docker-compose -f docker-compose-env-windows.yml up -d
```



