package by.javatr.bicrent.dao.valid;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.pool.ConnectionSQL;

import java.sql.*;

public class SQLValidation {
    private static final String SQL_USER_SELECT = "SELECT `user_login` FROM `user` WHERE `user_login` = ?";
    private static final String SQL_COMPANY_SELECT = "SELECT `company_id` FROM `company` WHERE `company_id` = ?";
    private static final String SQL_COMPANY_SELECT_BY_ACCOUNT_NUMBER =
            "SELECT `company_accountNumberOfPayer` FROM `company` WHERE `company_accountNumberOfPayer` = ?";
    private static final String SQL_SELECT_COMPANY_BY_ID =
            "SELECT `company_name`, `company_accountNumberOfPayer` FROM `company` WHERE `company_id` = ?";

    // Проверка на наличие аналогичного логина пользователя в БД
    public Boolean ifIdenticalLoginInDB (String login, Connection connection) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Boolean ifIdenticalLoginInDB = false;
        try {
            statement = connection.prepareStatement(SQL_USER_SELECT);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                ifIdenticalLoginInDB = true;
            }
        } catch(SQLException e) {
            try {
                throw new PersistentException(e);
            } catch (PersistentException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
        return ifIdenticalLoginInDB;
    }


    public Boolean ifIdenticalLoginInDB (String login) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ConnectionSQL connectionSQL = new ConnectionSQL();
        Boolean ifIdenticalLoginInDB = false;
        Connection connection = connectionSQL.getConnectionToDB();
        try {
            statement = connection.prepareStatement(SQL_USER_SELECT);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                 ifIdenticalLoginInDB = true;
            }
        } catch(SQLException e) {
            try {
                throw new PersistentException(e);
            } catch (PersistentException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
                connection.close();
            } catch(SQLException | NullPointerException e) {}
        }
        return ifIdenticalLoginInDB;
    }


    // Проверка на наличие id компании в БД
    public Boolean ifСompanyExist (Integer id, Connection connection) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Boolean ifСompanyExist = false;
        try {
            statement = connection.prepareStatement(SQL_COMPANY_SELECT);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                ifСompanyExist = true;
            }
        } catch(SQLException e) {
            try {
                throw new PersistentException(e);
            } catch (PersistentException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
        return ifСompanyExist;
    }

    // Проверка на наличие id компании в БД
    public Integer returnIdIfСompanyExist (Integer id, Connection connection) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer IdOfСompany = null;
        try {
            statement = connection.prepareStatement(SQL_COMPANY_SELECT);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                IdOfСompany = id;
            }
        } catch (SQLException e) {
            try {
                throw new PersistentException(e);
            } catch (PersistentException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
        return IdOfСompany;
    }

    // Проверка на наличие учетного номера плательщика компании в БД
    public Boolean ifСompanyNumberExist (Integer number, Connection connection) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Boolean ifСompanyExist = false;
        try {
            statement = connection.prepareStatement(SQL_COMPANY_SELECT_BY_ACCOUNT_NUMBER);
            statement.setInt(1, number);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                ifСompanyExist = true;
            }
        } catch(SQLException e) {
            try {
                throw new PersistentException(e);
            } catch (PersistentException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
        return ifСompanyExist;
    }
}
