import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame{


    private JPanel menuGUI;
    private JComboBox comboxSeleccion;
    private JButton btnSeleccion;


    public menu() {
        setContentPane(menuGUI);
        setTitle("Menú");
        setSize(300,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnSeleccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = comboxSeleccion.getSelectedItem().toString();
                if ("Divisa".equals(selectedItem)) {
                    main_window divisa = new main_window();

                } else if ("Temperatura".equals(selectedItem)) {
                    conversorTemp temperatura = new conversorTemp();
                } else if ("Masa".equals(selectedItem)) {
                    conversorPesos pesos = new conversorPesos(true);
                }
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        menu ventanamenu = new menu();
    }

}