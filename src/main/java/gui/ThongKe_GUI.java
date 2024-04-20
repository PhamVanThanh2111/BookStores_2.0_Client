package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entity.HoaDon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.impl.HoaDon_Impl;
import dao.impl.KhachHang_Impl;
import dao.impl.NhanVien_Impl;
import dao.impl.SanPham_Impl;
import entity.SanPham;

public class ThongKe_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultCategoryDataset datasetDoanhThu;
	private JFreeChart chartDoanhThu;
	private CategoryPlot categoryDoanhThu;
	private ChartPanel chartPanelDoanhThu;
	private DefaultCategoryDataset datasetSoLuong;
	private JFreeChart chartSoLuong;
	private CategoryPlot categorySoLuong;
	private ChartPanel chartPanelSoLuong;
	
	private HoaDon_Impl hoaDon_DAO;
	private SanPham_Impl sanPham_DAO;
	private NhanVien_Impl nhanVien_DAO;
	private KhachHang_Impl khachHang_DAO;
	
	private JPanel pnlThongKeDoanhThu;
	private JPanel pnlThongKeSoLuong;
	private JPanel pnlChucNang;
	private JPanel pnlSoLuongTonKho;
	private JPanel pnlDoanhThu;
	private JPanel pnlSanPhamBanChay;
	private JPanel pnlNangSuatNhanVien;
	private JLabel lblTuNgay;
	private JLabel lblDenNgay;
	private JLabel lblDanhSachSanPham;
	private JDateChooser dateChooserTuNgay;
	private JDateChooser dateChooserDenNgay;
	private JFreeChart chartSanPhamBanChay;
	private CategoryPlot categorySanPhamBanChay;
	private DefaultCategoryDataset datasetSanPhamBanChay;
	private ChartPanel chartPanelSanPhamBanChay;
	private JPanel pnlKhachHangMuaNhieuNhat;
	private JFreeChart chartKhachHangMuaNhieuNhat;
	private CategoryPlot categoryKhachHangMuaNhieuNhat;
	private DefaultCategoryDataset datasetKhachHangMuaNhieuNhat;
	private ChartPanel chartPanelKhachHangMuaNhieuNhat;
	private JPanel pnlThongKeDoanhThuNhanVien;
	private JPanel pnlThongKeSoLuongHoaDonVaSanPhamNhanVien;
	private JFreeChart chartDoanhThuNhanVien;
	private CategoryPlot categoryDoanhThuNhanVien;
	private DefaultCategoryDataset datasetDoanhThuNhanVien;
	private ChartPanel chartPanelDoanhThuNhanVien;
	private JFreeChart chartSoLuongHoaDonVaSanPhamNhanVien;
	private CategoryPlot categorySoLuongHoaDonVaSanPhamNhanVien;
	private DefaultCategoryDataset datasetSoLuongHoaDonVaSanPhamNhanVien;
	private ChartPanel chartPanelSoLuongHoaDonVaSanPhamNhanVien;
	private JSeparator separator;
	private JTable table;
	private DefaultTableModel model;

	private static final String URL = "rmi://PhamVanThanh:9891/";
	
	public ThongKe_GUI() throws RemoteException, MalformedURLException, NotBoundException {
		
		// khai bao DAO
		hoaDon_DAO = (HoaDon_Impl) Naming.lookup(URL + "hoaDonDAO");
		sanPham_DAO = (SanPham_Impl) Naming.lookup(URL + "sanPhamDAO");
		nhanVien_DAO = (NhanVien_Impl) Naming.lookup(URL + "nhanVienDAO");
		khachHang_DAO = (KhachHang_Impl) Naming.lookup(URL + "khachHangDAO");
		
		setLayout(null);
		
		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(0, 0, 1300, 720);
		add(pnlMain);
		pnlMain.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1300, 720);
		pnlMain.add(tabbedPane);
		
		pnlDoanhThu = new JPanel();
		pnlDoanhThu.setBounds(0, 0, 1300, 720);
		tabbedPane.addTab("Doanh thu", null, pnlDoanhThu, "Doanh thu");
		pnlDoanhThu.setLayout(null);
		
		pnlNangSuatNhanVien = new JPanel();
		tabbedPane.addTab("Năng suất nhân viên", null, pnlNangSuatNhanVien, "Năng suất nhân viên");
		pnlNangSuatNhanVien.setLayout(null);
		
		pnlSanPhamBanChay = new JPanel();
		pnlSanPhamBanChay.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.addTab("Sản phẩm bán chạy", null, pnlSanPhamBanChay, "Sản phẩm bán chạy");
		pnlSanPhamBanChay.setLayout(new BorderLayout(0, 0));
		
		pnlKhachHangMuaNhieuNhat = new JPanel();
		pnlKhachHangMuaNhieuNhat.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.addTab("Khách hàng mua nhiều nhất", null, pnlKhachHangMuaNhieuNhat, "Khách hàng mua nhiều nhất");
		pnlKhachHangMuaNhieuNhat.setLayout(new BorderLayout(0, 0));
		
		pnlSoLuongTonKho = new JPanel();
		tabbedPane.addTab("Số lượng tồn kho", null, pnlSoLuongTonKho, "Số lượng tồn kho");
		pnlSoLuongTonKho.setLayout(null);
		
		separator = new JSeparator();
		separator.setForeground(new Color(60, 60, 60));
		separator.setBounds(345, 60, 930, 2);
		pnlSoLuongTonKho.add(separator);
		
		lblDanhSachSanPham = new JLabel("Danh Sách Sản Phẩm Sắp Hết Hàng");
		lblDanhSachSanPham.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblDanhSachSanPham.setBounds(20, 40, 325, 40);
		pnlSoLuongTonKho.add(lblDanhSachSanPham);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Chọn vào nhân viên cần hiển thị thông tin");
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(20, 90, 1255, 582);
		pnlSoLuongTonKho.add(scrollPane);
		
		String[] cols = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng Tồn"};
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
		scrollPane.setViewportView(table);
		loadSanPhamGanHetHang();

		// header of table
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(73, 129, 158));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
		tableHeader.setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		
		pnlThongKeDoanhThu = new JPanel();
		pnlThongKeDoanhThu.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnlThongKeDoanhThu.setBounds(0, 0, 890, 330);
		pnlDoanhThu.add(pnlThongKeDoanhThu);
		
		pnlThongKeSoLuong = new JPanel();
		pnlThongKeSoLuong.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnlThongKeSoLuong.setBounds(0, 350, 890, 341);
		pnlDoanhThu.add(pnlThongKeSoLuong);
		
		pnlChucNang = new JPanel();
		pnlChucNang.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnlChucNang.setBackground(new Color(255, 255, 255));
		pnlChucNang.setBounds(908, 0, 385, 689);
		pnlDoanhThu.add(pnlChucNang);
		pnlChucNang.setLayout(null);
		
		JLabel lblChonNgay = new JLabel("Chọn Ngày:");
		lblChonNgay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChonNgay.setBounds(20, 20, 255, 40);
		pnlChucNang.add(lblChonNgay);
		
		lblTuNgay = new JLabel("Từ Ngày:");
		lblTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTuNgay.setBounds(35, 80, 90, 40);
		pnlChucNang.add(lblTuNgay);
		
		lblDenNgay = new JLabel("Đến Ngày:");
		lblDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDenNgay.setBounds(35, 140, 90, 40);
		pnlChucNang.add(lblDenNgay);
		
		dateChooserTuNgay = new JDateChooser();
		dateChooserTuNgay.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (dateChooserTuNgay.getDate() != null) {
					if (dateChooserTuNgay.getDate().after(new Date())) {
						JOptionPane.showMessageDialog(null, "Không được chọn sau ngày hôm nay!");
						dateChooserTuNgay.setDate(null);
					}
				}
			}
		});
		dateChooserTuNgay.setBackground(new Color(255, 255, 255));
		dateChooserTuNgay.getCalendarButton().setBounds(210, 0, 30, 40);
		dateChooserTuNgay.getCalendarButton().setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/HeThong/calendar.png")));
		dateChooserTuNgay.getCalendarButton().setBorder(null);
		dateChooserTuNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dateChooserTuNgay.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateChooserTuNgay.setToolTipText("Từ ngày");
		dateChooserTuNgay.setBounds(125, 80, 240, 40);
		dateChooserTuNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserTuNgay.setDateFormatString("dd/MM/yyyy");
		dateChooserTuNgay.setFont(new Font("SansSerif", Font.BOLD, 18));
		dateChooserTuNgay.setBorder(null);
		dateChooserTuNgay.getDateEditor().setEnabled(false);
		pnlChucNang.add(dateChooserTuNgay);
		
		dateChooserDenNgay = new JDateChooser();
		dateChooserDenNgay.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (dateChooserDenNgay.getDate() != null) {
					Date tuNgay = dateChooserTuNgay.getDate();
					Date denNgay = dateChooserDenNgay.getDate();
					if (kiemTraNgayHopLe(tuNgay, denNgay)) {
						try {
							thongKeTrongKhoang(tuNgay, denNgay);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		dateChooserDenNgay.setBackground(new Color(255, 255, 255));
		dateChooserDenNgay.getCalendarButton().setBounds(210, 0, 30, 40);
		dateChooserDenNgay.getCalendarButton().setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/HeThong/calendar.png")));
		dateChooserDenNgay.getCalendarButton().setBorder(null);
		dateChooserDenNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		dateChooserDenNgay.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateChooserDenNgay.setToolTipText("Đến ngày");
		dateChooserDenNgay.setBounds(125, 140, 240, 40);
		dateChooserDenNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooserDenNgay.setDateFormatString("dd/MM/yyyy");
		dateChooserDenNgay.setFont(new Font("SansSerif", Font.BOLD, 18));
		dateChooserDenNgay.setBorder(null);
		dateChooserDenNgay.getDateEditor().setEnabled(false);
		pnlChucNang.add(dateChooserDenNgay);
		
		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoi.setBackground(new Color(73, 129, 158));
		btnLamMoi.setBounds(230, 289, 135, 40);
		btnLamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					showAllChart();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				dateChooserTuNgay.setDate(null);
				dateChooserDenNgay.setDate(null);
			}
		});
		pnlChucNang.add(btnLamMoi);
		
		JLabel lblThngKCui = new JLabel("Thống Kê Cuối Ngày:");
		lblThngKCui.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblThngKCui.setBounds(20, 213, 255, 40);
		pnlChucNang.add(lblThngKCui);
		
		JButton btnThongKe = new JButton("Thống Kê");
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThongKe.setBackground(new Color(73, 129, 158));
		btnThongKe.setBounds(27, 289, 135, 40);
		btnThongKe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					thongKeTrongKhoang(new Date(), new Date());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		pnlChucNang.add(btnThongKe);
		
		pnlThongKeDoanhThuNhanVien = new JPanel();
		pnlThongKeDoanhThuNhanVien.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnlThongKeDoanhThuNhanVien.setBounds(0, 0, 1295, 334);
		pnlNangSuatNhanVien.add(pnlThongKeDoanhThuNhanVien);
		
		pnlThongKeSoLuongHoaDonVaSanPhamNhanVien = new JPanel();
		pnlThongKeSoLuongHoaDonVaSanPhamNhanVien.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pnlThongKeSoLuongHoaDonVaSanPhamNhanVien.setBounds(0, 354, 1295, 339);
		pnlNangSuatNhanVien.add(pnlThongKeSoLuongHoaDonVaSanPhamNhanVien);
	}
	
	private void showBarChartDoanhThu(Date tuNgay, Date denNgay) throws RemoteException {
		datasetDoanhThu = new DefaultCategoryDataset();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int i = 0;
		while (i <= tinhKhoangCachGiuaHaiNgay(tuNgay, denNgay)) {
			datasetDoanhThu.addValue(tinhDoanhThuTheoNgay(getSQLDate(plusDays(tuNgay, i))), "Doanh thu 1", dateFormat.format(plusDays(tuNgay, i)).toString());
			i++;
		}
		chartDoanhThu = ChartFactory.createBarChart("DOANH THU", "NGÀY", "VND", datasetDoanhThu, PlotOrientation.VERTICAL, true, true, false);
		
		categoryDoanhThu = chartDoanhThu.getCategoryPlot();
		// change background color
		categoryDoanhThu.setBackgroundPaint(new Color(255, 255, 255));

	    // set  bar chart color
	    ((BarRenderer)categoryDoanhThu.getRenderer()).setBarPainter(new StandardBarPainter());

	    BarRenderer rendererDoanhThu = (BarRenderer)chartDoanhThu.getCategoryPlot().getRenderer();
	    rendererDoanhThu.setSeriesPaint(0, new Color(129, 197, 219));
		
		LegendTitle legendTitle = chartDoanhThu.getLegend();
		legendTitle.setVisible(false);
		pnlThongKeDoanhThu.setLayout(null);
		
		chartPanelDoanhThu = new ChartPanel(chartDoanhThu);
		chartPanelDoanhThu.setBounds(2, 2, 886, 332);
		pnlThongKeDoanhThu.add(chartPanelDoanhThu);
		chartPanelDoanhThu.removeAll();
		chartPanelDoanhThu.repaint();
		chartPanelDoanhThu.validate();
	}
	
	private void showBarChartSoLuong(Date tuNgay, Date denNgay) throws RemoteException {
		datasetSoLuong = new DefaultCategoryDataset();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		// add value
		int i = 0;
		while (i <= tinhKhoangCachGiuaHaiNgay(tuNgay, denNgay)) {
			datasetSoLuong.addValue(hoaDon_DAO.getHoaDonTheoNgay(getSQLDate(plusDays(tuNgay, i))).size(), "Hóa đơn", dateFormat.format(plusDays(tuNgay, i)).toString());
			datasetSoLuong.addValue(tinhSoLuongSanPhamBanDuocTheoNgay(getSQLDate(plusDays(tuNgay, i))), "Sản phẩm", dateFormat.format(plusDays(tuNgay, i)).toString());
			i++;
		}
		
		chartSoLuong = ChartFactory.createBarChart("SỐ LƯỢNG SẢN PHẨM VÀ SỐ LƯỢNG HÓA ĐƠN BÁN ĐƯỢC", "NGÀY", "SỐ LƯỢNG", datasetSoLuong, PlotOrientation.VERTICAL, true, true, false);
		
		categorySoLuong = chartSoLuong.getCategoryPlot();
		//change background color
		categorySoLuong.setBackgroundPaint(new Color(255, 255, 255)); 

	    //set  bar chart color
	    ((BarRenderer)categorySoLuong.getRenderer()).setBarPainter(new StandardBarPainter());

	    BarRenderer rendererSoLuong = (BarRenderer)chartSoLuong.getCategoryPlot().getRenderer();
	    rendererSoLuong.setSeriesPaint(0, new Color(129, 197, 219));
	    rendererSoLuong.setSeriesPaint(1, new Color(56, 96, 127));
		pnlThongKeSoLuong.setLayout(null);
		
		chartPanelSoLuong = new ChartPanel(chartSoLuong);
		chartPanelSoLuong.setBounds(2, 2, 886, 332);
		pnlThongKeSoLuong.add(chartPanelSoLuong);
		chartPanelSoLuong.removeAll();
		chartPanelSoLuong.repaint();
	}
	
	private void showBarChartSanPhamBanChay() throws RemoteException { 
		datasetSanPhamBanChay = new DefaultCategoryDataset();
		
		// add value
		sanPham_DAO.getSanPhamBanChay().forEach((sanPham, soLuongDaBan) -> {
			if(sanPham == null){
				System.out.println("San pham null");
			}
			datasetSanPhamBanChay.addValue(soLuongDaBan, "Sản phẩm", sanPham.getTenSanPham());
		});
		
		chartSanPhamBanChay = ChartFactory.createBarChart("CÁC SẢN PHẨM BÁN CHẠY", "SẢN PHẨM", "SỐ LƯỢNG", datasetSanPhamBanChay, PlotOrientation.VERTICAL, true, true, false);
	
		categorySanPhamBanChay = chartSanPhamBanChay.getCategoryPlot();
		categorySanPhamBanChay.setBackgroundPaint(new Color(255, 255, 255));
		
		// set bar chart color
		((BarRenderer) categorySanPhamBanChay.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer rendererSanPhamBanChay = (BarRenderer)chartSanPhamBanChay.getCategoryPlot().getRenderer();
	    rendererSanPhamBanChay.setSeriesPaint(0, new Color(129, 197, 219));
	    rendererSanPhamBanChay.setSeriesPaint(1, new Color(56, 96, 127));
		
	    chartPanelSanPhamBanChay = new ChartPanel(chartSanPhamBanChay);
	    pnlSanPhamBanChay.add(chartPanelSanPhamBanChay);
	    pnlSanPhamBanChay.validate();
	}
	
	private void showBarChartKhachHangMuaNhieuNhat() throws RemoteException {
		datasetKhachHangMuaNhieuNhat = new DefaultCategoryDataset();

		khachHang_DAO.getDanhSachMuoiKhachHangMuaNhieuNhat().forEach((khachHang, soTien) -> {
			datasetKhachHangMuaNhieuNhat.addValue(soTien, "VNĐ", khachHang.getTenKhachHang());
		});
		
		chartKhachHangMuaNhieuNhat = ChartFactory.createBarChart("KHÁCH HÀNG MUA NHIỀU NHẤT", "TÊN KHÁCH HÀNG", "VNĐ", datasetKhachHangMuaNhieuNhat, PlotOrientation.VERTICAL, true, true, false);
		
		categoryKhachHangMuaNhieuNhat = chartKhachHangMuaNhieuNhat.getCategoryPlot();
		categoryKhachHangMuaNhieuNhat.setBackgroundPaint(new Color(255, 255, 255));
		
		// set bar chart color
		((BarRenderer) categoryKhachHangMuaNhieuNhat.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer rendererKhachHangMuaNhieuNhat = (BarRenderer)chartKhachHangMuaNhieuNhat.getCategoryPlot().getRenderer();
	    rendererKhachHangMuaNhieuNhat.setSeriesPaint(0, new Color(129, 197, 219));
	    rendererKhachHangMuaNhieuNhat.setSeriesPaint(1, new Color(56, 96, 127));
		
	    chartPanelKhachHangMuaNhieuNhat = new ChartPanel(chartKhachHangMuaNhieuNhat);
	    pnlKhachHangMuaNhieuNhat.add(chartPanelKhachHangMuaNhieuNhat);
	    pnlKhachHangMuaNhieuNhat.validate();
	}
	
	private void showBarChartThongKeNangSuatNhanVien() throws RemoteException { 
		datasetDoanhThuNhanVien = new DefaultCategoryDataset();
		datasetSoLuongHoaDonVaSanPhamNhanVien = new DefaultCategoryDataset();
		
		// add value
		nhanVien_DAO.getDoanhThuNhanVien().forEach((nhanVien, doanhThu) -> {
            datasetDoanhThuNhanVien.addValue(doanhThu, "Doanh thu", nhanVien.getTenNhanVien());
        });
		
		nhanVien_DAO.getTongHoaDonSoLuongNhanVien().forEach((nhanVien, soLuongSanPham) -> {
            List<HoaDon> hoaDons = null;
            try {
                hoaDons = hoaDon_DAO.getHoaDonTheoMaNhanVien(nhanVien.getMaNhanVien());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            datasetSoLuongHoaDonVaSanPhamNhanVien.addValue(hoaDons.size(), "Hóa đơn", nhanVien.getTenNhanVien());
			datasetSoLuongHoaDonVaSanPhamNhanVien.addValue(soLuongSanPham, "Sản phẩm", nhanVien.getTenNhanVien());
		});

		// chart 1
		chartDoanhThuNhanVien = ChartFactory.createBarChart("DOANH THU NHÂN VIÊN", "TÊN NHÂN VIÊN", "VNĐ", datasetDoanhThuNhanVien, PlotOrientation.VERTICAL, true, true, false);
		categoryDoanhThuNhanVien = chartDoanhThuNhanVien.getCategoryPlot();
		categoryDoanhThuNhanVien.setBackgroundPaint(new Color(255, 255, 255));
		
		// set bar chart color
		((BarRenderer) categoryDoanhThuNhanVien.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer rendererDoanhThuNhanVien = (BarRenderer)chartDoanhThuNhanVien.getCategoryPlot().getRenderer();
	    rendererDoanhThuNhanVien.setSeriesPaint(0, new Color(129, 197, 219));
	    rendererDoanhThuNhanVien.setSeriesPaint(1, new Color(56, 96, 127));
	    pnlThongKeDoanhThuNhanVien.setLayout(new BorderLayout(0, 0));
		
	    chartPanelDoanhThuNhanVien = new ChartPanel(chartDoanhThuNhanVien);
	    pnlThongKeDoanhThuNhanVien.add(chartPanelDoanhThuNhanVien);
	    pnlThongKeDoanhThuNhanVien.validate();
	    
	    // chart 2
		chartSoLuongHoaDonVaSanPhamNhanVien = ChartFactory.createBarChart("SỐ LƯỢNG HÓA ĐƠN VÀ SẢN PHẨM CỦA NHÂN VIÊN BÁN ĐƯỢC", "TÊN NHÂN VIÊN", "SỐ LƯỢNG", datasetSoLuongHoaDonVaSanPhamNhanVien, PlotOrientation.VERTICAL, true, true, false);
		
		categorySoLuongHoaDonVaSanPhamNhanVien = chartSoLuongHoaDonVaSanPhamNhanVien.getCategoryPlot();
		categorySoLuongHoaDonVaSanPhamNhanVien.setBackgroundPaint(new Color(255, 255, 255));
		
		// set bar chart color
		((BarRenderer) categorySoLuongHoaDonVaSanPhamNhanVien.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer rendererSoLuongHoaDonVaSanPhamNhanVien = (BarRenderer)chartSoLuongHoaDonVaSanPhamNhanVien.getCategoryPlot().getRenderer();
	    rendererSoLuongHoaDonVaSanPhamNhanVien.setSeriesPaint(0, new Color(129, 197, 219));
	    rendererSoLuongHoaDonVaSanPhamNhanVien.setSeriesPaint(1, new Color(56, 96, 127));
	    pnlThongKeSoLuongHoaDonVaSanPhamNhanVien.setLayout(new BorderLayout(0, 0));
		
	    chartPanelSoLuongHoaDonVaSanPhamNhanVien = new ChartPanel(chartSoLuongHoaDonVaSanPhamNhanVien);
	    pnlThongKeSoLuongHoaDonVaSanPhamNhanVien.add(chartPanelSoLuongHoaDonVaSanPhamNhanVien);
	    pnlThongKeSoLuongHoaDonVaSanPhamNhanVien.validate();
	}
	
	private double tinhDoanhThuTheoNgay(java.sql.Date date) throws RemoteException {
		return hoaDon_DAO.getTongDoanhThuTheoNgay(date);
	}
	
	private long tinhSoLuongSanPhamBanDuocTheoNgay(java.sql.Date date) throws RemoteException {
		return hoaDon_DAO.getSoLuongSanPhamBanDuocTheoNgay(date);
	}
	
	public void showAllChart() throws RemoteException {
		showBarChartDoanhThu(plusDays(new Date(), -6), new Date());
		showBarChartSoLuong(plusDays(new Date(), -6), new Date());
		showBarChartKhachHangMuaNhieuNhat();
		showBarChartSanPhamBanChay();
		showBarChartThongKeNangSuatNhanVien();
	}
	
	private int tinhKhoangCachGiuaHaiNgay(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	private void thongKeTrongKhoang(Date tuNgay, Date denNgay) throws RemoteException {
		showBarChartDoanhThu(tuNgay, denNgay);
		showBarChartSoLuong(tuNgay, denNgay);
	}
	
	private java.sql.Date getSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	private Date plusDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	private boolean kiemTraNgayHopLe(Date tuNgay, Date denNgay) {
		try {
			if (tuNgay.after(denNgay)) {
				JOptionPane.showMessageDialog(null, "Thứ tự ngày không hợp lệ!");
				dateChooserTuNgay.setDate(null);
				dateChooserDenNgay.setDate(null);
				return false;
			}
			else if (denNgay.after(new Date())) {
				JOptionPane.showMessageDialog(null, "Không được chọn sau ngày hiện tại!");
				dateChooserDenNgay.setDate(null);
				return false;
			}
			else if (tinhKhoangCachGiuaHaiNgay(tuNgay, denNgay) >= 7) {
				JOptionPane.showMessageDialog(null, "Tối đa 7 ngày!");
				dateChooserTuNgay.setDate(null);
				dateChooserDenNgay.setDate(null);
				return false;
			}
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bạn phải chọn \"Từ ngày\" trước!");
			dateChooserDenNgay.setDate(null);
			return false;
		}
	}
	
	// load danh sách sản phẩm gần hết hàng
	private void loadSanPhamGanHetHang() throws RemoteException {
		model.setRowCount(0);
		for (SanPham sanPham : sanPham_DAO.getSanPhamGanHetHang()) {
			Object[] objects = { sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getSoLuongTon()};
			model.addRow(objects);
		}
	}
}
