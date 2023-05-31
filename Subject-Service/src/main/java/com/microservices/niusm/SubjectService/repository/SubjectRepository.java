package com.microservices.niusm.SubjectService.repository;

import com.microservices.niusm.SubjectService.entity.Subject;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SubjectRepository extends ReactiveMongoRepository<Subject, Integer> {

    Mono<Subject> findBySubjectId(int subjectId);

    Flux<Subject> findByTeacherId(int teacherId);
}
