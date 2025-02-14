package com.artur.springbootrest.dao;


import com.artur.springbootrest.entity.Student;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Student> getAllStudents() {
        Session session = entityManager.unwrap(Session.class);
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> allStudents = query.getResultList();
        return allStudents;
    }
    @Override
    public void saveStudent(Student student) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(student);
    }
    @Override
    public Student getStudent(int id) {
        Session session = entityManager.unwrap(Session.class);
        Student student = session.get(Student.class, id);
        return student;
    }

    @Override
    public void deleteStudent(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query<Student> query = session.createQuery("delete from Student where id=:stdId");
        query.setParameter("stdId", id);
        query.executeUpdate();
    }
}
