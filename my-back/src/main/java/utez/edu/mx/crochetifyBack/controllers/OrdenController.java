package utez.edu.mx.crochetifyBack.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.orden.OrdenCreateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.shipment.ShipmentCreateRequest;
import utez.edu.mx.crochetifyBack.services.orden.OrdenService;

@RestController
@RequestMapping("/orden")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping
    public ResponseEntity<ResponseObject> createOrden(@RequestBody OrdenCreateRequest request){
        ResponseObject response = ordenService.createOrden(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
