package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.models.Course;
import it.uniroma2.models.Level;

import java.sql.*;

public class CreateCourseProcedureDAO implements ProcedureDAO{

    @Override
    public Course execute(Object... params) throws DAOException {
        String courseLevel = (String) params[0];
        Date courseDateActivation = (Date) params[1];
        int courseCode;
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call create_course(?,?,?)}");
            cs.setString(1,courseLevel);
            cs.setDate(2,courseDateActivation);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            courseCode = cs.getInt(3);
        }catch(SQLException e){
            throw new DAOException("Creation Course error: "+e.getMessage());
        }
        return new Course(courseCode,new Level(courseLevel),courseDateActivation,0);
    }
}
