package cn.chingshen.dao;

import cn.chingshen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author charlie
 */
public interface UserDao extends JpaRepository<User, Integer> {
}
