package com.account.mgmt.handlers;

import com.account.mgmt.db.AccountMgmtDaoService;
import com.account.mgmt.exception.MongoDatabaseException;
import com.account.mgmt.exception.UnAuthorizedAccessException;
import com.account.mgmt.exception.ValidationException;
import com.account.mgmt.jwt.JWTIssuer;
import com.account.mgmt.model.UserSignup;
import com.account.mgmt.util.AccountMgmtConstant;

public class UserInfoHandler {

	/**
	 * validate jwt expiry and validness and return subject
	 * 
	 * @param data
	 * @throws ValidationException
	 * @throws InvalidJwtException
	 * @throws MongoDatabaseException
	 */
	public String jwtValidationAndReturnSubect(String jwt) throws UnAuthorizedAccessException {
			// validate jwt null or not	
		if(jwt == null || jwt.isEmpty()){
			throw new UnAuthorizedAccessException(AccountMgmtConstant.INVALID_JWT_TOKEN_ERR_MSG);
		}
		// validate jwt is valid and not expired.
		return JWTIssuer.parseJWT(jwt);

	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws MongoDatabaseException
	 * @throws ValidationException
	 */
	public UserSignup getUserInfo(String userId) throws MongoDatabaseException, ValidationException {
		UserSignup signup = null;
		// get user info if subject
		if (userId == null || userId.isEmpty()) {
			throw new ValidationException(AccountMgmtConstant.INVALID_PROPERTY_REQUEST_ERR_MSG + userId);
		}
		signup = new AccountMgmtDaoService().getUserLogin(userId);

		if (signup == null) {
			throw new MongoDatabaseException(AccountMgmtConstant.NO_RECORD_FOUND_ERR_MSG);
		}

		return signup;

	}
}
