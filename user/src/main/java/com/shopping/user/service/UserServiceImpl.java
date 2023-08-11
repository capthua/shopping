package com.shopping.user.service;

import com.shopping.user.dao.mapper.UserMapper;
import com.shopping.user.dao.dataobject.User;
import com.shopping.user.api.UserService;
import com.shopping.user.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 如果添加@DubboService注解，可以不用加@Service，bean也会被实例化
 * describe:
 *
 * @author hanshaohua
 * @date 2019/11/01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public UserVO getUserById(Long id) {
        UserVO userVO = new UserVO();
        User user = userDao.selectById(id);
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public void save(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userDao.insert(user);
    }
}

