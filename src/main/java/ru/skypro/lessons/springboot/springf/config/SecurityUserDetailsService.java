package ru.skypro.lessons.springboot.springf.config;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.springf.repository.AuthUserRepository;

@Component
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AuthUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUserPrincipal(user);
    }

}

