package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class NuevaPersona extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaPersona frame = new NuevaPersona();
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
	public NuevaPersona() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setFont(new Font("Wide Latin", Font.BOLD, 11));
		setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Datos Personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre *:");
		lblNombre.setBounds(24, 43, 50, 14);
		contentPane.add(lblNombre);

		JTextField textFieldNombre = new JTextField();
		textFieldNombre.setBounds(79, 40, 86, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Seleccione la opcion e introduzca el valor:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(22, 113, 231, 55);
		getContentPane().add(panel);

		JRadioButton rbNif = new JRadioButton("NIF");
		Container buttonGroupTipo = null;
		buttonGroupTipo.add(rbNif);
		panel.add(rbNif);

		JRadioButton rbNie = new JRadioButton("NIE");
		buttonGroupTipo.add(rbNie);
		panel.add(rbNie);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(24, 203, 46, 14);
		getContentPane().add(lblTelefono);
		
			JLabel lblDocu = new JLabel("Documentación *:");
			lblDocu.setBounds(24, 86, 86, 14);
			getContentPane().add(lblDocu);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(79, 200, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblFechaNac = new JLabel("Fecha Nacimiento *:");
		lblFechaNac.setBounds(185, 203, 97, 14);
		getContentPane().add(lblFechaNac);

		JSpinner spinnerFecha = new JSpinner();
		Date fechaNac =new Date();
		//LocalDate fechaNac = LocalDate.parse(fechaNac);
		//Date fechaNac=Date.valueOf(fechaNac);
//		fechaNac.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")
		spinnerFecha.setModel(new SpinnerDateModel(new Date(1652292907349L), new Date(1652292907349L), null, Calendar.DAY_OF_YEAR));
		spinnerFecha.setBounds(285, 200, 127, 20);
		getContentPane().add(spinnerFecha);
	
	}

	private void setBorder(TitledBorder titledBorder) {
		
	}

	public void setBorder(EmptyBorder emptyBorder) {
		// TODO Auto-generated method stub
		
	}

	


}
