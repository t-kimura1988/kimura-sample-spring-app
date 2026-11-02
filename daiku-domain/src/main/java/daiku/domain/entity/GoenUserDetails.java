package daiku.domain.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class GoenUserDetails implements UserDetails {

    private final String username;

    private final TAccounts account;

    private final Collection<? extends GrantedAuthority> authorities;

    public GoenUserDetails(String username, TAccounts account, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.account = account;
        this.authorities = authorities;
    }

    public TAccounts account() {
        return this.account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
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
        return false;
    }
}
