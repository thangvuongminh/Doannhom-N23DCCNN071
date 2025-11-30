package model;

public class SinhVien {
    private String msv;
    private String hoVaTen;
    private String ngaySinh;
    private boolean gioiTinh;
    private String diaChi;
    private String maLop; 

    public SinhVien() {
    }

    public SinhVien(String msv, String hoVaTen, String ngaySinh, boolean gioiTinh, String diaChi, String maLop) {
        this.msv = msv;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.maLop = maLop;
    }

    public String getMsv() { return msv; }
    public void setMsv(String msv) { this.msv = msv; }

    public String getHoVaTen() { return hoVaTen; }
    public void setHoVaTen(String hoVaTen) { this.hoVaTen = hoVaTen; }

    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }

    public boolean isGioiTinh() { return gioiTinh; }
    public void setGioiTinh(boolean gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getMaLop() { return maLop; }
    public void setMaLop(String maLop) { this.maLop = maLop; }
}
