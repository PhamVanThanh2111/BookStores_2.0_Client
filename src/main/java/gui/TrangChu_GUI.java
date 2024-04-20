package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.border.LineBorder;

import dao.impl.Ca_Impl;
import dao.impl.HoaDon_Impl;
import dao.impl.NhanVien_Impl;
import entity.HoaDon;
import entity.NhanVien;

import javax.swing.ImageIcon;


public class TrangChu_GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblSoLuongGiaoDichValue;
	private JLabel lblTongThuValue;
	private Ca_Impl ca_DAO;
	private HoaDon_Impl hoaDon_DAO;
	private NhanVien_Impl nhanVien_DAO;
	private JLabel lblThoiGianValue;
	private JLabel lblTieuDe;
	private NhanVien nhanVien;
	
	private static final String URL = "rmi://PhamVanThanh:9891/";
	
	public TrangChu_GUI(NhanVien nhanVien) throws RemoteException, MalformedURLException, NotBoundException {
		ca_DAO = (Ca_Impl) Naming.lookup(URL + "caDAO");
		hoaDon_DAO = (HoaDon_Impl) Naming.lookup(URL + "hoaDonDAO");
		nhanVien_DAO = (NhanVien_Impl) Naming.lookup(URL + "nhanVienDAO");
		this.nhanVien = nhanVien;
		
		setLayout(null);

		JPanel pnlMain = new JPanel();
		pnlMain.setBorder(null);
		pnlMain.setBackground(new Color(241, 245, 249));
		pnlMain.setBounds(0, 0, 1300, 720);
		add(pnlMain);
		pnlMain.setLayout(null);
		
		lblTieuDe = new JLabel("Chào '" + nhanVien.getTenNhanVien() +"' , Chúc Bạn Ngày Mới Tốt Lành!");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTieuDe.setBounds(190, 28, 919, 58);
		pnlMain.add(lblTieuDe);
		
		JPanel pnlDoanhThu = new JPanel();
		pnlDoanhThu.setBackground(new Color(255, 255, 255));
		pnlDoanhThu.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDoanhThu.setBounds(550, 110, 430, 590);
		pnlMain.add(pnlDoanhThu);
		pnlDoanhThu.setLayout(null);
		
		JLabel lblDoanhThu = new JLabel("Doanh Thu");
		lblDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDoanhThu.setBounds(100, 10, 230, 40);
		pnlDoanhThu.add(lblDoanhThu);
		
		JLabel lblSoLuongGiaoDich = new JLabel("Số lượng giao dịch:");
		lblSoLuongGiaoDich.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoLuongGiaoDich.setBounds(34, 60, 170, 32);
		pnlDoanhThu.add(lblSoLuongGiaoDich);
		
		lblSoLuongGiaoDichValue = new JLabel();
		lblSoLuongGiaoDichValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoLuongGiaoDichValue.setBounds(214, 60, 170, 32);
		pnlDoanhThu.add(lblSoLuongGiaoDichValue);
		
		JLabel lblTongThu = new JLabel("Tổng thu:");
		lblTongThu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTongThu.setBounds(34, 115, 170, 32);
		pnlDoanhThu.add(lblTongThu);
		
		lblTongThuValue = new JLabel();
		lblTongThuValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTongThuValue.setBounds(214, 115, 170, 32);
		pnlDoanhThu.add(lblTongThuValue);
		
		JLabel lblIconDoanhThu = new JLabel("");
		lblIconDoanhThu.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/HeThong/money.png")));
		lblIconDoanhThu.setBounds(10, 452, 128, 128);
		pnlDoanhThu.add(lblIconDoanhThu);
		
		JLabel lblBackgroundDoanhThu = new JLabel("");
		lblBackgroundDoanhThu.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/HeThong/background_doanhthu.jpg")));
		lblBackgroundDoanhThu.setBounds(0, 0, 45, 13);
		lblBackgroundDoanhThu.setBounds(0, 0, pnlDoanhThu.getWidth(), pnlDoanhThu.getHeight());
		pnlDoanhThu.add(lblBackgroundDoanhThu);
		
		JPanel pnlThongTinCuaHang = new JPanel();
		pnlThongTinCuaHang.setBackground(new Color(255, 255, 255));
		pnlThongTinCuaHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlThongTinCuaHang.setBounds(50, 110, 465, 250);
		pnlMain.add(pnlThongTinCuaHang);
		pnlThongTinCuaHang.setLayout(null);
		
		JLabel lblThongTinCuaHang = new JLabel("Thông Tin Cửa Hàng");
		lblThongTinCuaHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongTinCuaHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblThongTinCuaHang.setBounds(117, 10, 230, 40);
		pnlThongTinCuaHang.add(lblThongTinCuaHang);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(34, 69, 106, 32);
		pnlThongTinCuaHang.add(lblEmail);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiaChi.setBounds(34, 124, 106, 32);
		pnlThongTinCuaHang.add(lblDiaChi);
		
		JLabel lblThanhToan = new JLabel("Thanh toán:");
		lblThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThanhToan.setBounds(34, 179, 106, 32);
		pnlThongTinCuaHang.add(lblThanhToan);
		
		JLabel lblEmailValue = new JLabel("nhom13bookstores@gmail.com");
		lblEmailValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmailValue.setBounds(164, 69, 267, 32);
		pnlThongTinCuaHang.add(lblEmailValue);
		
		JLabel lblDiaChiValue = new JLabel("Nguyễn Văn Bảo");
		lblDiaChiValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiaChiValue.setBounds(164, 124, 267, 32);
		pnlThongTinCuaHang.add(lblDiaChiValue);
		
		JLabel lblThanhToanValue = new JLabel("ACB - 23639277");
		lblThanhToanValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThanhToanValue.setBounds(164, 179, 267, 32);
		pnlThongTinCuaHang.add(lblThanhToanValue);
		
		JPanel pnlThongTinCaLam = new JPanel();
		pnlThongTinCaLam.setBackground(new Color(255, 255, 255));
		pnlThongTinCaLam.setLayout(null);
		pnlThongTinCaLam.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlThongTinCaLam.setBounds(50, 450, 465, 250);
		pnlMain.add(pnlThongTinCaLam);
		
		JLabel lblCaLam = new JLabel("Ca Làm");
		lblCaLam.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaLam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCaLam.setBounds(117, 10, 230, 40);
		pnlThongTinCaLam.add(lblCaLam);
		
		JLabel lblThoiGian = new JLabel("Thời gian:");
		lblThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThoiGian.setBounds(34, 69, 112, 32);
		pnlThongTinCaLam.add(lblThoiGian);
		
		JLabel lblNgayLam = new JLabel("Ngày làm:");
		lblNgayLam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNgayLam.setBounds(34, 124, 112, 32);
		pnlThongTinCaLam.add(lblNgayLam);
		
		JLabel lblGioGhiNhan = new JLabel("Giờ ghi nhận:");
		lblGioGhiNhan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGioGhiNhan.setBounds(34, 179, 112, 32);
		pnlThongTinCaLam.add(lblGioGhiNhan);
		
		lblThoiGianValue = new JLabel();
		lblThoiGianValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThoiGianValue.setBounds(164, 69, 267, 32);
		lblThoiGianValue.setText(ca_DAO.getCaTheoMa(nhanVien.getCa().getMaCa()).getThoiGian());
		pnlThongTinCaLam.add(lblThoiGianValue);
		
		JLabel lblNgayLamValue = new JLabel();
		lblNgayLamValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNgayLamValue.setBounds(164, 124, 267, 32);
		pnlThongTinCaLam.add(lblNgayLamValue);
		
		JLabel lblGioGhiNhanValue = new JLabel();
		lblGioGhiNhanValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGioGhiNhanValue.setBounds(164, 179, 267, 32);
		pnlThongTinCaLam.add(lblGioGhiNhanValue);
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lblNgayLamValue.setText(dateFormat.format(LocalDate.now()).toString());
		lblGioGhiNhanValue.setText(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute());
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/HeThong/background_trangchu.png")));
		lblBackGround.setBounds(1010, 110, 267, 590);
		pnlMain.add(lblBackGround);
		
		lblSoLuongGiaoDichValue.setText(hoaDon_DAO.getHoaDonTheoNhanVienNgayHienTai(nhanVien.getMaNhanVien()).size() + "");
		lblTongThuValue.setText(tinhDoanhThuNhanVienTrongNgay(nhanVien) + " VND");
	}
	
	private double tinhDoanhThuNhanVienTrongNgay(NhanVien nhanVien) throws RemoteException {
		List<HoaDon> hoaDonList = hoaDon_DAO.getHoaDonTheoNhanVienNgayHienTai(nhanVien.getMaNhanVien());
		return hoaDonList.stream().map(hoaDon -> hoaDon.getThanhTien()).reduce(0.0F, (a, b) -> a + b);
		/*nhanVien_DAO.getDoanhThuNhanVienTheoNgay(nhanVien.getMaNhanVien(), new Date(new java.util.Date().getTime()));*/
	}
	
	public void refresh() throws RemoteException {
		lblThoiGianValue.setText(ca_DAO.getCaTheoMa(nhanVien.getCa().getMaCa()).getThoiGian());
		lblTieuDe.setText("Chào '" + nhanVien.getTenNhanVien() +"' , Chúc Bạn Ngày Mới Tốt Lành!");
		lblSoLuongGiaoDichValue.setText(hoaDon_DAO.getHoaDonTheoMaNhanVien(nhanVien.getMaNhanVien()).size() + "");
		lblTongThuValue.setText(tinhDoanhThuNhanVienTrongNgay(nhanVien) + " VND");
	}
}
