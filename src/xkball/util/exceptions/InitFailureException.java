package xkball.util.exceptions;

public class InitFailureException extends RuntimeException{
    public InitFailureException(String msg){
        super(msg);
        //Log.log.printException(this);
    }
}
