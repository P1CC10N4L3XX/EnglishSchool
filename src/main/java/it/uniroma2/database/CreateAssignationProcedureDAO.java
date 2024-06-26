package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.models.Assignation;
import it.uniroma2.models.Course;
import it.uniroma2.models.Level;
import it.uniroma2.models.Teacher;

import java.sql.*;

public class CreateAssignationProcedureDAO implements ProcedureDAO{
    @Override
    public Assignation execute(Object... params) throws DAOException, DuplicatedEntryDAOException {
        String assignationTeacherCf = (String) params[0];
        int assignationCourseCode = Integer.parseInt((String)params[1]);
        String assignationCourseLevel = (String) params[2];
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call create_assignation(?,?,?)}");
            cs.setString(1,assignationTeacherCf);
            cs.setInt(2,assignationCourseCode);
            cs.setString(3,assignationCourseLevel);
            cs.execute();
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedEntryDAOException("L'insegnate specificato è stato già assegnato a questo corso!!");
        }catch(SQLException e){
            throw new DAOException("Assignation error: "+e.getMessage());
        }
        return new Assignation(new Teacher(assignationTeacherCf),new Course(assignationCourseCode,new Level(assignationCourseLevel)));
    }
}
