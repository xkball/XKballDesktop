package xkball.ui;

import xkball.interfaces.IColorSetting;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;

public class DarkJMenuUI extends BasicMenuUI {
    {
        this.selectionForeground = IColorSetting.charsColor2;
        this.selectionBackground = IColorSetting.partitionLinesColor4;
    }
    
//    @Override
//    public void paintBackground(Graphics var1, JMenu var2, Color var3){
//        var2.setBorderPainted(false);
//        super.paintBackground(var1,var2,var3);
//    }
    
    @Override
    public void installUI(JComponent var1){
        super.installUI(var1);
        var1.setBackground(IColorSetting.partitionLinesColor2);
        var1.setForeground(IColorSetting.charsColor1);
        JMenu var2 = (JMenu) var1;
        var2.getPopupMenu().setBackground(IColorSetting.partitionLinesColor2);
        var2.getPopupMenu().setBorderPainted(false);
        //var1.setBorder(null);
    }
}
