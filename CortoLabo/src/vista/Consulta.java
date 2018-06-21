/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.InscripcionDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Inscripciones;

/**
 *
 * @author Steven Diaz
 */
public class Consulta extends JFrame{
    public JLabel lblNombre, lblApellidos, lblProfesion, lblEdad, lblEstado;

    public JTextField nombre, apellidos, numeroAFP, edad;
    
    public JComboBox profesion;

    
    public JCheckBox estado;
    
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Inscrtibir");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add(lblApellidos);
        container.add(lblProfesion);
        container.add(lblEdad);
        container.add(lblEstado);
        container.add(numeroAFP);
        container.add(nombre);
        container.add(apellidos);
        container.add(profesion);
        container.add(edad);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(table);
        setSize(650, 650);
        eventos();
    }

    public final void agregarLabels() {
        lblNombre = new JLabel("Nombre:");
        lblApellidos = new JLabel("Apellidos:");
        lblProfesion   = new JLabel("Profesion:");
        lblEdad = new JLabel("Edad:");
        lblEstado  = new JLabel("Estado:");
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblApellidos.setBounds(10, 140, ANCHOC, ALTOC);
        lblProfesion.setBounds(250,60 , ANCHOC, ALTOC);
        lblEdad.setBounds(250,140 , ANCHOC, ALTOC);
        lblEstado.setBounds(250,200, ANCHOC, ALTOC);
    }

    public final void formulario() {
        nombre = new JTextField();
        apellidos = new JTextField();
        profesion = new JComboBox();
        edad = new JTextField();
        estado = new JCheckBox();
        
        resultados = new JTable();
        
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");

        table = new JPanel();
        profesion.addItem("Ingeniero");
        profesion.addItem("Astronauta");
        profesion.addItem("Cardiólogo");
        profesion.addItem("Profesor");
        profesion.addItem("Psicologo");
        
        estado = new JCheckBox();
        

        nombre.setBounds(80, 50, ANCHOC, ALTOC);
        apellidos.setBounds(80, 140, 100, ALTOC);
        profesion.setBounds(350, 50, 100, ALTOC);
        edad.setBounds(350, 140, 100, ALTOC);
        estado.setBounds(350, 200, 100, ALTOC);//(x,y,ANCHOC,ALTOC)
        

        buscar.setBounds(450, 300, ANCHOC, ALTOC);
        insertar.setBounds(10, 300, ANCHOC, ALTOC);
        actualizar.setBounds(150, 300, ANCHOC, ALTOC);
        eliminar.setBounds(300, 300, ANCHOC, ALTOC);
        resultados = new JTable();
        
        resultados = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; 
            }
        };
        table.setBounds(10, 350, 600, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    case 5:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Nombre");
        tm.addColumn("Apellidos");
        tm.addColumn("Profesion");
        tm.addColumn("Edad");
        tm.addColumn("Estado");

        InscripcionDao pd = new InscripcionDao();
        ArrayList<Inscripciones> filtros = pd.readAll();

        for (Inscripciones p : filtros) {
            System.out.println(p.getEstado());
            tm.addRow(new Object[]{p.getNombre(), p.getApellidos(), p.getProfesion(),  p.getEdad(),  p.getEstado()});
        }

        resultados.setModel(tm);
    }

   
    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionDao pd = new InscripcionDao();
                Inscripciones p = new Inscripciones(nombre.getText(), apellidos.getText(), Integer.parseInt(numeroAFP.getText()), profesion.getSelectedItem().toString(),Integer.parseInt(edad.getText()), estado.isSelected());

//                if (estado.isSelected()) {
//                    p.setEstado(true);
//                } else {
//                    p.setEstado(false);
//                }
                System.out.println(estado.isSelected());
                
                
                

                if (pd.create(p)) {
                    JOptionPane.showMessageDialog(null, "Persona registrada con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de regsitrar la persona");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionDao pd = new InscripcionDao();
                Inscripciones p = new Inscripciones(nombre.getText(), apellidos.getText(),Integer.parseInt(numeroAFP.getText()), profesion.getSelectedItem().toString(),Integer.parseInt(edad.getText()), true);

                if (estado.isSelected()) {
                    p.setEstado(true);
                }

                if (pd.update(p)) {//cambiar en metodo de Object Key a Generic g.
                    JOptionPane.showMessageDialog(null, "Persona modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar la persona");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionDao pd = new InscripcionDao();
                if (pd.delete(nombre.getText())) {
                    JOptionPane.showMessageDialog(null, "Persona eliminada con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar la persona");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InscripcionDao pd = new InscripcionDao();
                Inscripciones p = pd.read(nombre.getText());
                if (p == null) {
                    JOptionPane.showMessageDialog(null, "La persona buscada no se ha encontrado");
                } else {
                    nombre.setText(String.valueOf(p.getNombre()));
                    nombre.setText(p.getNombre());
                    apellidos.setText(p.getApellidos());
                    profesion.setSelectedItem(p.getProfesion());
                    edad.setText(Integer.parseInt(p.getEdad()));
                    estado.setText(Boolean.parseBoolean(p.getEstado()));

                    if (p.getEstado()) {
                        estado.setSelected(true);
                    } 
                }
            }
        });
        
        resultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    nombre.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
                    apellidos.setText(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
                    profesion.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 3).toString()); 
                    edad.setText(resultados.getValueAt(resultados.getSelectedRow(), 4).toString()); 
                    estado.setText(resultados.getValueAt(resultados.getSelectedRow(), 5).toString()); 
                    if (resultados.getValueAt(resultados.getSelectedRow(), 5).toString() == "false") {
                        estado.setSelected(true);
                    }
                }
            }
        });
    }
    
    public void limpiarCampos() {
        nombre.setText("");
        apellidos.setText("");
        profesion.setSelectedItem("");
        edad.setText("");
        estado.setSelected(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
    
}