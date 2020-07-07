package com.example.RedisDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;

@Configuration
public class SSMConfig {

	@Bean("SSM")
	public String getProperty() {

		final AWSSimpleSystemsManagement ssm = AWSSimpleSystemsManagementClientBuilder.defaultClient();

		try {

			return ssm.getParameter(new GetParameterRequest()
					.withName("demoParameter")
					.withWithDecryption(true))
					.getParameter()
					.getValue();

		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
