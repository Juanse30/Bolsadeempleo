/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnología de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de marzo de 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package universidadean.empleo.interfaz;

import universidadean.empleo.mundo.Aspirante;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Este es el panel donde se agregan aspirantes
 */
public class PanelAgregarAspirante extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String AGREGAR = "AgregarAspirante";

    private static final String BUSCAR_IMAGEN = "BuscarImagen";

    private static final String[] PROFESION = new String[]{Aspirante.ADMINISTRADOR, Aspirante.CONTADOR, Aspirante.ECONOMISTA, Aspirante.INGENIERO_INDUSTRIAL};

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazBolsaDeEmpleo principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * El combo para seleccionar la profesión del aspirante
     */
    private JComboBox comboProfesiones;

    /**
     * Es el campo para los años de experiencia del aspirante
     */
    private JTextField txtAniosExperiencia;

    /**
     * Es el campo para la imagen del aspirante
     */
    private JTextField txtImagen;

    /**
     * Es el campo para el teléfono del aspirante
     */
    private JTextField txtTelefono;

    /**
     * Es el campo para la edad del aspirante
     */
    private JTextField txtEdad;

    /**
     * Es el campo para el nombre del aspirante
     */
    private JTextField txtNombre;

    /**
     * Esta es la etiqueta para la profesión del aspirante
     */
    private JLabel etiquetaProfesion;

    /**
     * Esta es la etiqueta para los años de experiencia del aspirante
     */
    private JLabel etiquetaAniosExperiencia;

    /**
     * Esta es la etiqueta para la imagen del aspirante
     */
    private JLabel etiquetaImagen;

    /**
     * Esta es la etiqueta para el teléfono del aspirante
     */
    private JLabel etiquetaTelefono;

    /**
     * Esta es la etiqueta para la edad del aspirante
     */
    private JLabel etiquetaEdad;

    /**
     * Esta es la etiqueta para el nombre del aspirante
     */
    private JLabel etiquetaNombre;

    /**
     * Es el botón que se usa para agregar un aspirante
     */
    private JButton botonAgregar;

    /**
     * Es el botón que se usa para examinar el disco buscando la imagen del aspirante
     */
    private JButton botonExaminar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param interfaz Es una referencia a la clase principal de la interfaz - interfaz!=null
     */
    public PanelAgregarAspirante(InterfazBolsaDeEmpleo interfaz) {
        principal = interfaz;

        setLayout(new GridBagLayout());
        setBorder(new TitledBorder("Agregar Aspirante"));

        etiquetaNombre = new JLabel("Nombre: ");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(etiquetaNombre, gbc);

        txtNombre = new JTextField("");
        gbc.gridx = 1;
        add(txtNombre, gbc);

        etiquetaEdad = new JLabel("Edad: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(etiquetaEdad, gbc);

        txtEdad = new JTextField("");
        gbc.gridx = 1;
        add(txtEdad, gbc);

        etiquetaProfesion = new JLabel("Profesión: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(etiquetaProfesion, gbc);

        comboProfesiones = new JComboBox(PROFESION);
        gbc.gridx = 1;
        add(comboProfesiones, gbc);

        etiquetaAniosExperiencia = new JLabel("Años experiencia: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(etiquetaAniosExperiencia, gbc);

        txtAniosExperiencia = new JTextField("");
        gbc.gridx = 1;
        add(txtAniosExperiencia, gbc);

        etiquetaImagen = new JLabel("Imagen: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(etiquetaImagen, gbc);

        txtImagen = new JTextField("");
        botonExaminar = new JButton("Examinar");
        botonExaminar.setActionCommand(BUSCAR_IMAGEN);
        botonExaminar.addActionListener(this);
        JPanel panelImagen = new JPanel(new GridLayout(1, 3));
        panelImagen.add(txtImagen);
        panelImagen.add(botonExaminar);
        gbc.gridx = 1;
        add(panelImagen, gbc);

        etiquetaTelefono = new JLabel("Teléfono: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(etiquetaTelefono, gbc);

        txtTelefono = new JTextField("");
        gbc.gridx = 1;
        add(txtTelefono, gbc);

        JPanel panelBoton = new JPanel();
        botonAgregar = new JButton("Agregar hoja de vida");
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        panelBoton.add(botonAgregar);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(panelBoton, gbc);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este es el método que se ejecuta cuando se hace click sobre un botón
     * @param evento Es el evento del click sobre el botón - evento!=null
     */
    public void actionPerformed(ActionEvent evento) {
        String comando = evento.getActionCommand();

        if (AGREGAR.equals(comando)) {
            try {
                String profesion = (String) comboProfesiones.getSelectedItem();
                int aniosExperiencia = Integer.parseInt(txtAniosExperiencia.getText());
                String imagen = txtImagen.getText();
                String telefono = txtTelefono.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                String nombre = txtNombre.getText();

                if (nombre.equals("") || profesion.equals("") || imagen.equals("") || telefono.equals("") || nombre.equals(" ")) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar valores en todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (nombre.startsWith(" ")) {
                        JOptionPane.showMessageDialog(this, "El campo nombre no puede empezar con espacio en blanco ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (aniosExperiencia < 1 || edad < 1) {
                        JOptionPane.showMessageDialog(this, "Los campos edad y años de experiencia deben ser números enteros positivos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        principal.agregarAspirante(nombre, profesion, aniosExperiencia, edad, telefono, imagen);

                        txtAniosExperiencia.setText("");
                        txtImagen.setText("");
                        txtTelefono.setText("");
                        txtEdad.setText("");
                        txtNombre.setText("");
                    }
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Los campos edad y años de experiencia deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (BUSCAR_IMAGEN.equals(comando)) {
            JFileChooser fc = new JFileChooser("./data");
            fc.setDialogTitle("Buscar imagen del aspirante");
            fc.setMultiSelectionEnabled(false);

            int resultado = fc.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String imagen = fc.getSelectedFile().getAbsolutePath();
                txtImagen.setText(imagen);
            }
        }

    }
}
