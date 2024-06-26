package it.uniroma2.database;

import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DataTooLongException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.exceptions.LessonTimeException;

import java.sql.SQLException;

public interface ProcedureDAO<Return> {
    Return execute(Object... params) throws DAOException, DuplicatedEntryDAOException, DataTooLongException, LessonTimeException;
}
