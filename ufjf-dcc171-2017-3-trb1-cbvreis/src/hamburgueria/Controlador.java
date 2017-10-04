package hamburgueria;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.time.LocalTime;

class Controlador extends JFrame {

    static int numMesas = 1;
    private int numPedidos = 1;
    private final List<Mesa> mesas;
    private final JLabel lblPao = new JLabel("Pão");
    private final JLabel lblBife = new JLabel("Bife");
    private final JLabel lblRefrigerante = new JLabel("Refrigerante");
    private final JLabel lblMolho = new JLabel("Molho");
    private final JLabel lblDescricao = new JLabel("Descrição");
    private final JLabel lblValorTotal = new JLabel("Valor Total Pedido: ");
    private LocalTime horaPedidoFechado = LocalTime.now();
    private LocalTime horaPedidoAberto = LocalTime.now();
    private JTextField txtPao = new JTextField("");
    private JTextField txtBife = new JTextField();
    private JTextField txtRefrigerante = new JTextField();
    private JTextField txtMolho = new JTextField();
    private JTextField txtDescricao = new JTextField();
    private JTextField txtValorTotal = new JTextField();

    private final JList<Mesa> lstMesas = new JList<>(new DefaultListModel<>());
    private final JList<Pedido> lstPedidos = new JList<>(new DefaultListModel<>());
    private final JButton btnCriaMesa = new JButton("Cria mesa");
    private final JButton btnExcluiMesa = new JButton("Exclui Mesa");
    private final JButton btnCriaPedido = new JButton("Cria Pedido");
    private final JButton btnFechaPedido = new JButton("Fechar Pedido");
    private final JButton btnGerarRelatorio = new JButton("Emitir Relatórios");
    private final JButton btnCalcular = new JButton("Calcular valor do pedido: ");
    private JRadioButton jPaoCiabatta = new JRadioButton("Pao Ciabatta", false);
    private JRadioButton jPaoPreto = new JRadioButton("Pao Preto", false);
    private JRadioButton jPaoCaseiro = new JRadioButton("Pao Caseiro", false);
    private ButtonGroup handlerPao = new ButtonGroup();

    private JRadioButton jBifeFrango = new JRadioButton("Bife de Frango", false);
    private JRadioButton jBifeBoi = new JRadioButton("Bife de Boi", false);
    private JRadioButton jBifePorco = new JRadioButton("Bife de Porco", false);
    private ButtonGroup handlerBife = new ButtonGroup();

    private JRadioButton jCoca = new JRadioButton("Coca-Cola", false);
    private JRadioButton jFanta = new JRadioButton("Fanta", false);
    private JRadioButton jGuarana = new JRadioButton("Guaraná", false);
    private ButtonGroup handlerBebida = new ButtonGroup();

    private JRadioButton jMaionese = new JRadioButton("Maionese", false);
    private JRadioButton jCatshup = new JRadioButton("Catshup", false);
    private JRadioButton jBarbecue = new JRadioButton("Barbecue", false);
    private ButtonGroup handlerMolho = new ButtonGroup();

    private final JPanel resumoPedido = new JPanel(new GridLayout(5, 4));
    private StringBuilder resultado = new StringBuilder();
    private String stgRelatorio;
    private String stgHoraAbertura;
    private String stgHoraFechamento;

    private JanelaRelatorio janelaRel;
    private ArrayList<String> relatorio = new ArrayList<>();

    private double valorPedido = 0;
    private double valorTotalDia = 0;

    public Controlador(List<Mesa> sampleData) {
        super("Mesas");
        setMinimumSize(new Dimension(1200, 300));
        this.mesas = sampleData;
        lstMesas.setModel(new MesaListModel(mesas));
        janelaRel = new JanelaRelatorio(this);

        /*PEDIDO DE PAO*/
        resumoPedido.add(lblPao);
        resumoPedido.add(jPaoCiabatta);
        resumoPedido.add(jPaoCaseiro);
        resumoPedido.add(jPaoPreto);
        handlerPao.add(jPaoCiabatta);
        handlerPao.add(jPaoCaseiro);
        handlerPao.add(jPaoPreto);

        /*PEDIDO DE BIFES*/
        resumoPedido.add(lblBife);
        resumoPedido.add(jBifeFrango);
        resumoPedido.add(jBifeBoi);
        resumoPedido.add(jBifePorco);
        handlerBife.add(jBifeFrango);
        handlerBife.add(jBifeBoi);
        handlerBife.add(jBifePorco);

        /*PEDIDO DE MOLHOS*/
        resumoPedido.add(lblMolho);
        resumoPedido.add(jMaionese);
        resumoPedido.add(jCatshup);
        resumoPedido.add(jBarbecue);
        handlerMolho.add(jMaionese);
        handlerMolho.add(jCatshup);
        handlerMolho.add(jBarbecue);

        /*PEDIDO DE REFRIGERANTE*/
        resumoPedido.add(lblRefrigerante);
        resumoPedido.add(jCoca);
        resumoPedido.add(jFanta);
        resumoPedido.add(jGuarana);
        handlerBebida.add(jCoca);
        handlerBebida.add(jFanta);
        handlerBebida.add(jGuarana);

        resumoPedido.add(lblDescricao);
        resumoPedido.add(txtDescricao);
        resumoPedido.add(lblValorTotal);
        resumoPedido.add(txtValorTotal);
        txtValorTotal.disable();

        /*ADICIONANDO OS LAYOUTS*/
        add(new JScrollPane(lstMesas), BorderLayout.WEST);
        add(new JScrollPane(lstPedidos), BorderLayout.CENTER);
        add(new JScrollPane(resumoPedido), BorderLayout.EAST);

        /*ADICIONANDO BOTOES*/
        JPanel botoes = new JPanel(new GridLayout(3, 3));
        botoes.add(btnCalcular);
        botoes.add(btnGerarRelatorio);
        botoes.add(btnCriaMesa);
        botoes.add(btnExcluiMesa);
        botoes.add(btnCriaPedido);
        botoes.add(btnFechaPedido);
        add(botoes, BorderLayout.SOUTH);

        /*FUNCOES DO RADIOBUTTON*/
        jPaoCiabatta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Component cp : botoes.getComponents()) {
                    cp.setEnabled(false);

                }
                btnCalcular.setEnabled(true);
                valorPedido = 0;
                txtDescricao.setText("");
                txtValorTotal.setText("");

            }
        });
        jPaoPreto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Component cp : botoes.getComponents()) {
                    cp.setEnabled(false);
                }
                btnCalcular.setEnabled(true);
                valorPedido = 0;
                txtDescricao.setText("");
                txtValorTotal.setText("");
            }
        });
        jPaoCaseiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Component cp : botoes.getComponents()) {
                    cp.setEnabled(false);
                }
                btnCalcular.setEnabled(true);
                valorPedido = 0;
                txtDescricao.setText("");
                txtValorTotal.setText("");
            }
        });

        /*FUNCOES DOS BOTOES*/
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jPaoCiabatta.isSelected()) {
                    getTxtPao().setText("Ciabatta");
                    valorPedido = valorPedido + 3.5;

                    btnCalcular.setEnabled(true);
                } else if (jPaoCaseiro.isSelected()) {
                    getTxtPao().setText("Pão Caseiro");
                    valorPedido = valorPedido + 1.5;
                    for (Component cp : botoes.getComponents()) {
                        cp.setEnabled(false);
                    }
                    btnCalcular.setEnabled(true);
                } else {
                    getTxtPao().setText("Preto");
                    valorPedido = valorPedido + 2.5;
                    for (Component cp : botoes.getComponents()) {
                        cp.setEnabled(false);
                    }
                    btnCalcular.setEnabled(true);
                }
                if (jBifeFrango.isSelected()) {
                    txtBife.setText("Frango");
                    valorPedido = valorPedido + 3.5;
                } else if (jBifeBoi.isSelected()) {
                    txtBife.setText("Boi");
                    valorPedido = valorPedido + 1.5;
                } else {
                    txtBife.setText("Bife de Porco");
                    valorPedido = valorPedido + 4.5;
                }
                if (jBarbecue.isSelected()) {
                    txtMolho.setText("Barbecue");
                    valorPedido = valorPedido + 1.65;
                } else if (jCatshup.isSelected()) {
                    txtMolho.setText("Catshup");
                    valorPedido = valorPedido + 1.35;
                } else {
                    txtMolho.setText("Maionese");
                    valorPedido = valorPedido + 2.75;
                }
                if (jCoca.isSelected()) {
                    txtRefrigerante.setText("Coca-Cola");
                    valorPedido = valorPedido + 7.65;
                } else if (jFanta.isSelected()) {
                    txtRefrigerante.setText("fanta");
                    valorPedido = valorPedido + 5.35;
                } else {
                    txtRefrigerante.setText("Guaraná");
                    valorPedido = valorPedido + 6.75;
                }

                txtValorTotal.setText(String.valueOf(valorPedido));
                System.out.println(getTxtPao().getText());
                handlerPao.clearSelection();
                handlerBebida.clearSelection();
                handlerBife.clearSelection();
                handlerMolho.clearSelection();
                setValorTotalDia(getValorTotalDia() + valorPedido);
                btnCriaMesa.setEnabled(false);
                btnExcluiMesa.setEnabled(false);
                btnCriaPedido.setEnabled(false);
                btnFechaPedido.setEnabled(true);
                btnGerarRelatorio.setEnabled(false);
                btnCalcular.setEnabled(false);
            }

        });

        /*LIST MODEL E DESABILITANDO BOTEÕS*/
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lstPedidos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Pedido p = lstPedidos.getSelectedValue();
                if (p != null) {
                    for (Component cp : botoes.getComponents()) {
                        cp.setEnabled(true);
                    }
                    for (Component cp : resumoPedido.getComponents()) {
                        cp.setEnabled(true);
                    }
                } else {

                }

            }
        });

        lstMesas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if (selecionada != null) {
                    for (Component cp : botoes.getComponents()) {
                        cp.setEnabled(false);
                    }
                    for (Component cp : resumoPedido.getComponents()) {
                        cp.setEnabled(false);
                    }

                    btnCriaMesa.setEnabled(true);
                    btnCriaPedido.setEnabled(true);
                    System.out.println(selecionada);
                    lstPedidos.setModel(new PedidoListModel(selecionada.getPedidos()));
                } else {
                    lstPedidos.setModel(new DefaultListModel<>());
                }

            }
        });

        btnCriaMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDescricao.setText("");
                txtValorTotal.setText("");

                Mesa t = new Mesa(numMesas);
                mesas.add(t);
                lstMesas.updateUI();
                numMesas++;

            }
        });

        btnExcluiMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lstMesas.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione uma mesa", "ERRO!", JOptionPane.ERROR_MESSAGE);
                }

                for (Pedido p : lstMesas.getSelectedValue().getPedidos()) {
                    if (p.isFlagFechamento() == true) {
                        JOptionPane.showMessageDialog(null, "Feche os pedidos abertos", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }
                mesas.remove(lstMesas.getSelectedValue());
                lstMesas.clearSelection();
                lstMesas.updateUI();
            }
        });

        btnCriaPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstMesas.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Você deveria ter selecionado uma mesa", "ERRO!", JOptionPane.ERROR_MESSAGE);
                }
                txtDescricao.setText("");
                txtValorTotal.setText("");

                Pedido p = new Pedido(numPedidos);
                lstMesas.getSelectedValue().getPedidos().add(p);
                lstPedidos.updateUI();
                lstMesas.updateUI();
                numPedidos++;
                setStgHoraAbertura((horaPedidoAberto.toString()));

            }
        });

        btnFechaPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstPedidos.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Você deveria ter selecionado um Pedido", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (lstPedidos.getSelectedValue().isFlagFechamento()) {
                    //StringBuilder resultado = new StringBuilder();
                    stgHoraFechamento = horaPedidoFechado.toString();
                    String stgTxtPao = "";
                    stgTxtPao = getTxtPao().getText();
                    String stgtxtHora = "";
                    stgtxtHora = getStgHoraAbertura();

                    resultado.append(stgTxtPao + "\t");
                    resultado.append(txtBife.getText() + "\t");
                    resultado.append(txtMolho.getText() + "\t");
                    resultado.append(txtRefrigerante.getText() + "\t" + "\t");
                    resultado.append(txtDescricao.getText() + "\t");
                    resultado.append(txtValorTotal.getText() + "\t" + "\t");
                    resultado.append(stgtxtHora + "\t" + "\t" + "\t");
                    resultado.append(stgHoraFechamento + "\n");
                    relatorio.add(resultado.toString());
                    stgRelatorio = getStringRelatorio();
                    lstPedidos.getSelectedValue().setFlagFechamento(false);
                    valorPedido = 0;
                    btnCriaMesa.setEnabled(true);
                    btnExcluiMesa.setEnabled(true);
                    btnCriaPedido.setEnabled(true);
                    btnGerarRelatorio.setEnabled(true);
                    btnCalcular.setEnabled(true);
                    lstPedidos.updateUI();

                } else {
                    JOptionPane.showMessageDialog(null, "Esse pedido está fechado!", "ERRO!", JOptionPane.ERROR_MESSAGE);;
                    for (Component cp : botoes.getComponents()) {
                        cp.setEnabled(true);
                    }
                }
            }
        });

        btnGerarRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lstPedidos.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Você deveria ter selecionado um Pedido", "ERRO!", JOptionPane.ERROR_MESSAGE);;
                    return;
                } else if (lstPedidos.getSelectedValue().isFlagFechamento() == false) {

                }

                janelaRel.setVisible(true);

            }
        });

    }

    public String getTexto() {
        return stgRelatorio;
    }

    public String getStringRelatorio() {
        String str_relatorio = "";
        for (String s : relatorio) {
            str_relatorio += s + "\n";
        }
        return str_relatorio;
    }

    public JTextField getTxtPao() {
        return txtPao;
    }

    public double getValorTotalDia() {
        return valorTotalDia;
    }

    public void setValorTotalDia(double valorTotalDia) {
        this.valorTotalDia = valorTotalDia;
    }

    public String getStgHoraAbertura() {
        return stgHoraAbertura;
    }

    public void setStgHoraAbertura(String stgHoraAbertura) {
        this.stgHoraAbertura = stgHoraAbertura;
    }

    public String getStgHoraFechamento() {
        return stgHoraFechamento;
    }

    public void setStgHoraFechamento(String stgHoraFechamento) {
        this.stgHoraFechamento = stgHoraFechamento;
    }

}
