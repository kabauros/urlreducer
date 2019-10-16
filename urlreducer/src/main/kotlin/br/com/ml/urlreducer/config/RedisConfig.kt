package br.com.ml.urlreducer.config

import redis.clients.jedis.HostAndPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import redis.clients.jedis.JedisCluster

@Configuration
class RedisConfig {

    @Value("\${redis.host}")
    val host: String = ""
    @Value("\${redis.port}")
    val port: Int = 0

    @Bean
    fun jedisCluster(): JedisCluster {
        return JedisCluster(HostAndPort(host, port))
    }

}