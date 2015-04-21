package net.bechtelus.common;

import net.bechtelus.standard.StandardDAO;


//Abstract class DAO Factory
public abstract class DAOFactory {

// List of DAO types supported by the factory
public static final int MYSQL = 1;
public static final int ORACLE = 2;
public static final int SYBASE = 3;


// There will be a method for each DAO that can be 
// created. The concrete factories will have to 
// implement these methods.
public abstract StandardDAO getStandardDAO();


public static DAOFactory getDAOFactory(
   int whichFactory) {

	switch (whichFactory) {
		case MYSQL: 
			return new MySQLDAOFactory();
		case ORACLE    : 
	//		return new OracleDAOFactory();      
		case SYBASE    : 
	//		return new SybaseDAOFactory();
		default           : 
			return null;
	}
	}
}