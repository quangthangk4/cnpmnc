package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1.1 Trang Danh Sách
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = studentService.searchByName(keyword);
        } else {
            students = studentService.getAll();
        }
        model.addAttribute("students", students);
        return "students"; // render templates/students.html
    }

    // 1.2 Trang Chi Tiết
    @GetMapping("/{id}")
    public String getStudentById(@PathVariable String id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "student-detail"; // render templates/student-detail.html
    }

    // 1.3 Hiển thị Form Thêm Mới
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form"; // render templates/student-form.html
    }

    // 1.3 Xử lý lưu Thêm Mới
    @PostMapping
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    // 1.3 Hiển thị Form Chỉnh Sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "student-form";
    }

    // 1.3 Xử lý lưu Chỉnh Sửa
    @PostMapping("/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute("student") Student student) {
        student.setId(id); // Đảm bảo ID không bị đổi
        studentService.save(student);
        return "redirect:/students";
    }

    // 1.2 Xử lý Xóa
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}