package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.domain.Role;
import vn.BAITAP.TestAPI.service.RoleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public ResponseEntity<?> postMethodName(@RequestBody Role role) {
        Role roles = this.roleService.saveRole(role);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(roles);
    }

}
