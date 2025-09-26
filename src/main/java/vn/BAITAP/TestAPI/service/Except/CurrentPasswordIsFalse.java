package vn.BAITAP.TestAPI.service.Except;

public class CurrentPasswordIsFalse extends RuntimeException {
    public CurrentPasswordIsFalse(String mess) {
        super(mess);
    }
}
