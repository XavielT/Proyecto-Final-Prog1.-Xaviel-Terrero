package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.LoginFrame;

public class Main {
    public static void main(String[] args) {
        //El Look and Feel a Nimbus.
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la pila de errores si hay.
        }
        // Crear y mostrar la ventana de inicio de sesión.
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}



