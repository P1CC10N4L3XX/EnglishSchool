package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.models.Level;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelListProcedureDAO implements ProcedureDAO{

    private static final String LEVEL_NAME = "Nome";
    private static final String LEVEL_BOOK = "Libro";
    private static final String LEVEL_EXAM = "Esame";

    @Override
    public List<Level> execute(Object... params) throws DAOException {
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call level_list()}");
            cs.execute();
            List<Level> levelList = new ArrayList<>();
            ResultSet resultSet = cs.getResultSet();
            while(resultSet.next()){
                levelList.add(new Level(resultSet.getString(LEVEL_NAME),resultSet.getString(LEVEL_BOOK),resultSet.getBoolean(LEVEL_EXAM)));
            }
            return levelList;
        } catch(SQLException e){
            throw new DAOException();
        }
    }
}
