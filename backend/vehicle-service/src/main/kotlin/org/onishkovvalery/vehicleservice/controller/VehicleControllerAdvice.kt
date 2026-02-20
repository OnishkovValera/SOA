package org.onishkovvalery.vehicleservice.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.resource.NoResourceFoundException

@ControllerAdvice
@ConditionalOnProperty(name = ["app.rest.enabled"], havingValue = "true", matchIfMissing = true)
class VehicleControllerAdvice {
    @ExceptionHandler(HttpMessageConversionException::class)
    fun onMessageConvertError(error: HttpMessageConversionException) = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, error.message)

    @ExceptionHandler(IllegalArgumentException::class)
    fun onIllegalArgument(error: IllegalArgumentException) = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, error.message)

    @ExceptionHandler(Exception::class)
    fun onUnknownError(error: Exception) = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, error.message)

    @ExceptionHandler(MethodArgumentNotValidException::class, MethodArgumentTypeMismatchException::class)
    fun onValidation(error: Exception) = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, error.message)

    @ExceptionHandler(NoResourceFoundException::class)
    fun onNoResourceFound(): ProblemDetail {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Resource not found")
    }
}
