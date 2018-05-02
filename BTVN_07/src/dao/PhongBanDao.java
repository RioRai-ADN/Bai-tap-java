package dao;

import com.sun.org.glassfish.external.statistics.annotations.Reset;
import connect.MySqlConnection;
import model.PhongBan;

import java.sql.*;
import java.util.ArrayList;

public class PhongBanDao {

    /**
     * lay danh sach cac phong ban
     * table PHONGBAN
     * @return ArrayList
     * @throws SQLException
     */
    public ArrayList layDanhSachPhongBan() throws SQLException {

        ArrayList<PhongBan> list = new ArrayList <>();

        Connection cn = MySqlConnection.getConnection();
        String sql = "SELECT * from PHONGBAN";

        PreparedStatement ps = (PreparedStatement) cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            PhongBan pb = new PhongBan();
            pb.setMaPhg(rs.getInt("maPhg"));
            pb.setTenPhg(rs.getString("tenPhg"));

            list.add(pb);
        }

        cn.close();
        return list;
    }

    /**
     * lay danh sach phong ban theo TEN PHONG
     * nguoi dung nhap tu ban phim
     * @param tenPhong
     * @return ArrayList
     * @throws SQLException
     */
    public ArrayList layDanhSachPhongBan(String tenPhong) throws SQLException {

        ArrayList<PhongBan> list = new ArrayList <>();

        Connection cn = MySqlConnection.getConnection();
        String sql = "SELECT * from PHONGBAN where tenPhg like '%"+tenPhong+"%'";

        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            PhongBan p = new PhongBan();

            p.setMaPhg(rs.getInt("maPhg"));
            p.setTenPhg(rs.getString("tenPhg"));

            list.add(p);
        }

        cn.close();
        return list;
    }

    /**
     * them 1 phong ban voi TEN PHONG nhap tu ban phim
     * bang PHONGBAN co maPhong la rimary key AUTO_INCREMENT
     * tu tang maPhong len 1 khi them thanh cong!
     * @param tenPhongBan
     * @throws SQLException
     */
    public void themPhongBan(String tenPhongBan) throws SQLException {

        Connection cn = MySqlConnection.getConnection();
        String sql = "INSERT into PHONGBAN (tenPhg) values ('"+tenPhongBan+"')";

        Statement statement = cn.createStatement();

        int n = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

        if (n > 0) {
            System.out.println("Them thanh cong!!!");

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {

                int maPhong = rs.getInt(1);
                System.out.println("Ma phong vua them: " + maPhong);
            }
        }
        cn.close();
    }

    /**
     * dem so luong nhan vien trong phong ban
     * nhap ma phong
     * @param maPhong
     * @return int
     * @throws SQLException
     */
    public int demSoNhanVienPhongBan(int maPhong) throws SQLException {

        int count = 0;

        Connection cnn = MySqlConnection.getConnection();
        String sql = "SELECT COUNT(*) from NHANVIEN where phong = " + maPhong;

        PreparedStatement ps = cnn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next())
            count = rs.getInt(1);

        cnn.close();
        return count;
    }

    /**
     * tinh tong luong cua cac nhan vien trong phong ban
     * bang cach nhap ma phong can tinh
     * @param maPhong
     * @return long
     * @throws SQLException
     */
    public long tongLuongPhongBan(int maPhong) throws SQLException {

        long sum = 0;
        Connection cnn = MySqlConnection.getConnection();
        String sql = "SELECT SUM(luong) from NHANVIEN where phong = " + maPhong;

        PreparedStatement ps = cnn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next())
            sum = rs.getLong(1);

        cnn.close();
        return sum;
    }

    public void xuatThongKe() throws SQLException {

        ArrayList<PhongBan> list = layDanhSachPhongBan();
        long tongLuong = 0;
        int soLuongNhanVien = 0;
        double luongTB;
        int maPhg = 0;

        for (int i = 0; i < list.size(); i++) {

            maPhg = list.get(i).getMaPhg();
            soLuongNhanVien = demSoNhanVienPhongBan(maPhg);
            System.out.println("So nhan vien trong phong["+maPhg+"]:" + soLuongNhanVien);
            tongLuong = tongLuongPhongBan(maPhg);
            System.out.println("Tong Luong: " + tongLuong);
            luongTB = tongLuong/soLuongNhanVien;
            System.out.println("Luong Trung binh: " + luongTB);
            System.out.println("--------------------------");
        }


    }
    public static void main(String args[]) throws SQLException {

        PhongBanDao p = new PhongBanDao();
        p.xuatThongKe();
    }
}
