package gr.dit.voluntia.demo.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public CustomUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


    /**
     * Description:
     * Retrieves the authorities granted to the user.
     * @return  a collection of {@link GrantedAuthority} representing the user's authorities.
     * * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    /**
     * Description:
     * Retrieves the encripted password of the user
     * @return the user's password as a {@code String}.
     * * */
    @Override
    public String getPassword() {
        return password;
    }


    /**
     * Description:
     * Retrieves the username of the user
     * @return the user's username as a {@code String}.
     * * */
    @Override
    public String getUsername() {
        return username;
    }


    /**
     * Description:
     * Indicates whether the user's account has expired.
     * @return {@code true} if the account is non-expired, {@code false} otherwise.
     * * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

     /**
     * Description:
     * Indicates whether the user's account is locked or unlocked.
     * @return {@code true} if the account is non-locked, {@code false} otherwise.
     * * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


     /**
     * Description:
     * Indicates whether the user's credentials (password) have expired.
     * @return {@code true} if the credentials are non-expired, {@code false} otherwise.
     * * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


     /**
     * Description:
     * Indicates whether the user's account is enabled or disabled.
     * @return {@code true} if the user is enabled, {@code false} otherwise.
     * * */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
