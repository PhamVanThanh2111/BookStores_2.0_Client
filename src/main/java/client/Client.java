package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import dao.impl.*;
import entity.HoaDon;
import entity.TaiKhoan;
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

		HoaDon_Impl hoaDon_DAO = (HoaDon_Impl) Naming.lookup(URL + "hoaDonDAO");
//		KhachHang_Impl khachHang_DAO = (KhachHang_Impl) Naming.lookup(URL + "khachHangDAO");
//		NhanVien_Impl nhanVien_DAO = (NhanVien_Impl) Naming.lookup(URL + "nhanVienDAO");
//		ChiTietHoaDon_Impl chiTietHoaDon_DAO = (ChiTietHoaDon_Impl) Naming.lookup(URL + "chiTietHoaDonDAO");
//		SanPham_Impl sanPham_DAO = (SanPham_Impl) Naming.lookup(URL + "sanPhamDAO");
//		TaiKhoan_Impl taiKhoan_DAO = (TaiKhoan_Impl) Naming.lookup(URL + "taiKhoanDAO");
//
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
	}
}
