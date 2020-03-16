package com.rabbitforever.knowledge.models.eos;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "APP_ROLES")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false, updatable = false)
    private Long id;
    
	@Column(name = "NAME", nullable = false, unique=true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "APP_ROLE_ID")
    private List<AppPrivilege> appPrivilegeList;
    
    @ManyToMany(mappedBy="appRoleList",
    		fetch = FetchType.EAGER)
    private List<AppUser> appUserList;
    
    private AppRole() {
    	
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

	public List<AppPrivilege> getAppPrivilegeList() {
		return appPrivilegeList;
	}

	public void setAppPrivilegeList(List<AppPrivilege> appPrivilegeList) {
		this.appPrivilegeList = appPrivilegeList;
	}

	public List<AppUser> getAppUserList() {
		return appUserList;
	}

	public void setAppUserList(List<AppUser> appUserList) {
		this.appUserList = appUserList;
	}



}

