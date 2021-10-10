package com.maiacare.serverside.web.service;

import com.maiacare.serverside.web.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IappointmentService extends MongoRepository<Appointment,Long> {
}
