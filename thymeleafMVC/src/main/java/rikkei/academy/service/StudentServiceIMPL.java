package rikkei.academy.service;

import rikkei.academy.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceIMPL implements IStudentService{
    public static List<Student> studentList = new ArrayList<>();
    static {
        studentList.add(new Student(1,"Giang A Van", "Hai Phong", 10));
        studentList.add(new Student(2,"Do Chuan", "Nam Dinh", 15));
        studentList.add(new Student(3,"Hai Yen", "Ha Noi", 12));
        studentList.add(new Student(4,"Thu Dieu", "Yen Bai", 18));
    }
    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public Student findById(long id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id){
                return studentList.get(i);
            }
        }
        return null;
    }

    @Override
    public void save(Student student) {
        if (findById(student.getId()) != null){
            int index = studentList.indexOf(findById(student.getId()));
            studentList.set(index,student);
        } else {
            studentList.add(student);
        }
    }

    @Override
    public void deleteById(long id) {
        studentList.remove(findById(id));
    }
}
