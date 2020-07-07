package com.example.RedisDemo.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import com.example.RedisDemo.controller.StudentController;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
@ApplicationPath("/redis/v1")
public class JerseyConfig extends ResourceConfig {


		public JerseyConfig() {
			
			register(StudentController.class);
			register(SwaggerSerializers.class);
			register(ApiListingResource.class);
			register(WadlResource.class);
			
			packages("org.glassfish.jersey.examples.entityfiltering.selectable");
			register(JacksonFeature.class);
			register(SelectableEntityFilteringFeature.class);
			property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, "_fields");
			
			BeanConfig conf = new BeanConfig();
			conf.setTitle("Redis Demo");
			conf.setDescription("APP Demo with REdis");
			conf.setBasePath("/redis/v1");
			conf.setResourcePackage("com.example.RedisDemo.controller");
			conf.setContact("jacquessergio@yahoo.com.br");
			conf.setVersion("v1");
			conf.setLicenseUrl("http://www.dominioawslab.com.br");
			conf.setScan(true);
			
		}
	
	

}
