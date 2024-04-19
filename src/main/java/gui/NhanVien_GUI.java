package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.impl.NhanVien_Impl;
import dao.impl.TaiKhoan_Impl;
import entity.Ca;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVien_GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JDateChooser dateChooserNgaySinh;
	private JTextField txtDiaChi;
	private JTextField txtSoDienThoai;
	private JTextField txtcCCD;
	private JTextField txtEmail;
	private DefaultTableModel model;
	private JTable table;
	private JTableHeader tableHeader;
	private JComboBox<String> cbGioiTinh;
	private JComboBox<String> cbCa;
	private JTextField txtLuong;
	private JComboBox<String> cbChucVu;
	private JTextField txtTenNhanVien;
	private JLabel lblHinhAnh;
	private JLabel lblMaNhanVienValue;
	private JLabel lblChucVu;
	private JButton btnChonHinhAnh;
	private JFileChooser fileChooser;
	private File selectedFile;
	private String relativePath;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnSua;
	private JButton btnThem;
	private JButton btnTim;
	private TimKiemNhanVien_GUI timNhanVien_GUI;
	private JDesktopPane desktopPane;
	private ArrayList<NhanVien> ds;
	private Border borderDefault;

	private NhanVien_Impl nhanVien_DAO;
	private TaiKhoan_Impl taiKhoan_DAO;

	private static final String URL = "rmi://PhamVanThanh:9891/";
	public NhanVien_GUI() throws RemoteException, MalformedURLException, NotBoundException {

		// khai bao DAO
		nhanVien_DAO = (NhanVien_Impl) Naming.lookup(URL + "nhanVienDAO");
		taiKhoan_DAO = (TaiKhoan_Impl) Naming.lookup(URL + "taiKhoanDAO");

		ds = new ArrayList<NhanVien>();

		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1300, 720);
		add(panel);
		panel.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		desktopPane.setBounds(0, 0, 1300, 720);
		panel.add(desktopPane);
		
		JPanel pMain = new JPanel();
		pMain.setBounds(0, 0, 1300, 720);
		pMain.setBackground(new Color(241, 245, 249));
		pMain.setLayout(null);
		desktopPane.add(pMain);

		JPanel pNhapThongTin = new JPanel();
		pNhapThongTin.setBackground(new Color(255, 255, 255));
		pNhapThongTin.setBounds(869, 0, 430, 720);
		pNhapThongTin.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pMain.add(pNhapThongTin);
		pNhapThongTin.setLayout(null);
		
		lblHinhAnh = new JLabel("");
		lblHinhAnh.setToolTipText("Hình ảnh nhân viên");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setBounds(20, 20, 64, 64);
		lblHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		pNhapThongTin.add(lblHinhAnh);

		txtDiaChi = new JTextField();
		txtDiaChi.setBackground(new Color(255, 255, 255));
		txtDiaChi.setEditable(false);
		txtDiaChi.setToolTipText("Địa chỉ");
		txtDiaChi.setFont(new Font("SansSerif", Font.BOLD, 18));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(170, 652, 240, 40);
		txtDiaChi.setBorder(null);
		pNhapThongTin.add(txtDiaChi);

		JLabel lblSoDienThoai = new JLabel("Số Điện Thoại:");
		lblSoDienThoai.setToolTipText("Số điện thoại");
		lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSoDienThoai.setBounds(40, 145, 120, 40);
		pNhapThongTin.add(lblSoDienThoai);
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBackground(new Color(255, 255, 255));
		txtSoDienThoai.setEditable(false);
		txtSoDienThoai.setToolTipText("Số điện thoại");
		txtSoDienThoai.setFont(new Font("SansSerif", Font.BOLD, 18));
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(170, 145, 240, 40);
		borderDefault = txtSoDienThoai.getBorder();
		txtSoDienThoai.setBorder(null);
		pNhapThongTin.add(txtSoDienThoai);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setToolTipText("Email");
		lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblEmail.setBounds(40, 190, 56, 40);
		pNhapThongTin.add(lblEmail);

		txtcCCD = new JTextField();
		txtcCCD.setBackground(new Color(255, 255, 255));
		txtcCCD.setEditable(false);
		txtcCCD.setToolTipText("Căn cước công dân");
		txtcCCD.setFont(new Font("SansSerif", Font.BOLD, 18));
		txtcCCD.setColumns(10);
		txtcCCD.setBounds(170, 562, 240, 40);
		txtcCCD.setBorder(null);
		pNhapThongTin.add(txtcCCD);

		txtEmail = new JTextField();
		txtEmail.setBackground(new Color(255, 255, 255));
		txtEmail.setEditable(false);
		txtEmail.setToolTipText("Email");
		txtEmail.setFont(new Font("SansSerif", Font.BOLD, 18));
		txtEmail.setColumns(10);
		txtEmail.setBounds(170, 190, 240, 40);
		txtEmail.setBorder(null);
		pNhapThongTin.add(txtEmail);

		JPanel pDanhSach = new JPanel();
		pDanhSach.setBackground(new Color(255, 255, 255));
		pDanhSach.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pDanhSach.setBounds(0, 0, 850, 720);
		pMain.add(pDanhSach);
		pDanhSach.setLayout(null);

		JScrollPane scrollPaneNV = new JScrollPane();
		scrollPaneNV.setBounds(20, 100, 810, 545);
		scrollPaneNV.setToolTipText("Chọn vào nhân viên cần hiển thị thông tin");
		scrollPaneNV.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPaneNV.setBackground(new Color(255, 255, 255));
		pDanhSach.add(scrollPaneNV);
	
		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setBackground(new Color(255, 255, 255));
		dateChooserNgaySinh.getCalendarButton().setBounds(210, 0, 30, 40);
		dateChooserNgaySinh.getCalendarButton().setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/HeThong/calendar.png")));
		dateChooserNgaySinh.getCalendarButton().setBorder(null);
		dateChooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dateChooserNgaySinh.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateChooserNgaySinh.setToolTipText("Ngày sinh");
		dateChooserNgaySinh.setBounds(170, 517, 240, 40);
		dateChooserNgaySinh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		dateChooserNgaySinh.setFont(new Font("SansSerif", Font.BOLD, 18));
		dateChooserNgaySinh.setBorder(null);
		dateChooserNgaySinh.getDateEditor().setEnabled(false);
		pNhapThongTin.add(dateChooserNgaySinh);

		String cols[] = { "Mã NV", "Họ Và Tên", "Giới Tính", "Ngày Vào Làm",
				"Số Điện Thoại", "Địa Chỉ" };
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setToolTipText("Chọn vào nhân viên cần hiển thị thông tin");
		table.setRowHeight(30);
		table.setDefaultEditor(Object.class, null);
		table.setShowGrid(true); 
		table.setShowHorizontalLines(true);
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(141, 208, 229));
		table.setSelectionForeground(new Color(0, 0, 0));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				NhanVien nhanVien;
				try {
					nhanVien = nhanVien_DAO.getNhanVienTheoMa(model.getValueAt(row, 0).toString());
					lblHinhAnh.setIcon(new ImageIcon(NhanVien_GUI.class.getResource(nhanVien.getHinhAnh())));
					relativePath = nhanVien.getHinhAnh();
					txtTenNhanVien.setText(nhanVien.getTenNhanVien());
					lblChucVu.setText(nhanVien.getChucVu());
					// hinh anh
					txtSoDienThoai.setText(nhanVien.getSoDienThoai());
					txtEmail.setText(nhanVien.getEmail());
					lblMaNhanVienValue.setText(nhanVien.getMaNhanVien());
					if (nhanVien.getChucVu().equals("Bán hàng"))
						cbChucVu.setSelectedItem("Bán hàng");
					else if (nhanVien.getChucVu().equals("Quản lý"))
						cbChucVu.setSelectedItem("Quản lý");
					else
						cbChucVu.setSelectedIndex(-1);
					if (nhanVien.getCa().getMaCa().equals("C01"))
						cbCa.setSelectedItem("01");
					else if (nhanVien.getCa().getMaCa().equals("C02"))
						cbCa.setSelectedItem("02");
					else
						cbCa.setSelectedIndex(-1);
					dateChooserNgaySinh.setDate(nhanVien.getNgaySinh());
					txtcCCD.setText(nhanVien.getcCCD());
					if (nhanVien.getGioiTinh().equals("Nam"))
						cbGioiTinh.setSelectedItem("Nam");
					else if (nhanVien.getGioiTinh().equals("Nữ"))
						cbGioiTinh.setSelectedItem("Nữ");
					else 
						cbGioiTinh.setSelectedIndex(-1);
					txtDiaChi.setText(nhanVien.getDiaChi());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		scrollPaneNV.setViewportView(table);

		// header of table
		tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(73, 129, 158));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		tableHeader.setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		
		// set width of table
//		table.getColumnModel().getColumn(0).setPreferredWidth(30);
//		table.getColumnModel().getColumn(1).setPreferredWidth(102);
//		table.getColumnModel().getColumn(2).setPreferredWidth(170);
//		table.getColumnModel().getColumn(3).setPreferredWidth(35);
//		table.getColumnModel().getColumn(4).setPreferredWidth(60);
//		table.getColumnModel().getColumn(5).setPreferredWidth(70);
//		table.getColumnModel().getColumn(6).setPreferredWidth(50);
//		table.getColumnModel().getColumn(7).setPreferredWidth(102);
//		table.getColumnModel().getColumn(8).setPreferredWidth(40);
//		table.getColumnModel().getColumn(9).setPreferredWidth(30);
//		table.getColumnModel().getColumn(10).setPreferredWidth(50);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		
		cbChucVu = new JComboBox<String>();
		cbChucVu.setToolTipText("Chức vụ");
		cbChucVu.setFont(new Font("SansSerif", Font.BOLD, 18));
		cbChucVu.setBounds(170, 350, 240, 40);
		cbChucVu.setBackground(new Color(255, 255, 255));
		cbChucVu.setForeground(new Color(0, 0, 0));
		cbChucVu.addItem("Bán hàng");
		cbChucVu.addItem("Quản lý");
		cbChucVu.setSelectedIndex(-1);
		cbChucVu.setEnabled(false);
		cbChucVu.setBorder(null);
//		cbChucVu.setLayout(null);
		pNhapThongTin.add(cbChucVu);
		
		cbCa = new JComboBox<String>();
		cbCa.setToolTipText("Ca");
		cbCa.setFont(new Font("SansSerif", Font.BOLD, 18));
		cbCa.setBounds(170, 395, 240, 40);
		cbCa.setBackground(new Color(255, 255, 255));
		cbCa.setForeground(new Color(0, 0, 0));
		cbCa.addItem("01");
		cbCa.addItem("02");
		cbCa.setSelectedIndex(-1);
		cbCa.setEnabled(false);
		cbCa.setBorder(null);
//		cbCa.setLayout(null);
		pNhapThongTin.add(cbCa);
		
		cbGioiTinh = new JComboBox<String>();
		cbGioiTinh.setToolTipText("Giới tính");
		cbGioiTinh.setFont(new Font("SansSerif", Font.BOLD, 18));
		cbGioiTinh.setBounds(170, 607, 240, 40);
		cbGioiTinh.setBackground(new Color(255, 255, 255));
		cbGioiTinh.setForeground(new Color(0, 0, 0));
		cbGioiTinh.setEnabled(false);
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		cbGioiTinh.setSelectedIndex(-1);
		cbGioiTinh.setEnabled(false);
		cbGioiTinh.setBorder(null);
//		cbGioiTinh.setLayout(null);
		pNhapThongTin.add(cbGioiTinh);

								
								JLabel lblThongTin = new JLabel("Nhân Viên");
								lblThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
								lblThongTin.setBounds(20, 40, 130, 40);
								pDanhSach.add(lblThongTin);
								
								JSeparator separator = new JSeparator();
								separator.setForeground(new Color(60, 60, 60));
								separator.setBounds(126, 60, 704, 2);
								pDanhSach.add(separator);
								
								btnThem = new JButton("Thêm");
								btnThem.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/HeThong/add_person.png")));
								btnThem.setForeground(new Color(255, 255, 255));
								btnThem.setBackground(new Color(73, 129, 158));
								btnThem.setBounds(93, 660, 135, 40);
								btnThem.setOpaque(true);
								btnThem.setFont(new Font("SansSerif", Font.BOLD, 14));
								btnThem.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										if (btnThem.getText().equals("Thêm")) {
											enableEdit();
											focusable();
											btnXoa.setText("Hủy");
											btnThem.setText("Xác nhận");
											btnLamMoi.setEnabled(false);
											btnSua.setEnabled(false);
											btnTim.setEnabled(false);
										}
										else {
											try {
												add();
											} catch (RemoteException e1) {
												e1.printStackTrace();
											}
											disableEdit();
											unfocusable();
											btnThem.setText("Thêm");
											btnXoa.setText("Xóa");
											btnLamMoi.setEnabled(true);
											btnSua.setEnabled(true);
											btnTim.setEnabled(true);
										}
									}
								});
								pDanhSach.add(btnThem);
								
									btnSua = new JButton("Sửa");
									btnSua.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/HeThong/update_person.png")));
									btnSua.setBackground(new Color(73, 129, 158));
									btnSua.setForeground(new Color(255, 255, 255));
									btnSua.setBounds(393, 660, 135, 40);
									btnSua.setFont(new Font("SansSerif", Font.BOLD, 14));
									btnSua.addActionListener(new ActionListener() {
	
										@Override
										public void actionPerformed(ActionEvent e) {
											int row = table.getSelectedRow();
											if (btnSua.getText().equals("Sửa")) {
												if (row == -1)
													JOptionPane.showMessageDialog(null, "Bạn phải chọn vào nhân viên cần sửa!");
												else {
													enableEdit();
													focusable();
													btnXoa.setText("Hủy");
													btnSua.setText("Xác nhận");
													btnLamMoi.setEnabled(false);
													btnThem.setEnabled(false);
													btnTim.setEnabled(false);
												}
											}
											else {
												try {
													update();
												} catch (RemoteException e1) {
													e1.printStackTrace();
												}
												btnSua.setText("Sửa");
												btnXoa.setText("Xóa");
												disableEdit();
												unfocusable();
												btnLamMoi.setEnabled(true);
												btnThem.setEnabled(true);
												btnTim.setEnabled(true);
											}
										}
									});
									pDanhSach.add(btnSua);
								
										btnXoa = new JButton("Xóa");
										btnXoa.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/HeThong/remove_person.png")));
										btnXoa.setBackground(new Color(73, 129, 158));
										btnXoa.setForeground(new Color(255, 255, 255));
										btnXoa.setBounds(243, 660, 135, 40);
										btnXoa.setFont(new Font("SansSerif", Font.BOLD, 14));
										btnXoa.addActionListener(new ActionListener() {

											@Override
											public void actionPerformed(ActionEvent e) {
												if (btnXoa.getText().equals("Xóa")) {
													try {
														delete();
													} catch (RemoteException e1) {
														e1.printStackTrace();
													}
												}
												else {
													disableEdit();
													unfocusable();
													btnThem.setText("Thêm");
													btnSua.setText("Sửa");
													btnXoa.setText("Xóa");
													btnLamMoi.setEnabled(true);
													btnThem.setEnabled(true);
													btnSua.setEnabled(true);
													btnTim.setEnabled(true);
												}
											}
										});
										pDanhSach.add(btnXoa);		
											
											btnTim = new JButton("Tìm");
											btnTim.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/HeThong/find_person.png")));
											btnTim.setBackground(new Color(73, 129, 158));
											btnTim.setForeground(new Color(255, 255, 255));
											btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
											btnTim.setBounds(543, 660, 135, 40);
											btnTim.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(ActionEvent e) {
													// null cho lan dau chay va isClose cho nhung click sau
													if (timNhanVien_GUI == null || timNhanVien_GUI.isClosed()) {
														try {
															timNhanVien_GUI = new TimKiemNhanVien_GUI(ds);
														} catch (RemoteException | MalformedURLException | NotBoundException e1) {
															e1.printStackTrace();
														}
														timNhanVien_GUI.addInternalFrameListener(new InternalFrameAdapter() {
												            @Override
												            public void internalFrameActivated(InternalFrameEvent e) {
//												                System.out.println("Internal frame is activated.");
												            }

												            @Override
												            public void internalFrameDeactivated(InternalFrameEvent e) {
//												                System.out.println("Internal frame is deactivated.");
												            }

												            @Override
												            public void internalFrameOpened(InternalFrameEvent e) {
//												                System.out.println("Internal frame is opened.");
												            	disableButton();
												            	unfocusable();
												            }
												            
												            @Override
												            public void internalFrameClosed(InternalFrameEvent e) {
//												                System.out.println("Internal frame is closed.");
												                model.setRowCount(0);
												            	loadData(ds);
												            	ds.removeAll(ds);
												            	enableButton();
												            	focusable();
												            }
												        });
														desktopPane.add(timNhanVien_GUI).setVisible(true);
													}
												}
											});
											
											pDanhSach.add(btnTim);
											
											btnLamMoi = new JButton("Làm Mới");
											btnLamMoi.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/HeThong/refresh.png")));
											btnLamMoi.setOpaque(true);
											btnLamMoi.setForeground(Color.WHITE);
											btnLamMoi.setFont(new Font("SansSerif", Font.BOLD, 14));
											btnLamMoi.setBackground(new Color(73, 129, 158));
											btnLamMoi.setBounds(693, 660, 135, 40);
											btnLamMoi.addActionListener(new ActionListener() {
												
												@Override
												public void actionPerformed(ActionEvent e) {
													try {
														refresh();
													} catch (RemoteException e1) {
														e1.printStackTrace();
													}
												}
											});
											pDanhSach.add(btnLamMoi);

		JLabel lblLuong = new JLabel("Lương:");
		lblLuong.setToolTipText("Lương");
		lblLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblLuong.setBounds(880, 70, 56, 33);
		pNhapThongTin.add(lblLuong);

		txtLuong = new JTextField();
		txtLuong.setToolTipText("Lương");
		txtLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtLuong.setColumns(10);
		txtLuong.setBounds(960, 70, 160, 33);
		pNhapThongTin.add(txtLuong);
		
		lblChucVu = new JLabel("");
		lblChucVu.setToolTipText("Mã nhân viên");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setForeground(new Color(28, 28, 28));
		lblChucVu.setFont(new Font("SansSerif", Font.ITALIC, 20));
		lblChucVu.setBounds(94, 50, 177, 40);
		pNhapThongTin.add(lblChucVu);
		
		JLabel lblThongTinLienLac = new JLabel("Thông Tin Liên Lạc:");
		lblThongTinLienLac.setToolTipText("Thông tin liên lạc");
		lblThongTinLienLac.setHorizontalAlignment(SwingConstants.LEFT);
		lblThongTinLienLac.setForeground(new Color(28, 28, 28));
		lblThongTinLienLac.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblThongTinLienLac.setBounds(20, 100, 189, 40);
		pNhapThongTin.add(lblThongTinLienLac);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(60, 60, 60));
		separator_1.setBounds(20, 245, 390, 2);
		pNhapThongTin.add(separator_1);
		
		JLabel lblThongTinViecLam = new JLabel("Thông Tin Việc Làm:");
		lblThongTinViecLam.setToolTipText("Thông tin việc làm");
		lblThongTinViecLam.setHorizontalAlignment(SwingConstants.LEFT);
		lblThongTinViecLam.setForeground(new Color(28, 28, 28));
		lblThongTinViecLam.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblThongTinViecLam.setBounds(20, 260, 189, 40);
		pNhapThongTin.add(lblThongTinViecLam);
		
		JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
		lblMaNV.setToolTipText("Mã nhân viên");
		lblMaNV.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblMaNV.setBounds(40, 305, 120, 40);
		pNhapThongTin.add(lblMaNV);
		
		lblMaNhanVienValue = new JLabel("");
		lblMaNhanVienValue.setToolTipText("Mã nhân viên");
		lblMaNhanVienValue.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblMaNhanVienValue.setBounds(170, 305, 120, 40);
		pNhapThongTin.add(lblMaNhanVienValue);
		
		JLabel lblChucVu1 = new JLabel("Chức Vụ:");
		lblChucVu1.setToolTipText("Chức vụ");
		lblChucVu1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblChucVu1.setBounds(40, 350, 120, 40);
		pNhapThongTin.add(lblChucVu1);
		
		JLabel lblCa = new JLabel("Ca:");
		lblCa.setToolTipText("Ca");
		lblCa.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblCa.setBounds(40, 395, 120, 40);
		pNhapThongTin.add(lblCa);
		
		JLabel lblThongTinCaNhan = new JLabel("Thông tin cá nhân:");
		lblThongTinCaNhan.setToolTipText("Thông tin cá nhân");
		lblThongTinCaNhan.setHorizontalAlignment(SwingConstants.LEFT);
		lblThongTinCaNhan.setForeground(new Color(28, 28, 28));
		lblThongTinCaNhan.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblThongTinCaNhan.setBounds(20, 467, 189, 40);
		pNhapThongTin.add(lblThongTinCaNhan);
		
		JLabel lblNgaySinh = new JLabel("Ngày Sinh");
		lblNgaySinh.setToolTipText("Ngày sinh");
		lblNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNgaySinh.setBounds(40, 517, 120, 40);
		pNhapThongTin.add(lblNgaySinh);
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setToolTipText("Căn cước công dân");
		lblCCCD.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblCCCD.setBounds(40, 562, 120, 40);
		pNhapThongTin.add(lblCCCD);
		
		JLabel lblGioiTinh = new JLabel("Giới Tính:");
		lblGioiTinh.setToolTipText("Giới tính");
		lblGioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblGioiTinh.setBounds(40, 607, 120, 40);
		pNhapThongTin.add(lblGioiTinh);
		
		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setToolTipText("Địa chỉ");
		lblDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDiaChi.setBounds(40, 652, 120, 40);
		pNhapThongTin.add(lblDiaChi);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(60, 60, 60));
		separator_1_1.setBounds(20, 455, 390, 2);
		pNhapThongTin.add(separator_1_1);
		
		btnChonHinhAnh = new JButton("Choose");
		btnChonHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnChonHinhAnh.setBounds(20, 88, 64, 21);
		btnChonHinhAnh.setVisible(false);
		btnChonHinhAnh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				chooseFile();
			}
		});
		pNhapThongTin.add(btnChonHinhAnh);
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setEditable(false);
		txtTenNhanVien.setToolTipText("Tên nhân viên");
		txtTenNhanVien.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(94, 20, 240, 40);
		txtTenNhanVien.setBorder(null);
		txtTenNhanVien.setBackground(new Color(255, 255, 255));
		pNhapThongTin.add(txtTenNhanVien);

		unfocusable();
	}

	public void loadData(List<NhanVien> nhanViens) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		model.setRowCount(0);
		for (NhanVien nhanVien : nhanViens) {
			Object[] objects = { nhanVien.getMaNhanVien(), nhanVien.getTenNhanVien(),
					nhanVien.getGioiTinh(),
					simpleDateFormat.format(nhanVien.getNgayVaoLam()),
					nhanVien.getSoDienThoai(), nhanVien.getDiaChi() };
			model.addRow(objects);
		}
	}

	private void refresh() throws RemoteException {
		loadData(nhanVien_DAO.getAllNhanVien());
	}

	@SuppressWarnings("deprecation")
	private boolean add() throws RemoteException {
		if (kiemTraRong()) {
			JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin!");
			xoaTrang();
			return false;
		}
		else {
			if (txtTenNhanVien.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống!");
				txtTenNhanVien.requestFocus();
				xoaTrang();
				return false;
			}
			else if (!Regular_expression.validateTen(txtTenNhanVien.getText())) {
				JOptionPane.showMessageDialog(null, "Tên nhân viên không được chứa ký tự số, ký tự đặc biệt, ví dụ: \"Nguyễn Văn A\"!");
				txtTenNhanVien.requestFocus();
				xoaTrang();
				return false;
			}
			else if (lblHinhAnh.getIcon() == null) {
				JOptionPane.showMessageDialog(null, "Hình ảnh nhân viên không được để trống!");
				xoaTrang();
				return false;
			}
			else if (txtSoDienThoai.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Số điện thoại nhân viên không được để trống!");
				txtSoDienThoai.requestFocus();
				xoaTrang();
				return false;
			}
			else if (!Regular_expression.validatePhoneNumber(txtSoDienThoai.getText())) {
				JOptionPane.showMessageDialog(null, "Số điện thoại phải đúng 10 chữ số và không được bắt đầu bằng '02' hoặc '06'!");
				txtSoDienThoai.requestFocus();
				xoaTrang();
				return false;
			}
			else if (txtEmail.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Email nhân viên không được để trống!");
				txtEmail.requestFocus();
				xoaTrang();
				return false;
			}
			else if (!Regular_expression.validateEmail(txtEmail.getText())) {
				JOptionPane.showMessageDialog(null, "Email không hợp lệ! Ví dụ: 'thanh@gmail.com'!");
				txtEmail.requestFocus();
				xoaTrang();
				return false;
			}
			else if (cbChucVu.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Chức vụ nhân viên không được để trống!");
				xoaTrang();
				return false;
			}
			else if (cbCa.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Ca của nhân viên không được để trống!");
				xoaTrang();
				return false;
			}
			else if (dateChooserNgaySinh == null) {
				JOptionPane.showMessageDialog(null, "Ngày sinh của nhân viên không được để trống!");
				xoaTrang();
				return false;
			}
			else if (dateChooserNgaySinh.getDate().after(new java.util.Date()) || ((new java.util.Date().getYear() + 1900) - (dateChooserNgaySinh.getDate().getYear() + 1900) < 18)) {
				JOptionPane.showMessageDialog(null, "Ngày sinh của nhân viên phải trước ngày hiện tại và nhân viên phải đủ 18 tuổi!");
				xoaTrang();
				return false;
			}
			else if (txtcCCD.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Căn cước công dân của nhân viên không được để trống!");
				txtcCCD.requestFocus();
				xoaTrang();
				return false;
			}
			else if (!Regular_expression.validateCCCD(txtcCCD.getText())) {
				JOptionPane.showMessageDialog(null, "Căn cước công dân phải chứa 12 ký tự số!");
				txtcCCD.requestFocus();
				xoaTrang();
				return false;
			}
			else if (cbGioiTinh.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Giới tính của nhân viên không được để trống!");
				xoaTrang();
				return false;
			}
			else if (txtDiaChi.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Địa chỉ nhân viên không được để trống!");
				txtDiaChi.requestFocus();
				xoaTrang();
				return false;
			}
			else if (!Regular_expression.validateDiaChi(txtDiaChi.getText())) {
				JOptionPane.showMessageDialog(null, "Địa chỉ nhân viên không được chứa ký tự đặc biệt!");
				txtDiaChi.requestFocus();
				xoaTrang();
				return false;
			}
			else {
				// Thêm tài khoản mới cho nhân viên mới
				TaiKhoan taiKhoan = new TaiKhoan(txtSoDienThoai.getText());
				taiKhoan_DAO.themTaiKhoan(taiKhoan);

				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNhanVien(taiKhoan.getTaiKhoan());
				nhanVien.setTenNhanVien(txtTenNhanVien.getText());
				nhanVien.setDiaChi(txtDiaChi.getText());
				nhanVien.setGioiTinh(cbGioiTinh.getSelectedItem().toString());
				nhanVien.setNgaySinh(new Date(dateChooserNgaySinh.getDate().getTime()));
				nhanVien.setNgayVaoLam(new Date(new java.util.Date().getTime()));
				nhanVien.setcCCD(txtcCCD.getText());
				nhanVien.setEmail(txtEmail.getText());
				nhanVien.setSoDienThoai(txtSoDienThoai.getText());
				nhanVien.setChucVu(cbChucVu.getSelectedItem().toString());
				nhanVien.setTaiKhoan(taiKhoan);
				nhanVien.setCa(new Ca("C" + cbCa.getSelectedItem()));
				nhanVien.setHinhAnh(relativePath);
				nhanVien_DAO.themNhanVien(nhanVien);
				JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
				refresh();
				return true;
			}

		}
	}
	
	private boolean kiemTraRong() {
		if (txtTenNhanVien.getText().equals("") &&
				lblHinhAnh.getIcon() == null &&
				txtSoDienThoai.getText().equals("") &&
				txtEmail.getText().equals("") &&
				cbChucVu.getSelectedIndex() == -1 &&
				cbCa.getSelectedIndex() == -1 &&
				dateChooserNgaySinh == null &&
				txtcCCD.getText().equals("") &&
				cbGioiTinh.getSelectedIndex() == -1 &&
				txtDiaChi.getText().equals("")) {
			return true;
		}
		return false;
	}
	
	private boolean delete() throws RemoteException {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showInternalMessageDialog(null, "Bạn phải chọn nhân viên cần xóa!");
			return false;
		} else {
			int option = JOptionPane.showConfirmDialog(null,
					"Bạn có chắc muốn xóa nhân viên '" + model.getValueAt(row, 0) + "' chứ?", "Xóa?",
					JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				String maTaiKhoan = nhanVien_DAO.getNhanVienTheoMa((String) model.getValueAt(row, 0))
						.getTaiKhoan().getTaiKhoan();
				try {
					nhanVien_DAO.xoaNhanVienTheoMa(model.getValueAt(row, 0).toString());
					taiKhoan_DAO.xoaTaiKhoan(maTaiKhoan);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Không được xóa nhân viên này. Bởi vì sẽ mất toàn bộ dữ liệu hóa đơn và phiếu đặt của nhân viên này!");
					return false;
				}
				JOptionPane.showMessageDialog(null,
						"Xóa nhân viên '" + model.getValueAt(row, 0) + "' thành công!");
				refresh();
				return true;
			} else {
				return false;
			}
		}
	}
	@SuppressWarnings("deprecation")
	private boolean update() throws RemoteException {
		if (kiemTraRong()) {
			JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin!");
			return false;
		}
		else {
			if (txtTenNhanVien.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống!");
				txtTenNhanVien.requestFocus();
				return false;
			}
			else if (!Regular_expression.validateTen(txtTenNhanVien.getText())) {
				JOptionPane.showMessageDialog(null, "Tên nhân viên không được chứa ký tự số, ký tự đặc biệt, ví dụ: \"Nguyễn Văn A\"!");
				txtTenNhanVien.requestFocus();
				return false;
			}
			else if (lblHinhAnh.getIcon() == null) {
				JOptionPane.showMessageDialog(null, "Hình ảnh nhân viên không được để trống!");
				return false;
			}
			else if (txtSoDienThoai.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Số điện thoại nhân viên không được để trống!");
				txtSoDienThoai.requestFocus();
				return false;
			}
			else if (!Regular_expression.validatePhoneNumber(txtSoDienThoai.getText())) {
				JOptionPane.showMessageDialog(null, "Số điện thoại phải đúng 10 chữ số và không được bắt đầu bằng '02' hoặc '06'!");
				txtSoDienThoai.requestFocus();
				return false;
			}
			else if (txtEmail.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Email nhân viên không được để trống!");
				txtEmail.requestFocus();
				return false;
			}
			else if (!Regular_expression.validateEmail(txtEmail.getText())) {
				JOptionPane.showMessageDialog(null, "Email không hợp lệ! Ví dụ: 'thanh@gmail.com'!");
				txtEmail.requestFocus();
				return false;
			}
			else if (cbChucVu.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Chức vụ nhân viên không được để trống!");
				return false;
			}
			else if (cbCa.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Ca của nhân viên không được để trống!");
				return false;
			}
			else if (dateChooserNgaySinh == null) {
				JOptionPane.showMessageDialog(null, "Ngày sinh của nhân viên không được để trống!");
				return false;
			}
			else if (dateChooserNgaySinh.getDate().after(new java.util.Date()) || ((new java.util.Date().getYear() + 1900) - (dateChooserNgaySinh.getDate().getYear() + 1900) < 18)) {
				JOptionPane.showMessageDialog(null, "Ngày sinh của nhân viên phải trước ngày hiện tại và nhân viên phải đủ 18 tuổi!");
				return false;
			}
			else if (txtcCCD.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Căn cước công dân của nhân viên không được để trống!");
				txtcCCD.requestFocus();
				return false;
			}
			else if (!Regular_expression.validateCCCD(txtcCCD.getText())) {
				JOptionPane.showMessageDialog(null, "Căn cước công dân phải chứa 12 ký tự số!");
				txtcCCD.requestFocus();
				return false;
			}
			else if (cbGioiTinh.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Giới tính của nhân viên không được để trống!");
				return false;
			}
			else if (txtDiaChi.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Địa chỉ nhân viên không được để trống!");
				txtDiaChi.requestFocus();
				return false;
			}
			else if (!Regular_expression.validateDiaChi(txtDiaChi.getText())) {
				JOptionPane.showMessageDialog(null, "Địa chỉ nhân viên không được chứa ký tự đặc biệt!");
				txtDiaChi.requestFocus();
				return false;
			}
			else {
				int row = table.getSelectedRow();
				int option = JOptionPane.showConfirmDialog(null,
						"Bạn có chắc muốn sửa nhân viên '" + model.getValueAt(row, 0) + "' chứ?", "Sửa?",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					TaiKhoan taiKhoan = nhanVien_DAO.getNhanVienTheoMa(model.getValueAt(row, 0).toString())
							.getTaiKhoan();
					Date ngayVaoLam = nhanVien_DAO.getNhanVienTheoMa(model.getValueAt(row, 0).toString())
							.getNgayVaoLam();
					Date ngaySinh = nhanVien_DAO.getNhanVienTheoMa(model.getValueAt(row, 0).toString()).getNgaySinh();
					NhanVien nhanVien = new NhanVien(lblMaNhanVienValue.getText(), txtTenNhanVien.getText(), txtDiaChi.getText(),
						cbGioiTinh.getSelectedItem().toString(), ngaySinh, ngayVaoLam, txtcCCD.getText(),
								txtEmail.getText(), txtSoDienThoai.getText(), cbChucVu.getSelectedItem().toString(),
								taiKhoan, new Ca("C" + cbCa.getSelectedItem()), relativePath);
					nhanVien_DAO.suaNhanVien(nhanVien);
					JOptionPane.showMessageDialog(null, "Sửa thành công nhân viên '" + model.getValueAt(row, 0) + "'!");
					refresh();
					return true;
				} else {
					return false;
				}
			}
		}
	}
	private boolean chooseFile() { 
		fileChooser = new JFileChooser();
		
		// Chỉ cho phép chọn tệp hình ảnh
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png");
		fileChooser.setFileFilter(filter);
		
		// Thiết lập thư mục mặc định khi mở
		File defaultDirectory = new File(System.getProperty("user.dir") + "/data/image");
		fileChooser.setCurrentDirectory(defaultDirectory);
		
		// Chọn file
		int returnValue = fileChooser.showOpenDialog(null);
		selectedFile = fileChooser.getSelectedFile();
		
		try {
			String absolutePath = selectedFile.getAbsolutePath();
			absolutePath = absolutePath.replace("\\", "/");
			// cắt chuỗi từ /image về sau
			relativePath = absolutePath.substring(absolutePath.indexOf("/image"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn file!");
		}
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			// Nhấn Open file
			lblHinhAnh.setIcon(new ImageIcon(NhanVien_GUI.class.getResource(relativePath)));
			return true;
		}
		else // JFileChooser.CANCEL_OPTION
			return false;
	}
	
	private void enableEdit() { 
		txtTenNhanVien.setEditable(true);
		lblChucVu.setVisible(false);
		btnChonHinhAnh.setVisible(true);
		txtSoDienThoai.setEditable(true);
		txtSoDienThoai.setBorder(borderDefault);
		txtEmail.setEditable(true);
		txtEmail.setBorder(borderDefault);
		cbChucVu.setEnabled(true);
		cbChucVu.setBorder(borderDefault);
//		cbChucVu.setLayout(layoutDefaultCombobox);
		cbCa.setEnabled(true);
		cbCa.setBorder(borderDefault);
//		cbCa.setLayout(layoutDefaultCombobox);
		txtcCCD.setEditable(true);
		txtcCCD.setBorder(borderDefault);
		cbGioiTinh.setEnabled(true);
		cbGioiTinh.setBorder(borderDefault);
//		cbGioiTinh.setLayout(layoutDefaultCombobox);
		txtDiaChi.setEditable(true);
		txtDiaChi.setBorder(borderDefault);
		dateChooserNgaySinh.getDateEditor().setEnabled(true);
	}
	
	private void disableEdit() {
		txtTenNhanVien.setEditable(false); 
		txtTenNhanVien.setBorder(null); 
		txtSoDienThoai.setEditable(false); 
		txtSoDienThoai.setBorder(null); 
		txtEmail.setEditable(false); 
		txtEmail.setBorder(null);
		cbChucVu.setEnabled(false);
		cbChucVu.setBorder(null);
//		cbChucVu.setLayout(null);
		cbCa.setEnabled(false);
		cbCa.setBorder(null);
//		cbCa.setLayout(null);
		txtcCCD.setEditable(false); 
		txtcCCD.setBorder(null);
		cbGioiTinh.setEnabled(false);
		cbGioiTinh.setBorder(null);
//		cbGioiTinh.setLayout(null);
		txtDiaChi.setEditable(false); 
		txtDiaChi.setBorder(null);
		btnChonHinhAnh.setVisible(false);
		lblChucVu.setVisible(true);
		dateChooserNgaySinh.getDateEditor().setEnabled(false);
	}
	
	private void enableButton() {
		btnLamMoi.setEnabled(true);
		btnThem.setEnabled(true);
		btnXoa.setEnabled(true);
		btnSua.setEnabled(true);
		btnTim.setEnabled(true);
	}
	
	private void disableButton() {
		btnLamMoi.setEnabled(false);
		btnThem.setEnabled(false);
		btnXoa.setEnabled(false);
		btnSua.setEnabled(false);
		btnTim.setEnabled(false);
	}
	
	private void unfocusable() {
		txtTenNhanVien.setFocusable(false);
		txtSoDienThoai.setFocusable(false);
		txtEmail.setFocusable(false);
		txtcCCD.setFocusable(false);
		txtDiaChi.setFocusable(false);
	}
	
	private void focusable() {
		txtTenNhanVien.setFocusable(true);
		txtSoDienThoai.setFocusable(true);
		txtEmail.setFocusable(true);
		txtcCCD.setFocusable(true);
		txtDiaChi.setFocusable(true);
	}
	
	private void xoaTrang() {
		txtTenNhanVien.setText("");
		txtSoDienThoai.setText("");
		txtEmail.setText("");
		lblMaNhanVienValue.setText("");
		cbChucVu.setSelectedIndex(-1);
		cbCa.setSelectedIndex(-1);
		dateChooserNgaySinh.setDate(null);
		txtcCCD.setText("");
		cbGioiTinh.setSelectedIndex(-1);
		txtDiaChi.setText("");
	}
}
