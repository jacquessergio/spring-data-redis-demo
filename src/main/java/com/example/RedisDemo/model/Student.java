package com.example.RedisDemo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.example.RedisDemo.model.dto.StudentRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@RedisHash("Student")
@ApiModel(value = "com.example.RedisDemo.model.Student", description = "Modelo utilizado para representar informações do Estudante")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ApiModelProperty(dataType = "String", name = "id", required = true, value = "Identificador do Estudante")
	private String id;
	
	@ApiModelProperty(dataType = "String", name = "name", required = true, value = "Nome do Estudante")
	private String name;
	
	@ApiModelProperty(dataType = "com.example.RedisDemo.model.Gender", name = "gender", required = true, value = "Gênero do Estudante")
	private Gender gender;
	
	@ApiModelProperty(dataType = "Integer", name = "grade", required = true, value = "Nota do Estudante")
	private int grade;

	public Student() {

	}
	
	

	public Student(String id, String name, Gender gender, int grade) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.grade = grade;
	}



	public Student(final StudentRequestDTO dto) {
		this.name = dto.getName();
		this.gender = dto.getGender();
		this.grade = dto.getGrade();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public int getGrade() {
		return grade;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", grade=" + grade + "]";
	}

}
