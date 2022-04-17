package xkball.ui;

import xkball.interfaces.IColorSetting;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;

public class DarkJMenuItemUI extends BasicMenuItemUI {
    {
        this.selectionForeground = IColorSetting.charsColor2;
        this.selectionBackground = IColorSetting.partitionLinesColor4;
    }
    
    @Override
    public void paintBackground(Graphics var1, JMenuItem var2, Color var3){
        var2.setBorderPainted(false);
        super.paintBackground(var1,var2,var3);
    }
    
    @Override
    public void installUI(JComponent var1){
        super.installUI(var1);
        var1.setBackground(IColorSetting.partitionLinesColor2);
        var1.setForeground(IColorSetting.charsColor1);
    }
}
