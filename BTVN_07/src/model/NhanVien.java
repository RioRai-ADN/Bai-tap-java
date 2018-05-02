package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class NhanVien {

    private String maNv;
    private String hoTenNv;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private long luong;
    private int maPhong;


    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getHoTenNv() {
        return hoTenNv;
    }

    public void setHoTenNv(String hoTenNv) {
        this.hoTenNv = hoTenNv;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public long getLuong() {
        return luong;
    }

    public void setLuong(long luong) {
        this.luong = luong;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public void nhap() throws ParseException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ma nhan vien: ");
        this.maNv = scanner.nextLine();
        System.out.print("Nhap ho ten nhan vien: ");
        this.hoTenNv = scanner.nextLine();
        System.out.print("Nhap gioi tinh: ");
        this.gioiTinh = scanner.nextLine();
        System.out.print("Nhap ngay sinh: ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.ngaySinh = sdf.parse(scanner.nextLine());
        System.out.print("Nhap dia chi: ");
        this.diaChi = scanner.nextLine();
        System.out.print("Nhap luong: ");
        this.luong = scanner.nextLong();
        System.out.print("Nhap ma Phong ban: ");
        this.maPhong = scanner.nextInt();
    }
}
