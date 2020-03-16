package com.rabbitforever.knowledge.models.eos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserPrincipal implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7068947511129060582L;
	private final AppUser user;

    //

    public AppUserPrincipal(AppUser user) {
        this.user = user;
    }

    //

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> authorities = null;
       List<AppRole> appRoleList = user.getAppRoleList();
       if (appRoleList != null && appRoleList.size() > 0) {
    	   authorities = new ArrayList<GrantedAuthority>();
    	   for (AppRole appRole: appRoleList) {
    		   authorities.add(new SimpleGrantedAuthority(appRole.getName()));
    	   }
       }
//       List<GrantedAuthority> authorities =  Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//        authorities.add(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));)
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //

    public AppUser getAppUser() {
        return user;
    }

}
