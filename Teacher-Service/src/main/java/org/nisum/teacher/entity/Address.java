package org.nisum.teacher.entity;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String houseNumber;
    private String street;
    private String city;
    private String state;
    private String pincode;

}
