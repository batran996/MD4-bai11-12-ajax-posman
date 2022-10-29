package rikkei.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rikkei.academy.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    //lay list student
    List<Student> findAll();
    //trẩ về 1 đối tuognwj student
    Optional<Student> findById(Long id);
    //xoá theo id
    void deleteById(Long id);
    // lưu 1 đói tượng
    void save(Student student);
    // chck tồn tại
    Boolean existsByName(String name);
    //phân trang student
    Page<Student> findAll(Pageable pageable);

    List<Student> findAllByNameContaining(String name);
    Page<Student> findAllByNameContaining(String name, Pageable pageable);
}
