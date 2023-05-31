package org.nisum.teacher.controller;

import org.nisum.teacher.constant.Constants;
import org.nisum.teacher.dto.TeacherSubjectAddressDTO;
import org.nisum.teacher.entity.Teacher;
import org.nisum.teacher.exception.ResourceNotFoundException;
import org.nisum.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/save")
    public Mono<Teacher> save(@Valid @RequestBody Teacher teacher) {
        return teacherService.save(teacher).switchIfEmpty(Mono.error(new ResourceNotFoundException("Save not done properly")));
    }

    @GetMapping("/")
    public Flux<Teacher> getAll() {
        return teacherService.getAll();
    }


    @GetMapping("/{id}")
    public Mono<Teacher> getById(@PathVariable int id) {
        return teacherService.getFindById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException(Constants.RESOURCE_NOT_FOUND + id)));
    }

    @PutMapping("/{id}")
    public Mono<Teacher> doUpdate(@PathVariable int id, @Valid @RequestBody Teacher teacher) {

        return teacherService.getFindById(id).flatMap(e -> {
            e.setId(id);
            e.setTeacherName(teacher.getTeacherName());
            return teacherService.save(e);
        }).switchIfEmpty(Mono.error(new ResourceNotFoundException(Constants.RESOURCE_NOT_FOUND + id)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable int id) {
        return teacherService.getFindById(id)
                .flatMap(e -> teacherService.deleteById(e.getId())).switchIfEmpty(Mono.error(new ResourceNotFoundException(Constants.RESOURCE_NOT_FOUND + id)));
    }

    @GetMapping("/getTeacherInfo/{id}")
    public Mono<TeacherSubjectAddressDTO> getTeacherInfo(@PathVariable int id) {
        return teacherService.getTeacherInfo(id);
    }
}
