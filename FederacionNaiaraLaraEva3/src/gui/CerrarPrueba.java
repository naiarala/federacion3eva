package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import dao.AtletaDAO;
import dao.BronceDAO;
import dao.OroDAO;
import dao.PatrocinadorDAO;
import dao.PlataDAO;
import dao.PruebaDAO;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import entidades.Atleta;
import entidades.Bronce;
import entidades.Lugar;
import entidades.Metal;
import entidades.Oro;
import entidades.Participante;
import entidades.Patrocinador;
import entidades.Plata;
import entidades.Prueba;
import utils.ConexBD;
import validaciones.Validaciones;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JCheckBox;

public class CerrarPrueba extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Prueba prueba;
	private Prueba nueva = prueba;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private final ButtonGroup buttonGroupTipo = new ButtonGroup();
	private JTextField textField;
	protected Participante[] participante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int idprueba = 1;
					CerrarPrueba frame = new CerrarPrueba(idprueba);
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
	public CerrarPrueba(int idprueba) {
		PruebaDAO pDAO = new PruebaDAO(ConexBD.getCon());
		Prueba prueba = pDAO.buscarPorID(idprueba);
		if (prueba != null) {
			setTitle("Cerrar Prueba" + idprueba);
		} else
			setTitle("Cerrar Prueba INDETERMINADA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Datos de la prueba", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 424, 199);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblIdPrueba = new JLabel("IdPrueba:");
		lblIdPrueba.setBounds(22, 21, 77, 14);
		panel_1.add(lblIdPrueba);

		textField = new JTextField("" + prueba.getId());
		textField.setBounds(86, 14, 86, 20);
		panel_1.add(textField);
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(22, 48, 77, 14);
		panel_1.add(lblNombre);

		textFieldNombre = new JTextField(prueba.getNombre());
		textFieldNombre.setBounds(86, 41, 261, 20);
		panel_1.add(textFieldNombre);
		textFieldNombre.setEnabled(false);
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(22, 73, 46, 14);
		panel_1.add(lblFecha);

		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(200, 73, 46, 14);
		panel_1.add(lblLugar);

		JComboBox comboBoxLugar = new JComboBox();
		comboBoxLugar.setBounds(245, 69, 169, 22);
		panel_1.add(comboBoxLugar);
		comboBoxLugar.setEnabled(false);
		comboBoxLugar.setModel(new DefaultComboBoxModel(Lugar.values()));

		JPanel panel = new JPanel();
		panel.setBounds(22, 98, 210, 52);
		panel_1.add(panel);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Tipo de Prueba:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JRadioButton rbIndividual = new JRadioButton("Individual");

		rbIndividual.setEnabled(false);
		buttonGroupTipo.add(rbIndividual);
		panel.add(rbIndividual);

		JRadioButton rbEquipos = new JRadioButton("Por Equipos");
		rbEquipos.setEnabled(false);
		buttonGroupTipo.add(rbEquipos);
		panel.add(rbEquipos);
		if (prueba.isIndividual())
			rbIndividual.setSelected(true);
		else
			rbEquipos.setSelected(true);

		JLabel lblPatrocinador = new JLabel("Patrocinador:");
		lblPatrocinador.setBounds(22, 161, 87, 14);
		panel_1.add(lblPatrocinador);

		JLabel lblprimerpuesto = new JLabel("Primer puesto *:");
		lblprimerpuesto.setBounds(20, 211, 109, 14);
		contentPane.add(lblprimerpuesto);

		JPanel panelinsercionTiempo = new PanelInsercionTiempo();
		panelinsercionTiempo.setBackground(Color.WHITE);
		panelinsercionTiempo.setBounds(10, 233, 417, 52);
		contentPane.add(panelinsercionTiempo);

		JLabel lblidoro = new JLabel("Id oro *:");
		lblidoro.setBounds(10, 296, 69, 14);
		contentPane.add(lblidoro);

		DefaultComboBoxModel<Oro> orosModel = new DefaultComboBoxModel<Oro>();
		OroDAO oDAO = new OroDAO(ConexBD.getCon());
		ArrayList<Oro> orosList = (ArrayList<Oro>) oDAO.buscarTodos();
		JComboBox<Oro> comboBoxOro = new JComboBox<Oro>();
		String[] orosStr = new String[orosList.size()];
		for (int i = 0; i < orosList.size(); i++)
			orosStr[i] = orosList.get(i).toString();

		comboBoxOro.setModel(new DefaultComboBoxModel(orosStr));
		lblidoro.setLabelFor(comboBoxOro);
		comboBoxOro.setBounds(107, 207, 287, 22);
		contentPane.add(comboBoxOro);

		JLabel lblsegundopuesto = new JLabel("Segundo puesto *:");
		lblsegundopuesto.setBounds(10, 321, 109, 14);
		contentPane.add(lblsegundopuesto);

		JPanel panelinsercionTiempo2 = new PanelInsercionTiempo();
		panelinsercionTiempo2.setBackground(Color.WHITE);
		panelinsercionTiempo2.setBounds(10, 346, 417, 52);
		contentPane.add(panelinsercionTiempo2);

		JLabel lblidplata = new JLabel("Id plata *:");
		lblidplata.setBounds(10, 409, 59, 14);
		contentPane.add(lblidplata);

		DefaultComboBoxModel<Plata> platasModel = new DefaultComboBoxModel<Plata>();
		PlataDAO plDAO = new PlataDAO(ConexBD.getCon());
		ArrayList<Plata> platasList = (ArrayList<Plata>) plDAO.buscarTodos();
		JComboBox<Plata> comboBoxPlata = new JComboBox<Plata>();
		String[] platasStr = new String[platasList.size()];
		for (int i = 0; i < platasList.size(); i++)
			platasStr[i] = platasList.get(i).toString();

		comboBoxPlata.setModel(new DefaultComboBoxModel(platasStr));
		lblidplata.setLabelFor(comboBoxPlata);
		comboBoxPlata.setBounds(79, 407, 40, 18);
		contentPane.add(comboBoxPlata);

		JLabel lbltercerpuesto = new JLabel("Tercer puesto *:");
		lbltercerpuesto.setBounds(10, 434, 109, 14);
		contentPane.add(lbltercerpuesto);

		JPanel panelinsercionTiempo3 = new PanelInsercionTiempo();
		panelinsercionTiempo3.setBackground(Color.WHITE);
		panelinsercionTiempo3.setBounds(10, 459, 417, 52);
		contentPane.add(panelinsercionTiempo3);

		JLabel lblidbronce = new JLabel("Id bronce *:");
		lblidbronce.setBounds(23, 530, 69, 14);
		contentPane.add(lblidbronce);

		DefaultComboBoxModel<Bronce> broncesModel = new DefaultComboBoxModel<Bronce>();
		BronceDAO bDAO = new BronceDAO(ConexBD.getCon());
		ArrayList<Bronce> broncesList = (ArrayList<Bronce>) bDAO.buscarTodos();
		JComboBox<Bronce> comboBoxBronce = new JComboBox<Bronce>();
		String[] broncesStr = new String[broncesList.size()];
		for (int i = 0; i < broncesList.size(); i++)
			broncesStr[i] = broncesList.get(i).toString();

		comboBoxBronce.setModel(new DefaultComboBoxModel(broncesStr));
		lblidbronce.setLabelFor(comboBoxBronce);
		comboBoxBronce.setBounds(89, 522, 40, 22);
		contentPane.add(comboBoxBronce);

		DefaultComboBoxModel<Atleta> atletasModel = new DefaultComboBoxModel<Atleta>();
		AtletaDAO aDAO = new AtletaDAO(ConexBD.getCon());
		ArrayList<Atleta> atletassList = (ArrayList<Atleta>) aDAO.buscarTodos();
		/// Pero el modelo de comboBox básico requiere Strings
		String[] atletasStr = new String[atletassList.size()];
		for (int i = 0; i < atletassList.size(); i++)
			atletasStr[i] = atletassList.get(i).getPersona().data();

		JComboBox<Atleta> comboBoxPuesto1 = new JComboBox<Atleta>();
		comboBoxPuesto1.setModel(new DefaultComboBoxModel(atletasStr));
		lblprimerpuesto.setLabelFor(comboBoxPuesto1);
		comboBoxPuesto1.setBounds(79, 292, 40, 22);
		contentPane.add(comboBoxPuesto1);

		JComboBox<Atleta> comboBoxPuesto2 = new JComboBox<Atleta>();
		comboBoxPuesto2.setModel(new DefaultComboBoxModel(atletasStr));
		lblsegundopuesto.setLabelFor(comboBoxPuesto2);
		comboBoxPuesto2.setBounds(129, 317, 287, 22);
		contentPane.add(comboBoxPuesto2);

		JComboBox<Atleta> comboBoxPuesto3 = new JComboBox<Atleta>();
		comboBoxPuesto3.setModel(new DefaultComboBoxModel(atletasStr));
		lbltercerpuesto.setLabelFor(comboBoxPuesto3);
		comboBoxPuesto3.setBounds(129, 430, 287, 22);
		contentPane.add(comboBoxPuesto3);

		LocalDate hoyMas1MesLD = LocalDate.now().plusMonths(1);
		java.util.Date hoyMas1Mes = new Date(hoyMas1MesLD.getYear() - 1900, hoyMas1MesLD.getMonthValue() - 1,
				hoyMas1MesLD.getDayOfMonth());

		/// Las siguientes lineas seria lo deseable: trabajar diectamente con objetos en
		/// el model del comboBox
		DefaultComboBoxModel<Patrocinador> patrocinadoresModel = new DefaultComboBoxModel<Patrocinador>();
		JComboBox<Patrocinador> comboBoxPatrocinador = new JComboBox<Patrocinador>(patrocinadoresModel);
		PatrocinadorDAO patDAO = new PatrocinadorDAO(ConexBD.getCon());
		ArrayList<Patrocinador> patrocinadoresList = (ArrayList<Patrocinador>) patDAO.buscarTodos();
		for (Patrocinador pat : patrocinadoresList)
			comboBoxPatrocinador.addItem(pat);

		/// Pero el modelo de comboBox básico requiere Strings
		String[] patrocinadoresStr = new String[patrocinadoresList.size()];
		for (int i = 0; i < patrocinadoresList.size(); i++)
			patrocinadoresStr[i] = patrocinadoresList.get(i).mostrarBasico();
		comboBoxPatrocinador.setModel(new DefaultComboBoxModel(patrocinadoresStr));
		comboBoxPatrocinador.setBounds(96, 157, 261, 22);
		panel_1.add(comboBoxPatrocinador);
		comboBoxPatrocinador.setEnabled(false);

		JSpinner spinnerFecha = new JSpinner();
		spinnerFecha.setBounds(86, 69, 86, 20);
		panel_1.add(spinnerFecha);
		spinnerFecha.setEnabled(false);
		spinnerFecha.setModel(new SpinnerDateModel(hoyMas1Mes, hoyMas1Mes, null, Calendar.DAY_OF_YEAR));

		JLabel lbldefinitivo = new JLabel("Establecer como DEFINITIVO:");
		lbldefinitivo.setBounds(10, 555, 152, 14);
		contentPane.add(lbldefinitivo);
		JCheckBox chckbxNewCheckBox = new JCheckBox();
		chckbxNewCheckBox.setBounds(157, 546, 27, 23);
		contentPane.add(chckbxNewCheckBox);

		JButton buttonAceptar = new JButton("Aceptar");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";
				/// Tomar cada campo y validarlo. Si alguno no es correcto, avisar al usuario
				String nombre = textFieldNombre.getText();
				valido = Validaciones.validarNombrePrueba(nombre);
				if (!valido) {
					errores += "El nombre de la prueba no es válido (5-150 caracteres).\n";
					lblNombre.setForeground(Color.RED);
				} else
					nueva.setNombre(nombre);
				valido = false;

				java.util.Date fecha = (java.util.Date) spinnerFecha.getValue();
				valido = Validaciones.validarFechaNuevaPrueba(fecha);
				if (!valido) {
					errores += "La fecha de la prueba no es válido (posterior a 1 mes desde hoy).\n";
					lblFecha.setForeground(Color.RED);
				} else {
					LocalDate fechaLD = LocalDate.of(fecha.getYear() + 1900, fecha.getMonth() + 1, fecha.getDate());
					nueva.setFecha(fechaLD);
				}
				valido = false;
				valido = (comboBoxLugar.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el lugar de celebración de la nueva prueba.\n";
					lblLugar.setForeground(Color.RED);
				} else {
					Lugar lugar = (Lugar) comboBoxLugar.getSelectedItem();
					nueva.setLugar(lugar);
				}
				valido = false;
				valido = !(!rbIndividual.isSelected() && !rbEquipos.isSelected())
						|| (rbIndividual.isSelected() && rbEquipos.isSelected());
				if (!valido) {
					errores += "Debe seleccionar si la nueva prueba es individual O por equipos.\n";
					rbIndividual.setForeground(Color.RED);
					rbEquipos.setForeground(Color.RED);
				} else {
					nueva.setIndividual(rbIndividual.isSelected() ? true : false);
				}
				valido = false;
				valido = (comboBoxPatrocinador.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el Patrocinador de la nueva prueba.\n";
					lblPatrocinador.setForeground(Color.RED);
				} else {
					PatrocinadorDAO patDAO = new PatrocinadorDAO(ConexBD.getCon());
					String seleccionado = (String) comboBoxPatrocinador.getSelectedItem();
					String[] aux = seleccionado.split("\\.");
					long idPat = Long.valueOf(aux[0]);
					Patrocinador patrocinador = patDAO.buscarPorID(idPat);
					ConexBD.cerrarConexion();
					if (patrocinador == null)
						valido = false;
					else
						nueva.setPatrocinador(patrocinador);
				}

				Participante[] podio = new Participante[3];
				Metal[] metal = new Metal[3];

				valido = (comboBoxPuesto1.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el atleta del puesto 1 de la nueva prueba.\n";
					lblprimerpuesto.setForeground(Color.RED);
				} else {
					String puesto1str = (String) comboBoxPuesto1.getSelectedItem();
					AtletaDAO atDAO = new AtletaDAO(ConexBD.getCon());
					String[] aux = puesto1str.split("\\.");
					long idatleta = Long.valueOf(aux[0]);
					Atleta primero = atDAO.buscarPorID(idatleta);
					if (primero != null)
						podio[0] = primero;
				}
				valido = false;

				String oro = textFieldNombre.getText();
				valido = Validaciones.validarId(Oro.getId());
				if (!valido) {
					errores += "El  id del oro de la prueba no es válido.\n";
					lblidoro.setForeground(Color.RED);
				} else
					nueva.setParticipantes(participante);
				;
				valido = false;

				valido = (comboBoxPuesto2.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el atleta del puesto 2 de la nueva prueba.\n";
					lblsegundopuesto.setForeground(Color.RED);
				} else {
					String puesto2str = (String) comboBoxPuesto2.getSelectedItem();
					AtletaDAO atDAO = new AtletaDAO(ConexBD.getCon());
					String[] aux = puesto2str.split("\\.");
					long idatleta = Long.valueOf(aux[0]);
					Atleta segundo = atDAO.buscarPorID(idatleta);
					if (segundo != null)
						podio[1] = segundo;
				}
				valido = false;
				String plata = textFieldNombre.getText();
				valido = Validaciones.validarId(Plata.getId());
				if (!valido) {
					errores += "El  id de la plata de la prueba no es válido.\n";
					lblidoro.setForeground(Color.RED);
				} else
					nueva.setParticipantes(participante);
				;
				valido = false;

				valido = (comboBoxPuesto3.getSelectedIndex() != -1);
				if (!valido) {
					errores += "Debe seleccionar el atleta del puesto 3 de la nueva prueba.\n";
					lblprimerpuesto.setForeground(Color.RED);
				} else {
					String puesto3str = (String) comboBoxPuesto3.getSelectedItem();
					AtletaDAO atDAO = new AtletaDAO(ConexBD.getCon());
					String[] aux = puesto3str.split("\\.");
					long idatleta = Long.valueOf(aux[0]);
					Atleta tercero = atDAO.buscarPorID(idatleta);
					if (tercero != null)
						podio[2] = tercero;
				}
				valido = false;

				String bronce = textFieldNombre.getText();
				valido = Validaciones.validarId(Bronce.getId());
				if (!valido) {
					errores += "El  id de la plata de la prueba no es válido.\n";
					lblidoro.setForeground(Color.RED);
				} else
					nueva.setParticipantes(participante);
				;
				valido = false;

				valido = errores.isEmpty();

				if (!valido) {
					titulo = "ERROR: Campos inválidos";
					msj = "ERROR: los siguientes campos NO son válidos:\n\n";
					if (!errores.isEmpty())
						msj += errores + "\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					return;
				}

				/// Si todos los datos son correctos, llamar a PruebaDAO para insertar en la BD
				PruebaDAO pruebadao = new PruebaDAO(ConexBD.establecerConexion());
				boolean correcto = pruebadao.modificar(prueba);
				/// Tanto si la inserción de la nueva prueba tiene éxito como si no, avisar al
				/// usuario
				if (!correcto) {
					// hubo error al insertar en BD y no se generó la nueva prueba
					titulo = "ERROR al cerrar la Prueba en la BD";
					msj = "Hubo un error y NO se ha cerrado la prueba en la BD.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				} else {
					titulo = "Prueba " + prueba.getId() + " cerrada en la BD";
					msj = "Se ha cerrado correctamente la  prueba:\n" + prueba.toString();
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);
					/// Aqui se redirigiría al usuario hacia la pantalla principal
					/// TODO
				}
			}
		});
		buttonAceptar.setBounds(233, 578, 89, 23);
		contentPane.add(buttonAceptar);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = "Cerrar ventana";
				String msj = "¿Realmente desea cerrar la ventana?";
				int opcion = JOptionPane.showConfirmDialog(null, msj, titulo, JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					/// Aqui se redirigiría al usuario hacia la pantalla principal o se saldria
					System.exit(0); /// SALIR directamente
				}

			}
		});
//		System.out.println("Guardando datos en resultado_prueba.txt...");
//
//		File fOut1 = new File(" resultado_prueba<idPrueba>.txt");
//		FileWriter fw1 = null;
//		BufferedWriter bw1 = null;
//		try {
//			fw1 = new FileWriter(fOut1);
//			bw1 = new BufferedWriter(fw1);
//			for (int i = 0; i < Prueba.nuevaPrueba(); i++) {
//				Resultado r = new Resultado();
//				r = Resultado.nuevoResultado()[i];
//				bw1.write( Resultado de la prueba <idPrueba> “<nombrePrueba>” celebrada el pasado 
//		<fecha(dd/MM/aaaa)> en <lugar>. +"\n" +
//		Primer puesto para <nombreAtleta_1> (<NIFNIEAtleta_1>), con un tiempo de 
//		<tiempo1(hh:mm:ss,cc)>. Se le otorga el oro <idOro> de pureza <purezaOro>%. +"\n" + 
//		Segundo puesto para <nombreAtleta_2> (<NIFNIEAtleta_2>), con un tiempo de 
//		<tiempo2(hh:mm:ss,cc)>. Se le otorga la plata <idPlata> de pureza 
//		<purezaPlata>%. +"\n" +
//		Tercer puesto para <nombreAtleta_3> (<NIFNIEAtleta_3>), con un tiempo de 
//		<tiempo3(hh:mm:ss,cc)>. Se le otorga el bronce <idBronce> de pureza 
//		<purezaBronce>%. +"\n" +
//		Resultado <idResultado> cerrado a las <hh:mm:ss> del día <dd/MM/yyyy>. );
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (bw1 != null)
//					bw1.close();
//				if (fw1 != null)
//					fw1.close();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		buttonCancelar.setBounds(332, 578, 89, 23);
		contentPane.add(buttonCancelar);

	}
}
