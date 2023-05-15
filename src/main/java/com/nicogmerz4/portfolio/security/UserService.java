package com.nicogmerz4.portfolio.security;

import com.nicogmerz4.portfolio.dto.UserDTO;
import com.nicogmerz4.portfolio.repository.UserRepository;
import com.nicogmerz4.portfolio.service.CustomResponse;
import com.nicogmerz4.portfolio.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nicogmerz4.portfolio.model.User;
import com.nicogmerz4.portfolio.utils.ObjectMapperUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private ImageStorageService imageStorageService;

    public CustomResponse getUser() {
        User user = repo.findOneByName("admin").orElse(null);
        CustomResponse response = new CustomResponse();

        if (user != null) {
            UserDTO userDTO = ObjectMapperUtils.map(user, UserDTO.class);
            response.getBody().addData(userDTO);
            
            return response;
        }
        
        throw new RuntimeException("No se pudo recuperar el usuario.");
    }

    public CustomResponse uploadProfile(MultipartFile file) {
        User user = repo.findOneByName("admin").orElse(null);
        CustomResponse response = new CustomResponse();

        if (user != null) {
            String filename = imageStorageService.store(file, "profile");
            user.setProfile(filename);

            user = repo.save(user);

            UserDTO userDTO = ObjectMapperUtils.map(user, UserDTO.class);
            response.getBody().addData(userDTO);
            return response;
        }

        throw new RuntimeException("Algo salió mal.");
    }

    public CustomResponse uploadBackground(MultipartFile file) {
        User user = repo.findOneByName("admin").orElse(null);
        CustomResponse response = new CustomResponse();

        if (user != null) {
            String filename = imageStorageService.store(file, "background");
            user.setBackground(filename);

            user = repo.save(user);

            UserDTO userDTO = ObjectMapperUtils.map(user, UserDTO.class);
            response.getBody().addData(userDTO);
            return response;
        }

        throw new RuntimeException("Algo salió mal.");
    }
}
