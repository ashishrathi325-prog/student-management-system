package com.ashish.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashish.Entity.StudentEntity;
import com.ashish.Service.StudentService;

@Controller
public class PageController {
	@Autowired
	private StudentService service;
	@PostMapping("/students/add")
	public String saveStudent(StudentEntity student) {

	    service.add(student);

	    return "redirect:/students";
	}

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/students")
    public String students(Model model) {

        model.addAttribute("students", service.get());

        return "students";
    }

    @GetMapping("/students/add")
    public String addStudent() {
        return "add-student";
    }

    @GetMapping("/students/edit")
    public String editStudent() {
        return "edit-student";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }
    @GetMapping("/students/edit/{id}")
    public String editStudent(
            @PathVariable Long id,
            Model model) {

        StudentEntity student = service.getById(id);

        model.addAttribute("student", student);

        return "edit-student";
    }
    @PostMapping("/students/update/{id}")
    public String updateStudent(
            @PathVariable Long id,
            StudentEntity student) {

        service.update(id, student);

        return "redirect:/students";
    }
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        service.delete(id);

        return "redirect:/students";
    }
    
    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String username,
            @RequestParam String newPassword,
            Model model) {

        try {

            service.resetPassword(username, newPassword);

            model.addAttribute(
                    "message",
                    "Password updated successfully"
            );

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    "Username not found"
            );
        }

        return "reset-password";
    }
}