package vn.BAITAP.TestAPI.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User findUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    public Page<User> findAllUserPage(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public User findUserByRefreshToken(String re) {
        return this.userRepository.findByRefreshToken(re);
    }
}
