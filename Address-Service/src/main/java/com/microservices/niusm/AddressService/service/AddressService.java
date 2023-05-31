package com.microservices.niusm.AddressService.service;

import com.microservices.niusm.AddressService.entity.Address;
import com.microservices.niusm.AddressService.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AddressService {


    @Autowired
    private AddressRepository addressRepository;

    public Mono<Address> save(Address address) {
        return addressRepository.save(address);
    }

    public Flux<Address> getAll() {
        return addressRepository.findAll();
    }

    public Mono<Address> getFindById(int id) {
        return addressRepository.findById(id);
    }

    public Mono<Void> deleteById(int id) {
        return addressRepository.deleteById(id);
    }
}
