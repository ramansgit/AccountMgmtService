package com.account.mgmt.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.account.mgmt.controller.AccountMgmtApiService;
import com.account.mgmt.controller.AccountMgmtApiServiceImpl;
import com.account.mgmt.model.ApiResponseMessage;
import com.account.mgmt.model.UserSignIn;
import com.account.mgmt.model.UserSignup;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/api/v1/account/auth")
@Api(description = "the account-mgmt API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-10T14:43:58.709Z")
public class AccountMgmtApi {
	private final AccountMgmtApiService delegate = new AccountMgmtApiServiceImpl();
	
	@POST
	@Path("/register")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "user signup request", notes = "user signup request", response = ApiResponseMessage.class, tags = {
			"AccountMgmt" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "", response = ApiResponseMessage.class) })
	public Response signup(@ApiParam(value = "signup request") UserSignup body, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		return delegate.signup(body);
	}

	@GET
	@Path("/userinfo")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "get user details request", notes = "get user details request.", response = ApiResponseMessage.class, tags = {
			"AccountMgmt", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "get user details request", response = ApiResponseMessage.class) })

	public Response getUserDetails(@ApiParam(value = "jwt") @QueryParam("jwt") String jwt,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {

		return delegate.getUserDetails(jwt);
	}

	@POST
	@Path("/login")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "login request", notes = "login request.", response = ApiResponseMessage.class, tags = {
			"AccountMgmt", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "response for userlogin request", response = ApiResponseMessage.class) })

	public Response login(@ApiParam(value = "login request") UserSignIn body, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		return delegate.login(body);
	}

}
