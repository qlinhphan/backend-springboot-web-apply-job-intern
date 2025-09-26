package vn.BAITAP.TestAPI.service.Except;

public class NoUserByRefreshToken extends RuntimeException {
    public NoUserByRefreshToken(String mess) {
        super(mess);
    }
}
