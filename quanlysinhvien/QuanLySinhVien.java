package quanlysinhvien;

import ui.QuanLySinhVienFrame; // NHỚ IMPORT cái form Swing tao tạo cho bạn

public class QuanLySinhVien {

    public static void main(String[] args) {

        // Chạy UI Swing trên luồng EDT
        javax.swing.SwingUtilities.invokeLater(() -> {
            new QuanLySinhVienFrame().setVisible(true);
        });

    }
}
