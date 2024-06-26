package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DataTooLongException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.models.Teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherListProcedureDAO implements ProcedureDAO{
    private static String TEACHER_CF = "CF";
    private static String TEACHER_NAME = "Nome";
    private static String TEACHER_ADDRESS = "Indirizzo";
    private static String TEACHER_NATIONALITY = "Nazionalita";
    @Override
    public List<Teacher> execute(Object... params) throws DAOException{
        List<Teacher> teacherList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call teacher_list()}");
            cs.execute();
            ResultSet resultSet = cs.getResultSet();
            String teacherCf, teacherName, teacherAddress, teacherNationality;
            while(resultSet.next()){
                teacherCf = resultSet.getString(TEACHER_CF);
                teacherName = resultSet.getString(TEACHER_NAME);
                teacherAddress = resultSet.getString(TEACHER_ADDRESS);
                teacherNationality = resultSet.getString(TEACHER_NATIONALITY);
                teacherList.add(new Teacher(teacherCf,teacherName,teacherAddress,teacherNationality));
            }
        }catch(SQLException e){
            throw new DAOException("Teacher list error: "+e.getMessage());
        }
        return teacherList;
    }
}
