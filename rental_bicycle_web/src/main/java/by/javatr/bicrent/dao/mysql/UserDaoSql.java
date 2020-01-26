package by.javatr.bicrent.dao.mysql;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.UserDao;
import by.javatr.bicrent.dao.valid.SQLValidation;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.en_um.UserStatus;
import org.apache.logging.log4j.LogManager;

public class UserDaoSql extends BaseDaoSql implements UserDao {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final String SQL_USER_INSERT =
            "INSERT INTO `user` (`user_login`, `user_password`, `user_role`, `user_status`)" +
                    " VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT `user_login`, `user_password`, `user_role`, `user_status` FROM `user`" +
                    " WHERE `user_id` = ?";
    private static final String SQL_ALL_USERS_SELECT =
            "SELECT `user_id`, `user_login`, `user_password`, `user_role`, `user_status` FROM `user`" +
                    " ORDER BY `user_login`";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT `user_id`, `user_role`, `user_status` FROM `user` WHERE `user_login` = ? AND `user_password` = ?";
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT `user_id`, `user_role`, `user_status`, `user_password` FROM `user` WHERE `user_login` = ?";
    private static final String SQL_USER_UPDATE =
            "UPDATE `user` SET `user_login` = ?, `user_password` = ?, `user_role` = ?, `user_status` = ?" +
                    " WHERE `user_id` = ?";
    private static final String SQL_USER_DELETE = "DELETE FROM `user` WHERE `user_id` = ?";

    public UserDaoSql(Connection connection) {
        this.connection = connection;
    }

    protected UserDaoSql() {
        super();
    }

    @Override
    public Integer create(User user) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfUser = null;
        try {
            SQLValidation SQLValidation = new SQLValidation();
            statement = connection.prepareStatement(SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getId());
            statement.setInt(4, user.getUserStatus().getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                idOfUser = resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `users`");
                try {
                    throw new PersistentException();
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
            }
        } catch(SQLException e) {
            try {
                throw new SQLException(e);
            } catch (SQLException ex) {
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
        return idOfUser;
    }


    @Override
    public User read(String login, String password) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.getById(resultSet.getInt("user_role")));
                user.setUserStatus(UserStatus.getById(resultSet.getInt("user_status")));
            }
        } catch(SQLException e) {
            try {
                throw new SQLException(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                resultSet.close();
            } catch(SQLException |NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException |NullPointerException e) {}
        }
        return user;
    }

    @Override
    public User read(String login) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            try {
                statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
                statement.setString(1, login);
                resultSet = statement.executeQuery();
                if (resultSet != null && resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setLogin(login);
                    user.setRole(Role.getById(resultSet.getInt("user_role")));
                    user.setUserStatus(UserStatus.getById(resultSet.getInt("user_status")));
                    user.setPassword(resultSet.getString("user_password"));
                    resultSet = statement.executeQuery();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch(SQLException |NullPointerException e) {}
            try {
                if (statement != null) statement.close();
            } catch(SQLException |NullPointerException e) {}
        }
        return user;
    }

    @Override
    public List<User> read()  {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                statement = connection.prepareStatement(SQL_ALL_USERS_SELECT);
                resultSet = statement.executeQuery();
                User user = null;
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setLogin(resultSet.getString("user_login"));
                    user.setPassword(resultSet.getString("user_password"));
                    user.setRole(Role.getById(resultSet.getInt("user_role")));
                    user.setUserStatus(UserStatus.getById(resultSet.getInt("user_status")));
                    users.add(user);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                resultSet.close();
            } catch (SQLException |NullPointerException e) {
            }
            try {
                statement.close();
//                connection.close();
            } catch (SQLException |NullPointerException e) {
            }
        }
        return users;
    }


    @Override
    public User read(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            try {
                statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = new User();
                    user.setId(id);
                    user.setLogin(resultSet.getString("user_login"));
                    user.setPassword(resultSet.getString("user_password"));
                    user.setRole(Role.getById(resultSet.getInt("user_role")));
                    user.setUserStatus(UserStatus.getById(resultSet.getInt("user_status")));
                    return user;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException |NullPointerException e) {}
        }
    }

    @Override
    public void update(User user) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_USER_UPDATE);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getId());
            statement.setInt(4, user.getUserStatus().getId());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            try {
                throw new SQLException(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                statement.close();
            } catch(SQLException |NullPointerException e) {}
        }
    }

    //    @Override
    public void delete(Integer id)  {
        PreparedStatement statement = null;
        try {
            try {
                statement = connection.prepareStatement(SQL_USER_DELETE);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }
}
