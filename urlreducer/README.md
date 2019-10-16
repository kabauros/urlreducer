URL docker cluster redis 
https://itsmetommy.com/2018/05/24/docker-compose-redis-cluster/

Build and start all containers, dentro da pasta ../url-reducer/docker-redis-cluster-master
docker-compose up --build -d

Create cluster 
docker exec -it redis-1 redis-cli -p 7000 --cluster create 10.0.0.2:7000 10.0.0.3:7001 \
10.0.0.4:7002 10.0.0.5:7003 10.0.0.6:7004 10.0.0.7:7005 \
--cluster-replicas 1