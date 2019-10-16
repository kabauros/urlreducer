package br.com.ml.urlreducer.service

import br.com.ml.urlreducer.exception.NotFoundException
import br.com.ml.urlreducer.vo.UrlVO
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UrlService(private val logger: Logger) {

    @Autowired
    private lateinit var redisService: RedisService

    fun urlReducer(originalUrl: String): UrlVO{
        logger.info("reduce original url")
        var key  = redisService.keyBuilder()
        redisService.set(key, originalUrl)
        return UrlVO(url = "localhost:8080/$key")
    }

    fun findOriginalUrlByKey(key: String): UrlVO{
        logger.info("find original url by key: $key")
        var originalUrl: String? = redisService.get(key) ?: throw NotFoundException("Original url not found!")
        return UrlVO(url = originalUrl!!)
    }

    fun delUrl(key: String){
       logger.info("delete original url by key: $key")
       redisService.del(key)
    }






}