package it.uniroma2.database.utils;

import it.uniroma2.models.Role;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactoryInstance;
    private Connection connection;
    private ConnectionFactory(){
        try(FileInputStream fileInputStream = new FileInputStream(getClass().getClassLoader().getResource("config/db.properties").getFile())){
            Properties prop = new Properties();
            prop.load(fileInputStream);
            String username = prop.getProperty("USERNAME_LOGIN");
            String password = prop.getProperty("PASSWORD_LOGIN");
            String url = prop.getProperty("CONNECTION_URL");

            connection = DriverManager.getConnection(url,username,password);
        }catch(IOException | SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        return connection;
    }

    public static ConnectionFactory getInstance(){
        if(connectionFactoryInstance == null){
            connectionFactoryInstance = new ConnectionFactory();
        }
        return connectionFactoryInstance;
    }

    public void changeRole(Role role) throws SQLException{
        connection.close();
        try(FileInputStream fileInputStream = new FileInputStream(getClass().getClassLoader().getResource("config/db.properties").getFile())){
            Properties prop = new Properties();
            prop.load(fileInputStream);
            String url = prop.getProperty("CONNECTION_URL");
            String username = prop.getProperty("USERNAME_"+role.name());
            String password = prop.getProperty("PASSWORD_"+role.name());
            connection = DriverManager.getConnection(url,username,password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
