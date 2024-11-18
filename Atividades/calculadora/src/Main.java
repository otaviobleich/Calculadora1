import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculadora extends JFrame implements ActionListener {
    private JTextField tela;
    private String operador;
    private double num1, num2, resultado;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tela = new JTextField();
        tela.setEditable(false);
        tela.setHorizontalAlignment(JTextField.RIGHT);
        add(tela, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 4));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.addActionListener(this);
            painelBotoes.add(botao);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9') {
            tela.setText(tela.getText() + comando);
        } else if (comando.equals("C")) {
            tela.setText("");
            operador = "";
            num1 = num2 = resultado = 0;
        } else if (comando.equals("=")) {
            num2 = Double.parseDouble(tela.getText());
            switch (operador) {
                case "+": resultado = num1 + num2; break;
                case "-": resultado = num1 - num2; break;
                case "*": resultado = num1 * num2; break;
                case "/":
                    if (num2 != 0) resultado = num1 / num2;
                    else tela.setText("Erro: Div/0");
                    return;
            }
            tela.setText(String.valueOf(resultado));
        } else {
            operador = comando;
            num1 = Double.parseDouble(tela.getText());
            tela.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}
