package com.example.xiangyingcinema.dao;

import com.example.xiangyingcinema.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,String> ,JpaSpecificationExecutor<User> {
    User findByMobile(String mobile);


}
