package hamburgueria;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class JanelaRelatorio extends JFrame {

    private Controlador janelaMesa;

    private JButton btnConfirma = new JButton("Voltar ao menu anterior");
    private JButton btnRelatorio = new JButton("Gerar Relatorio");
    private JPanel pnRelatorio = new JPanel();

    public JanelaRelatorio(Controlador j) throws HeadlessException {
        super("Relatório");
        setMinimumSize(new Dimension(1200, 600));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.janelaMesa = j;
        JTextArea txtRelatorio = new JTextArea("");

        //pnRelatorio.add();
        add(new JScrollPane(txtRelatorio),BorderLayout.CENTER);

        JPanel pnBotoes = new JPanel(new GridLayout(1, 1));
        pnBotoes.add(btnRelatorio);
        pnBotoes.add(btnConfirma);
        add(pnBotoes, BorderLayout.SOUTH);
        /*ESCREVE RELATORIO*/
        btnRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtRelatorio.setText("PÃO" + "\t" + "BIFE" + "\t" + "MOLHO" + "\t" + "REFRIGERANTE" + "\t" + "DESCRICAO" + "\t" + "VALOR PEDIDO" + "\t" + "HORA ABERTURA" + "\t" + "\t" + "HORA FECHAMENTO" + "\n" + janelaMesa.getTexto());
            }

        });

        btnConfirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                janelaMesa.setVisible(true);
                dispose();

            }

        });

    }

}
