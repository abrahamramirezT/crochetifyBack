package utez.edu.mx.crochetifyBack.services.direction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionUserRequest;
import utez.edu.mx.crochetifyBack.entities.Direction;
import utez.edu.mx.crochetifyBack.entities.User;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.repositories.DirectionRepository;
import utez.edu.mx.crochetifyBack.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirectionServiceImp implements DirectionService{
    private static final Logger log = LoggerFactory.getLogger(DirectionServiceImp.class);


    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseObject createDirection(DirectionCreateRequest request) {
        try {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new CustomException("Usuario no encontrado"));


            Direction direction = Direction.builder()
                    .direction(request.getDirection())
                    .phone(request.getPhone())
                    .user(user)
                    .build();


            Direction savedDirection = directionRepository.save(direction);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> directionMap = mapper.convertValue(savedDirection, Map.class);

            return new ResponseObject(true, "Dirección registrada con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al registrar la dirección: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al registrar la dirección");
        }
    }

    @Override
    public ResponseObject updateDirection(Long id, DirectionUpdateRequest request) {
        try {
            if (id == null) {
                throw new CustomException("El ID de la dirección no puede ser nulo");
            }

            Direction existingDirection = directionRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Dirección no encontrada"));

            existingDirection.setDirection(request.getDirection());
            existingDirection.setPhone(request.getPhone());

            Direction updatedDirection = directionRepository.save(existingDirection);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> directionMap = mapper.convertValue(updatedDirection, Map.class);

            return new ResponseObject(true, "Dirección actualizada con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al actualizar la dirección: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al actualizar la dirección");
        }
    }

    @Override
    public ResponseObject getDirectionsByUserId(Long idUser) {
        try {
            if (idUser == null) {
                throw new CustomException("El ID del usuario no puede ser nulo");
            }

            List<Direction> directions = directionRepository.findByUser_IdUser(idUser);

            if (directions.isEmpty()) {
                return new ResponseObject(true, "No se encontraron direcciones asociadas al usuario", Collections.emptyMap());
            }

            Map<String, Object> directionMap = directions.stream()
                    .collect(Collectors.toMap(
                            direction -> String.valueOf(direction.getIdDirection()),
                            direction -> Map.of(
                                    "idDirection", direction.getIdDirection(),
                                    "direction", direction.getDirection(),
                                    "phone", direction.getPhone()
                            )
                    ));

            // Retornar la respuesta con éxito
            return new ResponseObject(true, "Direcciones encontradas con éxito", directionMap);

        } catch (Exception e) {
            log.error("Ocurrió un error al buscar las direcciones: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al buscar las direcciones");
        }
    }





}
