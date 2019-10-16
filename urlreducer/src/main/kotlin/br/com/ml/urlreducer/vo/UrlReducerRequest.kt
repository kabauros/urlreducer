package br.com.ml.urlreducer.vo

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class UrlReducerRequest (

    @get:NotNull(message = "{field} originalUrl {not_null}")
    @get:JsonProperty("original_url")
    var originalUrl: String?
)
