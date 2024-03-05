package org.maxym.spring.sensor.util.validator;

import lombok.RequiredArgsConstructor;
import org.maxym.spring.sensor.dto.MeasurementRequestDTO;
import org.maxym.spring.sensor.service.SensorService;
import org.maxym.spring.sensor.error.FieldErrorResponse;
import org.maxym.spring.sensor.exception.SensorNotExistException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return MeasurementRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        MeasurementRequestDTO measurementRequestDTO = (MeasurementRequestDTO) target;

        if (sensorService.findByName(measurementRequestDTO.sensor().name()).isEmpty()) {
            errors.rejectValue("sensor", "sensor.exist", "This sensor not exist.");
            throw new SensorNotExistException("An error occurred.", Collections.singletonList(new FieldErrorResponse("sensor", "This sensor not exist.")));
        }

    }
}