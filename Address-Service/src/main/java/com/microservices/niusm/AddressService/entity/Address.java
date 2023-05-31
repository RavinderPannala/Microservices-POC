package com.microservices.niusm.AddressService.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "address")
public class Address {

    @Id
    @NotNull
    private int id;

    private String hNo;
    @NotNull
    @Size(min = 2, message = "street must be min 2 char")
    private String street;
    @NotNull
    @Size(min = 3, message = "city must be min 3 char")
    private String city;

    @NotNull
    @Size(min = 3, message = "state must be min 3 char")
    private String state;

    @NotNull
    @Size(max = 6, min = 3, message = "Pincode must be min 3 char and max 6 char")
    private String pincode;

}
