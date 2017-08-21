Module Flow

AccountMgmtApi ->  
	AccountMgmtApiService -> 
			AccountMgmtApiServiceImpl
				->SignInHandler ( or any handlers)
					-> calls util classes 
						-> AccountMgmtDaoService
						

Deployment Steps:


1. update these configuration values as per your enviornment.

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

	
2. Running Application

java -jar AccountMgmtService-1.0-SNAPSHOT.jar server ../config.yml

	
3. Testing Uplication via Swagger endpoint

http://localhost:9095/swagger

