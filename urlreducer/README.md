# URL Reducer
Esse Serviço é responsável por: 
* Dada uma URL longa, ele devolve uma URL curta
* Data a URL curta, ele devolve a url original
* É permitido apagar as urls curtas 
* Ao acessar a url curta no navegador é redirecionado para o url original

#Como funciona:

### Para reduzir uma url:
Fazer um HTTP Resquest ( POST ) para  localhost:8080/urlreducer com o seguite body:

{
	"url":"https://celulares.mercadolivre.com.br/iphone/linea-iphone/iphone-8"
}

Então o retorno será : 

{
    "url": "localhost:8080/KQDyQq"
}

### Para buscar uma url original a partir de uma url reduzida:
Fazer um HTTP Resquest ( GET ) para  localhost:8080/{key}/urlextender:

Então o retorno será : 

{
    "url":"https://celulares.mercadolivre.com.br/iphone/linea-iphone/iphone-8"
}

### Para apagar uma url reduzida:
Fazer um HTTP Resquest ( DELETE ) para  localhost:8080/{key}:

#### Ao abrir a url reduzida no navegador será direcionado a url Original 

## Tecnologias:
https://redis.io/

* Ao reduzir uma url , é gerado o código / chave para ela. Esse código é armazenado no redis juntamente com a url original 
* Para encontrar a url original é feito uma busca no redis pelo código / chave gerado 
* Para apagar a url curta é feito o delete no no redis 

## Como rodar local o redis

#### docker-compose redis-cluster
https://itsmetommy.com/2018/05/24/docker-compose-redis-cluster/

#### Construindo e iniciando todos os containers, dentro da pasta ../url-reducer/urlreducer/docker-redis-cluster-master
docker-compose up --build -d

#### Criando o cluster 
docker exec -it redis-1 redis-cli -p 7000 --cluster create 10.0.0.2:7000 10.0.0.3:7001 \
10.0.0.4:7002 10.0.0.5:7003 10.0.0.6:7004 10.0.0.7:7005 \
--cluster-replicas 1