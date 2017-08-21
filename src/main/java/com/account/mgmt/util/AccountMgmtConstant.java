package com.account.mgmt.util;

public class AccountMgmtConstant {

	// db connection details
	public static final String MONGO_DB_NAME = "kahadb";
	public static final String MONGO_DB_HOSTNAME = "localhost";
	public static final int MONGO_DB_PORT = 27017;
	public static final String MONGO_DB_PASSWORD = "kaha123";
	public static final String MONGO_DB_USERNAME = "kaha_user";
	public static final String MONGO_DB_ADMIN = "kahadb";
	public static final String KAHA_USERS_COLLECTION = "users_collection";
	
	
	//email account details
	
	public static final String EMAIL_ADMIN_USER = "";
	public static final String EMAIL_ADMIN_PWD = "";
	public static final String SMTP_HOST_SERVER ="smtp.gmail.com";
	public static final String SMTP_HOST_PORT ="25";
	
	//jwt token keys
	
	public static final String JWT_ISSUER="jwtappissuer";
	public static final String JWT_SIGNING_KEY="MyCountryIndia123";
	
	// email pattern checker
	
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// code

	public static final String BAD_REQUEST = "BAD_REQUEST";

	public static final String UNAUTHORISED_ACCESS = "UNAUTHORISED_ACCESS";

	public static final String DB_EXCEPTION = "DB_EXCEPTION";

	public static final String GENERIC_EXCEPTION = "GENERIC_EXCEPTION";
	
	public static final String JWT_TOKEN_EXCEPTION = "JWT_TOKEN_EXCEPTION";
	
		
	public static final String EMAIL_EXCEPTION = "EMAIL_EXCEPTION";

	// error message

	public static final String USERNAME_FOUND_ERR_MSG = "User already registered in the system";
	public static final String USERID_NOT_FOUND_ERR_MSG = "Invalid UserId, No User found ";
	
	public static final String DB_USERNAMECHECK_FAIED_ERR_MSG = "Username check failed due to db unavailable";
	public static final String DB_USERIDCHECK_FAIED_ERR_MSG = "UserId check failed due to db unavailable";
	public static final String DB_USER_SAVE_OP_FAILED_ERR_MSG = "Unable to store user data into db due to db unavailable";
	
	public static final String EMAIL_NOT_SENT_ERR_MSG="Unable to send email to user";
	public static final String JWT_TOKEN_CREATION_FAILED_ERR_MSG="jwt token generation failed";
	
	public static final String NO_RECORD_FOUND_ERR_MSG="No record found for the given userId";
	
	public static final String INVALID_PAYLOAD_REQUEST_ERR_MSG = "Invalid payload,either payload data is empty or null";
	public static final String INVALID_PROPERTY_REQUEST_ERR_MSG = "Value can't be null for property";
	public static final String INVALID_EMAIL_FORMAT_ERR_MSG = "invalid email format";

	public static final String INVALID_JWT_TOKEN_ERR_MSG="jwt token is invalid or expired, please issue valid jwt token";
	
	// success messages

	public static final String SIGNUP_SUCCESS_MSG = "User Registered successfully";
	public static final String LOGIN_SUCCESS_MSG="Login successful";
	
	
	//end point for user details
	
	public static final String USER_DETAILS_API="http://localhost:9095/api/v1/account/auth/userinfo";
	

}
