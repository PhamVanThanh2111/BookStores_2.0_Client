package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;

import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import dao.impl.Ca_Impl;
import dao.impl.ChiTietHoaDon_Impl;
import dao.impl.ChiTietPhieuDatHangImpl;
import dao.impl.DungCuHocTap_Impl;
import dao.impl.HoaDon_Impl;
import dao.impl.KhachHang_Impl;
import dao.impl.NhaCungCap_Impl;
import dao.impl.NhaXuatBan_Impl;
import dao.impl.NhanVien_Impl;
import dao.impl.PhieuDatHang_Impl;
import dao.impl.Sach_Impl;
import dao.impl.SanPham_Impl;
import dao.impl.TaiKhoan_Impl;
import dao.impl.TheLoaiSach_Impl;
import gui.HeThongQuanLyNhaSach;

public class Client {
	private static final String URL = "rmi://PhamVanThanh:9891/";
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
			HeThongQuanLyNhaSach heThongQuanLyNhaSach = new HeThongQuanLyNhaSach();
			heThongQuanLyNhaSach.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Failed to initialize LaF");
		}
		
//		HoaDon_Impl hoaDon_Impl = (HoaDon_Impl) Naming.lookup(URL + "hoaDonDAO");
//		Ca_Impl caDAO = (Ca_Impl) Naming.lookup(URL + "caDAO");
//		ChiTietHoaDon_Impl chiTietHoaDonDAO = (ChiTietHoaDon_Impl) Naming.lookup(URL + "chiTietHoaDonDAO");
//		ChiTietPhieuDatHangImpl chiTietPhieuDatHangDAO = (ChiTietPhieuDatHangImpl) Naming.lookup(URL + "chiTietPhieuDatHangDAO");
//		DungCuHocTap_Impl dungCuHocTapDAO = (DungCuHocTap_Impl) Naming.lookup(URL + "dungCuHocTapDAO");
//		KhachHang_Impl khachHangDAO = (KhachHang_Impl) Naming.lookup(URL + "khachHangDAO");
//		NhaCungCap_Impl nhaCungCapDAO = (NhaCungCap_Impl) Naming.lookup(URL + "nhaCungCapDAO");
//		NhanVien_Impl nhanVienDAO = (NhanVien_Impl) Naming.lookup(URL + "nhanVienDAO");
//		NhaXuatBan_Impl nhaXuatBanDAO = (NhaXuatBan_Impl) Naming.lookup(URL + "nhaXuatBanDAO");
//		PhieuDatHang_Impl phieuDatHangDAO = (PhieuDatHang_Impl) Naming.lookup(URL + "phieuDatHangDAO");
//		Sach_Impl sachDAO = (Sach_Impl) Naming.lookup(URL + "sachDAO");
//		SanPham_Impl sanPhamDAO = (SanPham_Impl) Naming.lookup(URL + "sanPhamDAO");
//		TaiKhoan_Impl taiKhoanDAO = (TaiKhoan_Impl) Naming.lookup(URL + "taiKhoanDAO");
//		TheLoaiSach_Impl theLoaiSachDAO = (TheLoaiSach_Impl) Naming.lookup(URL + "theLoaiSachDAO");
		
//		System.out.println(caDAO.getCaTheoMa("C01").getTenCa());
//		System.out.println(chiTietHoaDonDAO.getAllChiTietHoaDonTheoMaHoaDon("HD00014").size());
//		System.out.println(dungCuHocTapDAO.getAllDungCuHocTap().size());
//		System.out.println(khachHangDAO.getAllKhachHang().size());
//		System.out.println(nhaCungCapDAO.getAllNhaCungCap().size());
//		System.out.println(nhanVienDAO.getAllNhanVien().size());
//		System.out.println(nhaXuatBanDAO.getAllNhaXuatBan().size());
//		System.out.println(sachDAO.getAllSach().size());
//		System.out.println(taiKhoanDAO.getMatKhauTheoMaNhanVien("NV0009"));
//		sanPhamDAO.getSanPhamBanChay().forEach((k, v) -> {
//			System.out.println(k.getTenSanPham() + " " + v);
//		});
//		System.out.println(theLoaiSachDAO.getAllTheLoaiSach().size());
//		
//		hoaDon_Impl.getAllHoaDon().forEach(hoaDon -> {
//			System.out.println(hoaDon.getMaHoaDon());
//			if (hoaDon.getMaHoaDon().equals("HD00010")) {
//				System.out.println(hoaDon.getNhanVien().getTenNhanVien());
//			}
//		});
	}
}
