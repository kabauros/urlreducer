package br.com.ml.urlreducer.exception

abstract class ApplicationException(message: String) : RuntimeException(message)

class NotFoundException(message: String) : ApplicationException(message)
