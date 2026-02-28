package vn.edu.hcmut.cse.adsoftweng.lab.service;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student getById(String id);

    List<Student> searchByName(String keyword);

    void save(Student student);

    void deleteById(String id);
}
