package com.rabbitforever.knowledge.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.rabbitforever.knowledge.models.eos.AppUser;

public interface UserService extends UserDetailsService{
	public List<AppUser> getAllUsers();
	public AppUser getUserById(Long id);
	public boolean saveUser(AppUser appUser);
	public boolean deleteUserById(Long id);
	public Authentication getCurrentAuthentication();
}
