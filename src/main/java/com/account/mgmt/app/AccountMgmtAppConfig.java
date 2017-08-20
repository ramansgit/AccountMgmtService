package com.account.mgmt.app;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class AccountMgmtAppConfig extends Configuration {

	/**
	 * version
	 */
	@NotEmpty
	private String version;

	/**
	 * version
	 * 
	 * @return
	 */
	@JsonProperty
	public String getVersion() {
		return version;
	}

	/**
	 * sets the version
	 * 
	 * @param version
	 */
	@JsonProperty
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * swagger configuration
	 */
	@JsonProperty("swagger")
	public SwaggerBundleConfiguration swaggerBundleConfiguration;

}