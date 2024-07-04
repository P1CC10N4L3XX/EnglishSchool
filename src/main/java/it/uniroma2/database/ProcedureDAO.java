package it.uniroma2.database;

import it.uniroma2.exceptions.*;

import java.sql.SQLException;

public interface ProcedureDAO<Return> {
    Return execute(Object... params) throws DAOException, DuplicatedEntryDAOException, DataTooLongException, LessonTimeException, NotExistentLessonException, VirException;
}
