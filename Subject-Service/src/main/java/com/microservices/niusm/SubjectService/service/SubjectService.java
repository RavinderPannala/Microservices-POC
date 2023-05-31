package com.microservices.niusm.SubjectService.service;

import com.microservices.niusm.SubjectService.entity.Subject;
import com.microservices.niusm.SubjectService.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Mono<Subject> save(Subject address) {
        return subjectRepository.save(address);
    }

    public Flux<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Mono<Subject> getFindById(int id) {
        return subjectRepository.findBySubjectId(id);
    }

    public Mono<Void> deleteById(int id) {
        return subjectRepository.deleteById(id);
    }

    public Flux<Subject> getByTeacherId(int teacherId) {
        return subjectRepository.findByTeacherId(teacherId);
    }
}
