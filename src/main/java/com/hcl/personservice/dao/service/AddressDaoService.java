package com.hcl.personservice.dao.service;

import com.hcl.personservice.model.Address;
import com.hcl.personservice.model.Person;
import com.hcl.personservice.repository.AddressRepository;
import com.hcl.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressDaoService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressDaoService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> getOneById(long id) {
        return addressRepository.findById(id);
    }

    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Address address) {
        return addressRepository.save(address);
    }

    public boolean delete(long id) {
        addressRepository.deleteById(id);
        return true;
    }

}
