package com.rabbitforever.knowledge.models.eos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "APP_PRIVILEGES",
uniqueConstraints=
@UniqueConstraint(columnNames={"APP_ROLE_ID", "APP_USER_ID"})
		)
public class AppPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false, updatable = false, unique = true)
    private Long id;


	@Column(name = "NAME", nullable = false)
    private String name;

	@Column(name = "APP_ROLE_ID", nullable = false)
    private Long appRoleId;
	
	@Column(name = "APP_USER_ID", nullable = false)
    private Long appUserId;


//    @ManyToOne(fetch = FetchType.LAZY)
//    private AppRole appRole;
//	
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    private AppUser appUser;
    
    
    private AppPrivilege() {
    	
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Long getAppRoleId() {
		return appRoleId;
	}

	public void setAppRoleId(Long appRoleId) {
		this.appRoleId = appRoleId;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

//	public AppRole getAppRole() {
//		return appRole;
//	}
//
//	public void setAppRole(AppRole appRole) {
//		this.appRole = appRole;
//	}
//
//	public AppUser getAppUser() {
//		return appUser;
//	}
//
//	public void setAppUser(AppUser appUser) {
//		this.appUser = appUser;
//	}


    
}

