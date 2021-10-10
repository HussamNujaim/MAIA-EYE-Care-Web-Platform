package com.maiacare.serverside.web.service;

import com.maiacare.serverside.web.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IuploadService {
    public String uploadPicture(MultipartFile uploadFile, HttpServletRequest request);

}
