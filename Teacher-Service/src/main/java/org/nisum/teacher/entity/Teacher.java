package org.nisum.teacher.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "teacher")
public class Teacher {

    private int id;
    private String teacherName;
    private int addressId;
}
