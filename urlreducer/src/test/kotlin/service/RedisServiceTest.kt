package service

import br.com.ml.urlreducer.service.RedisService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.slf4j.LoggerFactory
import redis.clients.jedis.JedisCluster
import com.nhaarman.mockito_kotlin.whenever
import org.mockito.*


class RedisServiceTest {


    lateinit var redisService: RedisService

    private val jedisCluster = Mockito.mock(JedisCluster::class.java)

    @Mock
    lateinit var redisServiceMock: RedisService

    @Before
    fun setup() {
        redisService = RedisService(jedisCluster, 1000, LoggerFactory.getLogger(RedisService::class.java))
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `redis set and get test`() {
        whenever(jedisCluster.get(Matchers.anyString())).thenReturn("teste1")
        redisService.set("teste1234", "teste1")
        var resp1 = redisService.get("teste1234")
        Assert.assertEquals("teste1", resp1)
    }

    @Test
    fun `redis set and get del and get test`() {
        redisServiceMock.del("teste1234")
        verify(redisServiceMock).del("teste1234")
    }

    @Test
    fun `get key builder`() {
        var key =redisService.keyBuilder()
        Assert.assertEquals(6, key.length)
    }


}