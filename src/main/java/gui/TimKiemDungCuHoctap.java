package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.impl.DungCuHocTap_Impl;
import dao.impl.NhaCungCap_Impl;
import entity.DungCuHocTap;
import entity.NhaCungCap;

public class TimKiemDungCuHoctap extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaDCHT;
	private JTextField txtTenDCHT;
	private JTextField txtGiaBan;
	private JTextField txtSoLuong;
	private JButton btnTim;
	private JComboBox<String> cbNhaCC;
	private List<DungCuHocTap> ds;
	
	private NhaCungCap_Impl nhaCungCap_DAO;
	private DungCuHocTap_Impl dungCuHocTap_DAO;
	
	private static final String URL = "rmi://PhamVanThanh:9891/";
	
	public TimKiemDungCuHoctap(List<DungCuHocTap> ds) throws RemoteException, MalformedURLException, NotBoundException {
		dungCuHocTap_DAO = (DungCuHocTap_Impl) Naming.lookup(URL + "dungCuHocTapDAO");
		nhaCungCap_DAO = (NhaCungCap_Impl) Naming.lookup(URL + "nhaCungCapDAO");
		
		this.ds = ds;
		setBounds(100, 100, 922, 415);
		getContentPane().setLayout(null);
		
		txtMaDCHT = new JTextField();
		txtMaDCHT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnTim.doClick();
				}
			}
		});
		txtMaDCHT.setColumns(10);
		txtMaDCHT.setBounds(220, 80, 240, 40);
		getContentPane().add(txtMaDCHT);
		
		JButton btnquayLai = new JButton("← Quay Lại");
		btnquayLai.setForeground(Color.WHITE);
		btnquayLai.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnquayLai.setBackground(new Color(73, 129, 158));
		btnquayLai.setBounds(10, 10, 135, 40);
		btnquayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loadAll();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				try {
					setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(btnquayLai);
		
		JLabel lblMaDCHT = new JLabel("Mã DCHT:");
		lblMaDCHT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaDCHT.setBounds(70, 80, 140, 40);
		getContentPane().add(lblMaDCHT);
		
		JLabel lblTnDcht = new JLabel("Tên DCHT:");
		lblTnDcht.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTnDcht.setBounds(70, 140, 161, 40);
		getContentPane().add(lblTnDcht);
		
		txtTenDCHT = new JTextField();
		txtTenDCHT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnTim.doClick();
				}
			}
		});
		txtTenDCHT.setColumns(10);
		txtTenDCHT.setBounds(220, 140, 240, 40);
		getContentPane().add(txtTenDCHT);
		
		cbNhaCC = new JComboBox<String>();
		cbNhaCC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnTim.doClick();
				}
			}
		});
		cbNhaCC.setSelectedIndex(-1);
		cbNhaCC.setBounds(220, 200, 240, 40);
		loadCBNCC();
		cbNhaCC.setSelectedIndex(-1);
		getContentPane().add(cbNhaCC);
		
		
		JLabel lblNhaCungCap = new JLabel("Nhà Cung Cấp:");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNhaCungCap.setBounds(70, 200, 127, 40);
		getContentPane().add(lblNhaCungCap);
		
		JLabel lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoLuong.setBounds(520, 200, 83, 40);
		getContentPane().add(lblSoLuong);
		
		JLabel lblGiaBan = new JLabel("Giá Bán:");
		lblGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGiaBan.setBounds(520, 140, 105, 40);
		getContentPane().add(lblGiaBan);
		
		txtGiaBan = new JTextField();
		txtGiaBan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnTim.doClick();
				}
			}
		});
		txtGiaBan.setColumns(10);
		txtGiaBan.setBounds(610, 140, 240, 40);
		getContentPane().add(txtGiaBan);
		
		txtSoLuong = new JTextField();
		txtSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnTim.doClick();
				}
			}
		});
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(610, 200, 240, 40);
		getContentPane().add(txtSoLuong);
		
		btnTim = new JButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBackground(new Color(73, 129, 158));
		btnTim.setBounds(750, 270, 135, 40);
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchDungCuHocTap();
				try {
					setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		} );
		getContentPane().add(btnTim);
		
	}
	
	public void searchDungCuHocTap() {
		try {
			for (DungCuHocTap dungCuHocTap: dungCuHocTap_DAO.getAllDungCuHocTap()) {
				boolean thoaMan = false;
				if (!txtMaDCHT.getText().isEmpty()) {
					if (dungCuHocTap.getMaSanPham().equalsIgnoreCase(txtMaDCHT.getText().trim())) {
						thoaMan = true;
					}
				}
				if (!txtTenDCHT.getText().isEmpty()) {
					if (dungCuHocTap.getTenSanPham().toLowerCase().contains(dinhDangChuoi(txtTenDCHT.getText().toLowerCase().trim()))) {
						thoaMan = true;
					}
				}
				if (!txtGiaBan.getText().isEmpty()) {
					if (dungCuHocTap.getGiaBan()==Integer.parseInt(dinhDangChuoi(txtGiaBan.getText().trim()))) {
						thoaMan = true;
					}
				}
				if (!txtSoLuong.getText().isEmpty()) {
					if (dungCuHocTap.getSoLuongTon()==Integer.parseInt(dinhDangChuoi(txtSoLuong.getText().trim()))) {
						thoaMan = true;
					}
				}
			
				
				if (cbNhaCC.getSelectedIndex()!=-1) {
//				NhaCungCap tenNhaCC= nhaCungCap_DAO.getNhaCCTheoMa(sanPham.getMaNhaCungCap());
//				if (tenNhaCC.getTenNCC().toLowerCase().equalsIgnoreCase(cbNhaCC.getSelectedItem().toString().toLowerCase())) {
//					thoaMan = true;
//				}
				}
				if (thoaMan) {
					ds.add(dungCuHocTap);	
				}
			}
		} catch (NumberFormatException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadCBNCC() throws RemoteException {
		for(NhaCungCap nhaCungCap : nhaCungCap_DAO.getAllNhaCungCap()) {
			cbNhaCC.addItem(nhaCungCap.getTenNCC());
		}
	}
	public void loadAll() throws RemoteException {
		for (DungCuHocTap dungCuHocTap : dungCuHocTap_DAO.getAllDungCuHocTap()) {
			ds.add(dungCuHocTap);
		}
	}
	public String dinhDangChuoi(String chuoi) {
		String s ="";
		s=chuoi;
		s=s.replaceAll("\\s+", " ");
		return s;
	}
}
