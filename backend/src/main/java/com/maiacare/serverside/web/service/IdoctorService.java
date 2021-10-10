package com.maiacare.serverside.web.service;

import com.maiacare.serverside.web.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IdoctorService extends MongoRepository<Doctor,Long> {
}
