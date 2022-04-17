package xkball.util.exceptions;

import xkball.parts.log.Log;

public class ContentsNotFoundException extends RuntimeException{
    public ContentsNotFoundException(String msg){
        super(msg);
        //Log.log.printException(this);
    }
}
