package com.microservices.niusm.AddressService.controller;


import com.microservices.niusm.AddressService.entity.Address;
import com.microservices.niusm.AddressService.exception.ResourceNotFoundException;
import com.microservices.niusm.AddressService.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Tag(name = "Address", description = "Address Management APIS")
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/save")
    public Mono<Address> save(@Valid @RequestBody Address address) {
        return addressService.save(address).switchIfEmpty(Mono.error(new ResourceNotFoundException("Save not done properly")));
    }

    @GetMapping("/")
    public Flux<Address> getAll() {
        return addressService.getAll();
    }

    @Operation(summary = "Retrieve a Address by Id",
            description = "Get a Address object by specifying its id. The response is Address object with id, state, city and street h.no.",
            tags = {"address", "getById"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the address",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public Mono<Address> getById(@PathVariable int id) {
        return addressService.getFindById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("resource not found with the id  " + id)));
    }

    @PutMapping("/{id}")
    public Mono<Address> UpdateById(@PathVariable int id, @Valid @RequestBody Address address) {

        return addressService.getFindById(id).flatMap(e -> {
            e.setId(id);
            e.setCity(address.getCity());
            e.setState(address.getState());
            e.setPincode(address.getPincode());
            e.setHNo(address.getHNo());
            return addressService.save(e);
        }).switchIfEmpty(Mono.error(new ResourceNotFoundException("resource not found with the id  " + id)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable int id) {
        return addressService.getFindById(id)
                .flatMap(e -> {
                    return addressService.deleteById(e.getId());
                }).switchIfEmpty(Mono.error(new ResourceNotFoundException("resource not found with the id  " + id)));
    }

}
