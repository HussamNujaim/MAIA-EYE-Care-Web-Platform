package com.maiacare.serverside.web.service;

import com.maiacare.serverside.web.entity.DrDepartment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IdrDeptService extends MongoRepository<DrDepartment,Long>, QueryByExampleExecutor<DrDepartment> {
}
