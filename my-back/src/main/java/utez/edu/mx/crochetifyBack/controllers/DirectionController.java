package utez.edu.mx.crochetifyBack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.crochetifyBack.dto.ResponseList;
import utez.edu.mx.crochetifyBack.dto.ResponseObject;
import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionCreateRequest;

import utez.edu.mx.crochetifyBack.dto.requests.direction.DirectionUpdateRequest;
import utez.edu.mx.crochetifyBack.dto.requests.stock.StockUpdateRequest;
import utez.edu.mx.crochetifyBack.services.direction.DirectionService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/directions")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    @PostMapping
    public ResponseEntity<ResponseObject> createDirection(@RequestBody DirectionCreateRequest request){
        ResponseObject response = directionService.createDirection(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateDirection(
            @PathVariable Long id,
            @RequestBody DirectionUpdateRequest request) {
        ResponseObject response = directionService.updateDirection(id,request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<ResponseObject> getDirectionsByUserId(@PathVariable Long idUser) {
        ResponseObject response = directionService.getDirectionsByUserId(idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}