package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.impl.Sach_Impl;
import entity.Sach;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class KhoiPhucSach_GUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel model;
	private JPanel pMain;
	private JScrollPane scrollPaneSach;
	private JButton btnKhoiPhuc;
	private JButton btnXoa ;
	private JButton btnquayLai;
	private JTable table;
	
	private List<Sach> ds;
	
	private Sach_Impl sach_DAO;

	private static final String URL = "rmi://PhamVanThanh:9891/";
	public KhoiPhucSach_GUI(List<Sach> ds) throws RemoteException, MalformedURLException, NotBoundException {
		this.ds = ds;
		sach_DAO = (Sach_Impl) Naming.lookup(URL + "sachDAO");
		
		setBounds(100, 100, 1199, 506);
		getContentPane().setLayout(null);
		
		pMain = new JPanel();
		pMain.setLayout(null);
		pMain.setBounds(10, 10, 1152, 465);
		
		getContentPane().add(pMain);
		
		scrollPaneSach = new JScrollPane();
		scrollPaneSach.setBorder(new LineBorder(new Color(80, 80, 80), 1, true));
		scrollPaneSach.setBackground(new Color(80, 80, 80));
		scrollPaneSach.setBounds(20, 0, 1132, 374);
		
		String cols[] = { "Mã Sách", "Tên Sách", "Xuất Xứ", "Giá Nhập", "Giá Bán", "Số Lượng Tồn", "Nhà Xuất Bản",
				"Thể Loại Sách", "Tác Giả", "Số Trang", "Năm Xuất Bản" };
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setToolTipText("Chọn vào sách cần hiển thị thông tin");
		table.setRowHeight(30);
		table.setDefaultEditor(Object.class, null);
		table.setShowGrid(true);
		table.setShowHorizontalLines(true);
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(141, 208, 229));
		table.setSelectionForeground(new Color(0, 0, 0));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		scrollPaneSach.setViewportView(table);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
		
		pMain.add(scrollPaneSach);
		
		btnKhoiPhuc = new JButton("Khôi Phục");
		btnKhoiPhuc.setOpaque(true);
		btnKhoiPhuc.setForeground(Color.WHITE);
		btnKhoiPhuc.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnKhoiPhuc.setBackground(new Color(73, 129, 158));
		btnKhoiPhuc.setBounds(507, 399, 135, 40);
		btnKhoiPhuc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				try {
					sach_DAO.khoiPhucSach((String) model.getValueAt(row, 0));
					System.out.println((String) model.getValueAt(row, 0));
					loadData(sach_DAO.getAllSachXoa());
				} catch (RemoteException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		pMain.add(btnKhoiPhuc);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnXoa.setBackground(new Color(73, 129, 158));
		btnXoa.setBounds(828, 399, 135, 40);
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm cần xóa!");
				} else {
					int tb = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa sản phẩm?", "Delete",
							JOptionPane.YES_NO_OPTION);
					if (tb == JOptionPane.YES_OPTION) {
						try {
							sach_DAO.xoaSachTheoMaSach((String) model.getValueAt(row, 0));
							loadData(sach_DAO.getAllSachXoa());
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}

				}

			}
		});
		pMain.add(btnXoa);
		
		btnquayLai = new JButton("← Quay Lại");
		btnquayLai.setForeground(Color.WHITE);
		btnquayLai.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnquayLai.setBackground(new Color(73, 129, 158));
		btnquayLai.setBounds(186, 399, 135, 40);
		btnquayLai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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

		});
		pMain.add(btnquayLai);
		loadData(sach_DAO.getAllSachXoa());
		
	}
	
	// load Data lên bảng
		public void loadData(List<Sach> ds) {
			model.setRowCount(0);
			for (Sach sach : ds) {
				Object[] object = { sach.getMaSanPham(), 
						sach.getTenSanPham(), 
						sach.getXuatXu(),
						sach.getGiaNhap(), 
						sach.getGiaBan(), 
						sach.getSoLuongTon(),
						sach.getNhaXuatBan().getTenNhaXuatBan(),
						sach.getTheLoaiSach().getTenTheLoaiSach(),
						sach.getTacGia(), 
						sach.getSoTrang(), 
						sach.getNamXuatBan() };
				model.addRow(object);
			}
		}
	private void loadALL() throws RemoteException {
		model.setRowCount(0);
		
		for (Sach sach : sach_DAO.getAllSach()) {
			ds.add(sach);
		}
	}
}
