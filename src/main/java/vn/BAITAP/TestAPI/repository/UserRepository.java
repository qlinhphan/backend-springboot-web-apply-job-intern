package vn.BAITAP.TestAPI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.BAITAP.TestAPI.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User save(User user);

    public List<User> findAll();

    public User findByEmail(String email);

    public User findById(long id);

    public void deleteById(long id);

    public Page<User> findAll(Pageable pageable);

    public User findByRefreshToken(String refreshToken);

}
