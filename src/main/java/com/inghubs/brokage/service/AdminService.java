package com.inghubs.brokage.service;

import com.inghubs.brokage.domain.bo.AdminBo;
import com.inghubs.brokage.domain.bo.CustomerBo;

public interface AdminService {

    AdminBo register(AdminBo adminBo);

    AdminBo login(AdminBo customerBo);
}
