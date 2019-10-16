package br.com.ml.urlreducer.exception

import org.slf4j.Logger
import org.slf4j.event.Level
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class DefaultExceptionHandler(private val logger: Logger) {

    @ExceptionHandler(Exception::class)
    fun handleError(exception: Exception): ResponseEntity<ErrorResponse> {
        logException(exception.message.toString(), exception, Level.ERROR)
        return getDefaultResponseMessage(exception.message.toString(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(notFoundException: NotFoundException): ResponseEntity<ErrorResponse> {
        logException(notFoundException.message.toString(), notFoundException, Level.INFO)
        return getDefaultResponseMessage(notFoundException.message.toString(), HttpStatus.NOT_FOUND)
    }

    private fun logException(message: String, exception: Exception, level: Level = Level.INFO) {
        when (level) {
            Level.ERROR -> logger.error(message,  exception)
            Level.INFO -> logger.info(message,  exception)
            else -> logger.warn(message,  exception)
        }
        logger.debug(message, exception)
    }

    private fun getDefaultResponseMessage(message: String, httpStatus: HttpStatus): ResponseEntity<ErrorResponse> {
        val errorMessages = listOf(
                ErrorMessage(message = message)
        )
        return ResponseEntity(ErrorResponse(errorMessages), httpStatus)
    }
}