package com.hcl.personservice.service;

import com.hcl.personservice.dao.service.AddressDaoService;
import com.hcl.personservice.dao.service.PersonDaoService;
import com.hcl.personservice.dto.AddressDto;
import com.hcl.personservice.dto.PersonDto;
import com.hcl.personservice.dto.ProjectDto;
import com.hcl.personservice.exception.PersonNotFoundException;
import com.hcl.personservice.model.Address;
import com.hcl.personservice.model.Person;
import com.hcl.personservice.model.Project;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final static String SPACE = " ";

    private final AddressDaoService addressDaoService;
    @Autowired
    private final PersonService personService;
    @Autowired
    public AddressService(AddressDaoService addressDaoService, PersonService personService) {
        this.addressDaoService = addressDaoService;
        this.personService = personService;
    }
//    private ModelMapper modelMapper = new ModelMapper();

    public List<AddressDto> getAll() {
        final List<Address> addresses = addressDaoService.getAll();
        return toDto(addresses);
    }

    public AddressDto getOneById(long id) {
        final Optional<Address> optionalAddress = addressDaoService.getOneById(id);
        System.out.println("################################");
        System.out.println(optionalAddress.get().getPerson());
        if (optionalAddress.isEmpty()) {
            throw new EntityNotFoundException("Address with #id" + id + " not found");
//            throw new EntityNotFoundException(id);
        }
        return toDto(optionalAddress.get());
    }

    public PersonDto getPersonByAddressId(long id) {
        final Optional<Person> optionalPerson = addressDaoService.getPersonByAddressId(id);
        System.out.println("################################");
        System.out.println(optionalPerson.get());
        if (optionalPerson.isEmpty()) {
            throw new EntityNotFoundException("Address with #id" + id + " not found");
//            throw new EntityNotFoundException(id);
        }
        return personService.toDto(optionalPerson.get());
//        return toDto(optionalPerson.get());
    }

    public boolean delete(long id) {
        return addressDaoService.delete(id);
    }

    public AddressDto create(AddressDto addressDto) {
        final Address address = toEntity(addressDto);
        final Address savedAddress = addressDaoService.create(address);
        return toDto(savedAddress);
    }

    public AddressDto update(AddressDto addressDto) {
        final Address address = toEntity(addressDto);
        final Address updatedAddress = addressDaoService.update(address);
        return toDto(updatedAddress);
    }



    // Utility methods ---------------------------------------------------
    private List<AddressDto> toDto(List<Address> addresses) {
        return addresses.stream()
                .map(address -> toDto(address))
                .collect(Collectors.toList());
    }

    private AddressDto toDto(Address address) {
//        final TypeMap<Person, PersonDto> typeMap = modelMapper.createTypeMap(Person.class, PersonDto.class);
//        typeMap.addMapping(Person::getFirstName, PersonDto::setFullName);
//        final PersonDto map = typeMap.map(person);

        AddressDto result = new AddressDto();
        result.setId(address.getId());
        result.setCity(address.getCity());
        result.setState(address.getState());
        return result;
    }

    private Address toEntity(AddressDto addressDto) {
        final Address address = new Address();
        address.setId(addressDto.getId());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        return address;
    }


}
