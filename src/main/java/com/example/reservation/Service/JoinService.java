package com.example.reservation.Service;

import com.example.reservation.Repository.UserRepository;
import com.example.reservation.dto.EmailRequestDTO;
import com.example.reservation.dto.JoinDTO;
import com.example.reservation.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//회원가입 서비스
@Service
public class JoinService {
    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO, EmailRequestDTO emailRequestDTO){

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String email = emailRequestDTO.getEmail();
        String introduce = joinDTO.getIntroduce();


        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setEmail(email);
        data.setIntroduce(introduce);
        data.setRole("ROLE_ADMIN");

        System.out.println(data);
        userRepository.save(data);
    }


}

