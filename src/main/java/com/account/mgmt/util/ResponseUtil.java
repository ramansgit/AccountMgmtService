package com.account.mgmt.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.account.mgmt.exception.EmailException;
import com.account.mgmt.exception.JWTIssuerException;
import com.account.mgmt.exception.MongoDatabaseException;
import com.account.mgmt.exception.UnAuthorizedAccessException;
import com.account.mgmt.exception.ValidationException;
import com.account.mgmt.model.ApiError;
import com.account.mgmt.model.ApiResponseMessage;
import com.account.mgmt.model.MsgSuccess;

/***
 * returns response to client
 * 
 * @author ramans
 *
 */
public class ResponseUtil {

	/**
	 * handle all type of exception
	 * @param e
	 * @return
	 */
	public static Response handleException(Exception e) {
		if (e instanceof ValidationException) {
			e.printStackTrace();
			return Response.ok().status(Status.BAD_REQUEST)
					.entity(new ApiResponseMessage(new ApiError(AccountMgmtConstant.BAD_REQUEST, e.getMessage())))
					.build();
		}
		if (e instanceof UnAuthorizedAccessException) {
			e.printStackTrace();
			return Response.ok().status(Status.UNAUTHORIZED).entity(
					new ApiResponseMessage(new ApiError(AccountMgmtConstant.UNAUTHORISED_ACCESS, e.getMessage())))
					.build();
		}
		if (e instanceof MongoDatabaseException) {
			e.printStackTrace();
			return Response.ok().status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ApiResponseMessage(new ApiError(AccountMgmtConstant.DB_EXCEPTION, e.getMessage())))
					.build();
		}

	
		if (e instanceof JWTIssuerException) {
			e.printStackTrace();
			return Response.ok().status(Status.INTERNAL_SERVER_ERROR).entity(
					new ApiResponseMessage(new ApiError(AccountMgmtConstant.JWT_TOKEN_EXCEPTION, e.getMessage())))
					.build();
		}
		if (e instanceof EmailException) {
			e.printStackTrace();
			return Response.ok().status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ApiResponseMessage(new ApiError(AccountMgmtConstant.EMAIL_EXCEPTION, e.getMessage())))
					.build();
		}

		if (e instanceof Exception) {
			e.printStackTrace();
			return Response.ok().status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ApiResponseMessage(new ApiError(AccountMgmtConstant.GENERIC_EXCEPTION, e.getMessage())))
					.build();
		}
		return Response.ok().status(Status.INTERNAL_SERVER_ERROR)
				.entity(new ApiResponseMessage(new ApiError(AccountMgmtConstant.GENERIC_EXCEPTION, e.getMessage())))
				.build();

	}

	/**
	 *
	 * @param data
	 * @return
	 */
	public static Response handleSuccessResp(Object data) {
		return Response.ok().status(Status.OK).entity(new ApiResponseMessage(data)).build();

	}

	/**
	 *
	 * @param message
	 * @return
	 */
	public static Response handleSuccessResp(String message) {
		return Response.ok().status(Status.OK).entity(new MsgSuccess(message)).build();

	}

	/**
	 *
	 * @param status
	 * @param code
	 * @param msg
	 * @return
	 */
	public static Response handleFailureResp(Status status, String code, String msg) {
		return Response.ok().status(status).entity(new ApiResponseMessage(new ApiError(code, msg))).build();

	}
}