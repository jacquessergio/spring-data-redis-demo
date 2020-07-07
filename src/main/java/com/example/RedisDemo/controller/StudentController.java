package com.example.RedisDemo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctc.wstx.io.CharsetNames;
import com.example.RedisDemo.bo.StudentBO;
import com.example.RedisDemo.model.Student;
import com.example.RedisDemo.model.dto.StudentRequestDTO;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Jacques S. Testoni
 * @since 18/06/2020
 */

@Api("ElastiCache for Redis Demo")
@Path("/demo")
public class StudentController {

	private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentBO bo;

	@POST
	@Path("/students")
	@ApiOperation(value = "Cadastra Estudante na Base de Dados Redis.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Registro inserido com sucesso!", response = Student.class),
			@ApiResponse(code = 422, message = "Entidade não pode ser processada."),
			@ApiResponse(code = 500, message = "Erro interno. Tente novamente!") })
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=" + CharsetNames.CS_UTF8)
	public Response save(@Valid StudentRequestDTO student) {
		LOG.info("Request Body: {}", student);

		try {
			final Student response = bo.create(new Student(student));
			return Response.ok(response).build();
		} catch (Exception e) {
			LOG.error("Erro: {}", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/students")
	@ApiOperation(value = "Atualiza Estudante na Base de Dados Redis.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Registro atualizado com sucesso!", response = Student.class),
			@ApiResponse(code = 422, message = "Entidade não pode ser processada."),
			@ApiResponse(code = 500, message = "Erro interno. Tente novamente!") })
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=" + CharsetNames.CS_UTF8)
	public Response update(@Valid Student student) {
		LOG.info("Request Body: {}", student);
		try {
			final Student response = bo.update(student);
			return Response.ok(response).build();
		} catch (Exception e) {
			LOG.error("Erro: {}", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GET
	@Path("/students")
	@ApiOperation(value = "Consulta todos Estudantes na Base de Dados Redis.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operação realizada com sucesso!", response = Student[].class),
			@ApiResponse(code = 204, message = "Nenhum registro encontrado."),
			@ApiResponse(code = 500, message = "Erro interno. Tente novamente!") })
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=" + CharsetNames.CS_UTF8)
	public Response listAll() {

		try {
			
			final List<Student> response = bo.findAll();

			if (response == null || response.isEmpty()) {
				return Response.noContent().build();
			}

			return Response.ok(new Gson().toJson(response)).build();
		} catch (Exception e) {
			LOG.error("Erro: {}", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/students/{id}")
	@ApiOperation(value = "Consulta um Estudante por ID na Base de Dados Redis.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operação realizada com sucesso!", response = Student.class),
			@ApiResponse(code = 204, message = "Nenhum Registro encontrado."),
			@ApiResponse(code = 500, message = "Erro interno. Tente novamente!") })
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=" + CharsetNames.CS_UTF8)
	public Response findById(@PathParam("id") String id) {

		try {
			final Student response = bo.findBy(id);

			if (response == null) {
				return Response.noContent().build();
			}

			return Response.ok(response).build();
		} catch (Exception e) {
			LOG.error("Erro: {}", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/students/{id}")
	@ApiOperation(value = "Remove um Estudante na Base de Dados Redis.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Registro removido com sucesso!"),
			@ApiResponse(code = 422, message = "Entidade não pode ser processada."),
			@ApiResponse(code = 500, message = "Erro interno. Tente novamente!") })
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON + ";" + MediaType.CHARSET_PARAMETER + "=" + CharsetNames.CS_UTF8)
	public Response delById(@PathParam("id") String id) {

		try {
			bo.deleteById(id);
			return Response.ok().build();
		} catch (Exception e) {
			LOG.error("Erro: {}", e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
