package com.rabbitforever.knowledge.models.eos;

import java.util.Date;
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
@Table(name = "APP_USERS")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID", nullable = false, updatable = false)
    private Long id;
	@Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD", nullable = true)
    private String password;
    @Column(name = "ENABLED", nullable = true)
    private Boolean enabled = true;
    @Column(name = "LAST_LOGIN_DATETIME", nullable = true)
    private Date lastLogin;

    
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "APP_USER_ID")
    private List<AppPrivilege> appPrivilegeList;
    

    

//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(name="APP_PRIVILEGES", joinColumns={@JoinColumn(referencedColumnName="APP_USER_ID")}
//                                        , inverseJoinColumns={@JoinColumn(referencedColumnName="APP_ROLE_ID")})
    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
    		CascadeType.ALL
        },
    		fetch = FetchType.EAGER
    		)
        @JoinTable(name = "APP_PRIVILEGES",
            joinColumns = @JoinColumn(name = "APP_USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "APP_ROLE_ID")
        )
    private List<AppRole> appRoleList;
    
    private AppUser() {
    	
    }
    public AppUser(String name, String email, String password) {
        this.username = email;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public List<AppPrivilege> getAppPrivilegeList() {
		return appPrivilegeList;
	}
	public void setAppPrivilegeList(List<AppPrivilege> appPrivilegeList) {
		this.appPrivilegeList = appPrivilegeList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<AppRole> getAppRoleList() {
		return appRoleList;
	}
	public void setAppRoleList(List<AppRole> appRoleList) {
		this.appRoleList = appRoleList;
	}


    
    
}
