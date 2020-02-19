package com.example.filedemo.entity;

public enum FileTypes {

	LETTER(1,"pismo"),
	SENTENCE(2,"wyrok"),
	DECREE(3,"zarządzenie");
	
	int number;
	String polishName;
	
	private FileTypes(int number, String polishName) {
		this.number = number;
		this.polishName = polishName;
	}
	
	
	
}
