package net.bechtelus.standard;


import java.util.List;

import net.bechtelus.common.DAOException;
import net.bechtelus.beans.Standard;

/**
 * @author daniel
 *
 */
public interface StandardDAO  {
	
	 /**
     * Create the given item in the database. The user ID must be null, otherwise it will throw
     * IllegalArgumentException. After creating, the DAO will set the obtained ID in the given user.
     * @param standard The item to be created in the database.
     * @throws IllegalArgumentException If the item ID is not null.
     * @throws DAOException If something fails at database level.
     */
	public void insert(Standard standard) throws DAOException;
	
	
	public void update(Standard standard) throws DAOException;
	
	 /**
     * Deletes a standard from the database
     * @param int ID  the key to the record
     * @throws IllegalArgumentException if the ID is null
     * @throws DAOException If something fails at database level.
     */
	public void delete(int id) throws DAOException;

	public Standard findbyKey(int userid) throws DAOException;

	 /**
     * List all the Standards sorted by Sort_order.
     * @param none
     * @throws DAOException If something fails at database level.
     */
	public List listStandards() throws DAOException;

}
