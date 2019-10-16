package br.com.ml.urlreducer.exception

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("error_message")
data class ErrorResponse @JsonCreator constructor(var errorMessages: List<ErrorMessage>) {
    constructor(errorMessage: ErrorMessage) : this(listOf(errorMessage))
}