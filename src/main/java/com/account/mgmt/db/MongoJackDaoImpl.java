package com.account.mgmt.db;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.account.mgmt.exception.MongoDatabaseException;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoJackDaoImpl<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoJackDaoImpl.class);
	JacksonDBCollection<T, String> col;
	DBCollection seqCol;

	public MongoJackDaoImpl(JacksonDBCollection<T, String> col) {
		this.col = col;

	}

	public MongoJackDaoImpl(DBCollection col) {

		this.seqCol = col;

	}

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws MongoDatabaseException
	 */
	public String save(T obj) throws MongoDatabaseException {

		try {
			WriteResult<T, String> result = col.insert(obj);
			return result.getSavedId();
		} catch (Exception e) {
			LOGGER.error("unable to save data in mondo db" + e);
			e.printStackTrace();
			throw new MongoDatabaseException("unable to save data");
		}
	}

	/**
	 * 
	 * @param query
	 * @return
	 * @throws MongoDatabaseException
	 */
	public Object get(DBObject query) throws MongoDatabaseException {
		try {
			T obj = col.findOne(query);
			return obj;
		} catch (Exception e) {
			LOGGER.error("unable to get data from mongo db" + e);
			e.printStackTrace();
			throw new MongoDatabaseException("unable to fetch data");
		}
	}

}
