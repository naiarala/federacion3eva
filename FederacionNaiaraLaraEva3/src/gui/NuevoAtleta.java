package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import dao.AtletaDAO;
import dao.EquipoDAO;
import dao.PatrocinadorDAO;
import dao.PruebaDAO;
import entidades.Atleta;
import entidades.Documentacion;
import entidades.Equipo;
import entidades.Lugar;
import entidades.Patrocinador;
import utils.ConexBD;
import validaciones.Validaciones;

import javax.swing.JButton;

public class NuevoAtleta extends JFrame {

	private JPanel contentPane;

  //private NuevaPersona contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaAtleta frame = new NuevaAtleta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NuevoAtleta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		// contentPane = NuevaPersona();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setFont(new Font("Wide Latin", Font.BOLD, 11));
		setBorder(
				new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Datos Físicos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						"Datos Físicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);

		JLabel lblAltura = new JLabel("Altura *:");
		lblAltura.setBounds(10, 43, 50, 14);
		contentPane.add(lblAltura);

		JTextField textFieldNombre = new JTextField();
		textFieldNombre.setBounds(79, 95, 86, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblformato = new JLabel("m. (en formato XX.XX)");
		lblformato.setBounds(175, 43, 116, 14);
		contentPane.add(lblformato);
		
		JLabel lblPeso = new JLabel("Peso *:");
		lblPeso.setBounds(10, 98, 50, 14);
		contentPane.add(lblPeso);

		JTextField textFieldNombre1 = new JTextField();
		textFieldNombre1.setBounds(79, 40, 86, 20);
		contentPane.add(textFieldNombre1);
		textFieldNombre1.setColumns(10);

		JLabel lblformato1 = new JLabel("Kg. (en formato XX.XX)");
		lblformato1.setBounds(175, 98, 116, 14);
		contentPane.add(lblformato1);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setBounds(10, 135, 50, 14);
		contentPane.add(lblEquipo);
		
		JComboBox comboBoxEquipo = new JComboBox();
		comboBoxEquipo.setModel(new DefaultComboBoxModel(Equipo.values()));
		comboBoxEquipo.setBounds(79, 131, 169, 22);
		contentPane.add(comboBoxEquipo);
		
		JButton buttonAceptar = new JButton("Aceptar");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Atleta nuevo = new Atleta();
				NuevaPersona nueva= new NuevaPersona();
				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";

				/// Tomar cada campo y validarlo. Si alguno no es correcto, avisar al usuario
				String nombre = textFieldNombre.getText();
				valido = Validaciones.validarNombre(nombre);
				if (!valido) {
					errores += "El nombre del atleta no es válido (3-50 caracteres).\n";
					lblNombre.setForeground(Color.RED);
				} else
					nueva.setName(nombre);
				valido = false;
				String telefono = lblTelefono.getText();
				valido = Validaciones.validarTelefono(telefono);
				if (!valido) {
					errores += "El teléfono del atleta no es válido (9 caracteres).\n";
					lblTelefono.setForeground(Color.RED);
				} else
					nuevo.setPersona(DatosPersona).setTelefono();
				valido = false;
				Documentacion nifnie = buttonGroupTipo.getText();
				valido = Validaciones.validarNIE(nifnie);
				if (!valido) {
					errores += "El nifnie del atleta no es válido (9 caracteres).\n";
					lblNie.setForeground(Color.RED);
				} else
					nueva.setName(nombre);
				valido = false;
				float altura= 0.0F;
				valido = Validaciones.validarAltura(altura);
				if (!valido) {
					errores += "La altura del atleta no es válido (XX.XX metros).\n";
					lblAltura.setForeground(Color.RED);
				} else
					nuevo.setAltura(altura);
				valido = false;

				float peso= 0.0F;
				valido = Validaciones.validarPeso(peso);
				if (!valido) {
					errores += "El peso del atleta no es válido (XX.XX metros).\n";
					lblPeso.setForeground(Color.RED);
				} else
					nuevo.setPeso(peso);
				valido = false;
				valido = (comboBoxEquipo.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el equipo del nuevo atleta.\n";
					lblEquipo.setForeground(Color.RED);
				} else {
					EquipoDAO patDAO = new EquipoDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxEquipo.getSelectedItem();
					String[] aux = seleccionado.split("\\.");
					long idPat = Long.valueOf(aux[0]);
					Equipo equipo = patDAO.buscarPorID(idPat);
					ConexBD.cerrarConexion();
					if (equipo == null)
						valido = false;
					else
						nuevo.setEquipo(equipo);
				}
				valido = errores.isEmpty();

				if (!valido) {
					titulo = "ERROR: Campos inválidos";
					msj = "ERROR: los siguientes campos del Nuevo Atleta NO son válidos:\n\n";
					if (!errores.isEmpty())
						msj += errores + "\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					return;
				}

				/// Si todos los datos son correctos, llamar a PruebaDAO para insertar en la BD
				AtletaDAO atletadao = new AtletaDAO(ConexBD.establecerConexion());
				long idatletanuevo = atletadao.insertarSinID(nuevo);
				/// Tanto si la inserción de la nueva prueba tiene éxito como si no, avisar al
				/// usuario
				if (idatletanuevo <= 0) {
					// hubo error al insertar en BD y no se generó la nueva prueba
					titulo = "ERROR al insertar la Nueva Prueba en la BD";
					msj = "Hubo un error y NO se ha insertado la nueva prueba en la BD.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				} else {
					nuevo.setId(idatletanuevo);
					titulo = "Nueva Prueba insertada en la BD";
					msj = "Se ha insertado correctamente el nuevo atleta:\n" + nuevo.toString();
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);
					///Aqui se redirigiría al usuario hacia la pantalla principal
					///TODO
				}
			}
		});
		buttonAceptar.setBounds(193, 227, 89, 23);
		contentPane.add(buttonAceptar);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonCancelar.setBounds(298, 227, 89, 23);
		contentPane.add(buttonCancelar);
	}

		
	

	private NuevaPersona NuevaPersona() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setBorder(TitledBorder titledBorder) {
		// TODO Auto-generated method stub

	}
}
