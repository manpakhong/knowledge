package com.rabbitforever.knowledge.repos;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.rabbitforever.knowledge.models.eos.AppUser;

public interface UserRepository 
extends 
//JpaRepository<AppUser, Long> 
CrudRepository<AppUser, Long> 
{
	public AppUser findByUsername(String username);

	
	
    @Query("UPDATE AppUser u SET u.lastLogin=:lastLogin WHERE u.username = ?#{ principal?.username }")
    @Modifying
    @Transactional
    public void updateLastLogin(@Param("lastLogin") Date lastLogin);
}
