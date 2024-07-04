package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.exceptions.NotExistentLessonException;
import it.uniroma2.models.Absence;
import it.uniroma2.models.Student;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAbsenceProcedureDAO implements ProcedureDAO{
    @Override
    public Absence execute(Object... params) throws DAOException, DuplicatedEntryDAOException,NotExistentLessonException {
        String studentCf = (String) params[0];
        Date date = Date.valueOf(LocalDate.parse((String) params[1]));
        Time startTime = Time.valueOf(LocalTime.parse((String) params[2]));
        Time endTime = Time.valueOf(LocalTime.parse((String) params[3]));
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call create_absence(?,?,?,?)}");
            cs.setString(1, studentCf);
            cs.setDate(2,date);
            cs.setTime(3,startTime);
            cs.setTime(4,endTime);
            cs.execute();
        }catch(SQLIntegrityConstraintViolationException e){
            if(e.getErrorCode() == 1062) {
                throw new DuplicatedEntryDAOException("L'assenza in questa fascia oraria per questo giorno è stata già creata per lo studente !!");
            }else if(e.getErrorCode() == 1452){
                throw new NotExistentLessonException("Verifica che la lezione inserita esista !!");
            }
        }catch(SQLException e){
            throw new DAOException("Abscence error: "+e.getMessage());
        }
        return new Absence(new Student(studentCf),date,startTime,endTime);
    }
}
