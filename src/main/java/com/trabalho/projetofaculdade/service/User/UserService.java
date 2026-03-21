package com.trabalho.projetofaculdade.service.User;

import com.trabalho.projetofaculdade.dto.user.UserRequestDTO;
import com.trabalho.projetofaculdade.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> GetAll();

    UserResponseDTO GetById(Long id);

    UserResponseDTO Add(UserRequestDTO dto);

    UserResponseDTO Update(Long id, UserRequestDTO dto);

    void Delete(Long id);

}
