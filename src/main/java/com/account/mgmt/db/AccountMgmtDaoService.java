package com.account.mgmt.db;

import org.mongojack.JacksonDBCollection;

import com.account.mgmt.exception.MongoDatabaseException;
import com.account.mgmt.model.UserSignup;
import com.account.mgmt.util.AccountMgmtConstant;
import com.account.mgmt.util.MongoUtil;
import com.mongodb.BasicDBObject;

public class AccountMgmtDaoService {

	private static MongoJackDaoImpl<UserSignup> usersAccountsDao = null;

	/**
	 * 
	 */
	public AccountMgmtDaoService() {

		JacksonDBCollection<UserSignup, String> userAccountDb = JacksonDBCollection.wrap(
				MongoUtil.getCollectionByName(AccountMgmtConstant.KAHA_USERS_COLLECTION), UserSignup.class,
				String.class);

		usersAccountsDao = new MongoJackDaoImpl<UserSignup>(userAccountDb);
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws MongoDatabaseException
	 */
	public String saveUserSignup(UserSignup data) throws MongoDatabaseException {

		return usersAccountsDao.save(data);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws MongoDatabaseException
	 */
	public UserSignup getUserLogin(String userId) throws MongoDatabaseException {

		return (UserSignup) usersAccountsDao.get(new BasicDBObject().append("_id", userId));
	}

	/**
	 * 
	 * @param username
	 * @return
	 * @throws MongoDatabaseException
	 */
	public boolean isNewUser(String username) throws MongoDatabaseException {

		UserSignup signup = (UserSignup) usersAccountsDao.get(new BasicDBObject().append("name", username));
		if (signup == null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws MongoDatabaseException
	 */
	public boolean isExistingUser(String userId) throws MongoDatabaseException {

		UserSignup signup = (UserSignup) usersAccountsDao.get(new BasicDBObject().append("_id", userId));
		if (signup == null) {
			return false;
		}
		return true;
	}
}
