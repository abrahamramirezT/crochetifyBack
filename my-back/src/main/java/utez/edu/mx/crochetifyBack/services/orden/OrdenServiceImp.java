package utez.edu.mx.crochetifyBack.services.orden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.orden.OrdenCreateRequest;
import utez.edu.mx.crochetifyBack.entities.Orden;
import utez.edu.mx.crochetifyBack.exceptions.CustomException;
import utez.edu.mx.crochetifyBack.repositories.OrdenRepository;

@Service
public class OrdenServiceImp implements OrdenService{

    private static final Logger log = LoggerFactory.getLogger(OrdenServiceImp.class);

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public ResponseObject createOrden(OrdenCreateRequest request) {
        try {
            Orden orden = Orden.builder()
                    .total(request.getTotal())
                    .purchase_day(request.getPurchase_day())
                    .build();

            Orden savedOrden = ordenRepository.save(orden);

            return new ResponseObject(true, "Orden registrada con éxito", null);

        } catch (Exception e) {
            log.error("Ocurrió un error al registrar la Orden: {}", e.getMessage(), e);
            throw new CustomException("Ocurrió un error al registrar la Orden");
        }
    }

}
