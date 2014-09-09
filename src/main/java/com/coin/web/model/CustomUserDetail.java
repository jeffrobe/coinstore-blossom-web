package com.coin.web.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.coin.web.service.impl.CustomUserDetailsService;

public class CustomUserDetail implements UserDetails { 
	private static final Logger log = Logger.getLogger(CustomUserDetail.class);
	private static final long serialVersionUID = 1L;
     
    private String username;
    
	@Override
	public String getUsername() {  return this.username; }
    public void setUsername(String v) { this.username = v;  }

    private Long id;
    public Long getId() { return id;    }
    public void setId(Long id) { this.id = id;    }
 
	//private String login;
    //public String getLogin() { return login;    }
    //public void setLogin(String login) { this.login = login;  }
 
	private String token;
    public String getToken() {  return token;    }
    public void setToken(String v) {  this.token = v;  }
    
	private String ip;
    public String getIp() { return ip;    }
    public void setIp(String v) { this.ip = v;  }
    
    private String displayName;
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String v) { this.displayName = v; }
    
    private String status;
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v;  }
     
    private String password;
    public String getPassword() {  return password; }
    public void setPassword(String password) { this.password = password; }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles());
		return authList;
	}
	     
	private List<String> roles;
	public List<String> getRoles() { return this.roles; }
	public void setRoles( List<String> roles ) { this.roles=roles; }
	 
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		 
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) authorities.add(new SimpleGrantedAuthority(role));
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
	

    @Override
	public String toString() {
		return "CustomUserDetail [id=" + id+ ", username=" + username + ",  password=" + password + ",  status=" + status + "]";
	}
}