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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.impl.TheLoaiSach_Impl;
import entity.TheLoaiSach;

public class TimKiemTheLoaiSach_GUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaTheLoaiSach;
	private JTextField txtTenTheLoaiSach;
	private JButton btnTim;
	
	private TheLoaiSach_Impl theLoaiSach_DAO;
	private ArrayList<TheLoaiSach> ds;

	private static final String URL = "rmi://PhamVanThanh:9891/";
	
	public TimKiemTheLoaiSach_GUI(ArrayList<TheLoaiSach> ds) throws RemoteException, MalformedURLException, NotBoundException {

		// khai bao DAO
		theLoaiSach_DAO = (TheLoaiSach_Impl) Naming.lookup(URL + "theLoaiSachDAO");
		this.ds = ds;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 330);
		getContentPane().setLayout(null);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 853, 405);
		getContentPane().add(contentPane);

		JLabel lblMaTheLoaiSach = new JLabel("Mã Thể loại sách:");
		lblMaTheLoaiSach.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaTheLoaiSach.setBounds(132, 87, 217, 40);
		contentPane.add(lblMaTheLoaiSach);

		JLabel lblEmail = new JLabel("Tên thể loại sách:");
		lblEmail.setToolTipText("Tên của thể loại sách cần tìm");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(132, 145, 160, 40);
		contentPane.add(lblEmail);

		txtTenTheLoaiSach = new JTextField();
		txtTenTheLoaiSach.setToolTipText("Nhập vào tên của thể loại sách cần tìm");
		txtTenTheLoaiSach.setColumns(10);
		txtTenTheLoaiSach.setBounds(309, 145, 390, 40);
		txtTenTheLoaiSach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnTim.doClick();
				}
			}
		});
		contentPane.add(txtTenTheLoaiSach);

		txtMaTheLoaiSach = new JTextField();
		txtMaTheLoaiSach.setToolTipText("Nhập vào mã thể loại sách cần tìm");
		txtMaTheLoaiSach.setColumns(10);
		txtMaTheLoaiSach.setBounds(309, 91, 390, 40);
		txtMaTheLoaiSach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnTim.doClick();
				}
			}
		});
		contentPane.add(txtMaTheLoaiSach);

		JButton btnQuayLai = new JButton("← Quay Lại");
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnQuayLai.setBackground(new Color(73, 129, 158));
		btnQuayLai.setBounds(10, 10, 135, 40);
		btnQuayLai.addActionListener(new ActionListener() {

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
		
		contentPane.add(btnQuayLai);
		btnTim = new JButton("Tìm");
		btnTim.setMnemonic(KeyEvent.VK_ENTER);
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBackground(new Color(73, 129, 158));
		btnTim.setBounds(364, 199, 135, 40);
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (! kiemTraDuLieuRong()) {
					try {
						searchTheLoaiSach();
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
		contentPane.add(btnTim);
	}
	private void searchTheLoaiSach() throws RemoteException {
		// TODO Auto-generated method stub
		for (TheLoaiSach theLoaiSach : theLoaiSach_DAO.getAllTheLoaiSach()) {
			boolean thoaMan = false;
			if (!txtMaTheLoaiSach.getText().isEmpty()) {
				if (theLoaiSach.getMaTheLoaiSach().equalsIgnoreCase(txtMaTheLoaiSach.getText().trim())) {
					thoaMan = true;
				}
			}
			if (!txtTenTheLoaiSach.getText().isEmpty()) {
				if (theLoaiSach.getTenTheLoaiSach().toLowerCase()
						.contains(txtTenTheLoaiSach.getText().toLowerCase().trim())) {
					thoaMan = true;
				}
			}
			if (thoaMan) {
				ds.add(theLoaiSach);
			}
		}
	}

	 private boolean kiemTraDuLieuRong() {
	        if (txtMaTheLoaiSach.getText().isEmpty() && txtTenTheLoaiSach.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Bạn phải điền ít nhất một thông tin!");
	            return true;
	        }
	        return false;
	    }
    private void loadAll() throws RemoteException {
        if (theLoaiSach_DAO.getAllTheLoaiSach() != null) {
            for (TheLoaiSach theLoaiSach : theLoaiSach_DAO.getAllTheLoaiSach()) {
                ds.add(theLoaiSach);
            }
        }
    }
    @SuppressWarnings("unused")
	private void dongCuaSoNoiTai() {
        try {
            setClosed(true);
        } catch (PropertyVetoException e1) {
            JOptionPane.showMessageDialog(null, "Lỗi khi đóng cửa sổ nội tại: " + e1.getMessage());
        }
    }

}
