package hamburgueria;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Hamburgueria {

    public static void main(String[] args) {
        Controlador janela = new Controlador(getSamplaDate());
        janela.setSize(700, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    private static List<Mesa> getSamplaDate() {

        Mesa m1 = new Mesa(0);

        List<Mesa> lstMesas = new ArrayList<>();
        lstMesas.add(m1);
        return lstMesas;
    }
}
