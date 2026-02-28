package vn.edu.hcmut.cse.adsoftweng.lab.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> searchByName(String keyword) {
        return studentRepository.findByNameContainsIgnoreCase(keyword);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(String id) {
        studentRepository.deleteById(id);
    }
}
