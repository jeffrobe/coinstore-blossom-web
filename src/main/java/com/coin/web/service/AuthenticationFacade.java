package com.coin.web.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.coin.web.model.CustomUserDetail;

 
@Component
public class AuthenticationFacade implements IAuthenticationFacade  {
 
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    @Override
    public String getUserName() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (auth==null) return null;
  		
    	Object princ = auth.getPrincipal();
  	    if (princ==null) return null;
  	    
  		if (princ instanceof CustomUserDetail) {
  			CustomUserDetail user = (CustomUserDetail) princ;    
  		    return user.getUsername();
  		}
  		
  		
        return null;
    }
    
    @Override
    public String getUserDisplayName() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (auth==null) return null;
  		
    	Object princ = auth.getPrincipal();
  	    if (princ==null) return null;
  	    
  		if (princ instanceof CustomUserDetail) {
  			CustomUserDetail user = (CustomUserDetail) princ;    
  		    return user.getUsername();
  		}
  		
  		
        return null;
    }
    
    @Override
    public Boolean isLoggedIn() {
    	if ( getUserId() == null ) return false;
  		
        return true;
    }
    
    @Override
    public Long getUserId() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (auth==null) return null;
  		
    	Object princ = auth.getPrincipal();
  	    if (princ==null) return null;
  	    
  		if (princ instanceof CustomUserDetail) {
  			CustomUserDetail user = (CustomUserDetail) princ;    
  		    return user.getId();
  		}
  		
  		
        return null;
    }
}