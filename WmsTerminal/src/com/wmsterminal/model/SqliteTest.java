package com.wmsterminal.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_sqlitetest")
public class SqliteTest {
	
	@DatabaseField(generatedId =true)
	private int id;
	
	@DatabaseField(columnName = "name")
	private String name;
	
	@DatabaseField(columnName = "age")
	private String age;

	public SqliteTest(){
		
		super();
	}
		
	public SqliteTest(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
}
