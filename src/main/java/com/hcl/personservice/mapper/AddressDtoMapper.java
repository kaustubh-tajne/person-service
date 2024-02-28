package com.hcl.personservice.mapper;

import com.hcl.personservice.dto.AddressDto;
import com.hcl.personservice.model.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressDtoMapper {

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
