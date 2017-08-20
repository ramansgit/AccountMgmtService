package com.account.mgmt.app;

import com.codahale.metrics.health.HealthCheck;

/**
 * dropwizard health check configuration
 * @author ramans
 *
 */
public class AccountMgmtAppHealthCheck extends HealthCheck {
    private final String version;
 
    public AccountMgmtAppHealthCheck(String version) {
        this.version = version;
    }
 
    /**
     * check the health of application
     */
    @Override
    protected Result check() throws Exception {
 
        return Result.healthy();
    }
}