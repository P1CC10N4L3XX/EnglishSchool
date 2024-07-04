package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DataTooLongException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.models.Course;
import it.uniroma2.models.Level;
import it.uniroma2.models.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class CreateStudentProcedureDAO implements ProcedureDAO{

    @Override
    public Student execute(Object... params) throws DAOException, DuplicatedEntryDAOException, DataTooLongException {
        String cf = (String) params[0];
        String name = (String) params[1];
        int absenceNumber = Integer.parseInt((String) params[2]);
        String telephonicNumber = (String) params[3];
        int courseCode = Integer.parseInt((String) params[4]);
        String courseLevel = (String) params[5];
        Date registrationDate = (Date) params[6];
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call create_student(?,?,?,?,?,?,?)}");
            cs.setString(1,cf);
            cs.setString(2,name);
            cs.setInt(3,absenceNumber);
            cs.setString(4,telephonicNumber);
            cs.setInt(5,courseCode);
            cs.setString(6,courseLevel);
            cs.setDate(7,registrationDate);
            cs.execute();
        } catch (SQLException e) {
            throw new DAOException("Creation student error: "+e.getMessage());
        }
        return new Student(cf,name,absenceNumber,telephonicNumber,new Course(courseCode,new Level(courseLevel)),registrationDate);
    }
}
