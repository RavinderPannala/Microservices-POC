package org.nisum.teacher.mapper;

import org.nisum.teacher.dto.TeacherSubjectAddressDTO;
import org.nisum.teacher.entity.Address;
import org.nisum.teacher.entity.Subject;
import org.nisum.teacher.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {

    @Mappings({
            @Mapping(source = "teacher.teacherName", target = "teacherName"),
            @Mapping(source = "address.houseNumber", target = "address.houseNumber"),
            @Mapping(source = "address.street", target = "address.street"),
            @Mapping(source = "address.city", target = "address.city"),
            @Mapping(source = "address.state", target = "address.state"),
            @Mapping(source = "address.pincode", target = "address.pincode"),
            @Mapping(source = "subjectList", target = "subjects"),
    })
    TeacherSubjectAddressDTO convert(Teacher teacher, List<Subject> subjectList, Address address);
}
