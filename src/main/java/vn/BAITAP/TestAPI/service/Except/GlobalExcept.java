package vn.BAITAP.TestAPI.service.Except;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.FieldResult;
import vn.BAITAP.TestAPI.domain.dto.ResponseData;

//email trung thi bat loi conflict
@RestControllerAdvice
public class GlobalExcept {
    @ExceptionHandler(value = Exceptions.class)
    public ResponseEntity<?> handleBlogAlreadyExistsException(Exceptions ex) {
        ResponseData<Object> rd = new ResponseData<>();
        rd.setError("Call api faild");
        rd.setMessage(ex.getMessage());
        rd.setSttCode(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(rd);
    }

    @ExceptionHandler(value = NotExistUserById.class)
    public ResponseEntity<?> handleBlogAlreadyExistsException(NotExistUserById ex) {
        ResponseData<Object> rd = new ResponseData<>();
        rd.setError("Call api faild");
        rd.setMessage(ex.getMessage());
        rd.setSttCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rd);
    }

    @ExceptionHandler(value = CurrentPasswordIsFalse.class)
    public ResponseEntity<?> handleBlogAlreadyExistsException(CurrentPasswordIsFalse ex) {
        ResponseData<Object> rd = new ResponseData<>();
        rd.setError("Call api faild");
        rd.setMessage(ex.getMessage());
        rd.setSttCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rd);
    }

    @ExceptionHandler(value = NoUserByRefreshToken.class)
    public ResponseEntity<?> handleBlogAlreadyExistsException(NoUserByRefreshToken ex) {
        ResponseData<Object> rd = new ResponseData<>();
        rd.setError("Call api faild");
        rd.setMessage(ex.getMessage());
        rd.setSttCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rd);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBlogAlreadyExistsException(MethodArgumentNotValidException ex) {
        BindingResult br = ex.getBindingResult();
        List<FieldError> rs = br.getFieldErrors();

        List<String> listErs = new ArrayList<>();
        for (FieldError string : rs) {
            listErs.add(string.getDefaultMessage());
        }

        ResponseData<Object> rd = new ResponseData<>();
        rd.setError("Call api faild");
        rd.setMessage(listErs);
        rd.setSttCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rd);
    }

    @ExceptionHandler(value = InforLoginFalse.class)
    public ResponseEntity<?> handleBlogAlreadyExistsException(InforLoginFalse ex) {
        ResponseData<Object> rd = new ResponseData<>();
        rd.setError("Call api faild");
        rd.setMessage(ex.getMessage());
        rd.setSttCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(rd);
    }

    @ExceptionHandler(value = PermissionErr.class)
    public ResponseEntity<?> handleBlogAlreadyExistsException(PermissionErr ex) {
        ResponseData<Object> rd = new ResponseData<>();
        rd.setError("Call api faild");
        rd.setMessage(ex.getMessage());
        rd.setSttCode(HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(rd);
    }
}
