package com.maiacare.serverside.web.service;

import com.maiacare.serverside.web.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpatientsService extends MongoRepository<Patient,Long> {
}
