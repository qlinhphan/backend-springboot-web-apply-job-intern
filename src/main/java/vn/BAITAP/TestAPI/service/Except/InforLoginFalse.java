package vn.BAITAP.TestAPI.service.Except;

public class InforLoginFalse extends RuntimeException {
    public InforLoginFalse(String mess) {
        super(mess);
    }
}
