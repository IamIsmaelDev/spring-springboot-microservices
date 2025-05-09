package com.banana.persistence;

import com.banana.models.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
//@Repository
public class StudentsRepository implements StudentsRepositoryInf {

    @Value("${url_conn}")
    private String urlConn;

    private List<Student> students = new ArrayList<>();

    public StudentsRepository() {
        students.add(new Student(1L, "Ricardo", "Ahumada", 1));
        students.add(new Student(2L, "Toni", "Fdez", 2));
        students.add(new Student(3L, "David", "Carcelen", 2));
        students.add(new Student(4L, "Ana", "Roca", 4));
        students.add(new Student(5L, "Petra", "Lopez", 3));
    }

    public void add(Student student) {
        this.students.add(student);
    }

    @Override
    public Student update(Student estudiante) {
        for (Student st : students) {
            if (st.getId() == estudiante.getId()) {
                st = estudiante;
                return st;
            }
        }
        return null;
    }

    public Student get(int idx) {
        return this.students.get(idx);
    }

    public Student getById(Long id) {
        for (Student st : students) {
            if (st.getId() == id) return st;
        }
        return null;
    }
}
