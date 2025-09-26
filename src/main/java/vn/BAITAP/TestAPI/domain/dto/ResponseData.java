package vn.BAITAP.TestAPI.domain.dto;

public class ResponseData<T> {
    private int sttCode;
    private String error;
    private Object message;
    private T data;

    public int getSttCode() {
        return sttCode;
    }

    public void setSttCode(int sttCode) {
        this.sttCode = sttCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
