package org.example.player;
import java.sql.*;

public class Song {
    Connection connection = null;
    ResultSet resultSet = null;
    private void connect() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/musicplayer","root","password");
    }

    public String[] getData() throws Exception{
        String[] names = new String[20];

        String query = "select * from song;";
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        for (int i =1; i<=getCount();i++){
            resultSet.next();
            names[i-1] = resultSet.getString("Songname");

        }

        statement.close();
        connection.close();

        return names;
    }

    public String[] getLocation() throws Exception{
        String[] names = new String[20];

        String query = "select * from song;";
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        for (int i =1; i<=getCount();i++){
            resultSet.next();
            names[i-1] = resultSet.getString("song_location");

        }

        statement.close();
        connection.close();

        return names;
    }

    public int getCount() throws Exception{
        String query = "select count(Songname) from song";
        connect();
        Statement statement = connection.createStatement();
        this.resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt(1);

    }

}


