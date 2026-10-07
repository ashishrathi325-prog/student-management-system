package com.ashish.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashish.Entity.StudentEntity;
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long>{
	Optional<StudentEntity>findByUsername(String username);

}
