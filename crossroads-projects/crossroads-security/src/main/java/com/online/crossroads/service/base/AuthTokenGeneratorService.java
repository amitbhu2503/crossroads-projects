package com.online.crossroads.service.base;

/**
 * Created by lenovo on 09-02-2016.
 */
public interface AuthTokenGeneratorService {

	public String generateSecret();

	public String[] decode(String token);
}
