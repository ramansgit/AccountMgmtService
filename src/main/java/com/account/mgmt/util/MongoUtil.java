package com.account.mgmt.util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * singleton interface to get mongo db instance
 *
 * @author ramans
 *
 */
public class MongoUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoUtil.class);
	/**
	 * mongo client
	 */
	private static MongoClient mongoclient;

	/**
	 * not exposed constructor
	 */
	private MongoUtil() {

	}

	/**
	 * singleton mongodb instance
	 *
	 * @return
	 */
	public static MongoClient getInstance() {

		if (mongoclient == null) {
			synchronized (MongoUtil.class) {
				if (mongoclient == null) {
					MongoCredential credential = MongoCredential.createCredential(AccountMgmtConstant.MONGO_DB_USERNAME,
							AccountMgmtConstant.MONGO_DB_ADMIN, AccountMgmtConstant.MONGO_DB_PASSWORD.toCharArray());
					mongoclient = new MongoClient(
							new ServerAddress(AccountMgmtConstant.MONGO_DB_HOSTNAME, AccountMgmtConstant.MONGO_DB_PORT),
							Arrays.asList(credential));
				}
			}

		}
		return mongoclient;
	}

	/**
	 * get db collection
	 *
	 * @param collectionName
	 * @return
	 */
	public static DBCollection getCollectionByName(String collectionName) {

		MongoClient client = MongoUtil.getInstance();
		DB db = client.getDB(AccountMgmtConstant.MONGO_DB_NAME);
		DBCollection col = db.getCollection(collectionName);

		return col;
	}

}