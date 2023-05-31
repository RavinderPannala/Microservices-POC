package com.microservices.niusm.SubjectService.exception;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ErrorMessage {

    private int statusCode;
    private String errorMessage;
    private String errorDescription;
    private LocalDate errorDate;

}
