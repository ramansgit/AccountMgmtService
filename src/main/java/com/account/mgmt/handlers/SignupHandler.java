package com.account.mgmt.handlers;

import java.util.Random;
import java.util.UUID;

import com.account.mgmt.db.AccountMgmtDaoService;
import com.account.mgmt.exception.MongoDatabaseException;
import com.account.mgmt.exception.ValidationException;
import com.account.mgmt.model.UserSignup;
import com.account.mgmt.util.AccountMgmtConstant;
import com.account.mgmt.validators.RequestValidator;

public class SignupHandler {

	/**
	 * 
	 * @param data
	 * @throws ValidationException
	 * @throws MongoDatabaseException
	 */
	public void validationPhase(UserSignup data) throws ValidationException, MongoDatabaseException {
		RequestValidator<UserSignup> validator = new RequestValidator<UserSignup>();

		// validate payload null , email check
		validator.paloadNullCheck(data);
		validator.payloadValidator(data);
		validator.validateEmail(data.getEmail());

		// check user name already with us
		if (!isNewUser(data)) {
			throw new ValidationException(AccountMgmtConstant.USERNAME_FOUND_ERR_MSG);
		}

	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws MongoDatabaseException
	 */
	public boolean isNewUser(UserSignup data) throws MongoDatabaseException {
		try {
			if (data != null) {
				String username = data.getName();
				return new AccountMgmtDaoService().isNewUser(username);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MongoDatabaseException(AccountMgmtConstant.DB_USERNAMECHECK_FAIED_ERR_MSG);
		}
		return false;
	}

	/**
	 * generate userId for the user
	 * 
	 * @param data
	 * @return
	 */
	public void generteUserId(UserSignup data) {

		if (data != null) {
			data.setUserId(UUID.randomUUID().toString());
		}

	}

	/**
	 * user registered in to the system
	 * 
	 * @param data
	 * @throws MongoDatabaseException
	 */
	public String saveUser(UserSignup data) throws MongoDatabaseException {
		try {
			if (data != null) {
				return new AccountMgmtDaoService().saveUserSignup(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MongoDatabaseException(AccountMgmtConstant.DB_USER_SAVE_OP_FAILED_ERR_MSG);
		}
		return "";
	}

}
