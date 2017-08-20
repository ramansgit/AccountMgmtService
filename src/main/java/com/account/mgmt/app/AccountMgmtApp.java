package com.account.mgmt.app;

import java.net.UnknownHostException;

import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.account.mgmt.api.AccountMgmtApi;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * holds dropwizard configuration
 * 
 * @author ramans
 *
 */
public class AccountMgmtApp extends Application<AccountMgmtAppConfig> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountMgmtApp.class);

	public static void main(String[] args) throws Exception {

		new AccountMgmtApp().run(args);
	}

	/**
	 * initalize the swagger configuration
	 */
	@Override
	public void initialize(Bootstrap<AccountMgmtAppConfig> bootstrap) {

		bootstrap.addBundle(new SwaggerBundle<AccountMgmtAppConfig>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AccountMgmtAppConfig configuration) {
				// this would be the preferred way to set up swagger, you can
				// also construct the object here programtically if you want
				return configuration.swaggerBundleConfiguration;
			}
		});
	}

	/**
	 * run method 
	 */
	@Override
	public void run(AccountMgmtAppConfig config, Environment env) throws UnknownHostException {

		env.jersey().register(new AccountMgmtApi());

		env.servlets().setSessionHandler(new SessionHandler());
		final AccountMgmtAppHealthCheck healthCheck = new AccountMgmtAppHealthCheck(config.getVersion());
		env.healthChecks().register("template", healthCheck);
		env.jersey().register(healthCheck);

	}

}