package org.maxym.spring.sensor.util.mapper;

import org.mapstruct.*;
import org.maxym.spring.sensor.dto.UserRequest;
import org.maxym.spring.sensor.dto.UserResponse;
import org.maxym.spring.sensor.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User map(UserRequest userRequestDTO);

    @Named("map")
    @InheritInverseConfiguration
    UserResponse map(User user);

    @IterableMapping(qualifiedByName = "map")
    List<UserResponse> mapList(List<User> users);
}
