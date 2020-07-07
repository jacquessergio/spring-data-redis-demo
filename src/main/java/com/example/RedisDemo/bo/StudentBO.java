package com.example.RedisDemo.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.RedisDemo.model.Student;
import com.example.RedisDemo.repository.StudentRepository;

@Component
public class StudentBO {

	private static final Logger LOG = LoggerFactory.getLogger(StudentBO.class);

	@Autowired
	private StudentRepository studentRepository;

	public Student create(final Student student) {
		return studentRepository.save(student);
	}

	public Student update(final Student student) {
		return studentRepository.save(student);
	}

	public Student findBy(final String id) {
		final Student student = studentRepository.findById(id).orElse(new Student());

		if (student.getId() == null) {
			LOG.info("Nenhum Registro encontrado.");
			return null;
		}
		return student;
	}

	public void deleteById(final String id) {
		studentRepository.deleteById(id);
	}

	public List<Student> findAll() {

		try {

			final List<Student> student = StreamSupport.stream(studentRepository.findAll().spliterator(), false)
					.collect(Collectors.toList());

			if (student == null || student.isEmpty()) {
				return new ArrayList<Student>();
			}
			return student;

		} catch (Exception e) {
			LOG.info("Erro ao consultar Lista de Estudantes: {}", e);
			return null;
		}

	}
}
