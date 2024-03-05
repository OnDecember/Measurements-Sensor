package org.maxym.spring.sensor.util.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.maxym.spring.sensor.dto.UserRequestDTO;
import org.maxym.spring.sensor.dto.UserResponseDTO;
import org.maxym.spring.sensor.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User map(UserRequestDTO userRequestDTO);

    @InheritConfiguration
    UserResponseDTO map(User user);
}
