package ru.gb.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.gb.domain.User;
import ru.gb.repository.RoleRepository;
import ru.gb.repository.UserRepository;

import java.util.Optional;

public class UserDetailsImpl implements UserDetailsService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserDetailsImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException("Can't find user " + username);
        }
        User user = optUser.get();
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }
}
