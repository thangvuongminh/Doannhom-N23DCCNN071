package controller;

import java.util.List;
import java.util.Map;
import model.Diem;
import model.SinhVien;
import service.SinhVienService;
import service.impl.SinhVienServiceImpl;

public class SinhVienController {

    private SinhVienService sinhVienService = new SinhVienServiceImpl();

    

    public List<SinhVien> getAllSinhVien() {
        return sinhVienService.getAll();
    }

    public SinhVien getSinhVienByMssv(String mssv) {
        return sinhVienService.findByMssv(mssv);
    }

    public List<SinhVien> searchSinhVienByName(String keyword) {
        return sinhVienService.searchByName(keyword);
    }

    

    public List<SinhVien> getAllWithClassKhoa() {
        return sinhVienService.getAllWithClassKhoa();
    }

    public SinhVien getWithClassKhoa(String mssv) {
        return sinhVienService.findWithClassKhoa(mssv);
    }

    public List<SinhVien> searchByNganh(String keyword) {
        return sinhVienService.searchByNganh(keyword);
    }


    public List<Diem> getDiemByMssv(String mssv) {
        return sinhVienService.getDiemByMssv(mssv);
    }

    public List<Diem> sortDiemByTongKet(String mssv, boolean asc) {
        return sinhVienService.sortDiemByTongKet(mssv, asc);
    }

  

    public SinhVien getFullByMssv(String mssv) {
        return sinhVienService.findFullByMssv(mssv);
    }

    public List<SinhVien> searchFullByNganh(String keyword) {
        return sinhVienService.searchFullByNganh(keyword);
    }

   

    public Map<String, Integer> thongKeTheoGioiTinh() {
        return sinhVienService.thongKeTheoGioiTinh();
    }



    public boolean addSinhVien(SinhVien sv) {
        return sinhVienService.insert(sv);
    }

    public boolean updateSinhVien(String mssvCu, SinhVien svMoi) {
        return sinhVienService.update(mssvCu, svMoi);
    }

    public boolean deleteSinhVien(String mssv) {
        return sinhVienService.delete(mssv);
    }

  

    public List<SinhVien> sortSinhVienByHoTen(boolean asc) {
        return sinhVienService.sortByHoTen(asc);
    }

    public List<SinhVien> sortSinhVienByNgaySinh(boolean asc) {
        return sinhVienService.sortByNgaySinh(asc);
    }
}
