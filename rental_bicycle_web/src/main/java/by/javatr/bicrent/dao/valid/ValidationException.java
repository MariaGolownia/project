package by.javatr.bicrent.dao.valid;

public class ValidationException extends Exception {

    public ValidationException(){
        super();
    };

    public ValidationException(String str){
        super(str);
    };

    public ValidationException(String str, Exception ex){
        super(str, ex);
    };

    public ValidationException(Exception ex){
        super(ex);
    };
}
