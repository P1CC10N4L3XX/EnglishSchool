package it.uniroma2.database;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DataTooLongException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.models.Teacher;

import java.sql.*;

public class CreateTeacherProcedureDAO implements ProcedureDAO{
    @Override
    public Teacher execute(Object... params) throws DAOException, DuplicatedEntryDAOException, DataTooLongException {
        String cf = (String) params[0];
        String name = (String) params[1];
        String address = (String) params[2];
        String nationality = (String) params[3];

        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call create_teacher(?,?,?,?)}");
            cs.setString(1, cf);
            cs.setString(2, name);
            cs.setString(3, address);
            cs.setString(4, nationality);
            cs.execute();
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedEntryDAOException("Esiste gia un professore con lo stesso codice fiscale");
        }catch(MysqlDataTruncation e){
            throw new DataTooLongException("Una o più informazioni aggiunte contengono troppi caratteri");
        } catch (SQLException e) {
            throw new DAOException("Error creation teacher: "+e.getMessage());
        }
        return new Teacher(cf,name,address,nationality);
    }
}
