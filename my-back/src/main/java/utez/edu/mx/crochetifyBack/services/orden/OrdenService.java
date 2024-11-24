package utez.edu.mx.crochetifyBack.services.orden;

import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.orden.OrdenCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.shipment.ShipmentCreateRequest;

public interface OrdenService {
    ResponseObject createOrden(OrdenCreateRequest request);
}
