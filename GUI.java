import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	public String out = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setEnabled(false);
		frame.setResizable(false);
		frame.setBounds(100, 100, 844, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("File Name :");
		
		textField = new JTextField();
		textField.setColumns(10);
		disk_scheduling dsk = new disk_scheduling();
		JButton compress = new JButton("compress");
		compress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = textField.getText();
				out = dsk.main(fileName);
				textArea.setText(out);
			}
		});
		
		textArea = new JTextArea();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(91)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
							.addGap(68)
							.addComponent(compress))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 741, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(compress))
					.addGap(34)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}