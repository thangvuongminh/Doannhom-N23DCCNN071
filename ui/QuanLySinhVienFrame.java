package ui;

import model.SinhVien;
import model.Diem;
import service.SinhVienService;
import service.impl.SinhVienServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class QuanLySinhVienFrame extends JFrame {

    private SinhVienService sinhVienService = new SinhVienServiceImpl();

   
    private JTextField txtMssv;
    private JTextField txtHoTen;
    private JTextField txtNgaySinh;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;
    private JTextField txtNganhHoc;
    private JTextField txtDiem;     

    private JTable tblSinhVien;
    private DefaultTableModel tblModel;

    private JTable tblDiem;
    private DefaultTableModel tblDiemModel;

    private JTextArea txtBaoCao;

    public QuanLySinhVienFrame() {
        initUI();
        initTables();
        loadAll();
    }

    private void initUI() {
        setTitle("CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);

        JPanel pnlInfo = new JPanel();
        pnlInfo.setBorder(BorderFactory.createTitledBorder("Thông Tin Sinh Viên"));
        pnlInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblMssv = new JLabel("MSSV:*");
        JLabel lblHoTen = new JLabel("Họ Tên:*");
        JLabel lblNgaySinh = new JLabel("Ngày Sinh:");
        JLabel lblGioiTinh = new JLabel("Giới Tính:");
        JLabel lblNganhHoc = new JLabel("Ngành Học / Mã Lớp:");
        JLabel lblDiem = new JLabel("Điểm (vd: 8, 9.5):");

        txtMssv = new JTextField();
        txtHoTen = new JTextField();
        txtNgaySinh = new JTextField();
        txtNganhHoc = new JTextField();
        txtDiem = new JTextField();

        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        ButtonGroup bgGioiTinh = new ButtonGroup();
        bgGioiTinh.add(rdoNam);
        bgGioiTinh.add(rdoNu);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlInfo.add(lblMssv, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        pnlInfo.add(txtMssv, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlInfo.add(lblHoTen, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        pnlInfo.add(txtHoTen, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlInfo.add(lblNgaySinh, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        pnlInfo.add(txtNgaySinh, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlInfo.add(lblGioiTinh, gbc);
        JPanel pnlGT = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlGT.add(rdoNam);
        pnlGT.add(rdoNu);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        pnlInfo.add(pnlGT, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlInfo.add(lblNganhHoc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        pnlInfo.add(txtNganhHoc, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 5;
        pnlInfo.add(lblDiem, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        pnlInfo.add(txtDiem, gbc);
        gbc.gridwidth = 1;

        tblModel = new DefaultTableModel();
        tblSinhVien = new JTable(tblModel);
        JScrollPane scrollTable = new JScrollPane(tblSinhVien);
        scrollTable.setBorder(BorderFactory.createTitledBorder("Danh Sách Sinh Viên"));

        tblDiemModel = new DefaultTableModel();
        tblDiem = new JTable(tblDiemModel);
        JScrollPane scrollDiem = new JScrollPane(tblDiem);
        scrollDiem.setBorder(BorderFactory.createTitledBorder("Môn Học & Điểm Của Sinh Viên Đang Chọn"));

        txtBaoCao = new JTextArea();
        txtBaoCao.setEditable(false);
        JScrollPane scrollBaoCao = new JScrollPane(txtBaoCao);
        scrollBaoCao.setBorder(BorderFactory.createTitledBorder("Báo Cáo Thống Kê"));

        JSplitPane rightSplit = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                scrollBaoCao,
                scrollDiem
        );
        rightSplit.setResizeWeight(0.5);

        JSplitPane splitCenter = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                scrollTable,
                rightSplit
        );
        splitCenter.setResizeWeight(0.55);

        JPanel pnlButtons = new JPanel(new GridLayout(2, 6, 5, 5));
        pnlButtons.setBorder(BorderFactory.createTitledBorder("Bảng Điều Khiển"));

        JButton btnThemMoi = new JButton("Thêm Mới");
        JButton btnCapNhat = new JButton("Cập Nhật");
        JButton btnXoa = new JButton("Xóa");
        JButton btnLamMoi = new JButton("Làm Mới Form");

        JButton btnTimMssv = new JButton("Tìm theo MSSV");
        JButton btnTimTen = new JButton("Tìm theo Tên");
        JButton btnTimNganh = new JButton("TK Lớp");
        JButton btnLamMoiDS = new JButton("Làm Mới DS");
        JButton btnSapXepTen = new JButton("Sắp Xếp Tên");
        JButton btnSapXepNgaySinh = new JButton("Sắp Xếp Ngày Sinh");
        JButton btnXemDiem = new JButton("Xem Điểm (1 SV)");

        JButton btnTkNganh = new JButton("TK Ngành (cũ)");
        JButton btnTkGioiTinh = new JButton("TK Giới Tính");
        JButton btnTopSv = new JButton("Top SV");

        pnlButtons.add(btnThemMoi);
        pnlButtons.add(btnCapNhat);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnLamMoi);
        pnlButtons.add(btnTimMssv);
        pnlButtons.add(btnTimTen);

        pnlButtons.add(btnTimNganh);
        pnlButtons.add(btnSapXepTen);
        pnlButtons.add(btnSapXepNgaySinh);
        pnlButtons.add(btnXemDiem);
        pnlButtons.add(btnTkNganh);
        pnlButtons.add(btnTkGioiTinh);
        pnlButtons.add(btnLamMoiDS);

        JPanel pnlBottom = new JPanel(new BorderLayout(5, 5));
        pnlBottom.add(pnlButtons, BorderLayout.CENTER);
        JPanel pnlTopSV = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlTopSV.add(btnTopSv);
        pnlBottom.add(pnlTopSV, BorderLayout.SOUTH);

        setLayout(new BorderLayout(5, 5));
        add(pnlInfo, BorderLayout.NORTH);
        add(splitCenter, BorderLayout.CENTER);
        add(pnlBottom, BorderLayout.SOUTH);

        tblSinhVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblSinhVien.getSelectedRow();
                if (row >= 0) {
                    fillFormFromTable(row);
                    String mssv = (String) tblModel.getValueAt(row, 0);
                    loadDiemForStudent(mssv);
                }
            }
        });

        btnThemMoi.addActionListener(e -> onThemMoi());
        btnCapNhat.addActionListener(e -> onCapNhat());
        btnXoa.addActionListener(e -> onXoa());
        btnLamMoi.addActionListener(e -> clearForm());

        btnTimMssv.addActionListener(e -> onTimMssv());
        btnTimTen.addActionListener(e -> onTimTen());

        btnTimNganh.addActionListener(e -> onThongKeLop());

        btnSapXepTen.addActionListener(e -> onSapXepTen());
        btnSapXepNgaySinh.addActionListener(e -> onSapXepNgaySinh());

        btnXemDiem.addActionListener(e -> onXemDiemTheoMon());

        btnTkNganh.addActionListener(e -> onThongKeNganhCu());
        btnLamMoiDS.addActionListener(e -> onLamMoiDanhSach());
        btnTkGioiTinh.addActionListener(e -> onThongKeGioiTinh());
        btnTopSv.addActionListener(e -> onTopSV());
    }

    private void initTables() {
        tblModel.setColumnIdentifiers(new String[]{
            "MSSV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Mã Lớp"
        });

        tblDiemModel.setColumnIdentifiers(new String[]{
            "Mã Môn", "Điểm Tổng Kết"
        });
    }

    private void loadAll() {
        List<SinhVien> list = sinhVienService.getAll();
        loadTable(list);
        clearDiemTable();
        txtBaoCao.setText("Đã load " + list.size() + " sinh viên.");
    }

    private void loadTable(List<SinhVien> list) {
        tblModel.setRowCount(0);
        for (SinhVien sv : list) {
            tblModel.addRow(new Object[]{
                sv.getMsv(),
                sv.getHoVaTen(),
                sv.getNgaySinh(),
                sv.isGioiTinh() ? "Nam" : "Nữ",
                sv.getMaLop()
            });
        }
    }

    private void fillFormFromTable(int row) {
        String mssv = (String) tblModel.getValueAt(row, 0);
        SinhVien sv = sinhVienService.findByMssv(mssv);
        if (sv == null) {
            return;
        }

        txtMssv.setText(sv.getMsv());
        txtHoTen.setText(sv.getHoVaTen());
        txtNgaySinh.setText(sv.getNgaySinh());
        if (sv.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtNganhHoc.setText(sv.getMaLop()); 
    }

    private SinhVien getSinhVienFromForm() {
        String mssv = txtMssv.getText().trim();
        String hoTen = txtHoTen.getText().trim();
        String ngaySinh = txtNgaySinh.getText().trim();
        boolean gioiTinh = rdoNam.isSelected();
        String maLop = txtNganhHoc.getText().trim();

        if (mssv.isEmpty() || hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "MSSV và Họ tên không được để trống");
            return null;
        }

        return new SinhVien(mssv, hoTen, ngaySinh, gioiTinh, "", maLop);
    }

    private void clearForm() {
        txtMssv.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtNganhHoc.setText("");
        txtDiem.setText("");
        rdoNam.setSelected(true);
        tblSinhVien.clearSelection();
        clearDiemTable();
        txtBaoCao.setText("");
    }

    private void clearDiemTable() {
        tblDiemModel.setRowCount(0);
    }

    private void onThemMoi() {
        SinhVien sv = getSinhVienFromForm();
        if (sv == null) {
            return;
        }

        if (sinhVienService.insert(sv)) {
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công");
            loadAll();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    private void onCapNhat() {
        int row = tblSinhVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng để cập nhật");
            return;
        }
        String mssvCu = (String) tblModel.getValueAt(row, 0);
        SinhVien svMoi = getSinhVienFromForm();
        if (svMoi == null) {
            return;
        }

        if (sinhVienService.update(mssvCu, svMoi)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            loadAll();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
        }
    }

    private void onXoa() {
        int row = tblSinhVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 dòng để xóa");
            return;
        }
        String mssv = (String) tblModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Xóa sinh viên " + mssv + " ?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (sinhVienService.delete(mssv)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadAll();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }
    }

    private void onTimMssv() {
        String mssv = JOptionPane.showInputDialog(this, "Nhập MSSV:");
        if (mssv == null || mssv.isBlank()) {
            return;
        }

        SinhVien sv = sinhVienService.findByMssv(mssv.trim());
        if (sv == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy MSSV");
            return;
        }
        loadTable(List.of(sv));
        clearDiemTable();
        txtBaoCao.setText("Kết quả tìm MSSV: " + mssv);
    }

    private void onTimTen() {
        String ten = JOptionPane.showInputDialog(this, "Nhập tên cần tìm:");
        if (ten == null || ten.isBlank()) {
            return;
        }

        List<SinhVien> list = sinhVienService.searchByName(ten.trim());
        loadTable(list);
        clearDiemTable();
        txtBaoCao.setText("Kết quả tìm theo tên: " + ten + " (" + list.size() + " SV)");
    }

    private void onThongKeLop() {
        Map<String, Integer> map = sinhVienService.thongKeTheoLop();

        StringBuilder sb = new StringBuilder("Thống kê theo LỚP:\n");
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            sb.append("- Lớp: ")
                    .append(e.getKey()) // tên lớp / mã lớp
                    .append(" -> ")
                    .append(e.getValue()) // số SV
                    .append(" sinh viên\n");
        }
        txtBaoCao.setText(sb.toString());
    }

    private void onSapXepTen() {
        List<SinhVien> list = sinhVienService.sortByHoTen(true);
        loadTable(list);
        clearDiemTable();
        txtBaoCao.setText("Đã sắp xếp theo họ tên (A → Z)");
    }

    private void onSapXepNgaySinh() {
        List<SinhVien> list = sinhVienService.sortByNgaySinh(true);
        loadTable(list);
        clearDiemTable();
        txtBaoCao.setText("Đã sắp xếp theo ngày sinh (tăng dần)");
    }

    private void loadDiemForStudent(String mssv) {
        List<Diem> listDiem = sinhVienService.sortDiemByTongKet(mssv, true);
        tblDiemModel.setRowCount(0);

        if (listDiem == null || listDiem.isEmpty()) {
            txtBaoCao.setText("Sinh viên " + mssv + " chưa có dữ liệu điểm.");
            return;
        }

        double sum = 0;
        for (Diem d : listDiem) {
            tblDiemModel.addRow(new Object[]{
                d.getMaMon(),
                d.getTongKet()
            });
            sum += d.getTongKet();
        }
        double avg = sum / listDiem.size();
        txtBaoCao.setText(
                "Điểm các môn của MSSV: " + mssv + "\n"
                + "Số môn học: " + listDiem.size() + "\n"
                + "Điểm TB: " + String.format("%.2f", avg)
        );
    }

    private void onXemDiemTheoMon() {
        int row = tblSinhVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn 1 sinh viên trên bảng trước đã.");
            return;
        }

        String mssv = (String) tblModel.getValueAt(row, 0);
        String hoTen = (String) tblModel.getValueAt(row, 1);

        List<Diem> listDiem = sinhVienService.sortDiemByTongKet(mssv, true);
        tblDiemModel.setRowCount(0);

        if (listDiem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sinh viên " + hoTen + " chưa có dữ liệu điểm.");
            txtBaoCao.setText("Sinh viên " + hoTen + " (" + mssv + ") chưa có dữ liệu điểm.");
            return;
        }

        double sum = 0;
        StringBuilder sb = new StringBuilder("Điểm các môn của " + hoTen + " (" + mssv + "):\n");
        for (Diem d : listDiem) {
            tblDiemModel.addRow(new Object[]{
                d.getMaMon(),
                d.getTongKet()
            });
            sb.append("- Môn: ").append(d.getMaMon())
                    .append(", TK: ").append(d.getTongKet()).append("\n");
            sum += d.getTongKet();
        }
        double avg = sum / listDiem.size();
        sb.append("Điểm trung bình: ").append(String.format("%.2f", avg));
        txtBaoCao.setText(sb.toString());
    }

    private void onThongKeNganhCu() {
        String nganh = JOptionPane.showInputDialog(this, "Nhập ngành (tên khoa) để thống kê:");
        if (nganh == null || nganh.isBlank()) {
            return;
        }

        List<SinhVien> list = sinhVienService.searchFullByNganh(nganh.trim());
        loadTable(list);
        clearDiemTable();

        txtBaoCao.setText("Thống kê ngành '" + nganh + "':\nSố sinh viên: " + list.size());
    }

    private void onLamMoiDanhSach() {
        loadAll();            
        txtBaoCao.setText("Đã làm mới danh sách sinh viên.");
    }

    private void onThongKeGioiTinh() {
        Map<String, Integer> map = sinhVienService.thongKeTheoGioiTinh();
        StringBuilder sb = new StringBuilder("Thống kê giới tính:\n");
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }
        txtBaoCao.setText(sb.toString());
    }

    private void onTopSV() {
        List<SinhVien> all = sinhVienService.getAll();
        double bestAvg = -1;
        SinhVien bestSv = null;

        for (SinhVien sv : all) {
            List<Diem> listDiem = sinhVienService.getDiemByMssv(sv.getMsv());
            if (listDiem.isEmpty()) {
                continue;
            }
            double sum = 0;
            for (Diem d : listDiem) {
                sum += d.getTongKet();
            }
            double avg = sum / listDiem.size();
            if (avg > bestAvg) {
                bestAvg = avg;
                bestSv = sv;
            }
        }

        if (bestSv == null) {
            txtBaoCao.setText("Không có dữ liệu điểm để tính Top SV.");
        } else {
            txtBaoCao.setText("Top SV:\n"
                    + "MSSV: " + bestSv.getMsv() + "\n"
                    + "Họ tên: " + bestSv.getHoVaTen() + "\n"
                    + "Điểm TB: " + String.format("%.2f", bestAvg));
        }
    }

}
