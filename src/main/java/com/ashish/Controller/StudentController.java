package com.ashish.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.Entity.StudentEntity;
import com.ashish.Service.StudentService;
@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	private StudentService ser;
	@PostMapping("/add")
	public  ResponseEntity <StudentEntity> add(@RequestBody StudentEntity s){
		return ResponseEntity.ok(ser.add(s));
		
	}
	@GetMapping("/all")
	public ResponseEntity <List<StudentEntity>>getAll(){
		return ResponseEntity.ok(ser.get());
		
	}
	@GetMapping("/get/{sid}")
	public ResponseEntity <StudentEntity>getById(@PathVariable Long sid){
		return ResponseEntity.ok(ser.getById(sid));
		
	}
	
	@PutMapping("/update/{sid}")
	public ResponseEntity<StudentEntity>update(@PathVariable Long sid,@RequestBody StudentEntity s){
		return ResponseEntity.ok(ser.update(sid, s));
	}
	@DeleteMapping("/delete/{sid}")
	public ResponseEntity <Void> delete (@PathVariable Long sid){
		ser.delete(sid);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/reset-password")
	public ResponseEntity<String> resetPassword(
	        @RequestParam String username,
	        @RequestParam String password) {

	    ser.resetPassword(username, password);

	    return ResponseEntity.ok("Password updated successfully");
	}

}
