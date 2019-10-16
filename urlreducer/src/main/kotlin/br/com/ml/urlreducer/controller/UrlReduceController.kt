package br.com.ml.urlreducer.controller

import br.com.ml.urlreducer.service.UrlService
import br.com.ml.urlreducer.vo.UrlReducerRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@RestController
class UrlReduceController {

    @Autowired
    private lateinit var urlService: UrlService

    @PostMapping("/urlreducer")
    fun urlReducer( @RequestBody @Validated urlReducerRequest: UrlReducerRequest): String{
       return urlService.urlReducer(urlReducerRequest.originalUrl!!)
    }

    @GetMapping("/{key}")
    fun findOriginalUrl(@PathVariable key: String): RedirectView {
        return RedirectView(urlService.findOriginalUrl(key))
    }

    @DeleteMapping("/{key}")
    fun delUrl(@PathVariable key: String){
        urlService.delUrl(key)
    }
}