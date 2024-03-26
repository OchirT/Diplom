package ru.netology.diplomproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.diplomproject.exceptions.ErrorInputDataException;
import ru.netology.diplomproject.repository.UserRepository;
import ru.netology.diplomproject.model.AppUser;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {


    private final UserRepository dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = dao.findByEmail(username);
        if (appUser == null) {
            throw new ErrorInputDataException("User service Unauthorized");
        }
        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .build();
    }
}
