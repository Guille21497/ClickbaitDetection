import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.io.Console;
import java.util.ResourceBundle.Control;

import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class mainWindow extends JFrame {

	private JPanel contentPane;

	controlador control = new controlador();
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow frame = new mainWindow();
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
	public mainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icon.png"));
		setTitle("Lector datasets: Detecci\u00F3n Autom\u00E1tica de Clickbait");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 678);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		mnOpciones.setIcon(new ImageIcon("img\\config.png"));
		mnOpciones.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnOpciones);
		
		JCheckBoxMenuItem ActivarLogs = new JCheckBoxMenuItem("Activar logs");
		ActivarLogs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnOpciones.add(ActivarLogs);
		
		JMenuItem botonRutas = new JMenuItem("Rutas ficheros");
		botonRutas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnOpciones.add(botonRutas);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.text);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JCheckBox checkBox1 = new JCheckBox(" Dataset 1: Dataset for clickbait detection");
		checkBox1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		checkBox1.setBackground(SystemColor.text);
		checkBox1.setBounds(34, 90, 441, 25);
		desktopPane.add(checkBox1);
		
		JCheckBox checkBox2 = new JCheckBox(" Dataset 2: News Clickbait");
		checkBox2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		checkBox2.setBackground(SystemColor.text);
		checkBox2.setBounds(34, 120, 414, 25);
		desktopPane.add(checkBox2);
		
		JCheckBox checkBox3 = new JCheckBox(" Dataset 3: Webis Clickbait 17");
		checkBox3.setFont(new Font("Tahoma", Font.PLAIN, 21));
		checkBox3.setBackground(SystemColor.text);
		checkBox3.setBounds(34, 150, 414, 25);
		desktopPane.add(checkBox3);
		
		JLabel lblConjuntoEntrenamiento = new JLabel("Crear conjuntos");
		lblConjuntoEntrenamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblConjuntoEntrenamiento.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblConjuntoEntrenamiento.setBounds(116, 0, 293, 68);
		desktopPane.add(lblConjuntoEntrenamiento);
		
		JLabel lblClickbait = new JLabel("% Clickbait [0 - 1]");
		lblClickbait.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClickbait.setBounds(34, 222, 143, 24);
		desktopPane.add(lblClickbait);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCrear.setBounds(34, 259, 297, 25);
		desktopPane.add(btnCrear);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setAutoscrolls(true);
		desktopPane_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Logs", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		desktopPane_1.setBackground(SystemColor.menu);
		desktopPane_1.setBounds(10, 295, 596, 296);
		desktopPane.add(desktopPane_1);
		
		
		JLabel consoleLabel = new JLabel(">");
		consoleLabel.setBackground(SystemColor.menu);
		consoleLabel.setVerticalAlignment(SwingConstants.TOP);
		consoleLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
		consoleLabel.setBounds(12, 23, 574, 262);
		desktopPane_1.add(consoleLabel);
		
		control.lecturaRutas(consoleLabel);
		
		JLabel lblTest = new JLabel("% Test [0 - 1]");
		lblTest.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTest.setBounds(34, 193, 118, 24);
		desktopPane.add(lblTest);
		
		textField_2 = new JTextField();
		textField_2.setText("0.25");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setColumns(10);
		textField_2.setBounds(179, 194, 56, 22);
		desktopPane.add(textField_2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBox.addItem("0");
		comboBox.addItem("0.1");
		comboBox.addItem("0.2");
		comboBox.addItem("0.3");
		comboBox.addItem("0.4");
		comboBox.addItem("0.5");
		comboBox.addItem("0.6");
		comboBox.addItem("0.7");
		comboBox.addItem("0.1");
		comboBox.addItem("0.9");
		comboBox.addItem("1");
		comboBox.addItem("TODO");
		comboBox.setSelectedItem((float) 0.3);
		
		comboBox.setMaximumRowCount(10);
		comboBox.setToolTipText("");
		comboBox.setBounds(179, 223, 76, 22);
		desktopPane.add(comboBox);
		
		btnCrear.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	boolean logs = false;
		    	if(ActivarLogs.isSelected()) {
					logs = true;
				}
		    	boolean dataset1 = false;
		    	boolean dataset2 = false;
		    	boolean dataset3 = false;
		    	if(checkBox1.isSelected()) {
		    		dataset1 = true;		    		  
		    	}
		    	if(checkBox2.isSelected()) {
		    		dataset2 = true;     
		    	}
		    	if(checkBox3.isSelected()) {
		    		dataset3 = true;
		    	}

		    	float porcentajeTest = Float.parseFloat(textField_2.getText());
		    	String porcentajeClickbait = (String) comboBox.getSelectedItem();
		    	
		    	
		    	control.crearConjuntos(dataset1, dataset2, dataset3, false, porcentajeClickbait, porcentajeTest, 1, logs, consoleLabel);
		    }
		});
		
		botonRutas.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	control.lecturaRutas(consoleLabel);
			    String rutas[] = new String[6];
			    rutas[0] = control.getRuta1();
			    rutas[1] = control.getRuta2();
			    rutas[2] = control.getRuta3();
			    rutas[3] = control.getRuta4();
			    rutas[4] = control.getRuta5();
			    rutas[5] = control.getRuta6();
				JFrame ventanaRutas = new rutasFicheros(control, rutas);
				ventanaRutas.show();
		    }
		});
	}
}
