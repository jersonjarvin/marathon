package com.ruc.marathon.repository;

import com.ruc.marathon.domain.RucEntity;
import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public  interface RucRepository  extends JpaRepository<RucEntity, Long> {
    RucEntity findByRuc(String ruc);
    @Modifying
    @Query("delete from Ruc where ruc = :ruc")
    void deleteUsersByFirstName(@Param("ruc") String ruc);
    void deleteByRuc(String ruc);
}
