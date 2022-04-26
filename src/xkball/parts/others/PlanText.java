package xkball.parts.others;

import org.jetbrains.annotations.NotNull;
import xkball.util.fileUtil.SerializeUtil;

import java.io.File;
import java.io.Serializable;

public class PlanText implements Serializable {
    
    private static final long serialVersionUID = 1461462L;
    
    private int page = 0;
    private String title;
    private final int[] states = new int[6];
    private final String[] texts = new String[6];
    
    public PlanText(String s,int page){
        this.title = s;
        this.page = page;
    }
    
    public void setTexts(String @NotNull [] newTexts){
        if(newTexts.length == 6){
            for(int i=0;i<6;i++){
                this.setText(i,newTexts[i]);
            }
        }
    }
    
    public void saveTexts(@NotNull File place){
        File p = new File(place.getPath()+File.separator+this.getPage()+".t");
        SerializeUtil.serializeObj(p,this);
    }
    
    public static PlanText getInstance(@NotNull File place, int page){
        File p = new File(place.getPath()+File.separator+page+".t");
        if(p.exists()){
            return (PlanText) SerializeUtil.deserialize(p);
        }
        else {
            return new PlanText("",page);
        }
    }
    
    public void setText(int i, String newText) {
        if(i<6){
            this.texts[i] = newText;
        }
    }
    
    public String getText(int i){
        return this.texts[i];
    }
    
    public void setStates(int @NotNull [] newStates){
        if(newStates.length == 6){
            for(int i=0;i<6;i++){
                this.setState(i,newStates[i]);
            }
        }
    }
    
    public void setState(int place,int state){
        if(place<6){
            states[place] = state;
        }
    }
    
    public int[] getStates(){
        return  states;
    }
    
    public int getState(int place){
        return states[place];
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public void setTitle(String s){
        this.title = s;
    }
}
