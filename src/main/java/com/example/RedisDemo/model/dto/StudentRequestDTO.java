package com.example.RedisDemo.model.dto;

import java.io.Serializable;

import com.example.RedisDemo.model.Gender;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "com.example.RedisDemo.model.dto.StudentRequestDTO", description = "DTO utilizado para inserir informações do Estudante")
public class StudentRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(dataType = "String", name = "name", required = true, value = "Nome do Estudante")
	private String name;

	@ApiModelProperty(dataType = "com.example.RedisDemo.model.Gender", name = "gender", required = true, value = "Gênero do Estudante")
	private Gender gender;

	@ApiModelProperty(dataType = "Integer", name = "grade", required = true, value = "Nota do Estudante")
	private int grade;

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public int getGrade() {
		return grade;
	}

}
