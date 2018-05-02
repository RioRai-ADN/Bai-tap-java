package dao;

import connect.MySqlConnection;
import model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class NhanVienDao {

    /**
     * them 1 nhan vien
     * ngay nhap con sai
     * @throws ParseException
     * @throws SQLException
     */
    public void themNhanVien() throws ParseException, SQLException {

        NhanVien nv = new NhanVien();
        nv.nhap();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = nv.getNgaySinh();
        String ngaySinh = format.format(date);

        Connection cn = MySqlConnection.getConnection();
        String sql = "INSERT into NHANVIEN(maNv, hoTen, phai, ngaySinh, diaChi, luong, phong)" +
                " values ('"+nv.getMaNv()+"', '"+nv.getHoTenNv()+"','"+nv.getGioiTinh()+"', " +
                "'"+ngaySinh+"', '"+nv.getDiaChi()+"', "+nv.getLuong()+","+nv.getMaPhong()+")";

        PreparedStatement ps = cn.prepareStatement(sql);
        int n = ps.executeUpdate();
        if (n > 0)
            System.out.println("Them Thanh Cong!!!");
        else
            System.out.println("Them that bai! Hay thu lai.");

        cn.close();
    }

    /**
     * cap nhat thong tin nhan vien
     * nhap ma nhan vien tu ban phim
     * chua bat loi nguoi dung
     * @throws SQLException
     */
    public void capNhatNhanVien() throws SQLException {

        NhanVien nv = new NhanVien();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ma nhan vien: ");
        nv.setMaNv(scanner.nextLine());
        System.out.print("Nhap ho ten nhan vien: ");
        nv.setHoTenNv(scanner.nextLine());
        System.out.print("Nhap gioi tinh: ");
        nv.setGioiTinh(scanner.next());
        System.out.print("Nhap dia chi: ");
        nv.setDiaChi(scanner.nextLine());
        System.out.print("Nhap luong: ");
        nv.setLuong(scanner.nextLong());
        System.out.print("Nhap ma Phong ban: ");
        nv.setMaPhong(scanner.nextInt());

        Connection cn = MySqlConnection.getConnection();
        String sql = "UPDATE NHANVIEN set hoTen = '"+nv.getHoTenNv()+"', phai = '"+nv.getGioiTinh()+"', diaChi = '"+nv.getDiaChi()+"'," +
                "luong = "+nv.getLuong()+", phong = "+nv.getMaPhong()+" where maNv = '"+nv.getMaNv()+"'";

        PreparedStatement ps = cn.prepareStatement(sql);
        int n = ps.executeUpdate();

        if (n > 0)
            System.out.println("Cap nhat thanh cong!!");
    }
    public static void main(String args[]) throws ParseException, SQLException {

        NhanVienDao d = new NhanVienDao();
        d.themNhanVien();
    }
}
