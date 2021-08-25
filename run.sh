#!/usr/bin/env bash
# 定义应用名称
app_name='my'
# 定义应用版本
app_version='1.0'
# 定义应用环境
profile_active='prod'
echo '----copy jar----'
docker stop ${app_name}
echo '----stop container----'
docker rm ${app_name}
echo '----rm container----'
docker rmi ${app_name}:${app_version}
echo '----rm image----'
# 打包编译docker镜像
docker build -t ${app_name}:${app_version} .
echo '----build image----'
docker run -p 16888:16888 --name ${app_name} \
-e 'spring.profiles.active'=${profile_active} \
-e TZ="Asia/Shanghai" \
-v /etc/localtime:/etc/localtime \
-v /data/logs/zangSpringboot:/data/logs/zangSpringboot \
-d ${app_name}:${app_version}
echo '----start container----'