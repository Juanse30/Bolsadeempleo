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

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Es el panel donde se muestra la lista de aspirantes y están los botones para interactuar con la lista
 */
public class PanelLista extends JPanel implements ListSelectionListener {
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
     * Es la lista que se muestra
     */
    private JList listaAspirantes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa todos sus componentes
     *
     * @param interfaz Es una referencia a la clase principal de la interfaz - interfaz != null
     */
    public PanelLista(InterfazBolsaDeEmpleo interfaz) {
        principal = interfaz;

        setLayout(new BorderLayout());
        setBorder(new TitledBorder("Aspirantes Registrados en la Bolsa"));

        listaAspirantes = new JList();
        listaAspirantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAspirantes.addListSelectionListener(this);

        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new LineBorder(Color.BLACK, 1)));
        scroll.getViewport().add(listaAspirantes);

        add(scroll, BorderLayout.CENTER);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de aspirantes que se está mostrando
     *
     * @param listaActualizada Es una lista con los aspirantes que deben mostrarse
     */
    public void actualizarLista(ArrayList<Aspirante> listaActualizada) {
        listaAspirantes.setListData(listaActualizada.toArray());
        listaAspirantes.setSelectedIndex(0);
    }

    /**
     * Selecciona un aspirante de la lista
     *
     * @param seleccionado La posición del aspirante que se debe seleccionar
     */
    public void seleccionar(int seleccionado) {
        listaAspirantes.setSelectedIndex(seleccionado);
        listaAspirantes.ensureIndexIsVisible(seleccionado);
    }

    /**
     * Cambia la información del aspirante que se esta mostrando de acuerdo al nuevo aspirante seleccionado
     *
     * @param evento El evento de cambio del ítem seleccionado en la lista. evento!=null
     */
    public void valueChanged(ListSelectionEvent evento) {
        if (listaAspirantes.getSelectedValue() != null) {
            Aspirante aspiranteSeleccionado = (Aspirante) listaAspirantes.getSelectedValue();
            principal.verDatos(aspiranteSeleccionado);
        }
    }

    /**
     * Informa si hay algún índice seleccionado en la lista de aspirantes.
     *
     * @return true si hay un ítem seleccionado, false de lo contrario
     */
    public boolean haySeleccionado() {
        return !listaAspirantes.isSelectionEmpty();
    }

    /**
     * Retorna el nombre del aspirante seleccionado en la lista
     *
     * @return Se retornó el nombre del aspirante seleccionado en la lista o null si no hay selección
     */
    public String darNombreSeleccionado() {
        String nombre = null;

        if (listaAspirantes.getSelectedValue() != null) {
            Aspirante aspiranteSeleccionado = (Aspirante) listaAspirantes.getSelectedValue();
            nombre = aspiranteSeleccionado.darNombre();
        }

        return nombre;
    }

}