package com.wmsterminal.func.db;


import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wmsterminal.model.SqliteTest;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TABLE_NAME = "sqlite-test.db";
	
	private Dao<SqliteTest ,Integer> sqliteTestDao = null;
	
	private DatabaseHelper(Context context)
	{
		super(context, TABLE_NAME, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		// TODO Auto-generated method stub
		try {
			TableUtils.createTable(connectionSource, SqliteTest.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		// TODO Auto-generated method stub
		try {
			TableUtils.dropTable(connectionSource, SqliteTest.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	private static DatabaseHelper instance;
	
	/**
	 * µ¥Àý»ñÈ¡¸ÃHelper
	 * @param context
	 * @return
	 */
	
	public static synchronized DatabaseHelper getHelper(Context context){
		
		if(instance == null){
			synchronized(DatabaseHelper.class){
				if(instance == null)
					instance = new DatabaseHelper(context);
			}
		}
		return instance;
		
	}
	
	/**
	 * »ñÈ¡sqliteTestDao
	 * @return
	 * @throws SQLException
	 */
	
	public Dao<SqliteTest,Integer> getSqliteTestDao() throws SQLException{
		
		if(sqliteTestDao == null){
			sqliteTestDao = getDao(SqliteTest.class);
		}
		return sqliteTestDao;
		
	}
	
	public void close(){
		
		super.close();
		sqliteTestDao = null;
	}

}
