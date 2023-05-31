package com.microservices.niusm.SubjectService.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "subject")
public class Subject {

    private int subjectId;

    @NonNull
    @Size(min = 2,max = 6,message = "Subject is in between 2 and 6 characters")
    private String subjectName;

    @NotBlank
    private int teacherId;

}
