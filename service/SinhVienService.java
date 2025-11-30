package service;

import java.util.List;
import java.util.Map;
import model.Diem;
import model.SinhVien;

public interface SinhVienService {
   

    List<SinhVien> getAll();

    Map<String, Integer> thongKeTheoLop();

    SinhVien findByMssv(String mssv);

    List<SinhVien> searchByName(String keyword);

   
    List<SinhVien> getAllWithClassKhoa();

    SinhVien findWithClassKhoa(String mssv);

    List<SinhVien> searchByNganh(String keyword);

 
    List<Diem> getDiemByMssv(String mssv);

    SinhVien findFullByMssv(String mssv);

    List<SinhVien> searchFullByNganh(String keyword);

    Map<String, Integer> thongKeTheoGioiTinh();

    boolean insert(SinhVien sv);

    boolean update(String mssvCu, SinhVien svMoi);

    boolean delete(String mssv);


    List<SinhVien> sortByHoTen(boolean asc);      

    List<SinhVien> sortByNgaySinh(boolean asc);

    List<Diem> sortDiemByTongKet(String mssv, boolean asc);
}
