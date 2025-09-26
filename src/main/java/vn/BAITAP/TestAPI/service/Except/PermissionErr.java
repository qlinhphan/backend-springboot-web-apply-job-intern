package vn.BAITAP.TestAPI.service.Except;

public class PermissionErr extends RuntimeException {
    public PermissionErr(String mess) {
        super(mess);
    }
}
