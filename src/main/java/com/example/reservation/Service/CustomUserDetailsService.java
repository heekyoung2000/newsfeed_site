package com.example.reservation.Service;

import com.example.reservation.Repository.UserRepository;
import com.example.reservation.dto.CustomUserDetails;
import com.example.reservation.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //db에서 조회
        UserEntity userData = userRepository.findByUsername(username);

        System.out.println("✨✨✨✨"+userData);
        System.out.println("✨✨✨✨"+username);
        if(userData!=null){
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            System.out.println("✔✔✔ userData가 customuserdetails로 넘어감");
            return new CustomUserDetails(userData);
        }
        return null;
    }
}