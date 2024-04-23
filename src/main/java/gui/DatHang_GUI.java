package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.impl.ChiTietPhieuDatHangImpl;
import dao.impl.DungCuHocTap_Impl;
import dao.impl.KhachHang_Impl;
import dao.impl.NhanVien_Impl;
import dao.impl.PhieuDatHang_Impl;
import dao.impl.Sach_Impl;
import dao.impl.SanPham_Impl;
import entity.ChiTietPhieuDatHang;
import entity.ChiTietPhieuDatKey;
import entity.PhieuDatHang;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DatHang_GUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> cbTenSP;
    private JComboBox<String> cbLoaiSP;
    private JTextField txtSoLuong;
    private JTextField txtMaKhachHang;
    private JTextField txtTenKhachHang;
    private JTextField txtSoDienThoai;
    private JTextField txtDiaChi;
    private JTextField txtConLai;
    private JTextField txtMaSanPham;
    private JTextField txtSearchSanPham;
    private JTableHeader tableHeader;
    private JLabel lblTongTienValue;

    private KhachHang khachHang;
    private SanPham sanPham;
    private DanhSachDatHang_GUI danhSachDatHang_GUI;

    private SanPham_Impl sanPham_DAO;
    private KhachHang_Impl khachHang_DAO;
    private PhieuDatHang_Impl phieuDatHang_DAO;
    private ChiTietPhieuDatHangImpl chiTietPhieuDatHang_DAO;
    private Sach_Impl sach_DAO;
    private DungCuHocTap_Impl dungCuHocTap_DAO;
    private NhanVien_Impl nhanVien_DAO;

    private static final String URL = "rmi://PhamVanThanh:9891/";

    public DatHang_GUI(NhanVien nhanVien, DanhSachDatHang_GUI danhSachDatHang_GUI) throws RemoteException, MalformedURLException, NotBoundException {
        this.danhSachDatHang_GUI = danhSachDatHang_GUI;
        setBackground(new Color(255, 255, 255));

        // khai bao DAO
        sanPham_DAO = (SanPham_Impl) Naming.lookup(URL + "sanPhamDAO");
        khachHang_DAO = (KhachHang_Impl) Naming.lookup(URL + "khachHangDAO");
        phieuDatHang_DAO = (PhieuDatHang_Impl) Naming.lookup(URL + "phieuDatHangDAO");
        chiTietPhieuDatHang_DAO = (ChiTietPhieuDatHangImpl) Naming.lookup(URL + "chiTietPhieuDatHangDAO");
        sach_DAO = (Sach_Impl) Naming.lookup(URL + "sachDAO");
        dungCuHocTap_DAO = (DungCuHocTap_Impl) Naming.lookup(URL + "dungCuHocTapDAO");
        nhanVien_DAO = (NhanVien_Impl) Naming.lookup(URL + "nhanVienDAO");

        setLayout(null);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(null);
        pnlMain.setBackground(new Color(241, 245, 249));
        pnlMain.setBounds(0, 0, 1300, 720);
        add(pnlMain);

        JPanel pTenKhachHang = new JPanel();
        pTenKhachHang.setBackground(new Color(255, 255, 255));
        pTenKhachHang.setLayout(null);
        pTenKhachHang.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        pTenKhachHang.setBounds(0, 0, 530, 280);
        pnlMain.add(pTenKhachHang);

        JLabel lblMaKhachHang = new JLabel("Mã Khách Hàng:");
        lblMaKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblMaKhachHang.setBounds(23, 70, 141, 40);
        pTenKhachHang.add(lblMaKhachHang);

        txtMaKhachHang = new JTextField();
        txtMaKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtMaKhachHang.setColumns(10);
        txtMaKhachHang.setBounds(174, 70, 336, 40);
        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.setFocusable(false);
        pTenKhachHang.add(txtMaKhachHang);

        JLabel lblTenKhachHang = new JLabel("Tên Khách Hàng:");
        lblTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblTenKhachHang.setBounds(23, 120, 141, 40);
        pTenKhachHang.add(lblTenKhachHang);

        txtTenKhachHang = new JTextField();
        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtTenKhachHang.setColumns(10);
        txtTenKhachHang.setBounds(174, 120, 336, 40);
        txtTenKhachHang.setFocusable(false);
        pTenKhachHang.add(txtTenKhachHang);

        JLabel lblSoDienThoai = new JLabel("Số Điện Thoại:");
        lblSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblSoDienThoai.setBounds(23, 170, 141, 40);
        pTenKhachHang.add(lblSoDienThoai);

        JLabel lblDiaChi = new JLabel("Địa Chỉ:");
        lblDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDiaChi.setBounds(23, 220, 141, 40);
        pTenKhachHang.add(lblDiaChi);

        txtSoDienThoai = new JTextField();
        txtSoDienThoai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtSoDienThoai.getText().length() != 10) { // Regular expression
                        JOptionPane.showMessageDialog(null, "Số điện thoại phải đủ 10 số!");
                    } else if (!Regular_expression.validatePhoneNumber(txtSoDienThoai.getText())) {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
                    } else {
                        try {
                            khachHang = khachHang_DAO.getKhachHangTheoSoDienThoai(txtSoDienThoai.getText());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        if (khachHang.getMaKhachHang() == null) {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng này!");
                        } else {
                            txtMaKhachHang.setText(khachHang.getMaKhachHang());
                            txtTenKhachHang.setText(khachHang.getTenKhachHang());
                            txtDiaChi.setText(khachHang.getDiaChi());
                        }
                    }
                }

            }
        });
        txtSoDienThoai.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtSoDienThoai.setColumns(10);
        txtSoDienThoai.setBounds(174, 170, 336, 40);
        pTenKhachHang.add(txtSoDienThoai);

        JLabel lblThongTinKhachHang = new JLabel("Thông Tin Khách Hàng");
        lblThongTinKhachHang.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblThongTinKhachHang.setBounds(20, 20, 219, 40);
        pTenKhachHang.add(lblThongTinKhachHang);

        txtDiaChi = new JTextField();
        txtDiaChi.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDiaChi.setEditable(false);
        txtDiaChi.setColumns(10);
        txtDiaChi.setBounds(174, 220, 336, 40);
        txtDiaChi.setFocusable(false);
        pTenKhachHang.add(txtDiaChi);

        JPanel pChiTietPhieuDatHang = new JPanel();
        pChiTietPhieuDatHang.setBackground(new Color(255, 255, 255));
        pChiTietPhieuDatHang.setToolTipText("");
        pChiTietPhieuDatHang.setLayout(null);
        pChiTietPhieuDatHang.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        pChiTietPhieuDatHang.setBounds(0, 300, 1298, 420);
        pnlMain.add(pChiTietPhieuDatHang);

        JScrollPane scrollPane;
        scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 70, 1218, 296);
        scrollPane.setToolTipText("Chọn vào sản phẩm cần hiển thị thông tin");
        scrollPane.setBorder(new LineBorder(new Color(80, 80, 80), 1, true));
        scrollPane.setBackground(new Color(80, 80, 80));
        pChiTietPhieuDatHang.add(scrollPane);

        String cols[] = {"Tên Mặt Hàng", "Tên Loại", "Số Lượng", "Đơn Giá", "Thành Tiền"};
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
        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                int row = table.getSelectedRow();
                if (model.getValueAt(row, 1).toString().equals("Sách")) {
                    cbLoaiSP.setSelectedIndex(0);
                    ;
                } else {
                    cbLoaiSP.setSelectedIndex(1);
                }
                try {
                    loadDataIntoComboboxTenSP(cbLoaiSP.getSelectedItem().toString());
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                cbTenSP.setSelectedItem(model.getValueAt(row, 0));
                txtSoLuong.setText(model.getValueAt(row, 2).toString());
                try {
                    txtConLai.setText((sanPham_DAO.getSanPhamTheoTen(cbTenSP.getSelectedItem().toString()).getSoLuongTon() - Integer.parseInt(txtSoLuong.getText())) + "");
                } catch (NumberFormatException | RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
        scrollPane.setViewportView(table);

        // header of table
        tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(73, 129, 158));
        tableHeader.setForeground(Color.white);
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
        tableHeader.setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        JLabel lblThngTinPhieuDatHang = new JLabel("Thông Tin Hóa Đơn");
        lblThngTinPhieuDatHang.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblThngTinPhieuDatHang.setBounds(20, 20, 203, 40);
        pChiTietPhieuDatHang.add(lblThngTinPhieuDatHang);

        JLabel lblTongTien = new JLabel("Tổng Tiền:");
        lblTongTien.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTongTien.setBounds(70, 373, 110, 40);
        pChiTietPhieuDatHang.add(lblTongTien);

        lblTongTienValue = new JLabel("");
        lblTongTienValue.setHorizontalAlignment(SwingConstants.CENTER);
        lblTongTienValue.setForeground(new Color(255, 0, 0));
        lblTongTienValue.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTongTienValue.setBounds(175, 373, 166, 40);
        lblTongTienValue.setBorder(new LineBorder(new Color(0, 0, 0)));
        pChiTietPhieuDatHang.add(lblTongTienValue);

        JButton btnDatHang = new JButton("Đặt");
        btnDatHang.setIcon(new ImageIcon(DatHang_GUI.class.getResource("/image/HeThong/wallet.png")));
        btnDatHang.setBackground(new Color(73, 129, 158));
        btnDatHang.setForeground(new Color(255, 255, 255));
        btnDatHang.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnDatHang.setBounds(1093, 373, 135, 40);
        btnDatHang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (tinhThanhTien() > 0) {
                    try {
                        lapPhieuDatHang(nhanVien.getMaNhanVien());
                    } catch (SQLException | RemoteException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào!");
                }
            }
        });
        pChiTietPhieuDatHang.add(btnDatHang);

        JPanel pThongTinKH = new JPanel();
        pThongTinKH.setBackground(new Color(255, 255, 255));
        pThongTinKH.setBorder(
                new LineBorder(new Color(0, 0, 0), 2));
        pThongTinKH.setBounds(550, 0, 748, 280);
        pnlMain.add(pThongTinKH);
        pThongTinKH.setLayout(null);

        JLabel lblLoaiSP = new JLabel("Loại Sản Phẩm:");
        lblLoaiSP.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblLoaiSP.setBounds(23, 70, 134, 40);
        pThongTinKH.add(lblLoaiSP);

        JLabel lblTenSP = new JLabel("Tên Sản Phẩm:");
        lblTenSP.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblTenSP.setBounds(23, 120, 134, 40);
        pThongTinKH.add(lblTenSP);

        JLabel lblSoLuong = new JLabel("Số Lượng:");
        lblSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblSoLuong.setBounds(23, 220, 134, 40);
        pThongTinKH.add(lblSoLuong);


        cbLoaiSP = new JComboBox<String>();
        cbLoaiSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cbLoaiSP.setBounds(170, 70, 284, 40);
        cbLoaiSP.addItem("Sách");
        cbLoaiSP.addItem("Dụng cụ học tập");
        cbLoaiSP.setSelectedIndex(-1);
        cbLoaiSP.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbLoaiSP.getSelectedIndex() != -1) {
                    try {
                        loadDataIntoComboboxTenSP(cbLoaiSP.getSelectedItem().toString());
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    cbTenSP.setEnabled(true);
                } else {
                    cbTenSP.setEnabled(false);
                }
            }
        });
        pThongTinKH.add(cbLoaiSP);

        cbTenSP = new JComboBox<String>();
        cbTenSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cbTenSP.setBounds(170, 120, 284, 40);
        cbTenSP.setSelectedIndex(-1);
        cbTenSP.setEnabled(false);
        cbTenSP.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbTenSP.getSelectedIndex() != -1) {
                    try {
                        sanPham = sanPham_DAO.getSanPhamTheoTen(cbTenSP.getSelectedItem().toString());
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    txtConLai.setText(sanPham.getSoLuongTon() + "");
                    txtMaSanPham.setText(sanPham.getMaSanPham());
                    txtSoLuong.setEnabled(true);
                } else {
                    txtSoLuong.setEnabled(false);
                }
            }
        });
        pThongTinKH.add(cbTenSP);

        JButton btnThem = new JButton("Thêm");
        btnThem.setIcon(new ImageIcon(DatHang_GUI.class.getResource("/image/HeThong/cart.png")));
        btnThem.setBackground(new Color(73, 129, 158));
        btnThem.setForeground(new Color(255, 255, 255));
        btnThem.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnThem.setBounds(462, 170, 130, 40);
        btnThem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (khachHang == null) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng!");
                    } else if (sanPham == null) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm!");
                    } else if (Integer.parseInt(txtConLai.getText()) < Integer.parseInt(txtSoLuong.getText())) {
                        JOptionPane.showMessageDialog(null, "Không đủ sản phẩm!");
                    } else if (Integer.parseInt(txtSoLuong.getText()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn không!");
                    } else {
                        if (trungSanPham(cbTenSP.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(null, "Đã có sản phẩm này!");
                        } else {
                            Object[] objects = {cbTenSP.getSelectedItem().toString(), cbLoaiSP.getSelectedItem().toString(), txtSoLuong.getText(), sanPham.getGiaBan(), Integer.parseInt(txtSoLuong.getText()) * sanPham.getGiaBan()};
                            model.addRow(objects);
                            lblTongTienValue.setText(tinhThanhTien() + " VND");
                            try {
                                txtConLai.setText(Integer.parseInt(txtConLai.getText()) - Integer.parseInt(txtSoLuong.getText()) + "");
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }

                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải là số và không được để trống!");
                }
            }
        });
        pThongTinKH.add(btnThem);

        txtSoLuong = new JTextField();
        txtSoLuong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnThem.doClick();
                }
            }
        });
        txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtSoLuong.setBounds(170, 220, 282, 40);
        txtSoLuong.setEnabled(false);
        pThongTinKH.add(txtSoLuong);
        txtSoLuong.setColumns(10);

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setIcon(new ImageIcon(DatHang_GUI.class.getResource("/image/HeThong/trash-can.png")));
        btnXoa.setBackground(new Color(73, 129, 158));
        btnXoa.setForeground(new Color(255, 255, 255));
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showInternalMessageDialog(null, "Bạn phải chọn sản phẩm cần xóa!");
                } else {
                    int option = JOptionPane.showConfirmDialog(null,
                            "Bạn có chắc muốn xóa sản phẩm '" + model.getValueAt(row, 0) + "' chứ?", "Xóa?",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        model.removeRow(row);
                        lblTongTienValue.setText(tinhThanhTien() + " VND");
                    }
                }
            }
        });
        btnXoa.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnXoa.setBounds(602, 170, 136, 40);
        pThongTinKH.add(btnXoa);

        JButton btnLamMoi = new JButton("Làm Mới");
        btnLamMoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lamMoi();
            }
        });
        btnLamMoi.setIcon(new ImageIcon(DatHang_GUI.class.getResource("/image/HeThong/refresh.png")));
        btnLamMoi.setBackground(new Color(73, 129, 158));
        btnLamMoi.setForeground(new Color(255, 255, 255));
        btnLamMoi.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLamMoi.setBounds(602, 220, 136, 40);
        pThongTinKH.add(btnLamMoi);

        JLabel lblThongTinSanPham = new JLabel("Thông Tin Sản Phẩm");
        lblThongTinSanPham.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblThongTinSanPham.setBounds(20, 20, 192, 40);
        pThongTinKH.add(lblThongTinSanPham);

        JLabel lblConLai = new JLabel("Còn Lại:");
        lblConLai.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblConLai.setBounds(23, 170, 134, 40);
        pThongTinKH.add(lblConLai);

        txtConLai = new JTextField();
        txtConLai.setEditable(false);
        txtConLai.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtConLai.setColumns(10);
        txtConLai.setBounds(170, 170, 282, 40);
        txtConLai.setFocusable(false);
        pThongTinKH.add(txtConLai);

        JButton btnSua = new JButton("Sửa");
        btnSua.setIcon(new ImageIcon(DatHang_GUI.class.getResource("/image/HeThong/update.png")));
        btnSua.setForeground(Color.WHITE);
        btnSua.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSua.setBackground(new Color(73, 129, 158));
        btnSua.setBounds(462, 220, 130, 40);
        btnSua.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showInternalMessageDialog(null, "Bạn phải chọn sản phẩm cần sửa!");
                } else {
                    SanPham sanPham;
                    try {
                        sanPham = sanPham_DAO.getSanPhamTheoMa(txtMaSanPham.getText());
                        if (sanPham.getSoLuongTon() < Integer.parseInt(txtSoLuong.getText())) {
                            JOptionPane.showMessageDialog(null, "Không đủ sản phẩm!");
                        } else {
                            model.setValueAt(cbTenSP.getSelectedItem().toString(), row, 0);
                            model.setValueAt(cbLoaiSP.getSelectedItem().toString(), row, 1);
                            try {
                                if (Integer.parseInt(txtSoLuong.getText()) <= 0) {
                                    JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn không!");
                                } else if (model.getValueAt(row, 2).toString().equals(txtSoLuong.getText())) {
                                    JOptionPane.showMessageDialog(null, "Bạn chưa thay đổi số lượng!");
                                } else {
                                    model.setValueAt(txtSoLuong.getText(), row, 2);
                                    txtConLai.setText(sanPham.getSoLuongTon() - Integer.parseInt(txtSoLuong.getText()) + "");
                                }
                                model.setValueAt(sanPham.getGiaBan(), row, 3);
                                model.setValueAt(Integer.parseInt(txtSoLuong.getText()) * sanPham.getGiaBan(), row, 4);
                                lblTongTienValue.setText(tinhThanhTien() + " VND");
                            } catch (Exception e2) {
                                JOptionPane.showMessageDialog(null, "Số lượng phải là số!");
                            }
                        }
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        pThongTinKH.add(btnSua);

        txtMaSanPham = new JTextField();
        txtMaSanPham.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txtMaSanPham.getText().equals("")) {
                        lamMoiThongTinSanPham();
                    } else {
                        try {
                            sanPham = sanPham_DAO.getSanPhamTheoMa(txtMaSanPham.getText());
                            String tenSanPham = sanPham.getTenSanPham();
                            int soLuongTon = sanPham.getSoLuongTon();
                            if (sanPham.getMaSanPham() == null) {
                                JOptionPane.showMessageDialog(null, "Không có sản phẩm này!");
                                lamMoi();
                            } else {
                                txtConLai.setText(sanPham.getSoLuongTon() + "");
                                cbTenSP.setSelectedItem(sanPham.getTenSanPham().toString());
                                if (txtMaSanPham.getText().charAt(0) == 'S' || txtMaSanPham.getText().charAt(0) == 's') {
                                    cbLoaiSP.setSelectedIndex(0);
                                } else {
                                    cbLoaiSP.setSelectedIndex(1);
                                }
                                txtConLai.setText(soLuongTon + "");
                                cbTenSP.setSelectedItem(tenSanPham);
                            }
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        txtMaSanPham.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtMaSanPham.setColumns(10);
        txtMaSanPham.setBounds(505, 70, 233, 40);
        pThongTinKH.add(txtMaSanPham);

        JLabel lblMaSanPham = new JLabel("Mã:");
        lblMaSanPham.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblMaSanPham.setBounds(465, 70, 40, 40);
        pThongTinKH.add(lblMaSanPham);

        JLabel lblTimSanPham = new JLabel("Tìm:");
        lblTimSanPham.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblTimSanPham.setBounds(464, 120, 41, 40);
        pThongTinKH.add(lblTimSanPham);

        txtSearchSanPham = new JTextField();
        txtSearchSanPham.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtSearchSanPham.setColumns(10);
        txtSearchSanPham.setBounds(505, 120, 233, 40);
        txtSearchSanPham.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchText = txtSearchSanPham.getText().toLowerCase();
                    if (searchText.isEmpty()) {
                        // If search text is empty, show all items
                        try {
                            loadDataIntoComboboxTenSP(cbLoaiSP.getSelectedItem().toString());
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        // Filter items based on the search text
                        ArrayList<String> filteredItems = new ArrayList<>();
                        for (String item : getDanhSachComboBoxTenSanPham()) {
                            if (item.toLowerCase().contains(searchText)) {
                                filteredItems.add(item);
                            }
                        }
                        themArrayListVaoComboBox(filteredItems, cbTenSP);
                    }
                }
            }
        });
        pThongTinKH.add(txtSearchSanPham);

    }

    private void themArrayListVaoComboBox(ArrayList<String> a, JComboBox<String> b) {
        b.removeAllItems();
        for (String item : a) {
            b.addItem(item);
        }
    }

    private ArrayList<String> getDanhSachComboBoxTenSanPham() {
        ArrayList<String> ds = new ArrayList<String>();
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbTenSP.getModel();
        int size = model.getSize();
        for (int i = 0; i < size; i++) {
            String item = model.getElementAt(i);
            ds.add(item);
        }
        return ds;
    }

    // load data ten sach vao combobox
    private void loadDataIntoComboboxTenSP(String loaiSanPham) throws RemoteException {
        cbTenSP.removeAllItems();
        if (loaiSanPham.equals("Sách")) {
            for (SanPham sanPham : sach_DAO.getAllSach()) {
                cbTenSP.addItem(sanPham.getTenSanPham());
            }
        } else {
            for (SanPham sanPham : dungCuHocTap_DAO.getAllDungCuHocTap()) {
                cbTenSP.addItem(sanPham.getTenSanPham());
            }
        }
    }

    private float tinhThanhTien() {
        float thanhTien = 0;
        try {
            thanhTien = Float.parseFloat(model.getValueAt(0, 4).toString());
            for (int i = 1; i < model.getRowCount(); i++) {
                thanhTien += Float.parseFloat(model.getValueAt(i, 4).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thanhTien;
    }

    private boolean trungSanPham(String tenSanPham) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(tenSanPham)) {
                return true;
            }
        }
        return false;
    }

//	private int soLuongSanPhamDuTinh(String tenSanPham) { 
//		int soLuong = sanPham_DAO.getSanPhamTheoTenSanPham(tenSanPham).getSoLuongTon();
//		for (int i = 0; i < model.getRowCount(); i++) {
//			if (model.getValueAt(i, 0).equals(tenSanPham)) {
//				return true;
//			}
//		}
//		return soLuong;
//	}

    private void lapPhieuDatHang(String maNhanVien) throws SQLException, RemoteException {
    	PhieuDatHang phieuDatHang = new PhieuDatHang();
    	if (khachHang != null) {
			phieuDatHang.setKhachHang(khachHang);
		}
        phieuDatHang.setNhanVien(nhanVien_DAO.getNhanVienTheoMa(maNhanVien));
        phieuDatHang.setNgayLap(new java.sql.Date(new Date().getTime()));
        phieuDatHang.setThanhTien(tinhThanhTien());
        phieuDatHang = phieuDatHang_DAO.lapPhieuDatHang(phieuDatHang);
        for (int i = 0; i < model.getRowCount(); i++) {
            SanPham sanPham = sanPham_DAO.getSanPhamTheoTen(model.getValueAt(i, 0).toString());
            ChiTietPhieuDatHang chiTietPhieuDatHang = new ChiTietPhieuDatHang();
            ChiTietPhieuDatKey chiTietPhieuDatKey = new ChiTietPhieuDatKey();
            int soLuong = Integer.parseInt(model.getValueAt(i, 2).toString());
            chiTietPhieuDatKey.setMaSanPham(sanPham.getMaSanPham());
            chiTietPhieuDatKey.setMaPhieuDatHang(phieuDatHang.getMaPhieuDatHang());
            chiTietPhieuDatHang.setPhieuDatHang(phieuDatHang);
            chiTietPhieuDatHang.setSanPham(sanPham);
            chiTietPhieuDatHang.setId(chiTietPhieuDatKey);
            chiTietPhieuDatHang.setSoLuong(soLuong);
            chiTietPhieuDatHang.setDonGia(Float.parseFloat(model.getValueAt(i, 3).toString()));
            chiTietPhieuDatHang_DAO.themChiTietPhieuDatHang(chiTietPhieuDatHang);
            if (!sanPham_DAO.banSanPham(sanPham.getMaSanPham(), soLuong)) {
				JOptionPane.showMessageDialog(null, "Sản phẩm này đã hết hàng!");
			};
        }

        JOptionPane.showMessageDialog(null, "Đặt hàng thành công!");
        danhSachDatHang_GUI.refresh();
    }

    private void lamMoi() {
    	khachHang = null;
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSoDienThoai.setText("");
        txtDiaChi.setText("");
        model.setRowCount(0);
        lamMoiThongTinSanPham();
    }

    private void lamMoiThongTinSanPham() {
        cbLoaiSP.setSelectedIndex(-1);
        cbTenSP.setSelectedIndex(-1);
        txtConLai.setText("");
        txtSoLuong.setText("");
        txtMaSanPham.setText("");
        txtSearchSanPham.setText("");
    }
}
