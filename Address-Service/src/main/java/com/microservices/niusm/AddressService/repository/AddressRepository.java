package com.microservices.niusm.AddressService.repository;

import com.microservices.niusm.AddressService.entity.Address;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveMongoRepository<Address, Integer> {
}
