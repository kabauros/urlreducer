package service

import br.com.ml.urlreducer.exception.NotFoundException
import br.com.ml.urlreducer.service.RedisService
import br.com.ml.urlreducer.service.UrlService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory


internal class UrlServiceTest {

    @InjectMocks
    lateinit var urlService: UrlService

    @Mock
    lateinit var redisService: RedisService

    @Before
    fun setUp() {
        urlService = UrlService(LoggerFactory.getLogger(UrlService::class.java))
        MockitoAnnotations.initMocks(this)
    }

    @Test(expected = NotFoundException::class)
    fun ` not found exception`(){
        urlService.findOriginalUrl("test")
    }

    @Test
    fun `find original url`(){
        whenever(redisService.get("ABCDE")).thenReturn("https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd")
        var url = urlService.findOriginalUrl("ABCDE")
        Assert.assertEquals("https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd", url)
    }

    @Test
    fun `reduce url`(){
        whenever(redisService.keyBuilder()).thenReturn("ABCDE")
        var urlReducer = urlService.urlReducer("https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd")
        Assert.assertEquals("localhost:8080/ABCDE", urlReducer)
    }

    @Test
    fun ` del url`(){
        Mockito.doNothing().`when`(redisService).del("ABCDE")
        urlService.delUrl("test")

    }
}