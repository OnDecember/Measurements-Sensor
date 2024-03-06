package org.maxym.spring.sensor.util.validator;

import lombok.RequiredArgsConstructor;
import org.maxym.spring.sensor.dto.SensorRequest;
import org.maxym.spring.sensor.service.SensorService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return SensorRequest.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target,
                         @NonNull Errors errors) {

        SensorRequest sensor = (SensorRequest) target;

        if (sensorService.findByName(sensor.name()).isPresent()) {
            errors.rejectValue("name", "sensor.exist", "This sensor is already exist.");
        }
    }
}
