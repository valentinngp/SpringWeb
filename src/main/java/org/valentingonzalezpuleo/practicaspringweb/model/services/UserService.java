package org.valentingonzalezpuleo.practicaspringweb.model.services;

import lombok.RequiredArgsConstructor;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.UserEntity;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.UserCreateDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.UserDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.entities.dtos.UserUpdateDTO;
import org.valentingonzalezpuleo.practicaspringweb.model.exceptions.UserNotFoundException;
import org.valentingonzalezpuleo.practicaspringweb.model.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));
        return modelMapper.map(entity, UserDTO.class);
    }

    // NUEVO: Método para buscar por username
    public UserDTO findByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con username: " + username));
        return modelMapper.map(entity, UserDTO.class);
    }

    // CORREGIDO: Ahora recibe UserCreateDTO
    public void save(UserCreateDTO dto) {
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        userRepository.save(entity);
    }

    // NUEVO: Método para actualizar usando UserUpdateDTO
    public void update(Long id, UserUpdateDTO dto) {
        // 1. Buscamos si existe
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));

        // 2. Actualizamos los campos
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        // 3. Guardamos
        userRepository.save(entity);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No se puede eliminar. Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
