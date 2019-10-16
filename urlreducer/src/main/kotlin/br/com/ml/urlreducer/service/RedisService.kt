package br.com.ml.urlreducer.service

import org.apache.commons.lang3.RandomStringUtils
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisCluster
import java.util.concurrent.TimeUnit

@Service
class RedisService(@Autowired val jedisCluster: JedisCluster,
                   @Value("\${redis.ttl}") val ttl: Int = 0, private val logger: Logger) {

    fun set(key: String, value: String) {
        try {
            with(jedisCluster) {
                this.set(key, value )
                this.expire(key, ttl)
            }
        } catch (e: Exception) {
            logger.error("Could not ADD value to redis! class=RedisService error=${e.message}", e)
            throw e
        }
    }

    fun get(key: String): String? {
        return try {
            with(jedisCluster) {
                get(key)
            }
        } catch (e: Exception) {
            logger.error("Could not GET value to redis! class=RedisService error=${e.message} ", e)
            throw e
        }
    }

    fun del(key: String) {
        try {
            with(jedisCluster) {
                del(key)
            }
        } catch (e: Exception) {
            logger.error("Could not DEL value to redis! class=RedisService error=${e.message} ", e)
            throw e
        }
    }

    fun keyBuilder(): String {
        return RandomStringUtils.randomAlphabetic(6)
    }

}