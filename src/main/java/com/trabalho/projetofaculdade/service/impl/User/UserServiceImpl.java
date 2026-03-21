package com.trabalho.projetofaculdade.service.impl.User;

import com.trabalho.projetofaculdade.dto.user.UserRequestDTO;
import com.trabalho.projetofaculdade.dto.user.UserResponseDTO;
import com.trabalho.projetofaculdade.exception.RecursoNaoEncontradoException;
import com.trabalho.projetofaculdade.model.User;
import com.trabalho.projetofaculdade.repository.User.UserRepository;
import com.trabalho.projetofaculdade.service.Log.ActionLogService;
import com.trabalho.projetofaculdade.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ActionLogService actionLogService;

    @Override
    public List<UserResponseDTO> GetAll() {
        try{
            return userRepository.findAll()
                    .stream()
                    .map(this::toResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            actionLogService.log("User", "ERROR", "Ocorreu um erro inesperado ao buscar todos os registros.\n Stack Trace: " + e.getStackTrace() + "Mensagem:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserResponseDTO GetById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    actionLogService.log("User", "ERROR", "Usuário não encontrado com id: " + id);
                    return new RecursoNaoEncontradoException("Usuario não encontrado com id: " + id);
                });

        actionLogService.log("User", "GET", "Usuário encontrado com o nome:" + user.getName());
        return toResponseDTO(user);
    }

    @Override
    public UserResponseDTO Add(UserRequestDTO dto) {

        try {
            User user = User.builder()
                    .Document(dto.getDocument())
                    .Name(dto.getName())
                    .MotherName(dto.getMotherName())
                    .areaCode(dto.getAreaCode())
                    .Celphone(dto.getCelphone())
                    .Email(dto.getEmail())
                    .BirthDate(dto.getBirthDate())
                    .RegistrationNumber(dto.getRegistrationNumber())
                    .UserType(dto.getUserType())
                    .CreatedAt(dto.getCreatedAt())
                    .Active(dto.getActive())
                    .build();
            UserResponseDTO response = toResponseDTO(userRepository.save(user));
            actionLogService.log("User", "ADD", "Usuário criado com documento: " + dto.getDocument());
            return response;
        } catch (Exception e) {
            actionLogService.log("User", "ERROR", "Ocorreu um erro inesperado ao adicionar um novo usuario.\n Stack Trace: " + e.getStackTrace() + "Mensagem:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserResponseDTO Update(Long id, UserRequestDTO dto) {

        try{
            User user = userRepository.findById(id)
                    .orElseThrow(() -> {
                                actionLogService.log("User", "ERROR", "Usuario não encontrado com id: " + id);
                                return new RecursoNaoEncontradoException("Usuario não encontrado com id: " + id);
                            });

            user.setDocument(dto.getDocument());
            user.setName(dto.getName());
            user.setMotherName(dto.getMotherName());
            user.setAreaCode(dto.getAreaCode());
            user.setCelphone(dto.getCelphone());
            user.setEmail(dto.getEmail());
            user.setBirthDate(dto.getBirthDate());
            user.setRegistrationNumber(dto.getRegistrationNumber());
            user.setUserType(dto.getUserType());
            user.setCreatedAt(dto.getCreatedAt());
            user.setActive(dto.getActive());

            UserResponseDTO response = toResponseDTO(userRepository.save(user));
            actionLogService.log("User", "UPDATE", "Usuário atualizado com id: " + id);
            return response;
        } catch (Exception e) {
            actionLogService.log("User", "ERROR", "Ocorreu um erro inesperado ao atualizar um usuario.\n Stack Trace: " + e.getStackTrace() + "Mensagem:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Delete(Long id) {

        try{
            User user = userRepository.findById(id)
                    .orElseThrow(() -> {
                    actionLogService.log("User", "ERROR", "Usuario não encontrado com id: " + id);
                return new RecursoNaoEncontradoException("Usuario não encontrado com id: " + id);
            });

            user.setActive(false); //Não deletamos para manter a auditoria

            userRepository.save(user);
            actionLogService.log("User", "DELETE", "Usuário desativado com id: " + id);
        } catch (Exception e) {
            actionLogService.log("User", "ERROR", "Ocorreu um erro inesperado ao desativar um usuario.\n Stack Trace: " + e.getStackTrace() + "Mensagem:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .Id(user.getId())
                .Document(user.getDocument())
                .Name(user.getName())
                .MotherName(user.getMotherName())
                .areaCode(user.getAreaCode())
                .Celphone(user.getCelphone())
                .Email(user.getEmail())
                .BirthDate(user.getBirthDate())
                .RegistrationNumber(user.getRegistrationNumber())
                .UserType(user.getUserType())
                .CreatedAt(user.getCreatedAt())
                .Active(user.getActive())
                .build();
    }
}
