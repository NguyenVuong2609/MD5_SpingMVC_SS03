package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Student;
import rikkei.academy.service.IStudentService;

import java.util.List;

@Controller
@RequestMapping(value = {"/","/students"})
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @GetMapping
    public String showListStudent(Model model){
        List<Student> studentList = studentService.findAll();
        model.addAttribute("listStudent", studentList);
        return "students/list";
    }
    @GetMapping("{id}")
    public String detailStudent(@PathVariable long id, Model model){
        Student student = studentService.findById(id);
        System.out.println(student);
        model.addAttribute("student",student);
        return "students/detail";
    }
    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable long id){
        studentService.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("create")
    public String showFormCreate(){
        return "students/create";
    }
    @PostMapping("create")
    public String actionCreate(@RequestParam("name") String name,
                               @RequestParam("address") String address,
                               @RequestParam("age") int age){
        long id;
        if (studentService.findAll().size() == 0){
            id = 1;
        } else {
            id = studentService.findAll().get(studentService.findAll().size() - 1).getId() + 1;
        }
        Student student = new Student(id, name, address, age);
        studentService.save(student);
        return "redirect:/";
    }
    @GetMapping("edit/{id}")
    public String showFormEdit(@PathVariable Long id,Model model){
        System.out.println("controller here");
        Student student = studentService.findById(id);
        model.addAttribute("student",student);
        return "students/edit";
    }
    @PostMapping("/edit")
    public String actionEdit(Student student){
        studentService.save(student);
        return "redirect:/";
    }
}
