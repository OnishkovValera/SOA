package org.onishkovvalery.shopservice.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.nio.channels.ClosedChannelException

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(ClosedChannelException::class)
    fun onClosedChannelException(e: ClosedChannelException) = ProblemDetail.forStatusAndDetail(HttpStatus.SERVICE_UNAVAILABLE, e.message)

    @ExceptionHandler(Exception::class)
    fun onUnknownError(e: Exception): ProblemDetail {
        println(e)
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
    }
}