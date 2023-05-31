package com.microservices.niusm.SubjectService.controller;

import com.microservices.niusm.SubjectService.entity.Subject;
import com.microservices.niusm.SubjectService.exception.ResourceNotFoundException;
import com.microservices.niusm.SubjectService.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {


    @Autowired
    private SubjectService subjectService;

    @PostMapping("/save")
    public Mono<Subject> save(@Valid @RequestBody Subject subject) {
        return subjectService.save(subject).switchIfEmpty(Mono.error(new ResourceNotFoundException("Save not done properly")));
    }

    @GetMapping("/")
    public Flux<Subject> getAll() {
        return subjectService.getAll();
    }


    @GetMapping("/{id}")
    public Mono<Subject> getById(@PathVariable int id) {
        return subjectService.getFindById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException("resource not found with the id  " + id)));
    }

    @GetMapping("/teacher/{teacherId}")
    public Flux<Subject> getByTeacherId(@PathVariable int teacherId) {
        return subjectService.getByTeacherId(teacherId)
                .switchIfEmpty(Flux.error(new ResourceNotFoundException("resource not found with the id  " + teacherId)));
    }

    @PutMapping("/{id}")
    public Mono<Subject> UpdateById(@PathVariable int id, @Valid @RequestBody Subject subject) {

        return subjectService.getFindById(id).flatMap(e -> {
            e.setSubjectId(id);
            e.setSubjectName(subject.getSubjectName());
            return subjectService.save(e);
        }).switchIfEmpty(Mono.error(new ResourceNotFoundException("resource not found with the id  " + id)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable int id) {
        return subjectService.getFindById(id)
                .flatMap(e -> {
                    return subjectService.deleteById(e.getSubjectId());
                }).switchIfEmpty(Mono.error(new ResourceNotFoundException("resource not found with the id  " + id)));
    }

}
