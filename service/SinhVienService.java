package service;

import java.util.List;
import java.util.Map;
import model.Diem;
import model.SinhVien;

public interface SinhVienService {
    // ============ 1. CƠ BẢN ============

    List<SinhVien> getAll();

    Map<String, Integer> thongKeTheoLop();

    SinhVien findByMssv(String mssv);

    List<SinhVien> searchByName(String keyword);

    // ============ 2. LỚP + KHOA (KHÔNG ĐIỂM) ============
    List<SinhVien> getAllWithClassKhoa();

    SinhVien findWithClassKhoa(String mssv);

    List<SinhVien> searchByNganh(String keyword);

    // ============ 3. ĐIỂM / FULL PROFILE ============
    List<Diem> getDiemByMssv(String mssv);

    SinhVien findFullByMssv(String mssv);

    List<SinhVien> searchFullByNganh(String keyword);

    Map<String, Integer> thongKeTheoGioiTinh();

    boolean insert(SinhVien sv);

    boolean update(String mssvCu, SinhVien svMoi);

    boolean delete(String mssv);

    // ============ 4. SẮP XẾP (NEW) ============
    List<SinhVien> sortByHoTen(boolean asc);      // asc = true: tăng dần, false: giảm dần

    List<SinhVien> sortByNgaySinh(boolean asc);

    List<Diem> sortDiemByTongKet(String mssv, boolean asc);
}
