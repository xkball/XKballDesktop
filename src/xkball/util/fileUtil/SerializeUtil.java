package xkball.util.fileUtil;

import xkball.parts.log.Log;

import java.io.*;

public class SerializeUtil {
    //序列化用
    public static void serializeObj(File file,Object o) {
        if(file.exists()){
            file.delete();
        }
        if(!file.exists()){
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.log.printException(e);
            }
        }
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.log.printException(e);
        }
    }
    
    public static Object deserialize(File file) {
        Object o = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object obj = ois.readObject();
            o = obj;
            ois.close();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Log.log.printException(e);
        }
        return o;
    }
}
