package com.xdbigdata.app_center.service.impl;

import com.xdbigdata.app_center.domain.User;
import com.xdbigdata.app_center.service.IUserService;
import com.xdbigdata.mybatis.Service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService<User> implements IUserService {
}
