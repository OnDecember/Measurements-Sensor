package org.maxym.spring.sensor.controller;

import lombok.RequiredArgsConstructor;
import org.maxym.spring.sensor.dto.RoleResponse;
import org.maxym.spring.sensor.dto.UserRole;
import org.maxym.spring.sensor.exception.RoleNotFoundException;
import org.maxym.spring.sensor.exception.UserNotFoundException;
import org.maxym.spring.sensor.model.Role;
import org.maxym.spring.sensor.model.User;
import org.maxym.spring.sensor.service.RoleService;
import org.maxym.spring.sensor.service.UserService;
import org.maxym.spring.sensor.util.mapper.RoleMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping
    public ResponseEntity<?> getAllRoles() {

        List<Role> roles = roleService.findAll();
        List<RoleResponse> roleResponses = roleMapper.mapList(roles);

        return ResponseEntity.status(HttpStatus.OK).body(roleResponses);
    }

    @PostMapping("/grant")
    public ResponseEntity<?> grantRole(@RequestBody UserRole userRole) {

        String username = userRole.username();
        String roleName = userRole.role();

        // TODO: validator
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("User %s not found", username)));

        // TODO: validator
        Role role = roleService.findByRole(roleName)
                .orElseThrow(() -> new RoleNotFoundException(String.format("Role %s not found", roleName)));

        userService.grantRole(role, user);

        return ResponseEntity.status(HttpStatus.OK).body("Role was granted");
    }

    @PostMapping("/take")
    public ResponseEntity<?> takeRole(@RequestBody UserRole userRole) {

        String username = userRole.username();
        String roleName = userRole.role();

        // TODO: validator
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("User %s not found", username)));

        // TODO: validator
        Role role = roleService.findByRole(roleName)
                .orElseThrow(() -> new RoleNotFoundException(String.format("Role %s not found", roleName)));

        // TODO: validator
        if (!userService.hasRole(role, user)) {
            throw new RoleNotFoundException(String.format("User %s does not have %s role", username, roleName));
        }

        userService.takeRole(role, user);

        return ResponseEntity.status(HttpStatus.OK).body("Role was taken");
    }
}
