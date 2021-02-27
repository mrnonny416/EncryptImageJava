import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JPasswordField;

public class MainGUI {

	private JFrame frmImageEncryption;
	private JTextField txtBrowseFile;
	private JTextField txtOutFile;
	String fileBrowseName;
	File fileBrowseImage;
	String encryptText;
	public Encrypt encrypt;
	public Decrypt decrypt;
	private JPasswordField txtSecretKey;
	boolean isbtnEncrypt = false;
	boolean isbtnDecrypt = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmImageEncryption.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmImageEncryption = new JFrame();
		frmImageEncryption.setTitle("Image Encryption");
		frmImageEncryption.setResizable(false);
		frmImageEncryption.setBounds(100, 100, 666, 691);
		frmImageEncryption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		txtBrowseFile = new JTextField();
		txtSecretKey = new JPasswordField();
		txtOutFile = new JTextField();
		txtOutFile.setColumns(10);
		JPanel panel = new JPanel();
		JButton btnBrowseFile = new JButton("Browse");
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setEnabled(false);
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setEnabled(false);
		JButton btnShowKey = new JButton("Show");
		JLabel lblBrowse = new JLabel("Browse");
		JLabel lblOutFIle = new JLabel("Out FIle");
		JLabel lblSecretKey = new JLabel("SecretKey");
		JButton btnExit = new JButton("Exit");
		JLabel lblPreview = new JLabel("");
		lblPreview.setBackground(Color.GRAY);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblBrowse)
									.addGap(38))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblOutFIle, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addGap(26))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSecretKey)
									.addGap(24)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtSecretKey)
								.addComponent(txtBrowseFile, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(txtOutFile, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
							.addGap(9)
							.addComponent(btnShowKey)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addComponent(btnExit))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnDecrypt, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
									.addComponent(btnEncrypt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnBrowseFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(70))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPreview, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBrowseFile)
						.addComponent(txtBrowseFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBrowse))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEncrypt)
						.addComponent(txtOutFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOutFIle))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDecrypt)
						.addComponent(lblSecretKey)
						.addComponent(txtSecretKey, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShowKey))
					.addGap(45)
					.addComponent(btnExit)
					.addGap(97)
					.addComponent(lblPreview, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		txtBrowseFile.setEditable(false);
		btnShowKey.setEnabled(false);
		txtOutFile.setEditable(false);
		panel.setBackground(Color.WHITE);
		txtBrowseFile.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmImageEncryption.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(24)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel);
		frmImageEncryption.getContentPane().setLayout(groupLayout);
// Start Action ---------------------------------------------------------------------------------------------------------------

		btnBrowseFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser browseFile = new JFileChooser("C:\\Users\\rusak\\Desktop");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG/JPEG", ".PNG/.png", ".JPEG/.jpeg");
				browseFile.addChoosableFileFilter(filter);
				int ret = browseFile.showDialog(null, "Choose File");
				if (ret == JFileChooser.APPROVE_OPTION) {
					fileBrowseName = browseFile.getSelectedFile().toString();
					fileBrowseImage = new File(browseFile.getSelectedFile(), fileBrowseName);
					txtBrowseFile.setText(fileBrowseName);
					String[] arrName = fileBrowseName.split("\\.");
					btnEncrypt.setEnabled(false);
					btnDecrypt.setEnabled(false);
					btnShowKey.setEnabled(false);
					txtSecretKey.setText("");
					try {
						if (arrName.length == 2) {
							encrypt = new Encrypt(browseFile.getSelectedFile(), fileBrowseName);
							txtOutFile.setText(encrypt.changName());
							isbtnDecrypt = false;
							isbtnEncrypt = true;
							lblPreview.setIcon(new ImageIcon(PreviewImage()));

						} else {
							decrypt = new Decrypt(browseFile.getSelectedFile(), fileBrowseName);
							txtOutFile.setText(decrypt.changName());
							isbtnDecrypt = true;
							isbtnEncrypt = false;
							lblPreview.setIcon(null);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		btnEncrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(txtSecretKey.getPassword()) != "")
					try {
						encrypt.encrypt(String.valueOf(txtSecretKey.getPassword()));

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});

		btnDecrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(txtSecretKey.getPassword()) != "")
					try {
						decrypt.decrypt(String.valueOf(txtSecretKey.getPassword()));

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});

		txtSecretKey.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (txtSecretKey.getPassword().toString() != "") {
					btnEncrypt.setEnabled(isbtnEncrypt);
					btnDecrypt.setEnabled(isbtnDecrypt);
					btnShowKey.setEnabled(true);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		btnShowKey.addMouseListener(new MouseListener() {
			@Override
			public void mouseExited(MouseEvent e) {
				txtSecretKey.setEchoChar('*');
			}

			@Override
			public void mousePressed(MouseEvent e) {
				txtSecretKey.setEchoChar((char) 0);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				txtSecretKey.setEchoChar('*');
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

		});

		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
// End Action--------------------------------------------------------------------------------------------------------------
	}// End initialize()

	Image PreviewImage() {
		if (fileBrowseName != null) {

			ImageIcon browseIcon = new ImageIcon(fileBrowseName);
			Image browseImage = browseIcon.getImage();
			Image modifinedDabImage = browseImage.getScaledInstance(500, 300, java.awt.Image.SCALE_SMOOTH);
			browseIcon = new ImageIcon(modifinedDabImage);
			/*JFrame showPreview = new JFrame();
			showPreview.setBounds(browseIcon.getIconWidth() / 2, browseIcon.getIconHeight() / 2, 1000, 600);
			showPreview.setVisible(true);
			showPreview.getContentPane().add(new JLabel(new ImageIcon(modifinedDabImage)));
			showPreview.pack();*/
			return modifinedDabImage;
		}
		return null;
	}
}// End Main