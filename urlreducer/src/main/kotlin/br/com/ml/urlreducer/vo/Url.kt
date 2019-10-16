package br.com.ml.urlreducer.vo

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class UrlVO (

    @get:NotNull(message = "{field} originalUrl {not_null}")
    @get:JsonProperty("url")
    var url: String?
)

