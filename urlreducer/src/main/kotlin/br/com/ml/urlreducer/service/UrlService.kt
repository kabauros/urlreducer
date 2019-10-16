package br.com.ml.urlreducer.service

import br.com.ml.urlreducer.exception.NotFoundException
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UrlService(private val logger: Logger) {

    @Autowired
    private lateinit var redisService: RedisService

    fun urlReducer(originalUrl: String): String{
        var key  = redisService.keyBuilder()
        redisService.set(key, originalUrl)
        logger.info("reducer url: $originalUrl")
        return "localhost:8080/$key"
    }

    fun findOriginalUrl(key: String): String{
        var originalUrl: String? = redisService.get(key) ?: throw NotFoundException("Url not found!")
        logger.info("find url by key: $key")
        return originalUrl!!
    }

    fun delUrl(key: String){
       logger.info("delete url by key: $key")
       redisService.del(key)
    }

}