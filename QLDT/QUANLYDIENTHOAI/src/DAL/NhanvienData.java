/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import DTO.Nhanvien;
import GUI.JFrameNhanvien;
import Utils.Connect;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;

/**
 *
 * @author Gaara
 */
public class NhanvienData {
    public static PreparedStatement ps;

    public static ResultSet rs;

    public static Connect conn;

    public static ArrayList<Nhanvien> getListnv() {

        ArrayList<Nhanvien> ListNV = new ArrayList<Nhanvien>();
        try {
            ps = (PreparedStatement) Connect.getConnect().prepareStatement("SELECT * FROM nhan_vien order by Ma_Nhan_vien asc");
            rs = ps.executeQuery();
            Nhanvien nv;
            while (rs.next()) {
                nv = new Nhanvien(rs.getString("Ma_Nhan_vien"), rs.getString("Ten_Nhan_vien"), rs.getDate("Ngay_sinh"), rs.getString("Dia_chi"), rs.getString("Phone"),rs.getString("Chucvu"));
                ListNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListNV;
    }

    public static ArrayList<Nhanvien> ListNV(String manv) {
        ArrayList<Nhanvien> list = new ArrayList<Nhanvien>();

        try {
            ps = (PreparedStatement) Connect.getConnect().prepareStatement("SELECT * FROM nhan_vien where Ma_Nhan_vien = '" + JFrameNhanvien.txt8.getText() + "'");
            rs = ps.executeQuery();

            Nhanvien nv;
            while (rs.next()) {
                nv = new Nhanvien(
                        rs.getString("Ma_Nhan_vien"),
                        rs.getString("Ten_Nhan_vien"),
                        rs.getDate("Ngay_sinh"),
                        rs.getString("Dia_chi"),
                        rs.getString("So_dien_thoai"),
                        rs.getString("Chucvu")


                );
                list.add(nv);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static ArrayList<Nhanvien> addStaff(String manv) {
        ArrayList<Nhanvien> list = new ArrayList<Nhanvien>();
        try {
            ps = (PreparedStatement) Connect.getConnect().prepareStatement("INSERT INTO `nhan_vien`"
                    + "(`Ma_Nhan_vien`, `Ten_Nhan_vien`, `Ngay_sinh`, `Dia_chi`, `Phone`,`Chucvu`)"
                    + "VALUES ('" + JFrameNhanvien.txt1.getText() + "','" + JFrameNhanvien.txt2.getText() + "','" + Date.valueOf(JFrameNhanvien.txt3.getText()) + "','" + JFrameNhanvien.txt4.getText() + "','" + JFrameNhanvien.txt5.getText()+"','" + JFrameNhanvien.txt6.getText()+ "')");

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Thêm " + JFrameNhanvien.txt2.getText() + " thành công !! ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

        return list;
    }

    public static ArrayList<Nhanvien> deleteStaff(String manv) {
        ArrayList<Nhanvien> list = new ArrayList<Nhanvien>();
        try {
            ps = (PreparedStatement) Connect.getConnect().prepareStatement("DELETE FROM `nhan_vien` where Ma_nhan_vien = '" + JFrameNhanvien.txt1.getText() + "'");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã xoá " + JFrameNhanvien.txt2.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed");

        }

        return list;
    }

    public static ArrayList<Nhanvien> updateStaff(String manv) {
        ArrayList<Nhanvien> list = new ArrayList<Nhanvien>();

        try {
            ps = (PreparedStatement) Connect.getConnect().prepareStatement("UPDATE `nhan_vien` SET `Ten_Nhan_vien`='" + JFrameNhanvien.txt2.getText() + "',`Ma_Nhan_vien`='" + JFrameNhanvien.txt1.getText() + "',`Ngay_sinh`='" + Date.valueOf(JFrameNhanvien.txt3.getText()) + "',`Dia_chi`='" + JFrameNhanvien.txt4.getText() + "',`Phone`='" + JFrameNhanvien.txt5.getText() + "',`Chucvu`='" + JFrameNhanvien.txt6.getText() + "' WHERE Ma_Nhan_vien = '" + manv+ "'");
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Sửa thành công !! ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

        return list;
    }
    public static void sreachStaff (String sql,JTable tb) {
         try{
            ps = (PreparedStatement) Connect.getConnect().prepareStatement(sql);
            rs = ps.executeQuery();
            tb.setModel((DbUtils.resultSetToTableModel(rs)));
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e ,"Thông báo lỗi",1);
        }
    }
    
}
