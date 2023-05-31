package org.nisum.teacher.dto;

import org.nisum.teacher.entity.Address;
import org.nisum.teacher.entity.Subject;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubjectAddressDTO {

    private String teacherName;
    private List<Subject> subjects;
    private Address address;
}
