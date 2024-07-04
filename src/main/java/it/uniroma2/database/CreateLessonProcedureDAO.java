package it.uniroma2.database;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.*;
import it.uniroma2.models.Course;
import it.uniroma2.models.Lesson;
import it.uniroma2.models.Level;
import it.uniroma2.models.Teacher;

import java.sql.*;
import java.time.LocalTime;

public class CreateLessonProcedureDAO implements ProcedureDAO{
    @Override
    public Lesson execute(Object... params) throws DAOException, DuplicatedEntryDAOException, DataTooLongException, LessonTimeException,VirException {
        int courseCode = Integer.parseInt((String) params[0]);
        String courseLevel = (String) params[1];
        String teacher = (String) params[2];
        String day = (String) params[3];
        Time startTime = Time.valueOf(LocalTime.parse((String) params[4]));
        Time endTime = Time.valueOf(LocalTime.parse((String) params[5]));
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("call create_lesson(?,?,?,?,?,?)");
            cs.setInt(1,courseCode);
            cs.setString(2,courseLevel);
            cs.setString(3,teacher);
            cs.setString(4,day);
            cs.setTime(5,startTime);
            cs.setTime(6,endTime);
            cs.execute();
        }catch(SQLIntegrityConstraintViolationException e){
            if(e.getErrorCode() == 1062) {
                throw new DuplicatedEntryDAOException("Lezione inserita già esistente !!");
            }else if(e.getErrorCode() == 1452){
                throw new VirException("Corso o insegnante inserito non esistente !!");
            }
        }catch(MysqlDataTruncation e){
            throw new DataTooLongException(e.getMessage());
        }catch(SQLException e){
            if(e.getSQLState().contains("45000")){
                throw new LessonTimeException(e.getMessage());
            }else{
                throw new DAOException("Lesson creation error: "+e.getMessage());
            }
        }
        return new Lesson(new Course(courseCode,new Level(courseLevel)),new Teacher(teacher),day,startTime,endTime);
    }
}
