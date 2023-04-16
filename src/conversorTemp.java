import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

public class conversorTemp extends JFrame {
    private JPanel ventanaTemperature;
    private JLabel iconTo;
    private JLabel iconFrom;
    private JLabel iconConverter;
    private JTextField fromTemperature;
    private JComboBox comboxFrom;
    private JTextField toTemperature;
    private JComboBox comboxTo;
    private JButton btnCalcular;
    private JButton btnMenu;


    public conversorTemp() {
        setContentPane(ventanaTemperature);
        setTitle("Conversor de temperatura");
        setSize(600, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Map<String, String> nombresUnidades = new HashMap<>();
        nombresUnidades.put("Celsius", "C");
        nombresUnidades.put("Fahrenheit", "F");
        nombresUnidades.put("Kelvin", "K");

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenemos la opcion seleccionada por el usuario.
                String from = comboxFrom.getSelectedItem().toString();
                String to = comboxTo.getSelectedItem().toString();

                // Obtener el nombre  correspondiente.
                String fromTempLabel = nombresUnidades.get(from);
                String toTempLabel = nombresUnidades.get(to);

                try {
                    double cantidad = Double.parseDouble(fromTemperature.getText());
                    // Realizar la conversión
                    // Si la divisa de origen es diferente a Pesos Mexicanos, calcular la tasa de conversión correspondiente.
                    double resultado = conversion(fromTempLabel, cantidad , toTempLabel);

                    // Mostrar el resultado en el campo toCurrency.
                    toTemperature.setText(String.format("%.4f", resultado));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La cantidad ingresada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        comboxFrom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fromTemperature.setText(null);
                toTemperature.setText(null);
                String from = comboxFrom.getSelectedItem().toString();
                String fromCurrencyLabel = nombresUnidades.get(from);
                String path = "/images/" + fromCurrencyLabel + ".png";
                ImageIcon nuevoIcono = new ImageIcon(getClass().getResource(path));
                iconFrom.setIcon(nuevoIcono);
            }
        });
        comboxTo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                toTemperature.setText(null);
                String to = comboxTo.getSelectedItem().toString();
                String toUnidadesLabel = nombresUnidades.get(to);
                String path = "/images/" + toUnidadesLabel + ".png";
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

    private double conversion(String fromTemp, double cantidad, String toTemp) {
        double temperatura = 0.0;

        switch (fromTemp) {
            case "F":
                if (toTemp.equals("F")) {
                    temperatura = cantidad;
                } else if (toTemp.equals("C")) {
                    temperatura = (cantidad - 32) * 5/9;
                } else if (toTemp.equals("K")) {
                    temperatura = (cantidad - 32) * 5/9 + 273.15;
                }
                break;
            case "K":
                if (toTemp.equals("K")) {
                    temperatura = cantidad;
                } else if (toTemp.equals("C")) {
                    temperatura = cantidad - 273.15;
                } else if (toTemp.equals("F")) {
                    temperatura = (cantidad - 273.15) * 9/5 + 32;
                }
                break;
            case "C":
                if (toTemp.equals("K")) {
                    temperatura = cantidad + 273.15;
                } else if (toTemp.equals("C")) {
                    temperatura = cantidad;
                } else if (toTemp.equals("F")) {
                    temperatura = (cantidad * 9/5) + 32;
                }
                break;
        }
        return temperatura;
    }
}