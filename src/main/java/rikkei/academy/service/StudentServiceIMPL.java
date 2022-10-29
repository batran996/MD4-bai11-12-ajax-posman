package rikkei.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Student;
import rikkei.academy.repository.IStudentRepository;

import java.util.List;
import java.util.Optional;

@Service

public class StudentServiceIMPL implements IStudentService {
    @Autowired
    IStudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Boolean existsByName(String name) {
        return studentRepository.existsByName(name);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> findAllByNameContaining(String name) {
        return studentRepository.findAllByNameContaining(name);
    }

    @Override
    public Page<Student> findAllByNameContaining(String name, Pageable pageable) {
        return studentRepository.findAllByNameContaining(name,pageable);
    }
}
