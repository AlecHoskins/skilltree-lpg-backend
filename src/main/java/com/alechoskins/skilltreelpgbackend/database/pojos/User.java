package com.alechoskins.skilltreelpgbackend.database.pojos;

import com.alechoskins.skilltreelpgbackend.global.AppEnums;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    private String username;
    private String password;
    private String email;
    private boolean isActive;
    private ArrayList<AppEnums.Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles != null){
            return roles.stream().map(
                    x -> new SimpleGrantedAuthority( x.getValue() )
            ).toList();
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
