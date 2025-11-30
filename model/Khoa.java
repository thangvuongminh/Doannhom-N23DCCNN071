/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Khoa {
    private String maKhoa;
    private String tenKhoa;
    private String sdt;
    private String email;

    public Khoa() {
    }

    public Khoa(String maKhoa, String tenKhoa, String sdt, String email) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.sdt = sdt;
        this.email = email;
    }

    // Getters and Setters
    public String getMaKhoa() { return maKhoa; }
    public void setMaKhoa(String maKhoa) { this.maKhoa = maKhoa; }

    public String getTenKhoa() { return tenKhoa; }
    public void setTenKhoa(String tenKhoa) { this.tenKhoa = tenKhoa; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}