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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import java.awt.Toolkit;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JProgressBar;

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
	Color pelColor = new Color(251, 225, 232);
	Color frmColor = new Color(249, 201, 215);

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
		frmImageEncryption.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\rusak\\eclipse-workspace\\EncryptImage\\encrypt-2-569399.png"));
		frmImageEncryption.getContentPane().setBackground(frmColor);
		frmImageEncryption.setTitle("Image Encryption");
		frmImageEncryption.setResizable(false);
		frmImageEncryption.setBounds(100, 100, 573, 694);
		frmImageEncryption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		txtBrowseFile = new JTextField();
		txtSecretKey = new JPasswordField();
		txtOutFile = new JTextField();
		txtOutFile.setColumns(10);
		JPanel panel = new JPanel();
		JButton btnBrowseFile = new JButton("  Browse  ");
		btnBrowseFile.setBackground(new Color(255, 255, 255));
		btnBrowseFile.setForeground(new Color(102, 163, 255));
		btnBrowseFile.setBorder(new MatteBorder(3, 3, 1, 1, (Color) new Color(204, 153, 204)));
		btnBrowseFile.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setEnabled(false);
		btnEncrypt.setBackground(new Color(255, 255, 255));
		btnEncrypt.setForeground(new Color(102, 163, 255));
		btnEncrypt.setBorder(new MatteBorder(3, 3, 1, 1, (Color) new Color(204, 153, 204)));
		btnEncrypt.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		JButton btnDecrypt = new JButton("Decrypt");

		btnDecrypt.setEnabled(false);
		btnDecrypt.setBackground(new Color(255, 255, 255));
		btnDecrypt.setForeground(new Color(102, 163, 255));
		btnDecrypt.setBorder(new MatteBorder(3, 3, 1, 1, (Color) new Color(204, 153, 204)));
		btnDecrypt.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnDecrypt.setEnabled(false);

		JButton btnShowKey = new JButton(" Show ");
		btnShowKey.setEnabled(false);
		btnShowKey.setBackground(new Color(255, 255, 255));
		btnShowKey.setForeground(new Color(102, 163, 255));
		btnShowKey.setBorder(new MatteBorder(3, 3, 1, 1, (Color) new Color(204, 153, 204)));
		btnShowKey.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

		txtBrowseFile.setEditable(false);
		btnShowKey.setEnabled(false);
		txtOutFile.setEditable(false);

		JLabel lblBrowse = new JLabel("Browse : ");
		lblBrowse.setForeground(new Color(153, 153, 153));
		lblBrowse.setFont(new Font("Segoe Script", Font.BOLD, 20));

		JLabel lblOutFIle = new JLabel("Out FIle : ");
		lblOutFIle.setForeground(new Color(153, 153, 153));
		lblOutFIle.setFont(new Font("Segoe Script", Font.BOLD, 20));

		JLabel lblSecretKey = new JLabel("SecretKey : ");
		lblSecretKey.setForeground(new Color(153, 153, 153));
		lblSecretKey.setFont(new Font("Segoe Script", Font.BOLD, 20));

		JButton btnExit = new JButton("Exit");
		btnExit.setEnabled(false);
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.setForeground(new Color(102, 163, 255));
		btnExit.setBorder(new MatteBorder(3, 3, 1, 1, (Color) new Color(204, 153, 204)));
		btnExit.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

		JLabel lblPreview = new JLabel("");
		lblPreview.setFont(new Font("Segoe Script", Font.PLAIN, 30));
		lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreview.setForeground(new Color(0, 0, 0));
		lblPreview.setBackground(Color.GRAY);

		JLabel lblPreview_1 = new JLabel("Preview");
		lblPreview_1.setForeground(new Color(153, 153, 153));
		lblPreview_1.setFont(new Font("Segoe Script", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblPreview, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblBrowse)
									.addComponent(lblSecretKey)
									.addComponent(lblOutFIle))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(txtSecretKey, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnShowKey))
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(btnEncrypt, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnDecrypt, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(txtBrowseFile, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnBrowseFile))
									.addComponent(txtOutFile, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
								.addGap(213))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblPreview_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(450, Short.MAX_VALUE)))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBrowse)
						.addComponent(txtBrowseFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowseFile, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOutFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOutFIle))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSecretKey)
						.addComponent(txtSecretKey, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShowKey, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEncrypt)
						.addComponent(btnDecrypt)
						.addComponent(btnExit))
					.addGap(16)
					.addComponent(lblPreview_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPreview, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);

		panel.setBackground(pelColor);
		txtBrowseFile.setColumns(10);

		JLabel lblNewLabel = new JLabel("Image Encryption Program");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(176, 224, 230));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 30));

		JButton btnAbountUs = new JButton("");

		btnAbountUs.setBackground(frmColor);
		btnAbountUs.setBorder(null);
		btnAbountUs.setIcon(new ImageIcon("abount.png"));

		GroupLayout groupLayout = new GroupLayout(frmImageEncryption.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 537, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addComponent(btnAbountUs, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(btnAbountUs, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
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
							lblPreview.setText(null);
							lblPreview.setIcon(new ImageIcon(PreviewImage()));
						} else {
							decrypt = new Decrypt(browseFile.getSelectedFile(), fileBrowseName);
							txtOutFile.setText(decrypt.changName());
							isbtnDecrypt = true;
							isbtnEncrypt = false;
							lblPreview.setIcon(null);
							lblPreview.setText("Can't Show Encryption Image");
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
		
		btnAbountUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"Program Encrypt&Decrypt Imgae\r\n"
						+ "Tawee Sopapan 		Code 62523206004-0\r\n"
						+ "Arta Chermue 		Code 62523206015-6\r\n"
						+ "Tanadon Ratanasupa 	Code 62523206020-6\r\n"
						+ "For ENGCE110"
						,"About Us",JOptionPane.PLAIN_MESSAGE);
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
			/*
			 * JFrame showPreview = new JFrame();
			 * showPreview.setBounds(browseIcon.getIconWidth() / 2,
			 * browseIcon.getIconHeight() / 2, 1000, 600); showPreview.setVisible(true);
			 * showPreview.getContentPane().add(new JLabel(new
			 * ImageIcon(modifinedDabImage))); showPreview.pack();
			 */
			return modifinedDabImage;
		}
		return null;
	}

}// End Main