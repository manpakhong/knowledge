package com.rabbitforever.knowledge.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.rabbitforever.knowledge.models.eos.AppUser;
import com.rabbitforever.knowledge.models.eos.AppUserPrincipal;
import com.rabbitforever.knowledge.repos.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements  UserService {
	// Implementing Constructor based DI
    @Autowired
    private WebApplicationContext applicationContext;
	private UserRepository userRepository;

	public UserServiceImpl() {
		super();
	}
    @PostConstruct
    public void completeSetup() {
        userRepository = applicationContext.getBean(UserRepository.class);
    }
	@Autowired
	public UserServiceImpl(UserRepository repository) {
		super();
		this.userRepository = repository;
	}

	public List<AppUser> getAllUsers() {
		List<AppUser> list = new ArrayList<AppUser>();
		userRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	public AppUser getUserById(Long id) {
		AppUser user = userRepository.findById(id).get();
		return user;
	}

	public boolean saveUser(AppUser appUser) {
		try {
			userRepository.save(appUser);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	public boolean deleteUserById(Long id) {
		try {
			userRepository.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appuser = userRepository.findByUsername(username);
		AppUserPrincipal appUserPrincipal = new AppUserPrincipal(appuser);
		return appUserPrincipal;
	}

	public Authentication getCurrentAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    return authentication;
		}
		return null;
	}


}
