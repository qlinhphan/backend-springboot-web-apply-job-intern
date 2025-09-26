package vn.BAITAP.TestAPI.service.Except;

public class NotExistUserById extends RuntimeException {
    public NotExistUserById(String mess) {
        super(mess);
    }
}
