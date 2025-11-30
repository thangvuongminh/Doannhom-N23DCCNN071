package quanlysinhvien;

import ui.QuanLySinhVienFrame; 

public class QuanLySinhVien {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new QuanLySinhVienFrame().setVisible(true);
        });

    }
}
