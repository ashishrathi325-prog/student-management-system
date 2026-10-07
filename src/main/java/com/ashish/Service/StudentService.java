package com.ashish.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashish.Entity.StudentEntity;
import com.ashish.Repository.StudentRepository;
@Service
public class StudentService {
	private final StudentRepository repo;

	// Add student 
	 private final PasswordEncoder passwordEncoder;

	StudentService(PasswordEncoder passwordEncoder, StudentRepository repo) {
		this.passwordEncoder = passwordEncoder;
		this.repo = repo;
	}
	    public StudentEntity add(StudentEntity stu) {
	    	stu.setPassword(passwordEncoder.encode(stu.getPassword()));
	    	return repo.save(stu);
	    }
	
	// get all student
	public List <StudentEntity> get (){
		 return repo.findAll();
	}
	
	// Get by id
	public StudentEntity getById (Long sid){
		 return repo.findById(sid).orElse(null);
//				.orElseThrow(() -> new
//				 RuntimeException("Student not found with id "+sid));
	}
	
	//update student 
    public StudentEntity update(Long sid,StudentEntity stu) {
    	StudentEntity existing=repo.findById(sid).orElseThrow(()-> new
    	RuntimeException("Student not found with id "+sid));
    	existing.setName(stu.getName());
    	existing.setCourse(stu.getCourse());
    	 existing.setAge(stu.getAge());
    	return repo.save(existing);
    }
    
    //delete  student
    public void delete(Long sid) {
    	 repo.deleteById(sid);
    }
    
    public void resetPassword(String username, String newPassword) {
        StudentEntity user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));

        repo.save(user);
    }
   
}
