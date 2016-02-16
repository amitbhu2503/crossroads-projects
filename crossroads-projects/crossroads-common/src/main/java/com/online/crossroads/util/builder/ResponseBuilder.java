package com.online.crossroads.util.builder;


import com.online.crossroads.response.Response;

/**
 * Created by lenovo on 15-02-2016.
 */
public class ResponseBuilder {
    public static Response build(int code, String message, Object data) {
        Response response = new Response(code, message, data);
        return response;
    }

}