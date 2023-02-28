package restaurantepkg;

import RestauranteAplication.Main;
import restauranteDAO.ClienteDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRegistro {
    public JPanel panel1;
    private JTextField inserirNome;
    private JTextField inserirCep;
    private JTextField inserirEmail;
    private JButton registrarButton;
    public JButton voltarButton;
    private JTextField inserirTelefone;
    private JTextField insiraSenha;

    private Main main = new Main();

    public JFrame telaRegistroTela = new JFrame("Registro");

public TelaRegistro() {

    ClienteDAO clienteDAO = new ClienteDAO();
    Clientes cliente = new Clientes();

    inserirNome.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cliente.setNomeCliente(inserirNome.getText());

        }
    });
    inserirCep.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            cliente.setCepCliente(inserirCep.getText());

        }
    });
    inserirEmail.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cliente.setEmailCliente(inserirEmail.getText());

        }
    });
    inserirTelefone.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cliente.setTelefoneCliente(inserirTelefone.getText());

        }
    });

    insiraSenha.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cliente.setSenha_cliente(insiraSenha.getText());
            System.out.println(cliente.getSenha_cliente());
        }
    });


    registrarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            telaRegistroTela.dispose();
            if (registroECorreto(cliente) != false) {
                clienteDAO.save(cliente);
                JOptionPane.showMessageDialog(null, "Registro Concluído!", "Registro", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    });

    voltarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });

}
    public void iniciarTelaRegistro() {

        telaRegistroTela.setContentPane(new TelaRegistro().panel1);
        telaRegistroTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaRegistroTela.pack();
        telaRegistroTela.setVisible(true);

    }
    public boolean registroECorreto(Clientes cliente) {
        String textoTela = "Informações não preenchidas!\n";
        boolean verificaRegistro = true;
        if (cliente.getNomeCliente() == null) {
            textoTela += "Nome do Cliente;\n";
            verificaRegistro = false;
        }
        if (cliente.getCepCliente() == null) {
            textoTela += "CEP do Cliente;\n";
            verificaRegistro = false;
        }
        if (cliente.getEmailCliente() == null) {
            textoTela += "Email do Cliente;\n";
            verificaRegistro = false;
        }
        if (cliente.getTelefoneCliente() == null) {
            textoTela += "Telefone do Cliente;\n";
            verificaRegistro = false;
        }
        if (verificaRegistro == false) {
            JOptionPane.showMessageDialog(null,textoTela,"Registro",JOptionPane.ERROR_MESSAGE);
        }
        return verificaRegistro;
    }
}
