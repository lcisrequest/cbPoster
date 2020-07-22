package com.cbposter.dao;

import com.cbposter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: lc
 * @Date: 2020/7/22 16:38
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

}
