package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.models.Credentials;
import it.uniroma2.models.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginProcedureDAO implements ProcedureDAO{

    @Override
    public Credentials execute(Object... params) throws DAOException {
        String username = (String) params[0];
        String password = (String) params[1];
        int role;

        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call login(?,?,?)}");
            cs.setString(1, username);
            cs.setString(2,password);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.executeQuery();
            role = cs.getInt(3);
        }catch(SQLException e){
            throw new DAOException("Login error: "+ e.getMessage());
        }
        return new Credentials(username,password, Role.fromInt(role));
    }
}
