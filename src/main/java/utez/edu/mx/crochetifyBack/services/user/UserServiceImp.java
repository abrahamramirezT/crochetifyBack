package utez.edu.mx.crochetifyBack.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.user.UserUpdateStatusRequest;
import utez.edu.mx.crochetifyBack.repositories.UserRepository;
import utez.edu.mx.crochetifyBack.entities.ERole;
import utez.edu.mx.crochetifyBack.entities.Role;
import utez.edu.mx.crochetifyBack.entities.User;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.exceptions.CustomNotFoundException;

@Service
public class UserServiceImp implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseObject createUser(UserCreateRequest request) {
        try {
            Role userRole = Role.builder()
                    .name(ERole.USER)
                    .build();

            User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .image(request.getImage())
                    .status(true)
                    .role(userRole)
                    .build();

            userRepository.save(user);
            return new ResponseObject(true, "Usuario registrado con exito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al registrar el usuario: {}", e.getMessage(), e);
            throw new CustomException("Ocurrio un error al registrar el usaurio");
        }

    }

    @Override
    public ResponseObject updateUser(UserUpdateRequest request) {
        try {
            User currentUser = userRepository.findById(request.getIdUser())
                    .orElseThrow(() -> new CustomNotFoundException(
                            "Usuario con ID " + request.getIdUser() + " no encontrado"));

            if (request.getName() != null && !request.getName().equals(currentUser.getName())) {
                currentUser.setName(request.getName());
            }

            if (request.getImage() != null && !request.getImage().equals(currentUser.getImage())) {
                currentUser.setImage(request.getImage());
            }

            userRepository.save(currentUser);

            return new ResponseObject(true, "Usuario actualizado con éxito", null);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de actualizar un usuario que no existe: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al actualizar el usuario: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al actualizar el usuario");
        }
    }

    @Override
    public ResponseObject updateUserStatus(UserUpdateStatusRequest request) {
        try {
            User currentUser = userRepository.findById(request.getIdUser())
                    .orElseThrow(() -> new CustomNotFoundException(
                            "Usuario con ID " + request.getIdUser() + " no encontrado"));

            if (currentUser.isStatus() != request.isStatus()) {
                currentUser.setStatus(request.isStatus());
            }
            userRepository.save(currentUser);
            return new ResponseObject(true, "Estado del usuario actualizado con éxito", null);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de actualizar un usuario que no existe: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al actualizar el estado del usuario: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al actualizar el estado del usuario");
        }
    }

    @Override
    public ResponseObject getUserById(Long idUser) {
        try {
            User currentUser = userRepository.findById(idUser)
                    .orElseThrow(() -> new CustomNotFoundException("Usuario con ID " + idUser + " no encontrado"));

            return createResponseObject("Usuario recuperado con éxito", currentUser);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de recuperar un usuario que no existe: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar usuario: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar el usuario");
        }

    }

    @Override
    public ResponseList getUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new CustomNotFoundException("No existen usuarios registrados");
            }
            return createResponseList("Usuario recuperado con éxito", users);
        } catch (CustomNotFoundException e) {
            log.warn("Error: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al recuperar los usuarios: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al recuperar los usuarios");
        }
    }

    @Override
    public ResponseObject deleteUserbyID(Long idUser) {
        try {
            if (!userRepository.existsById(idUser)) {
                throw new CustomNotFoundException("Usuario con ID " + idUser + " no encontrado");
            }
            userRepository.deleteById(idUser);
            return createResponseObject("Usuario eliminado con éxito", null);

        } catch (CustomNotFoundException e) {
            log.warn("Intento de eliminar un usuario que no existe: {}", e.getMessage());
            throw e;

        } catch (Exception e) {
            log.error("Ocurrió un error al eliminar el usuario: {}", e.getMessage());
            throw new CustomException("Ocurrió un error al eliminar el usuario");
        }
    }

    private ResponseObject createResponseObject(String message, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        return new ResponseObject(true, message, response);
    }

    private ResponseList createResponseList(String message, List<User> users) {
        Map<String, List<?>> response = new HashMap<>();
        response.put("users", users);
        return new ResponseList(true, message, response);
    }

}
