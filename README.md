# sharding-jdbc-demo
sharding jdbc demo + mybatis + hikari


### docker 安装mysq集群

##### 主库
[mysqld]
log-bin=mysql-bin    # [必须]启用二进制日志
server-id=1          # [必须]服务器唯一ID，默认是1，一般取IP最后一段，这里看情况分配
##### 从库
[mysqld]
log-bin=mysql-bin    # [必须]启用二进制日志
server-id=2          # [必须]服务器唯一ID，默认是1，一般取IP最后一段，这里看情况分配

### 启动，端口分别用3306和3307即可
docker run -d \
-p 3306:3306 \
--privileged=true \
-e MYSQL_ROOT_PASSWORD="123456" \
--name mysql3306 \
-v $PWD/3306/my.cnf:/etc/my.cnf \
-v $PWD/3306,target=/var/lib/mysql \
--restart always \
mysql:latest 


#### 主库创建同步用户
CREATE USER 'repl'@'%' IDENTIFIED WITH mysql_native_password BY 'Mypwd@123456';
#### 主库给同步用户授权
GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
#### 主库创建用户并配置读写权限
CREATE USER 'madmars'@'%' IDENTIFIED WITH mysql_native_password BY 'Mypwd@123456';
GRANT ALL PRIVILEGES ON *.* TO 'madmars'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
#### 查询主库状态，并记录 File 的值和 Position 的值
SHOW MASTER STATUS;


#### 配置slave (master_log_file 和 master_log_pos 是主库的file和position值)
change master to
master_host='172.17.0.1',
master_user='repl',
master_log_file='mysql-bin.000003',
master_log_pos=1345,
master_port=3306,
master_password='Mypwd@123456';
#### 启动salve
START SLAVE;
#### 查看slave状态
SHOW SLAVE STATUS\G;
#### 从库用户配置只读权限
CREATE USER 'madmarsreadonly'@'%' IDENTIFIED WITH mysql_native_password BY 'Mypwd@123456';
GRANT SELECT ON *.* TO 'madmarsreadonly'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
