package org.nisum.teacher.exception;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ErrorMessage {

    private int statusCode;
    private String errorMsg;
    private String errorDescription;
    private LocalDate errorDate;

}
