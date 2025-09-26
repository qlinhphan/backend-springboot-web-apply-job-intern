package vn.BAITAP.TestAPI.service;

import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.Role;
import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    public Role findRoleById(long id) {
        return this.roleRepository.findById(id);
    }

    public Role findRoleByUser(User user) {
        return this.roleRepository.findByUser(user);
    }
}
