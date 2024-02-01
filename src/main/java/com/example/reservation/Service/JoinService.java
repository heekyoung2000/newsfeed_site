package com.example.reservation.Service;

import com.example.reservation.Repository.UserRepository;
import com.example.reservation.dto.EmailRequestDTO;
import com.example.reservation.dto.JoinDTO;
import com.example.reservation.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    public void joinProcess(JoinDTO joinDTO, EmailRequestDTO emailRequestDTO, MultipartFile profileImage)throws IOException {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String email = emailRequestDTO.getEmail();
        String introduce = joinDTO.getIntroduce();
        
        //프로필 이미지를 서버에 업로드하고 경로를 설정
        String profileImagePath = saveProfileImage(profileImage);


        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setEmail(email);
        data.setIntroduce(introduce);
        data.setProfileImagePath(profileImagePath); // 프로필 이미지 경로 설정
        data.setRole("ROLE_ADMIN");

        System.out.println(data);
        userRepository.save(data);
    }

    private String saveProfileImage(MultipartFile profileImage) throws IOException {
        String profileImagePath = "";// 기본값으로 빈 문자열 설정
        if(profileImage!=null && !profileImage.isEmpty()){
            //프로필 이미지를 저장할 디렉토리 경로 설정(절대 경로 설정)
            String uploadDirectory = "D://바탕화면//reservation99";

            //업로드할 파일명 생성
            String fileName = profileImage.getOriginalFilename();
            //프로필 이미지를 서버에 업로드하고 저장
            File uploadPath = new File(uploadDirectory);
            if(!uploadPath.exists()){
                uploadPath.mkdirs();
            }
            File dest = new File(uploadPath + "/" + fileName);
            profileImage.transferTo(dest);
            profileImagePath = dest.getAbsolutePath();

        }
       return profileImagePath;
    }


}

