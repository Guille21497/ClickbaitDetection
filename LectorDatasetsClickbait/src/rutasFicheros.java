import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;

public class rutasFicheros extends JFrame {

	private JPanel contentPane;
	private JTextField ruta6;
	private JTextField ruta1;
	private JTextField ruta3;
	private JTextField ruta4;
	private JTextField ruta2;
	private JTextField ruta5;

	/**
	 * Create the frame.
	 */
	public rutasFicheros(controlador control, String[] rutas) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\folder.png"));
		setTitle("Rutas de los ficheros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 762, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 737, 258);
		contentPane.add(tabbedPane);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Dataset 1", null, panel_4, null);

		JLabel lblDatasetForClickbait = new JLabel("Dataset for clickbait detection");
		lblDatasetForClickbait.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatasetForClickbait.setBounds(12, 13, 403, 23);
		panel_4.add(lblDatasetForClickbait);

		JLabel label_1 = new JLabel("Fichero 1: ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(12, 57, 87, 16);
		panel_4.add(label_1);

		ruta1 = new JTextField();
		ruta1.setText(rutas[0]);
		ruta1.setColumns(10);
		ruta1.setBounds(77, 54, 634, 22);
		panel_4.add(ruta1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 37, 403, 2);
		panel_4.add(separator_1);

		JLabel lblFichero = new JLabel("Fichero 2: ");
		lblFichero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFichero.setBounds(12, 89, 87, 16);
		panel_4.add(lblFichero);

		ruta2 = new JTextField();
		ruta2.setText(rutas[1]);
		ruta2.setColumns(10);
		ruta2.setBounds(77, 86, 634, 22);
		panel_4.add(ruta2);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Dataset 2", null, panel, null);

		JLabel lblNewsClickbait = new JLabel("News Clickbait");
		lblNewsClickbait.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewsClickbait.setBounds(12, 13, 403, 23);
		panel.add(lblNewsClickbait);

		JLabel label_3 = new JLabel("Fichero 1: ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(12, 57, 87, 16);
		panel.add(label_3);

		ruta3 = new JTextField();
		ruta3.setText(rutas[2]);
		ruta3.setColumns(10);
		ruta3.setBounds(77, 54, 634, 22);
		panel.add(ruta3);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 37, 403, 2);
		panel.add(separator_2);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Dataset 3", null, panel_1, null);

		JLabel lblWebisClickbait = new JLabel("Webis Clickbait 17");
		lblWebisClickbait.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWebisClickbait.setBounds(12, 13, 403, 23);
		panel_1.add(lblWebisClickbait);

		JLabel label_5 = new JLabel("Fichero 1: ");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(12, 57, 87, 16);
		panel_1.add(label_5);

		ruta4 = new JTextField();
		ruta4.setText(rutas[3]);
		ruta4.setColumns(10);
		ruta4.setBounds(77, 54, 634, 22);
		panel_1.add(ruta4);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 37, 403, 2);
		panel_1.add(separator_3);

		JLabel lblFichero_1 = new JLabel("Fichero 2: ");
		lblFichero_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFichero_1.setBounds(12, 89, 87, 16);
		panel_1.add(lblFichero_1);

		ruta5 = new JTextField();
		ruta5.setText(rutas[4]);
		ruta5.setColumns(10);
		ruta5.setBounds(77, 86, 634, 22);
		panel_1.add(ruta5);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Dataset 4", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("The Examiner - Spam Clickbait Catalog");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(12, 13, 403, 23);
		panel_3.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Fichero 1: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 57, 87, 16);
		panel_3.add(lblNewLabel_1);

		ruta6 = new JTextField();
		ruta6.setText(rutas[5]);
		ruta6.setBounds(77, 54, 634, 22);
		panel_3.add(ruta6);
		ruta6.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 37, 403, 2);
		panel_3.add(separator);

		JButton guardarRutas = new JButton("Guardar");
		guardarRutas.setBounds(12, 264, 218, 25);
		contentPane.add(guardarRutas);

		// --------------------------------------------
		
		guardarRutas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				control.setRuta1(ruta1.getText());
				control.setRuta2(ruta2.getText());
				control.setRuta3(ruta3.getText());
				control.setRuta4(ruta4.getText());
				control.setRuta5(ruta5.getText());
				control.setRuta6(ruta6.getText());
				setVisible(false);
			}
		});
	}
}
