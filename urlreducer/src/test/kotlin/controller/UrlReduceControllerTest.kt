package controller

import br.com.ml.urlreducer.controller.UrlReduceController
import br.com.ml.urlreducer.service.UrlService
import br.com.ml.urlreducer.vo.UrlReducerRequest
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
        whenever(urlService.urlReducer(anyString())).thenReturn("localhost:8080/ABCDE")
        val urlReducerRequest = UrlReducerRequest(originalUrl = "https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd")
        var urlReduce = urlReduceController.urlReducer(urlReducerRequest)
        Assert.assertNotNull(urlReduce)
        Assert.assertEquals("localhost:8080/ABCDE", urlReduce)
        verify(urlService).urlReducer(anyString())
    }


    @Test
    fun `find url`(){
        whenever(urlService.findOriginalUrl(anyString())).thenReturn("https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd")
        var urlReduce = urlReduceController.findOriginalUrl("ABCDE")
        Assert.assertNotNull(urlReduce)
        Assert.assertEquals("https://www.mercadolivre.com.br/p/MLB8752432#position=1&type=product&tracking_id=889ab29b-6604-4fa0-88bb-41129a4617cd", urlReduce.url.toString())
        verify(urlService).findOriginalUrl(anyString())
    }

    @Test
    fun `del url`(){

        Mockito.doNothing().`when`(urlService).delUrl("ABCDE")
        var urlReduce = urlReduceController.delUrl("ABCDE")
        verify(urlService).delUrl(anyString())
    }
}