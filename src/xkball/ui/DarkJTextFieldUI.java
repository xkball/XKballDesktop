package xkball.ui;

import xkball.interfaces.IColorSetting;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;

public class DarkJTextFieldUI extends BasicTextFieldUI {
    
    @Override
    public void installUI(JComponent var1){
        super.installUI(var1);
        var1.setOpaque(false);
        var1.setForeground(IColorSetting.charsColor1);
        JTextField var2 = (JTextField) var1;
        var2.setCaretColor(IColorSetting.charsColor2);
        var2.setBorder(null);
        var2.setFont(new Font("",Font.PLAIN,15));
    }

}
