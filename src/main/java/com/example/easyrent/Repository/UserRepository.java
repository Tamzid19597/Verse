package com.example.easyrent.Repository;

import com.example.easyrent.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Override
    Optional<User> findById(Integer integer);
//    @Query(value = "select * from users where email= :email", nativeQuery = true)
//    public List<User> findByEmail(String email);

    Optional<User> findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET number=:number, address=:address where email=:email", nativeQuery = true)
    public int updateByemail(String email,String number,String address);

}
