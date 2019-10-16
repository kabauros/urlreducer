package br.com.ml.urlreducer.exception

abstract class ApplicationException(message: String, throwable: Throwable? = null) : RuntimeException(message, throwable)

class NotFoundException(message: String, throwable: Throwable? = null) : ApplicationException(message, throwable)
