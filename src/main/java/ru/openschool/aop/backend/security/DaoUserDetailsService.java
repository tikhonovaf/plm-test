package ru.openschool.aop.backend.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.openschool.aop.backend.model.MigrUser;
import ru.openschool.aop.backend.repository.MigrUserRepository;

import javax.inject.Inject;

@Component
public class DaoUserDetailsService implements UserDetailsService {
    @Inject
    private MigrUserRepository migrUserRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        MigrUser user = migrUserRepository.findByLogin(login)
//                .orElseThrow(() -> new UsernameNotFoundException("Username " + login + " not found!"));

        MigrUser user = new MigrUser();
        user.setLogin("ADMIN");
        user.setPassword("8257a3811b9f6bb9d59dfb3931e220fa5574cee38fff551066caca1a50b1691ebdffa87f2d7213910e8bdbcf4d669c2756e57196667dd8f5e8af66971b2");

        //                .orElseThrow(() -> new UsernameNotFoundException("Username " + login + " not found!"));

        return User.withUsername(login)
                .password(user.getPassword())
                .roles(user.getRole() == null ? "ADMIN" : user.getRole().getName())
                .build();
    }
}
