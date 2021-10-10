package com.maiacare.serverside.web.service;

import com.maiacare.serverside.web.entity.SysRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsysRoleService extends MongoRepository<SysRole,Long> {
}
