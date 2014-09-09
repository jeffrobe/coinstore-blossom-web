package com.coin.web.forms;

import java.util.Set;

import com.coin.model.Notificationtype;


public class ProfileForm {
	private Long id;
    public Long getId() {  return id; }
    public void setId(Long v) { this.id = v; }

    private Long userId;
    public Long getUserId() {  return userId; }
    public void setUserId(Long v) { this.userId = v; }

    private String firstname;
    public String getFirstname() {  return firstname; }
    public void setFirstname(String v) { this.firstname = v; }

    private String displayname;
    public String getDisplayname() { return displayname; }
    public void setDisplayname(String v) { this.displayname = v; }

    private String username;
    public String getUsername() { return username; }
    public void setUsername(String v) { this.username = v; }


    private String tos;
    public String getTos() {  return tos; }
    public void setTos(String v) { this.tos = v; }

    
    private String lastname;
    public String getLastname() {  return lastname; }
    public void setLastname(String v) { this.lastname = v; }


    private String hash;
    public String getHash() { return hash;  }
    public void setHash(String v) { this.hash = v; }

    private String password;
    public String getPassword() {  return password; }
    public void setPassword(String v) { this.password = v; }

    private String password2;
    public String getPassword2() {  return password2; }
    public void setPassword2(String v) { this.password2 = v; }

    private String email;
    public String getEmail() { return email;  }
    public void setEmail(String v) { this.email = v; }
    
    private String phone;
    public String getPhone() { return phone;  }
    public void setPhone(String v) { this.phone = v; }
    
    private String token;
    public String getToken() { return token;  }
    public void setToken(String v) { this.token = v; }

    private String authyid;
    public String getAuthyid() { return authyid;  }
    public void setAuthyid(String v) { this.authyid = v; }
     
    private String imageResponseField;
    public String getImageResponseField() { return imageResponseField;  }
    public void setImageResponseField(String v) { this.imageResponseField = v; }
    
    private String imageChallengeField;
    public String getImageChallengeField() { return imageChallengeField;  }
    public void setImageChallengeField(String v) { this.imageChallengeField = v; }
        
    private Set<Notificationtype> notificationtypes;
    public void setNotificationtypes(Set<Notificationtype> v) { this.notificationtypes=v; }
    public Set<Notificationtype> getNotificationtypes() { return this.notificationtypes; }

    private String[] notificationtypestr;
    public void setNotificationtypestr(String[] v) { this.notificationtypestr=v; }
    public String[] getNotificationtypestr() { return this.notificationtypestr; }

}
