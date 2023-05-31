package org.nisum.teacher.service;

import org.nisum.teacher.dto.TeacherSubjectAddressDTO;
import org.nisum.teacher.entity.Address;
import org.nisum.teacher.entity.Subject;
import org.nisum.teacher.entity.Teacher;
import org.nisum.teacher.mapper.TeacherMapper;
import org.nisum.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    WebClient.Builder webclient;

    @Autowired
    TeacherMapper teacherMapper;

    @Value("${address.api.base.url}")
    private String addressServiceBaseUrl;

    @Value("${subject.api.base.url}")
    private String subjectServiceBaseUrl;

    public Mono<Teacher> save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Flux<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Mono<Teacher> getFindById(int id) {
        return teacherRepository.findById(id);
    }

    public Mono<Void> deleteById(int id) {
        return teacherRepository.deleteById(id);
    }

    public Mono<TeacherSubjectAddressDTO> getTeacherInfo(int id) {
        return teacherRepository.findById(id)
                .zipWhen(teacher -> getSubjectFlux(teacher).collectList().zipWith(getAddressMono(teacher)))
                .map(tuple -> teacherMapper.convert(tuple.getT1(), tuple.getT2().getT1(), tuple.getT2().getT2()));
    }

    public Flux<Subject> getSubjectFlux(Teacher teacher) {
        return webclient.baseUrl(subjectServiceBaseUrl).build()
                .get()
                .uri("/api/subject/teacher/{teacherId}", teacher.getId())
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToFlux(Subject.class);
                    } else if (clientResponse.statusCode().is4xxClientError() || clientResponse.statusCode().is5xxServerError()) {
                        return Flux.empty();
                    } else {
                        return Flux.empty();
                    }
                });
    }

    public Mono<Address> getAddressMono(Teacher teacher) {
        return webclient.baseUrl(addressServiceBaseUrl).build().get().uri("/api/address/{id}", teacher.getAddressId())
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(Address.class);
                    } else if (clientResponse.statusCode().is4xxClientError() || clientResponse.statusCode().is5xxServerError()) {
                        return Mono.empty();
                    } else {
                        return Mono.empty();
                    }
                });
    }

}
