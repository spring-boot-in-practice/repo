package com.manning.sbip.ch06.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class LoginAttemptService {

	private static final int MAX_ATTEMPTS_COUNT = 3;
	private LoadingCache<String, Integer> loginAttemptCache;

	public LoginAttemptService() {
		loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(final String key) {
						return 0;
					}
				});
	}
	
	
	public void loginSuccess(String username) {
		loginAttemptCache.invalidate(username);
	}

	public void loginFailed(String username) {
		int failedAttemptCounter = 0;
		
		try {
			failedAttemptCounter = loginAttemptCache.get(username);
		} 
		catch (ExecutionException e) {
			failedAttemptCounter = 0;
		}
		failedAttemptCounter++;
		loginAttemptCache.put(username, failedAttemptCounter);
	}
	
	public boolean isBlocked(String username) {
		try {
			return loginAttemptCache.get(username) >= MAX_ATTEMPTS_COUNT;
		} 
		catch (ExecutionException e) {
			return false;
		}
	}
}
