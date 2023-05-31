package org.nisum.teacher.entity;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    private int subjectId;
    private String subjectName;

}
