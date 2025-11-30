package dao;

import java.sql.*;
import java.util.*;
import model.Diem;
import model.SinhVien;

public class SinhVienDao extends BaseDAO {

    private SinhVien mapSV(ResultSet rs) throws Exception {
        SinhVien sv = new SinhVien();
        sv.setMsv(rs.getString("MSV"));
        sv.setHoVaTen(rs.getString("Ho_va_ten"));
        sv.setNgaySinh(rs.getString("Ngay_sinh"));
        sv.setGioiTinh(rs.getBoolean("Gioi_tinh"));
        sv.setDiaChi(rs.getString("Dia_chi"));
        sv.setMaLop(rs.getString("Ma_lop"));
        return sv;
    }

    public List<SinhVien> getAll() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapSV(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Map<String, Integer> thongKeTheoLop() {
        Map<String, Integer> map = new LinkedHashMap<>();

        String sql = "SELECT Ma_lop, COUNT(*) AS SoSV FROM SinhVien GROUP BY Ma_lop";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maLop = rs.getString("Ma_lop");  // tên cột đúng trong bảng
                int soSv = rs.getInt("SoSV");
                map.put(maLop, soSv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public SinhVien findByMssv(String mssv) {
        String sql = "SELECT * FROM SinhVien WHERE MSV = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mssv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapSV(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<SinhVien> searchByName(String keyword) {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien WHERE Ho_va_ten LIKE ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapSV(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SinhVien> getAllWithClassKhoa() {
        List<SinhVien> list = new ArrayList<>();

        String sql = """
            SELECT sv.*
            FROM SinhVien sv
            JOIN Lop l ON sv.Ma_lop = l.Ma_lop
            JOIN Khoa k ON l.Ma_khoa = k.Ma_khoa
        """;

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapSV(rs)); // chỉ gán vào field gốc của SinhVien
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public SinhVien findWithClassKhoa(String mssv) {
        String sql = """
            SELECT sv.*
            FROM SinhVien sv
            JOIN Lop l ON sv.Ma_lop = l.Ma_lop
            JOIN Khoa k ON l.Ma_khoa = k.Ma_khoa
            WHERE sv.MSV = ?
        """;

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mssv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapSV(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Map<String, Integer> thongKeTheoKhoa() {
        Map<String, Integer> map = new HashMap<>();

        String sql = """
        SELECT k.Ten_khoa AS TenKhoa, COUNT(*) AS SL
        FROM SinhVien sv
        JOIN Lop l ON sv.Ma_lop = l.Ma_lop
        JOIN Khoa k ON l.Ma_khoa = k.Ma_khoa
        GROUP BY k.Ten_khoa
    """;

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("TenKhoa"), rs.getInt("SL"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public List<SinhVien> searchByNganh(String keyword) {
        List<SinhVien> list = new ArrayList<>();

        String sql = """
            SELECT sv.*
            FROM SinhVien sv
            JOIN Lop l ON sv.Ma_lop = l.Ma_lop
            JOIN Khoa k ON l.Ma_khoa = k.Ma_khoa
            WHERE k.Ten_khoa LIKE ?
        """;

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapSV(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Diem> getDiemByMssv(String mssv) {
        List<Diem> list = new ArrayList<>();

        String sql = "SELECT * FROM Diem WHERE MSV = ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mssv);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Diem d = new Diem();
                d.setMaMon(rs.getString("Ma_mon"));
                d.setMsv(rs.getString("MSV"));
                d.setTongKet(rs.getFloat("Tong_ket"));
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public SinhVien findFullByMssv(String mssv) {
        // Nếu sau này muốn full thật thì tạo DTO riêng, giờ cho compile chạy trước
        return findWithClassKhoa(mssv);
    }

    public List<SinhVien> searchFullByNganh(String keyword) {
        // Tạm thời giống searchByNganh
        return searchByNganh(keyword);
    }

    public Map<String, Integer> thongKeTheoGioiTinh() {
        Map<String, Integer> map = new HashMap<>();

        String sql = "SELECT Gioi_tinh, COUNT(*) AS SL FROM SinhVien GROUP BY Gioi_tinh";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String label = rs.getBoolean("Gioi_tinh") ? "Nam" : "Nữ";
                map.put(label, rs.getInt("SL"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public boolean insert(SinhVien sv) {
        String sql = """
            INSERT INTO SinhVien(MSV, Ho_va_ten, Ngay_sinh, Gioi_tinh, Dia_chi, Ma_lop)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sv.getMsv());
            ps.setString(2, sv.getHoVaTen());
            ps.setString(3, sv.getNgaySinh());
            ps.setBoolean(4, sv.isGioiTinh());
            ps.setString(5, sv.getDiaChi());
            ps.setString(6, sv.getMaLop());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(String oldMssv, SinhVien sv) {
        String sql = """
            UPDATE SinhVien SET
                MSV = ?, Ho_va_ten = ?, Ngay_sinh = ?, Gioi_tinh = ?, Dia_chi = ?, Ma_lop = ?
            WHERE MSV = ?
        """;

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sv.getMsv());
            ps.setString(2, sv.getHoVaTen());
            ps.setString(3, sv.getNgaySinh());
            ps.setBoolean(4, sv.isGioiTinh());
            ps.setString(5, sv.getDiaChi());
            ps.setString(6, sv.getMaLop());
            ps.setString(7, oldMssv);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(String mssv) {
        String sql = "DELETE FROM SinhVien WHERE MSV = ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mssv);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
