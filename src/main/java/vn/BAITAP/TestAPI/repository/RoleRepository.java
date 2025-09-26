package vn.BAITAP.TestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.BAITAP.TestAPI.domain.Role;
import vn.BAITAP.TestAPI.domain.User;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role save(Role role);

    public Role findById(long id);

    public Role findByUser(User user);
}
