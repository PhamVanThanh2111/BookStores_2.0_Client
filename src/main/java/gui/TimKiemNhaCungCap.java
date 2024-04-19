package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.impl.NhaCungCap_Impl;
import entity.NhaCungCap;

public class TimKiemNhaCungCap extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNhaCungCap;
	private JTextField txtTenNhaCungCap;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private NhaCungCap_Impl nhaCungCap_Dao;
	private ArrayList<NhaCungCap> ds;
	
	private static final String URL = "rmi://PhamVanThanh:9891/";
	
	public TimKiemNhaCungCap(ArrayList<NhaCungCap>ds) throws RemoteException, MalformedURLException, NotBoundException {
		nhaCungCap_Dao = (NhaCungCap_Impl) Naming.lookup(URL + "nhaCungCapDAO");
		this.ds = ds;
		setBounds(100, 100, 910, 350);
		getContentPane().setLayout(null);
		
		JButton btnquayLai = new JButton("← Quay Lại");
		btnquayLai.setBounds(10, 10, 135, 40);
		btnquayLai.setForeground(Color.WHITE);
		btnquayLai.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnquayLai.setBackground(new Color(73, 129, 158));
		getContentPane().add(btnquayLai);
		
		JLabel lblMaNCC = new JLabel("Mã Nhà Cung Cấp:");
		lblMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaNCC.setBounds(70, 80, 161, 40);
		getContentPane().add(lblMaNCC);
		
		txtMaNhaCungCap = new JTextField();
		txtMaNhaCungCap.setColumns(10);
		txtMaNhaCungCap.setBounds(230, 80, 240, 40);
		getContentPane().add(txtMaNhaCungCap);
		
		txtTenNhaCungCap = new JTextField();
		txtTenNhaCungCap.setColumns(10);
		txtTenNhaCungCap.setBounds(230, 140, 240, 40);
		getContentPane().add(txtTenNhaCungCap);
		
		JLabel lblTenNhaCungCap = new JLabel("Tên Nhà Cung Cấp:");
		lblTenNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenNhaCungCap.setBounds(70, 140, 161, 40);
		getContentPane().add(lblTenNhaCungCap);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(70, 200, 127, 40);
		getContentPane().add(lblEmail);
		
		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiaChi.setBounds(520, 200, 74, 40);
		getContentPane().add(lblDiaChi);
		
		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSDT.setBounds(520, 140, 135, 40);
		getContentPane().add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(600, 140, 240, 40);
		getContentPane().add(txtSDT);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(600, 200, 240, 40);
		getContentPane().add(txtDiaChi);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBackground(new Color(73, 129, 158));
		btnTim.setBounds(750, 270, 135, 40);
		getContentPane().add(btnTim);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(230, 200, 240, 40);
		getContentPane().add(txtEmail);
		
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					searchNhaCungCap();
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
		
		btnquayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnquayLai)) {
					try {
						loadALL();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					try {
						setClosed(true);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	public void searchNhaCungCap() throws RemoteException {
		for (NhaCungCap nhacc: nhaCungCap_Dao.getAllNhaCungCap()) {
			boolean thoaMan = false;
			if (!txtMaNhaCungCap.getText().isEmpty()) {
				if (nhacc.getMaNCC().equalsIgnoreCase(txtMaNhaCungCap.getText().trim())) {
					thoaMan = true;
				}
			}
			if (!txtTenNhaCungCap.getText().isEmpty()) {
				if (nhacc.getTenNCC().toLowerCase().contains(txtTenNhaCungCap.getText().toLowerCase().trim())) {
					thoaMan = true;
				}
			}
			if (!txtDiaChi.getText().isEmpty()) {
				if (nhacc.getDiaChi().toLowerCase().contains(txtDiaChi.getText().toLowerCase().trim())) {
					thoaMan = true;
				}
			}
			if (!txtSDT.getText().isEmpty()) {
				if (nhacc.getSoDienThoai().toLowerCase().contains(txtSDT.getText().toLowerCase().trim())) {
					thoaMan = true;
				}
			}
			if (!txtEmail.getText().isEmpty()) {
				if (nhacc.getEmail().toLowerCase().contains(txtEmail.getText().toLowerCase().trim())) {
					thoaMan = true;
				}
			}
			if (thoaMan) {
				ds.add(nhacc);
			}
		}
	}
	
	public void loadALL() throws RemoteException {
		for(NhaCungCap nhaCC : nhaCungCap_Dao.getAllNhaCungCap()) {
			ds.add(nhaCC);
		}
	}
	
}
