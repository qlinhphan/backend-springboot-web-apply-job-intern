package vn.BAITAP.TestAPI.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.domain.UserJob;

public interface UserJobRepository extends JpaRepository<UserJob, Long> {
    public UserJob save(UserJob uj);

    public List<UserJob> findAll();

    public Page<UserJob> findByUser(Pageable pageable, User user);

    public List<UserJob> findByUser(User user);
}
