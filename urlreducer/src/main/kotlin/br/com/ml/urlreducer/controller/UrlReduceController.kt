package br.com.ml.urlreducer.controller

import br.com.ml.urlreducer.service.UrlService
import br.com.ml.urlreducer.vo.UrlVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@RestController
class UrlReduceController {

    @Autowired
    private lateinit var urlService: UrlService

    @PostMapping("/urlreducer")
    fun urlReducer( @RequestBody @Validated urlVO: UrlVO): UrlVO{
       return urlService.urlReducer(urlVO.url!!)
    }

    @GetMapping("/{key}/urlextender")
    fun urlExtender(@PathVariable key: String): UrlVO{
        return urlService.findOriginalUrlByKey(key)
    }

    @GetMapping("/{key}")
    fun goToOriginalUrl(@PathVariable key: String): RedirectView {
        return RedirectView(urlService.findOriginalUrlByKey(key).url.toString())
    }

    @DeleteMapping("/{key}")
    fun delUrl(@PathVariable key: String){
        urlService.delUrl(key)
    }
}