package service.impl;

import dao.SinhVienDao;
import java.util.List;
import java.util.Map;
import model.Diem;
import model.SinhVien;
import service.SinhVienService;

public class SinhVienServiceImpl implements SinhVienService {

    private SinhVienDao sinhVienDAO = new SinhVienDao();

    @Override
    public List<SinhVien> getAll() {
        return sinhVienDAO.getAll();
    }

    @Override
    public Map<String, Integer> thongKeTheoLop() {
        return sinhVienDAO.thongKeTheoLop();   // gọi xuống DAO
    }

    @Override
    public SinhVien findByMssv(String mssv) {
        return sinhVienDAO.findByMssv(mssv);
    }

    @Override
    public List<SinhVien> searchByName(String keyword) {
        return sinhVienDAO.searchByName(keyword);
    }

    @Override
    public List<SinhVien> getAllWithClassKhoa() {
        return sinhVienDAO.getAllWithClassKhoa();
    }

    @Override
    public SinhVien findWithClassKhoa(String mssv) {
        return sinhVienDAO.findWithClassKhoa(mssv);
    }

    @Override
    public List<SinhVien> searchByNganh(String keyword) {
        return sinhVienDAO.searchByNganh(keyword);
    }

    @Override
    public List<Diem> getDiemByMssv(String mssv) {
        return sinhVienDAO.getDiemByMssv(mssv);
    }

    @Override
    public SinhVien findFullByMssv(String mssv) {
        return sinhVienDAO.findFullByMssv(mssv);
    }

    @Override
    public List<SinhVien> searchFullByNganh(String keyword) {
        return sinhVienDAO.searchFullByNganh(keyword);
    }

    @Override
    public Map<String, Integer> thongKeTheoGioiTinh() {
        return sinhVienDAO.thongKeTheoGioiTinh();
    }

    @Override
    public boolean insert(SinhVien sv) {
        return sinhVienDAO.insert(sv);
    }

    @Override
    public boolean update(String mssvCu, SinhVien svMoi) {
        return sinhVienDAO.update(mssvCu, svMoi);
    }

    @Override
    public boolean delete(String mssv) {
        return sinhVienDAO.delete(mssv);
    }

    @Override
    public List<SinhVien> sortByHoTen(boolean asc) {
        List<SinhVien> list = getAll(); // gọi lại service getAll()

        list.sort((a, b) -> {
            String lastA = getLastName(a.getHoVaTen());
            String lastB = getLastName(b.getHoVaTen());

            return asc
                    ? lastA.compareToIgnoreCase(lastB)
                    : lastB.compareToIgnoreCase(lastA);
        });

        return list;
    }

    private String getLastName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            return "";
        }
        String[] parts = fullName.trim().split("\\s+");
        return parts[parts.length - 1];
    }

    @Override
    public List<SinhVien> sortByNgaySinh(boolean asc) {
        List<SinhVien> list = getAll();
        list.sort((a, b) -> {
            // nếu Ngay_sinh đang là String dd/MM/yyyy thì có thể so sánh String,
            // hoặc parse ra LocalDate cho chuẩn
            int cmp = a.getNgaySinh().compareTo(b.getNgaySinh());
            return asc ? cmp : -cmp;
        });
        return list;
    }

    @Override
    public List<Diem> sortDiemByTongKet(String mssv, boolean asc) {
        List<Diem> list = getDiemByMssv(mssv); // dùng hàm service đã có
        list.sort((a, b) -> {
            int cmp = Float.compare(a.getTongKet(), b.getTongKet());
            return asc ? cmp : -cmp;
        });
        return list;
    }
}
