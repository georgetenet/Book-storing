
package com.example.book.service;

import com.example.book.model.Book;
import com.example.book.model.Student;
import com.example.book.repository.BookRepository;
import com.example.book.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
    @Service
    public class StudentService {

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private BookRepository bookRepository;

        // Find all students
        public List<Student> findAll() {
            return studentRepository.findAll();
        }

        // Find a student by ID
        public Optional<Student> findById(Long id) {
            return studentRepository.findById(id);
        }

        // Save or update a student
        public Student save(Student student) {
            return studentRepository.save(student);
        }

        // Delete a student by ID
        public void delete(Long id) {
            studentRepository.deleteById(id);
        }

        // Assign a book to a student
        @Transactional
        public void assignBookToStudent(Long bookId, Long studentId) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            book.setOwner(student);
            bookRepository.save(book);
        }

        // Find all books owned by a student
        public List<Book> findBooksByStudentId(Long StudentID) {
            return bookRepository.findAllByOwnerId(StudentID);
        }

        // Update the students details
        public Student update(Long id, Student studentDetails) {
            return studentRepository.findById(id)
                    .map(student -> {
                        student.setFirstName(studentDetails.getFirstName());
                        student.setSurname(studentDetails.getSurname());
                        return studentRepository.save(student);
                    })
                    .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        }
    }






