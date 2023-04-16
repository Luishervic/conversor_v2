import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;


public class main_window extends JFrame {
    private JPanel ventanaMonedas;
    private JLabel iconFrom;
    private JLabel iconTo;
    private JLabel iconConverter;
    private JTextField fromCurrency;
    private JTextField toCurrency;
    private JComboBox comboxFrom;
    private JComboBox comboxTo;
    private JButton btnCalcular;
    private JButton btnMenu;

    public main_window(){

        setContentPane(ventanaMonedas);
        setTitle("Conversor de divisas");
        setSize(600,350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Map<String, String> nombresDivisas = new HashMap<>();
        nombresDivisas.put("Pesos Mexicanos", "MXN");
        nombresDivisas.put("Dólares (USA)", "USD");
        nombresDivisas.put("Euros", "EUR");
        nombresDivisas.put("Libras Esterlinas", "GBP");
        nombresDivisas.put("Yenes", "JPY");
        nombresDivisas.put("Wones", "KRW");


        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenemos la opcion seleccionada por el usuario.
                String from = comboxFrom.getSelectedItem().toString();
                String to = comboxTo.getSelectedItem().toString();
                double cantidad = Double.parseDouble(fromCurrency.getText());

                // Obtener el nombre ISO correspondiente.
                String fromCurrencyLabel = nombresDivisas.get(from);
                String toCurrencyLabel = nombresDivisas.get(to);

                try {
                    // Si la divisa de origen es diferente a Pesos Mexicanos, calcular la tasa de conversión correspondiente.
                    double tasaConversion = obtenerTasaDeConversion(toCurrencyLabel);

                    if (!fromCurrencyLabel.equals("MXN")) {
                        // Obtener la tasa de conversión de la divisa de origen.
                        double mxnTasa = obtenerTasaDeConversion(fromCurrencyLabel);

                        // Obtener la tasa de conversión inversa con respecto a Pesos Mexicanos.
                        double pesosConversion = cantidad * (1/mxnTasa);

                        // Multiplicar la tasa de conversión inversa por la tasa de conversión de Pesos Mexicanos.
                        cantidad = pesosConversion;
                    }

                    // Calcular la conversión.
                    double result = cantidad * (tasaConversion);

                    // Mostrar el resultado en el campo toCurrency.
                    toCurrency.setText(String.format("%.4f", result));
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "La cantidad ingresada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }


        });

        comboxFrom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                toCurrency.setText(null);
                fromCurrency.setText(null);
                String from = comboxFrom.getSelectedItem().toString();
                String fromCurrencyLabel = nombresDivisas.get(from);
                String path = "/images/" + fromCurrencyLabel + ".png";
                ImageIcon nuevoIcono = new ImageIcon(getClass().getResource(path));
                iconFrom.setIcon(nuevoIcono);
            }
        });
        comboxTo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                toCurrency.setText(null);
                String to = comboxTo.getSelectedItem().toString();
                String toCurrencyLabel = nombresDivisas.get(to);
                String path = "/images/" + toCurrencyLabel + ".png";
                ImageIcon nuevoIcono = new ImageIcon(getClass().getResource(path));
                iconTo.setIcon(nuevoIcono);
            }
        });
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu ventanamenu = new menu();
                dispose();
            }
        });
    }

    // Obtener la tasa de conversión correspondiente a la  divisa
    private double obtenerTasaDeConversion(String divisa) {
        double tasa = 0.0;

        switch (divisa) {
            case "USD":
                tasa = 0.055094;
                break;
            case "EUR":
                tasa = 0.050488;
                break;
            case "MXN":
                tasa = 1;
                break;
            case "JPY":
                tasa = 7.277153;
                break;
            case "GBP":
                tasa = 0.044324;
                break;
            case "KRW":
                tasa = 72.529148;
                break;
        }
        return tasa;
    }

}