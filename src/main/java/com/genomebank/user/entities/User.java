package com.genomebank.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Rol_User", // Tabla intermedia
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    private Boolean active = Boolean.TRUE;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(rol -> (GrantedAuthority) () -> "ROLE_" + rol.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override public boolean isAccountNonExpired() { return true; } // La cuenta nunca expira
    @Override public boolean isAccountNonLocked() { return true; } // La cuenta nunca se bloquea
    @Override public boolean isCredentialsNonExpired() { return true; } // Las credenciales nunca expiran
    @Override public boolean isEnabled() { return active; } // El usuario está habilitado si activo es true
}
