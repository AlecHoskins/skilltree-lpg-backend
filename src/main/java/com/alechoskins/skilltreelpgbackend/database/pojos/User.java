package com.alechoskins.skilltreelpgbackend.database.pojos;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stl_user")
public class User implements UserDetails {

    //region PROPERTIES

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;

    @Column(nullable = true)
    private boolean isAccountNonExpired;
    @Column(nullable = true)
    private boolean isAccountNonLocked;
    @Column(nullable = true)
    private boolean isCredentialsNonExpired;
    @Column(nullable = true)
    private boolean isEnabled;
    //endregion

    //region RELATIONSHIPS

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    //endregion

    //region METHODS

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles != null){
            return roles.stream().map(
                    x -> new SimpleGrantedAuthority( x.getName() )
            ).toList();
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    //endregion
}
