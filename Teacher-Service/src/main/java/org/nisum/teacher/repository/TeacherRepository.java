package org.nisum.teacher.repository;

import org.nisum.teacher.entity.Teacher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends ReactiveMongoRepository<Teacher,Integer> {
}
