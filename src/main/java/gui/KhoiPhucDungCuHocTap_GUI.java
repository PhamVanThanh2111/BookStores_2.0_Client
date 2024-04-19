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
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.impl.DungCuHocTap_Impl;
import entity.DungCuHocTap;

public class KhoiPhucDungCuHocTap_GUI extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private JTableHeader tableHeader;
	private JButton btnXoa, btnKhoiPhuc;
	private List<DungCuHocTap> ds;
	private JButton btnquayLai;
	
	private DungCuHocTap_Impl dungCuHocTap_DAO;

	private static final String URL = "rmi://PhamVanThanh:9891/";
	public KhoiPhucDungCuHocTap_GUI(List<DungCuHocTap> ds) throws RemoteException, MalformedURLException, NotBoundException {
		this.ds = ds;
		
		dungCuHocTap_DAO = (DungCuHocTap_Impl) Naming.lookup(URL + "dungCuHocTapDAO");
		
		setBounds(100, 100, 882, 495);
		getContentPane().setLayout(null);

		JPanel pMain = new JPanel();
		pMain.setBounds(0, 0, 870, 465);
		getContentPane().add(pMain);
		pMain.setLayout(null);
		JScrollPane scrollPaneNXB = new JScrollPane();
		scrollPaneNXB.setToolTipText("Chọn vào nhà xuất bản cần hiển thị thông tin");
		scrollPaneNXB.setBorder(new LineBorder(new Color(80, 80, 80), 1, true));
		scrollPaneNXB.setBackground(new Color(80, 80, 80));
		scrollPaneNXB.setBounds(20, 0, 830, 374);

		String[] cols = { "Mã Dụng Cụ Học Tập", "Tên Dụng Cụ Học Tập", "Xuất Xứ", "Giá Nhập", "Giá Bán", "Số Lượng Tồn",
				"Nhà Cung Cấp" };
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);

		table.setRowHeight(25);
		table.setSelectionBackground(new Color(141, 208, 229));
		table.setSelectionForeground(new Color(0, 0, 0));
		table.setDefaultEditor(Object.class, null);
		tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(73, 129, 158));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		tableHeader.setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneNXB.setViewportView(table);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

		pMain.add(scrollPaneNXB);

		btnKhoiPhuc = new JButton("Khôi Phục");
		btnKhoiPhuc.setOpaque(true);
		btnKhoiPhuc.setForeground(Color.WHITE);
		btnKhoiPhuc.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnKhoiPhuc.setBackground(new Color(73, 129, 158));
		btnKhoiPhuc.setBounds(562, 399, 135, 40);
		btnKhoiPhuc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				DungCuHocTap dungCuHocTap = new DungCuHocTap();
				dungCuHocTap.setMaSanPham((String) model.getValueAt(row, 0));
				try {
					dungCuHocTap_DAO.khoiPhucDungCuHocTap((String) model.getValueAt(row, 0));
					loadData(dungCuHocTap_DAO.getAllDungCuHocTapXoa());
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
		btnXoa.setBounds(715, 399, 135, 40);
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn dụng cụ học tập cần xóa!");
				} else {
					int tb = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dụng cụ học tập này?", "Delete",
							JOptionPane.YES_NO_OPTION);
					if (tb == JOptionPane.YES_OPTION) {
						try {
							dungCuHocTap_DAO.xoaDungCuHocTapTheoMa((String) model.getValueAt(row, 0));
							loadData(dungCuHocTap_DAO.getAllDungCuHocTapXoa());
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
		btnquayLai.setBounds(409, 399, 135, 40);
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
		loadData(dungCuHocTap_DAO.getAllDungCuHocTapXoa());
	}

	private void loadData(List<DungCuHocTap> ds) {
		model.setRowCount(0);
		for (DungCuHocTap dungCuHocTap : ds) {
			Object[] o = { dungCuHocTap.getMaSanPham(), 
					dungCuHocTap.getTenSanPham(), 
					dungCuHocTap.getXuatXu(), 
					dungCuHocTap.getGiaNhap(),
					dungCuHocTap.getGiaBan(), 
					dungCuHocTap.getSoLuongTon(),
					dungCuHocTap.getNhaCungCap().getTenNCC() };
			model.addRow(o);
		}
	}

	private void loadALL() throws RemoteException {
		model.setRowCount(0);
		for (DungCuHocTap dungCuHocTap : dungCuHocTap_DAO.getAllDungCuHocTap()) {
			ds.add(dungCuHocTap);
		}
	}
}
