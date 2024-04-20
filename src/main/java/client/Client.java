package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import dao.impl.*;
import entity.*;
import gui.DangNhap_GUI;
import gui.HeThongQuanLyNhaSach;

public class Client {
	private static final String URL = "rmi://PhamVanThanh:9891/";
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
//			HeThongQuanLyNhaSach heThongQuanLyNhaSach = new HeThongQuanLyNhaSach();
//			heThongQuanLyNhaSach.setVisible(true);
			DangNhap_GUI dangNhapGui = new DangNhap_GUI();
			dangNhapGui.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Failed to initialize LaF");
		}

//		HoaDon_Impl hoaDon_DAO = (HoaDon_Impl) Naming.lookup(URL + "hoaDonDAO");
//		KhachHang_Impl khachHang_DAO = (KhachHang_Impl) Naming.lookup(URL + "khachHangDAO");
//		NhanVien_Impl nhanVien_DAO = (NhanVien_Impl) Naming.lookup(URL + "nhanVienDAO");
//		ChiTietHoaDon_Impl chiTietHoaDon_DAO = (ChiTietHoaDon_Impl) Naming.lookup(URL + "chiTietHoaDonDAO");
//		SanPham_Impl sanPham_Impl = (SanPham_Impl) Naming.lookup(URL + "sanPhamDAO");
//		TaiKhoan_Impl taiKhoan_DAO = (TaiKhoan_Impl) Naming.lookup(URL + "taiKhoanDAO");
//		PhieuDatHang_Impl phieuDatHang_Impl = (PhieuDatHang_Impl) Naming.lookup(URL + "phieuDatHangDAO");
//		ChiTietPhieuDatHangImpl chiTietPhieuDatHangImpl = (ChiTietPhieuDatHangImpl) Naming.lookup(URL + "chiTietPhieuDatHangDAO");
//		TaiKhoan taiKhoan = new TaiKhoan();
//		taiKhoan.setMatKhau("123");
//		taiKhoan_DAO.themTaiKhoan(taiKhoan);
//		System.out.println(taiKhoan.getTaiKhoan());
//		sanPham_DAO.getSanPhamBanChay().forEach((k, v) -> {
//			if(k == null){
//				System.out.println("San pham null");
//			}
//			System.out.println(k.getTenSanPham() + " - " + v);
//		});
		//		String maPhieuDatHang = "PDH0001";
//		PhieuDatHang phieuDatHang = new PhieuDatHang();
//		phieuDatHang.setMaPhieuDatHang("PD00001");
//		phieuDatHang.setNhanVien(new NhanVien("NV0002"));
//		phieuDatHang.setKhachHang(new KhachHang("KH00022"));
//		phieuDatHang.setNgayLap(Date.valueOf(LocalDate.now()));
//		phieuDatHang.setThanhTien(1231);
//		phieuDatHang = phieuDatHang_Impl.lapPhieuDatHang(phieuDatHang);
//		System.out.println(phieuDatHang.getMaPhieuDatHang());
//
//		ChiTietPhieuDatHang chiTietPhieuDatHang = new ChiTietPhieuDatHang();
//		SanPham sanPham = sanPham_Impl.getSanPhamTheoMa("DCHT00002");
//		ChiTietPhieuDatKey chiTietPhieuDatKey = new ChiTietPhieuDatKey(phieuDatHang.getMaPhieuDatHang(),"DCHT00002");
//		chiTietPhieuDatHang.setPhieuDatHang(phieuDatHang);
//		chiTietPhieuDatHang.setSanPham(sanPham);
//		chiTietPhieuDatHang.setId(chiTietPhieuDatKey);
//		chiTietPhieuDatHang.setSoLuong(12);
//		chiTietPhieuDatHang.setDonGia(123);
//		chiTietPhieuDatHangImpl.themChiTietPhieuDatHang(chiTietPhieuDatHang);
//		System.out.println(taiKhoan_DAO.getTaiKhoanTheoMaTaiKhoan("NV0001").getTaiKhoan());
	}
}
