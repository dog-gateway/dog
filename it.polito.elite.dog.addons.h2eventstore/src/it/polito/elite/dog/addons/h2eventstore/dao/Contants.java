package it.polito.elite.dog.addons.h2eventstore.dao;

public class Contants {

	final static String SCHEMA = "CREATE TABLE EVENT(NAME VARCHAR(100), VALUE DECIMAL, UNIT VARCHAR(5), TIMESTAMP TIMESTAMP);";

	final static String INSERT = "INSERT INTO EVENT (NAME, VALUE, UNIT, TIMESTAMP) VALUES (?, ?, ?, ?);";
}
