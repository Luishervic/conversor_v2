import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

public class conversorPesos extends JFrame{
    private JPanel ventanaPesos;
    private JLabel iconTo;
    private JLabel iconFrom;
    private JLabel iconConverter;
    private JTextField fromMass;
    private JComboBox comboxFrom;
    private JTextField toMass;
    private JComboBox comboxTo;
    private JButton btnCalcular;
    private JButton btnMenu;

    public conversorPesos(boolean visibility) {
        setContentPane(ventanaPesos);
        setTitle("Conversor de masa");
        setSize(600,350);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(visibility);

        Map<String, String> nombresUnidades = new HashMap<>();
        nombresUnidades.put("Kilogramos", "KG");
        nombresUnidades.put("Gramos", "gr");
        nombresUnidades.put("Toneladas", "TON");
        nombresUnidades.put("Libras", "LB");

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenemos la opcion seleccionada por el usuario.
                String from = comboxFrom.getSelectedItem().toString();
                String to = comboxTo.getSelectedItem().toString();

                // Obtener el nombre  correspondiente.
                String fromMassLabel = nombresUnidades.get(from);
                String toMassLabel = nombresUnidades.get(to);

                try {
                    double cantidad = Double.parseDouble(fromMass.getText());
                    // Realizar la conversión
                    // Si la divisa de origen es diferente a Pesos Mexicanos, calcular la tasa de conversión correspondiente.
                    double resultado = conversion(fromMassLabel, cantidad, toMassLabel);

                    // Mostrar el resultado en el campo toCurrency.
                    toMass.setText(String.format("%.4f", resultado));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La cantidad ingresada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        comboxFrom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                toMass.setText(null);
                fromMass.setText(null);
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
                toMass.setText(null);
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

    private double conversion(String fromMass, double cantidad, String toMass) {
        double masa = 0.0;

        switch (fromMass) {
            case "KG":
                if (toMass.equals("KG")) {
                    masa = cantidad;
                } else if (toMass.equals("gr")) {
                    masa = cantidad * 1000;
                } else if (toMass.equals("TON")) {
                    masa = cantidad / 1000;
                } else if (toMass.equals("LB")) {
                    masa = cantidad * 2.20462262185;
                }
                break;
            case "gr":
                if (toMass.equals("KG")) {
                    masa = cantidad / 1000;
                } else if (toMass.equals("gr")) {
                    masa = cantidad;
                } else if (toMass.equals("TON")) {
                    masa = cantidad / 1000000;
                } else if (toMass.equals("LB")) {
                    masa = cantidad / 453.592;
                }
                break;
            case "TON":
                if (toMass.equals("KG")) {
                    masa = cantidad * 1000;
                } else if (toMass.equals("gr")) {
                    masa = cantidad * 1000000;
                } else if (toMass.equals("TON")) {
                    masa = cantidad;
                } else if (toMass.equals("LB")) {
                    masa = cantidad * 2204.6226218488;
                }
                break;
            case "LB":
                if (toMass.equals("KG")) {
                    masa = cantidad / 2.205;
                } else if (toMass.equals("gr")) {
                    masa = cantidad * 453.592;
                } else if (toMass.equals("TON")) {
                    masa = cantidad / 2204.6226218488;
                } else if (toMass.equals("LB")) {
                    masa = cantidad;
                }
                break;
        }
        return masa;
    }
}