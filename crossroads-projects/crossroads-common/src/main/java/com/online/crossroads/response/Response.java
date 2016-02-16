package com.online.crossroads.response;

/**
 * Created by lenovo on 15-02-2016.
 */
public class Response implements BaseResponse {
    private HttpStatus status;
    private Object data;

    public Response() {
        super();
    }

    public Response(int code, String message, Object data) {
        this.status = new HttpStatus(code, message);
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}