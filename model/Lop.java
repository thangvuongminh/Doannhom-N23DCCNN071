/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Lop {
    private String maLop;
    private String tenLop;
    private String heDaoTao;
    private String maKhoa; // Khóa ngoại trỏ sang bảng Khoa

    public Lop() {
    }

    public Lop(String maLop, String tenLop, String heDaoTao, String maKhoa) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.heDaoTao = heDaoTao;
        this.maKhoa = maKhoa;
    }

    // Getters and Setters
    public String getMaLop() { return maLop; }
    public void setMaLop(String maLop) { this.maLop = maLop; }

    public String getTenLop() { return tenLop; }
    public void setTenLop(String tenLop) { this.tenLop = tenLop; }

    public String getHeDaoTao() { return heDaoTao; }
    public void setHeDaoTao(String heDaoTao) { this.heDaoTao = heDaoTao; }

    public String getMaKhoa() { return maKhoa; }
    public void setMaKhoa(String maKhoa) { this.maKhoa = maKhoa; }
}
