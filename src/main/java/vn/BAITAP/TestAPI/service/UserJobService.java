package vn.BAITAP.TestAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.domain.UserJob;
import vn.BAITAP.TestAPI.repository.UserJobRepository;

@Service
public class UserJobService {
    private UserJobRepository userJobRepository;

    public UserJobService(UserJobRepository userJobRepository) {
        this.userJobRepository = userJobRepository;
    }

    public UserJob saveUserJob(UserJob uj) {
        return this.userJobRepository.save(uj);
    }

    public List<UserJob> findAllNoHasPage() {
        return this.userJobRepository.findAll();
    }

    public Page<UserJob> findByUserAndPagination(Pageable pageable, User user) {
        return this.userJobRepository.findByUser(pageable, user);
    }

    public List<UserJob> findAllByUserNoPagi(User user) {
        return this.userJobRepository.findByUser(user);
    }

}
