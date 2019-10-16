package controller

import br.com.ml.urlreducer.controller.UrlReduceController
import br.com.ml.urlreducer.service.UrlService
import br.com.ml.urlreducer.vo.UrlVO
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UrlReduceControllerTest {

    @InjectMocks
    lateinit var urlReduceController: UrlReduceController

    @Mock
    lateinit var urlService: UrlService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `url reducer`(){
        whenever(urlService.urlReducer(anyString())).thenReturn(UrlVO(url = "localhost:8080/ABCDEF"))
        val urlReducerRequest = UrlVO(url = "https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd")
        var urlReduce = urlReduceController.urlReducer(urlReducerRequest)
        Assert.assertNotNull(urlReduce)
        Assert.assertEquals("localhost:8080/ABCDEF", urlReduce.url.toString())
        verify(urlService).urlReducer(anyString())
    }

    @Test
    fun `url extender`(){
        whenever(urlService.findOriginalUrlByKey(anyString())).thenReturn(UrlVO(url = "https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd"))
        var urlReduce = urlReduceController.urlExtender("ABCDEF")
        Assert.assertNotNull(urlReduce)
        Assert.assertEquals("https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd", urlReduce.url.toString())
        verify(urlService).findOriginalUrlByKey(anyString())
    }

    @Test
    fun `go to original url`(){
        whenever(urlService.findOriginalUrlByKey(anyString())).thenReturn(UrlVO(url = "https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd"))
        var urlReduce = urlReduceController.goToOriginalUrl("ABCDEF")
        Assert.assertNotNull(urlReduce)
        Assert.assertEquals("https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd", urlReduce.url.toString())
        verify(urlService).findOriginalUrlByKey(anyString())
    }

    @Test
    fun `del url`(){
        Mockito.doNothing().`when`(urlService).delUrl("ABCDEF")
        urlReduceController.delUrl("ABCDEF")
        verify(urlService).delUrl(anyString())
    }
}