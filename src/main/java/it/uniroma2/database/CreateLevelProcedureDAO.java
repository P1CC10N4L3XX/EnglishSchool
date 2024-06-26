package it.uniroma2.database;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DataTooLongException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.models.Level;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class CreateLevelProcedureDAO implements ProcedureDAO{
    @Override
    public Level execute(Object... params) throws DAOException, DuplicatedEntryDAOException, DataTooLongException {
        String levelName = (String) params[0];
        String levelBook = (String) params[1];
        boolean levelExam = (boolean) params[2];

        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call create_level(?,?,?)}");
            cs.setString(1,levelName);
            cs.setString(2,levelBook);
            cs.setBoolean(3, levelExam);
            cs.execute();
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedEntryDAOException("Livello con nome gia esistente!!");
        }catch(MysqlDataTruncation e){
            throw new DataTooLongException("Una o più informazioni aggiunte contengono troppi caratteri !!");
        }catch(SQLException e){
            throw new DAOException("Level creation error: "+e.getMessage());
        }
        return new Level(levelName,levelBook,levelExam);
    }
}
