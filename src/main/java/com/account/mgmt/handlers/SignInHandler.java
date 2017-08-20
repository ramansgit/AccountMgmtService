package com.account.mgmt.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.account.mgmt.db.AccountMgmtDaoService;
import com.account.mgmt.exception.EmailException;
import com.account.mgmt.exception.JWTIssuerException;
import com.account.mgmt.exception.MongoDatabaseException;
import com.account.mgmt.exception.ValidationException;
import com.account.mgmt.jwt.JWTIssuer;
import com.account.mgmt.model.UserSignIn;
import com.account.mgmt.model.UserSignup;
import com.account.mgmt.util.AccountMgmtConstant;
import com.account.mgmt.util.EmailUtil;
import com.account.mgmt.validators.RequestValidator;

public class SignInHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SignInHandler.class);
	/**
	 * 
	 * @param data
	 * @throws ValidationException
	 * @throws MongoDatabaseException
	 */
	public void validationPhase(UserSignIn data) throws ValidationException, MongoDatabaseException {
		RequestValidator<UserSignIn> validator = new RequestValidator<UserSignIn>();

		// validate payload null , email check
		validator.paloadNullCheck(data);
		validator.payloadValidator(data);

		// check user id exist
		if (!isExistingUser(data.getUserId())) {
			throw new ValidationException(AccountMgmtConstant.USERID_NOT_FOUND_ERR_MSG);
		}

	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws MongoDatabaseException
	 */
	public boolean isExistingUser(String userId) throws MongoDatabaseException {
		try {
			if (userId != null && !userId.isEmpty()) {
				return new AccountMgmtDaoService().isExistingUser(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MongoDatabaseException(AccountMgmtConstant.DB_USERIDCHECK_FAIED_ERR_MSG);
		}
		return false;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws MongoDatabaseException
	 */
	public UserSignup getUserLogin(String userId) throws MongoDatabaseException {
		if (userId != null && !userId.isEmpty()) {
			return new AccountMgmtDaoService().getUserLogin(userId);
		}
		return null;

	}

	/**
	 * 
	 * @param userId
	 * @param username
	 * @param email
	 * @throws JWTIssuerException
	 * @throws EmailException
	 * @throws ValidationException
	 */
	public void buildJWTAndSendMail(String userId, String username, String email)
			throws JWTIssuerException, EmailException, ValidationException {

		if (userId != null && !userId.isEmpty()) {
			String jwt = JWTIssuer.buildJWT(userId);
			
			if (jwt != null && !jwt.isEmpty()) {
				LOGGER.info("sending email to user");
				EmailUtil.sendMail(username, email, AccountMgmtConstant.USER_DETAILS_API + "?jwt=" + jwt);
			} else {
				throw new JWTIssuerException(AccountMgmtConstant.JWT_TOKEN_CREATION_FAILED_ERR_MSG);
			}
		} else {
			throw new ValidationException(AccountMgmtConstant.USERID_NOT_FOUND_ERR_MSG);
		}

	}

}
