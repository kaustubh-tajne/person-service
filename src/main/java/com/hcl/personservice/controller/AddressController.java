package com.hcl.personservice.controller;

import com.hcl.personservice.dto.AddressDto;
import com.hcl.personservice.dto.PersonDto;
import com.hcl.personservice.service.AddressService;
import com.hcl.personservice.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressService/v1/addresses")
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class.getName());

    //    @Autowired
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        System.out.println("Args of addressController constructor called");
        this.addressService = addressService;
    }

    @GetMapping("/data")
    public void getData(@RequestParam String name, @RequestParam int myAge) {
        LOGGER.info("getData");
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll() {
        List<AddressDto> result = addressService.getAll();
//        ResponseEntity<List<PersonDto>> responseEntity = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
//        ResponseEntity<List<PersonDto>> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
//        ResponseEntity<List<PersonDto>> responseEntity = ResponseEntity.accepted().body(result);

        ResponseEntity<List<AddressDto>> responseEntity = ResponseEntity.ok(result);
        ResponseEntity.ok().body(result);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getOneById(@PathVariable long id) {
        LOGGER.info("GetOneById - {}", id);

        final AddressDto result = addressService.getOneById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
//            throw new Exception();
        }
        return ResponseEntity.ok(result);
    }

    // Getting the person from address
    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDto> getPersonByAddressId(@PathVariable long id) {
        LOGGER.info("GetPersonByAddress - {} ", id);

        final PersonDto result = addressService.getPersonByAddressId(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
//            throw new Exception();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@RequestBody AddressDto addressDto) {
        LOGGER.info("Person Got Created - {}", addressDto);

        final AddressDto result = addressService.create(addressDto);
        if (result == null)
            return ResponseEntity.unprocessableEntity().build();
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AddressDto> update(@RequestBody AddressDto addressDto) {
        LOGGER.info("Person Got updated - {}", addressDto);

        final AddressDto result = addressService.update(addressDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity()
                    .build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        LOGGER.info("Person Got deleted {}", id);

        final boolean isDeleted = addressService.delete(id);
        if (!isDeleted) {
            // check the more perfect status code
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }




}
