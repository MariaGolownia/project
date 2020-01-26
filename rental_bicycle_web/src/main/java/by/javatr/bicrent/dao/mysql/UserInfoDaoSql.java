package by.javatr.bicrent.dao.mysql;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.UserInfoDao;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.en_um.Country;
import org.apache.logging.log4j.LogManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoSql extends BaseDaoSql implements UserInfoDao {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final String SQL_USER_INFO_INSERT =
            "INSERT INTO `userinfo` " +
                    "(`userInfo_id`, `userInfo_surname`, `userInfo_name`, `userInfo_secondName`, `userInfo_birthDate`,`userInfo_country`," +
                    " `userInfo_passportIssueDate`, `userInfo_passportIssuingAuthority`, `userInfo_passportIdentificationNumber` ," +
                    " `userInfo_passportSerialNumber`,`userInfo_passportAddressRegistration`,`userInfo_passportAddressResidence`" +
                    ",`userInfo_phoneNumber`,`userInfo_secondPhoneNumber`,`userInfo_email`)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_USER_INFO =
            "SELECT  `userInfo_surname`, `userInfo_name`, `userInfo_secondName`, `userInfo_birthDate`,`userInfo_country`," +
                    "`userInfo_passportIssueDate`, `userInfo_passportIssuingAuthority`, `userInfo_passportIdentificationNumber`, " +
                    "`userInfo_passportSerialNumber`,`userInfo_passportAddressRegistration`,`userInfo_passportAddressResidence`," +
                    "`userInfo_phoneNumber`,`userInfo_secondPhoneNumber`,`userInfo_email` FROM `userinfo` WHERE `userInfo_id` = ?";
    private static final String SQL_SELECT_USER_INFO_BY_COUNTRY =
            "SELECT `userInfo_id`, `userInfo_surname`, `userInfo_name`, `userInfo_secondName`, `userInfo_birthDate`," +
                    "`userInfo_passportIssueDate`, `userInfo_passportIssuingAuthority`, `userInfo_passportIdentificationNumber`," +
                    "`userInfo_passportSerialNumber`,`userInfo_passportAddressRegistration`,`userInfo_passportAddressResidence`," +
                    "`userInfo_phoneNumber`,`userInfo_secondPhoneNumber`,`userInfo_email` FROM `userinfo` WHERE `userInfo_country` = ?";
        private static final String SQL_USER_INFO_UPDATE =
            "UPDATE `userinfo` SET `userInfo_surname` = ?, `userInfo_name` = ?, `userInfo_secondName`= ?, " +
                    "`userInfo_birthDate` = ?,`userInfo_country` = ?, `userInfo_passportIssueDate` = ?," +
                    " `userInfo_passportIssuingAuthority` = ?, `userInfo_passportIdentificationNumber` = ?," +
                    "`userInfo_passportSerialNumber` = ?,`userInfo_passportAddressRegistration`  = ?," +
                    "`userInfo_passportAddressResidence` = ?, `userInfo_phoneNumber`  = ?,`userInfo_secondPhoneNumber` = ?," +
                    "`userInfo_email` = ? WHERE `userInfo_id` = ?";
    private static final String SQL_USER_INFO_DELETE = "DELETE FROM `userinfo` WHERE `userInfo_id` = ?";
    private static final String SQL_SELECT_USER_INFO_BY_PASSPORT_ID =
            "SELECT `userInfo_id`, `userInfo_country`, `userInfo_surname`, `userInfo_name`, `userInfo_secondName`, `userInfo_birthDate`," +
                    "`userInfo_passportIssueDate`, `userInfo_passportIssuingAuthority`," +
                    "`userInfo_passportSerialNumber`,`userInfo_passportAddressRegistration`,`userInfo_passportAddressResidence`," +
                    "`userInfo_phoneNumber`,`userInfo_secondPhoneNumber`,`userInfo_email` FROM `userinfo`" +
                    " WHERE `userInfo_passportIdentificationNumber` = ?";

    public UserInfoDaoSql(Connection connection) {
        this.connection = connection;
    }

    protected UserInfoDaoSql() {
        super();
    }

    @Override
    public UserInfo readByUser(User user) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_USER_INFO);
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            UserInfo userInfo = new UserInfo();
            while(resultSet.next()) {
                userInfo = new UserInfo();
                userInfo.setId(resultSet.getInt(user.getId()));
                userInfo.setSurname(resultSet.getString("userInfo_surname"));
                userInfo.setName(resultSet.getString("userInfo_name"));
                userInfo.setSecondName(resultSet.getString("userInfo_secondName"));
                userInfo.setBirthDate(resultSet.getDate("userInfo_birthDate").toLocalDate());
                userInfo.setCountry(Country.getCountry(resultSet.getString("userInfo_country")));
                userInfo.setPassportIssueDate(resultSet.getDate("userInfo_passportIssueDate").toLocalDate());
                userInfo.setPassportIssuingAuthority(resultSet.getString("userInfo_passportIssuingAuthority"));
                userInfo.setPassportIdentificationNumber(resultSet.getString("userInfo_passportIdentificationNumber"));
                userInfo.setPassportSerialNumber(resultSet.getString("userInfo_passportSerialNumber"));
                userInfo.setPassportAddressRegistration(resultSet.getString("userInfo_passportAddressRegistration"));
                userInfo.setPassportAddressResidence(resultSet.getString("userInfo_passportAddressResidence"));
                userInfo.setPhoneNumber(resultSet.getLong("userInfo_phoneNumber"));
                userInfo.setSecondPhoneNumber(resultSet.getLong("userInfo_secondPhoneNumber"));
                userInfo.setEmail(resultSet.getString("userInfo_email"));
            }
            try {
                resultSet.close();
            } catch( NullPointerException e) {}
            try {
                statement.close();
            } catch( NullPointerException e) {}
        return userInfo;
    }

    @Override
    public List<UserInfo> readByCountry(String country) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_USER_INFO_BY_COUNTRY);
            statement.setString(1, country);
            resultSet = statement.executeQuery();
            List<UserInfo> userInfoList = new ArrayList<>();
            UserInfo userInfo = new UserInfo();
            while(resultSet.next()) {
                userInfo = new UserInfo();
                userInfo.setId(resultSet.getInt("userInfo_id"));
                userInfo.setSurname(resultSet.getString("userInfo_surname"));
                userInfo.setName(resultSet.getString("userInfo_name"));
                userInfo.setSecondName(resultSet.getString("userInfo_secondName"));
                userInfo.setBirthDate(resultSet.getDate("userInfo_birthDate").toLocalDate());
                userInfo.setCountry(Country.getCountry(country));
                userInfo.setPassportIssueDate(resultSet.getDate("userInfo_passportIssueDate").toLocalDate());
                userInfo.setPassportIssuingAuthority(resultSet.getString("userInfo_passportIssuingAuthority"));
                userInfo.setPassportIdentificationNumber(resultSet.getString("userInfo_passportIdentificationNumber"));
                userInfo.setPassportSerialNumber(resultSet.getString("userInfo_passportSerialNumber"));
                userInfo.setPassportAddressRegistration(resultSet.getString("userInfo_passportAddressRegistration"));
                userInfo.setPassportAddressResidence(resultSet.getString("userInfo_passportAddressResidence"));
                userInfo.setPhoneNumber(resultSet.getLong("userInfo_phoneNumber"));
                userInfo.setSecondPhoneNumber(resultSet.getLong("userInfo_secondPhoneNumber"));
                userInfo.setEmail(resultSet.getString("userInfo_email"));
                userInfoList.add(userInfo);
            }
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        return userInfoList;
    }

    @Override
    public UserInfo readByPassportId(String search) {
        UserInfo userInfo = new UserInfo();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_INFO_BY_PASSPORT_ID);
            statement.setString(1, search);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                userInfo.setId(resultSet.getInt("userInfo_id"));
                String countryStr = resultSet.getString("userInfo_country");
                userInfo.setCountry(countryStr==null?null:Country.getCountry(countryStr));
                userInfo.setSurname(resultSet.getString("userInfo_surname"));
                userInfo.setName(resultSet.getString("userInfo_name"));
                userInfo.setSecondName(resultSet.getString("userInfo_secondName"));
                Date resultDate = resultSet.getDate("userInfo_birthDate");
                userInfo.setBirthDate(resultDate==null?null:resultDate.toLocalDate());
                userInfo.setPassportIdentificationNumber(search);
                Date resultPassportIssueDate = resultSet.getDate("userInfo_passportIssueDate");
                userInfo.setPassportIssueDate(resultPassportIssueDate==null?null:resultPassportIssueDate.toLocalDate());
                userInfo.setPassportIssuingAuthority(resultSet.getString("userInfo_passportIssuingAuthority"));
                userInfo.setPassportSerialNumber(resultSet.getString("userInfo_passportSerialNumber"));
                userInfo.setPassportAddressRegistration(resultSet.getString("userInfo_passportAddressRegistration"));
                userInfo.setPassportAddressResidence(resultSet.getString("userInfo_passportAddressResidence"));
                userInfo.setPhoneNumber(resultSet.getLong("userInfo_phoneNumber"));
                userInfo.setSecondPhoneNumber(resultSet.getLong("userInfo_secondPhoneNumber"));
                userInfo.setEmail(resultSet.getString("userInfo_email"));
            }

        } catch(SQLException e) {
         e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
        return userInfo;
}

    @Override
    public Integer create(UserInfo userInfo) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfLocation = null;
        try {
            statement = connection.prepareStatement(SQL_USER_INFO_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userInfo.getId());
            statement.setString(2, userInfo.getSurname());
            statement.setString(3, userInfo.getName());
            statement.setString(4, userInfo.getSecondName());
            statement.setDate(5, (userInfo.getBirthDate() == null) ? null : Date.valueOf(userInfo.getBirthDate()));
            statement.setString(6, (userInfo.getCountry() == null) ? null : userInfo.getCountry().getName());
            statement.setDate(7, (userInfo.getPassportIssueDate() == null) ? null : Date.valueOf(userInfo.getPassportIssueDate()));
            statement.setString(8, userInfo.getPassportIssuingAuthority());
            statement.setString(9, userInfo.getPassportIdentificationNumber());
            statement.setString(10, userInfo.getPassportSerialNumber());
            statement.setString(11, userInfo.getPassportAddressRegistration());
            statement.setString(12, userInfo.getPassportAddressResidence());
            statement.setLong(13, userInfo.getPhoneNumber());
            statement.setLong(14, (userInfo.getSecondPhoneNumber() == null) ? 0L : userInfo.getSecondPhoneNumber());
            statement.setString(15, userInfo.getEmail());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                idOfLocation = resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `users`");
                throw new PersistentException();
            }
        } catch (PersistentException e) {
           e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
        return idOfLocation;
    }

    @Override
    public UserInfo read(Integer id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_USER_INFO);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            UserInfo userInfo = new UserInfo();
            while(resultSet.next()) {
                userInfo = new UserInfo();
                userInfo.setId(id);
                userInfo.setSurname(resultSet.getString("userInfo_surname"));
                userInfo.setName(resultSet.getString("userInfo_name"));
                userInfo.setSecondName(resultSet.getString("userInfo_secondName"));
                Date resultDate = resultSet.getDate("userInfo_birthDate");
                userInfo.setBirthDate(resultDate==null?null:resultDate.toLocalDate());
                userInfo.setCountry(Country.getCountry(resultSet.getString("userInfo_country")));
                Date resultPassportIssueDate = resultSet.getDate("userInfo_passportIssueDate");
                userInfo.setPassportIssueDate(resultPassportIssueDate==null?null:resultPassportIssueDate.toLocalDate());
                userInfo.setPassportIssuingAuthority(resultSet.getString("userInfo_passportIssuingAuthority"));
                userInfo.setPassportIdentificationNumber(resultSet.getString("userInfo_passportIdentificationNumber"));
                userInfo.setPassportSerialNumber(resultSet.getString("userInfo_passportSerialNumber"));
                userInfo.setPassportAddressRegistration(resultSet.getString("userInfo_passportAddressRegistration"));
                userInfo.setPassportAddressResidence(resultSet.getString("userInfo_passportAddressResidence"));
                userInfo.setPhoneNumber(resultSet.getLong("userInfo_phoneNumber"));
                userInfo.setSecondPhoneNumber(resultSet.getLong("userInfo_secondPhoneNumber"));
                userInfo.setEmail(resultSet.getString("userInfo_email"));
            }
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        return userInfo;
    }

    @Override
    public void update(UserInfo userInfo) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_USER_INFO_UPDATE, Statement.RETURN_GENERATED_KEYS);
            Integer userInfoId = userInfo.getId();
            if (userInfoId != null) {
                statement.setString(1, userInfo.getSurname());
                statement.setString(2, userInfo.getName());
                statement.setString(3, userInfo.getSecondName());
                statement.setDate(4,(userInfo.getBirthDate() == null) ? null : Date.valueOf(userInfo.getBirthDate()));
                statement.setString(5, userInfo.getCountry().getName());
                statement.setDate(6, (userInfo.getPassportIssueDate() == null) ? null : Date.valueOf(userInfo.getPassportIssueDate()));
                statement.setString(7, userInfo.getPassportIssuingAuthority());
                statement.setString(8, userInfo.getPassportIdentificationNumber());
                statement.setString(9, userInfo.getPassportSerialNumber());
                statement.setString(10, userInfo.getPassportAddressRegistration());
                statement.setString(11, userInfo.getPassportAddressResidence());
                statement.setLong(12, userInfo.getPhoneNumber());
                statement.setLong(13, userInfo.getSecondPhoneNumber());
                statement.setString(14, userInfo.getEmail());
            }
            else  {
                try {
                    throw new PersistentException("Check the input id and its presence in the database!");
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
            }

            statement.setInt(15, userInfo.getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}

    }

    @Override
    public void delete(Integer id) throws SQLException  {
        PreparedStatement statement = null;

            statement = connection.prepareStatement(SQL_USER_INFO_DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
    }
}
