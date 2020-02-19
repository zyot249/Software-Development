package com.group4.itss.ui.component;


import com.group4.itss.ui.screen.BoostStrapUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.net.URL;

@Component
public class PopUp {
    @Autowired
    private static JFrame frame;

    public enum PopupTypes { ERROR , SUCCESS, CONFIRM};

    public static int render(PopupTypes type, String message, String title) {
        switch (type) {
            case ERROR:
                return renderErrorPopup(message, title);
            case SUCCESS:
                return renderSuccessPopup(message, title);
            case CONFIRM:
                return renderConfirmPopup(message, title);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private static int renderErrorPopup(String message, String title) {
        JOptionPane.showMessageDialog(frame,
                message,
                title,
                JOptionPane.ERROR_MESSAGE,
                createIcon(PopupTypes.ERROR));
        return 0;
    }

    private static int renderSuccessPopup(String message, String title) {
        JOptionPane.showMessageDialog(frame,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE,
                createIcon(PopupTypes.SUCCESS));
        return 0;
    }

    private static int renderConfirmPopup(String message, String title) {
        Object[] options = {"CANCEL", "NO", "YES"};
        return JOptionPane.showOptionDialog(frame,
                message,
                title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                createIcon(PopupTypes.CONFIRM),
                options, options[0]);
    }

    private static ImageIcon createIcon(PopupTypes type) {
        return  new ImageIcon(getResource(type));
    }

    private static URL getResource(PopupTypes type) {
        switch (type) {
            case SUCCESS:
                return BoostStrapUI.class.getResource("/image/check.png");
            case ERROR:
                return BoostStrapUI.class.getResource("/image/highlight.png");
            case CONFIRM:
                return BoostStrapUI.class.getResource("/image/help.png");
            default:
                return null;
        }
    }
}
