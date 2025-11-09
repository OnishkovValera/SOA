package org.onishkovvalery.vehicleservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class VehicleControllerAdvice {

    @ExceptionHandler(HttpMessageConversionException::class)
    fun onMessageConvertError(error: HttpMessageConversionException): ResponseEntity<Unit> {
        return ResponseEntity.badRequest().build()
    }

}