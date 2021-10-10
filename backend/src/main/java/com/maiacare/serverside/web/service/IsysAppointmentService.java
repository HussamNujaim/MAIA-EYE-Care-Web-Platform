package com.maiacare.serverside.web.service;

import com.maiacare.serverside.web.entity.SysAppointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IsysAppointmentService extends MongoRepository<SysAppointment,Long> {
}
