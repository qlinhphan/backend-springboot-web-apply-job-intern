package vn.BAITAP.TestAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.BAITAP.TestAPI.domain.Company;
import vn.BAITAP.TestAPI.domain.Role;
import vn.BAITAP.TestAPI.domain.User;
import vn.BAITAP.TestAPI.domain.dto.ObjectPaginate;
import vn.BAITAP.TestAPI.domain.dto.UserNoEncodeDTO;
import vn.BAITAP.TestAPI.service.CompanyService;
import vn.BAITAP.TestAPI.service.RoleService;
import vn.BAITAP.TestAPI.service.UserService;
import vn.BAITAP.TestAPI.service.Except.CurrentPasswordIsFalse;
import vn.BAITAP.TestAPI.service.Except.Exceptions;
import vn.BAITAP.TestAPI.service.Except.NotExistUserById;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private CompanyService companyService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService,
            CompanyService companyService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.companyService = companyService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> postMethodName(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "roleId", required = false) String roleId,
            @RequestParam(value = "companyId", required = false) String companyId) throws Exceptions {
        List<User> users = this.userService.findAllUsers();
        boolean checkUserIsExist = users.stream().anyMatch(x -> x.getEmail().equals(email));
        if (checkUserIsExist) {
            throw new Exceptions("The email is exist");
        }
        User userNew = new User();
        if (email != null) {
            userNew.setEmail(email);
        }
        if (name != null) {
            userNew.setName(name);
        }
        if (address != null) {
            userNew.setAddress(address);
        }
        if (password != null) {
            userNew.setPassword(this.passwordEncoder.encode(password));
        }
        if (roleId != null) {
            long idRole = Long.parseLong(roleId);
            Role rl = this.roleService.findRoleById(idRole);
            userNew.setRole(rl);
        }
        if (companyId != null) {
            long idCom = Long.parseLong(companyId);
            Company c = this.companyService.findCompById(idCom);
            userNew.setCompany(c);
        }
        User userSave = this.userService.saveUser(userNew);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(userSave);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable("id") long id) {
        User user = this.userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users-all")
    public ResponseEntity<?> getMethodName() {
        List<User> users = this.userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/user/update") // ko update mat khau tai trang admin
    public ResponseEntity<User> putMethodNames(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "roleId", required = false) String roleId,
            @RequestParam(value = "password", required = false) String password, // password nay de kiem tra xem co dung
                                                                                 // hay ko
            @RequestParam(value = "newPassword", required = false) String newPassword) // password moi
            throws Exceptions {

        long idUser = Long.parseLong(id);
        User userToUpdate = this.userService.findUserById(idUser);
        if (userToUpdate != null) {
            if (email != null) {
                if (userToUpdate.getEmail().equals(email)) {
                    userToUpdate.setEmail(email);
                } else {
                    List<User> users = this.userService.findAllUsers();
                    boolean checkEmailExist = users.stream().anyMatch(x -> email.equals(x.getEmail()));
                    if (checkEmailExist) {
                        throw new Exceptions("Email is existed");
                    }
                    userToUpdate.setEmail(email);
                }
            }
            if (name != null) {
                userToUpdate.setName(name);
            }
            if (address != null) {
                userToUpdate.setAddress(address);
            }
            if (password != null) {
                boolean checkPass = this.passwordEncoder.matches(password, userToUpdate.getPassword());
                if (checkPass & newPassword != null) {
                    userToUpdate.setPassword(this.passwordEncoder.encode(newPassword));
                } else {
                    throw new CurrentPasswordIsFalse("Current password is false or it is null");
                }
            } else {
                throw new CurrentPasswordIsFalse("Current password is false or it is null");
            }
            if (roleId != null) {
                long idRole = Long.parseLong(roleId);
                Role rl = this.roleService.findRoleById(idRole);
                userToUpdate.setRole(rl);
            }

            User updateOk = this.userService.saveUser(userToUpdate);

            return ResponseEntity.ok().body(updateOk);
        } else {
            throw new NotExistUserById("Not exist user who has this id");
        }
    }

    @DeleteMapping("/user/del/{id}")
    public ResponseEntity<?> deleteUserOk(
            @PathVariable(value = "id", required = true) long id) {
        User userTodel = this.userService.findUserById(id);
        if (userTodel != null) {
            this.userService.deleteUserById(id);
            return ResponseEntity.ok().body("Delete user success");
        } else {
            return ResponseEntity.badRequest().body("Not exist this user");
        }
    }

    @GetMapping("/user/haspage")
    public ResponseEntity<?> getMethodName(
            @RequestParam(value = "current", required = true) int current,
            @RequestParam(value = "limit", required = true) int limit) {

        List<User> usersNoPage = this.userService.findAllUsers();

        Pageable pageable = PageRequest.of(current - 1, limit);
        Page<User> usersPage = this.userService.findAllUserPage(pageable);
        List<User> users = usersPage.getContent();

        ObjectPaginate<Object> op = new ObjectPaginate<>();
        op.setCurrent(current);
        op.setLimit(limit);
        op.setPages(usersPage.getTotalPages());
        op.setSumObj(usersNoPage.size());
        op.setData(users);

        return ResponseEntity.ok().body(op);
    }

    @GetMapping("/user/find-by-email")
    public ResponseEntity<?> getMethodName(@RequestParam("email") String email) {
        User user = this.userService.findUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

}
