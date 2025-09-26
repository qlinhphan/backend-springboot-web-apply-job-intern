// package vn.BAITAP.TestAPI.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.servlet.HandlerInterceptor;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import vn.BAITAP.TestAPI.domain.Role;
// import vn.BAITAP.TestAPI.domain.User;
// import vn.BAITAP.TestAPI.service.RoleService;
// import vn.BAITAP.TestAPI.service.UserService;
// import vn.BAITAP.TestAPI.service.Except.Exceptions;
// import vn.BAITAP.TestAPI.service.Except.PermissionErr;

// @Configuration
// public class InterceptorConfiguration implements HandlerInterceptor {

// private UserService userService;
// private RoleService roleService;

// public InterceptorConfiguration(UserService userService, RoleService
// roleService) {
// this.userService = userService;
// this.roleService = roleService;
// }

// @Override
// public boolean preHandle(HttpServletRequest request, HttpServletResponse
// response, Object handler)
// throws Exception {

// String uri = request.getRequestURI();
// String method = request.getMethod();

// String email =
// SecurityContextHolder.getContext().getAuthentication().getName();
// System.out.println(">>> CHECK EMAIL: " + email);

// System.out.println("URI: " + uri);
// System.out.println("METHOD: " + method);

// User user = this.userService.findUserByEmail(email);
// Role role = this.roleService.findRoleByUser(user);
// String nameRole = role.getName();
// boolean isCheckURI = uri.contains("user");
// if (isCheckURI && nameRole.equals("user")) {
// throw new PermissionErr("You cannot access this api");
// }

// return true;
// }
// }
