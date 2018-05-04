package ua.com.jcoh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.jcoh.entity.User;

public interface UserDAO extends JpaRepository<User,Integer> {
    @Query("from User u where u.username =:username")
    public User findByUserName(@Param("username") String username);

    @Query("from User u where u.code =:code")
    public User findByUserCode(@Param("code")String code);
}
