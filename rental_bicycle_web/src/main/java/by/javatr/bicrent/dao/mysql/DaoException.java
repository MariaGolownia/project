package by.javatr.bicrent.dao.mysql;

public class DaoException extends Exception{

    public DaoException(){
        super();
    };

    public DaoException(String str){
        super(str);
    };

    public DaoException(String str, Exception ex){
        super(str, ex);
    };

    public DaoException(Exception ex){
        super(ex);
    };
}
