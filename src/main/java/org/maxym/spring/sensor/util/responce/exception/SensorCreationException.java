package org.maxym.spring.sensor.util.responce.exception;

import lombok.Getter;
import org.maxym.spring.sensor.util.responce.error.FieldErrorResponse;

import java.util.List;

@Getter
public class SensorCreationException extends ApplicationException {

    public SensorCreationException(String message, List<FieldErrorResponse> errors) {
        super(message, errors);
    }
}
