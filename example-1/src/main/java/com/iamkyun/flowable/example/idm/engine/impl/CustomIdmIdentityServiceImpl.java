package com.iamkyun.flowable.example.idm.engine.impl;

import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.impl.IdmIdentityServiceImpl;

/**
 * 自定义的IdmIdentityService实现
 *
 * @author kyun
 */
public class CustomIdmIdentityServiceImpl extends IdmIdentityServiceImpl {
    @Override
    public UserQuery createUserQuery() {
        return new CustomUserQueryImpl();
    }
}
