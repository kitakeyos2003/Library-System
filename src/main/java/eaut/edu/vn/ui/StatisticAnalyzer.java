package eaut.edu.vn.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import eaut.edu.vn.service.LoanDetailService;
import eaut.edu.vn.database.ConnectMySQL;
import eaut.edu.vn.service.ReaderService;
import eaut.edu.vn.service.LoanService;
import eaut.edu.vn.service.BookService;
import eaut.edu.vn.model.LoanDetail;
import eaut.edu.vn.model.Reader;
import eaut.edu.vn.model.Loan;
import eaut.edu.vn.model.Book;

import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.util.Util;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class StatisticAnalyzer extends CustomFrame {
    public String tenTk = "";
    public int thongke = 1;
    JButton btnQLDG, btnQLPM, btnQLPT, btnQLS, btnQuayLai, btnChiTietSach, btnChiTietDG, btnChiTietPM, btnChiTietPT;
    DefaultTableModel dtmPM;
    JTable tbPM;

    public StatisticAnalyzer(String title) {
        super(title);
        this.setSize(1025, 580);
        setHeader(new Header("THỐNG KÊ"));
        setFooter(new Footer());
        hienThi();
    }

    public void hienThi() {
        // TODO Auto-generated method stubad

    }

    public int DemSach() {
        int SoLuongSach = 0;
        BookService sv = new BookService();
        ArrayList<Book> ds = sv.layToanBoSach();
        for (Book s : ds) {
            SoLuongSach++;
        }
        return SoLuongSach;
    }

    public int DemPhieuMuon() {
        int SoLuongPM = 0;
        LoanService pmsv = new LoanService();
        ArrayList<Loan> ds = pmsv.layThongTinPhieuMuon();
        for (Loan pm : ds) {
            SoLuongPM++;
        }
        return SoLuongPM;
    }

    public int DemPhieuPhieuTra() {
        int SoLuongPT = 0;
        LoanDetailService ctsv = new LoanDetailService();
        ArrayList<LoanDetail> ds = ctsv.layChiTietPhieuMuon();
        for (LoanDetail ctpm : ds) {
            if (ctpm.getNgayTra() != null) {
                SoLuongPT++;
            }
        }

        return SoLuongPT;
    }

    public int DemDocGia() {
        int SoLuongDG = 0;
        ReaderService dgsv = new ReaderService();
        ArrayList<Reader> ds = dgsv.layToanBoDocGia();
        for (Reader dg : ds) {
            SoLuongDG++;
        }
        return SoLuongDG;
    }

    public void initComponents() {
        JPanel pnQuanLy = new JPanel();
        pnQuanLy.setLayout(new BoxLayout(pnQuanLy, BoxLayout.Y_AXIS));

        JPanel pnHang1 = new JPanel();
        pnHang1.setLayout(new FlowLayout());
        pnHang1.setBackground(Color.WHITE);

        int kqs = DemSach();
        int kqpm = DemPhieuMuon();
        int kqpt = DemPhieuPhieuTra();
        int kqdg = DemDocGia();

        JPanel pnChiTietSach = new JPanel();
        pnChiTietSach.setLayout(new BorderLayout());
        btnChiTietSach = new JButton("Chi tiết");
        pnChiTietSach.add(btnChiTietSach, BorderLayout.EAST);
        pnChiTietSach.setBackground(new Color(51, 217, 178));

        JPanel pnThongKeSach = new JPanel();
        pnThongKeSach.setLayout(new BoxLayout(pnThongKeSach, BoxLayout.Y_AXIS));
        btnQLS = new JButton();
        JLabel lblTKSach = new JLabel("Có " + kqs + " đầu sách");
        pnThongKeSach.add(btnQLS);
        pnThongKeSach.add(lblTKSach);
        pnThongKeSach.add(pnChiTietSach);

        JPanel pnChiTietDocGia = new JPanel();
        pnChiTietDocGia.setLayout(new BorderLayout());
        btnChiTietDG = new JButton("Chi tiết");
        pnChiTietDocGia.add(btnChiTietDG, BorderLayout.EAST);
        pnChiTietDocGia.setBackground(new Color(204, 142, 53));

        JPanel pnThongKeDocGia = new JPanel();
        pnThongKeDocGia.setLayout(new BoxLayout(pnThongKeDocGia, BoxLayout.Y_AXIS));
        btnQLDG = new JButton();
        JLabel lblTKDocGia = new JLabel("Có " + kqdg + " độc giả");
        pnThongKeDocGia.add(btnQLDG);
        pnThongKeDocGia.add(lblTKDocGia);
        pnThongKeDocGia.add(pnChiTietDocGia);

        JPanel pnHang2 = new JPanel();
        pnHang2.setLayout(new FlowLayout());
        pnHang2.setBackground(Color.WHITE);


        JPanel pnChiTietPhieuMuon = new JPanel();
        pnChiTietPhieuMuon.setLayout(new BorderLayout());
        btnChiTietPM = new JButton("Chi tiết");
        pnChiTietPhieuMuon.add(btnChiTietPM, BorderLayout.EAST);
        pnChiTietPhieuMuon.setBackground(new Color(37, 204, 247));

        JPanel pnThongKePhieuMuon = new JPanel();
        pnThongKePhieuMuon.setLayout(new BoxLayout(pnThongKePhieuMuon, BoxLayout.Y_AXIS));
        btnQLPM = new JButton();
        JLabel lblTKPM = new JLabel("Có " + kqpm + " phiếu mượn");
        pnThongKePhieuMuon.add(btnQLPM);
        pnThongKePhieuMuon.add(lblTKPM);
        pnThongKePhieuMuon.add(pnChiTietPhieuMuon);

        JPanel pnChiTietPhieuTra = new JPanel();
        pnChiTietPhieuTra.setLayout(new BorderLayout());
        btnChiTietPT = new JButton("Chi tiết");
        pnChiTietPhieuTra.add(btnChiTietPT, BorderLayout.EAST);
        pnChiTietPhieuTra.setBackground(new Color(237, 76, 103));

        JPanel pnThongKePhieuTra = new JPanel();
        pnThongKePhieuTra.setLayout(new BoxLayout(pnThongKePhieuTra, BoxLayout.Y_AXIS));
        btnQLPT = new JButton();
        JLabel lblTKPT = new JLabel("Có " + kqpt + " phiếu trả");
        pnThongKePhieuTra.add(btnQLPT);
        pnThongKePhieuTra.add(lblTKPT);
        pnThongKePhieuTra.add(pnChiTietPhieuTra);


        btnQLS.setPreferredSize(new Dimension(270, 150));
        btnQLPM.setPreferredSize(btnQLS.getPreferredSize());
        btnQLDG.setPreferredSize(btnQLS.getPreferredSize());
        btnQLPT.setPreferredSize(btnQLS.getPreferredSize());

        pnThongKeSach.setBackground(new Color(51, 217, 178));
        pnThongKePhieuMuon.setBackground(new Color(37, 204, 247));
        pnThongKePhieuTra.setBackground(new Color(237, 76, 103));
        pnThongKeDocGia.setBackground(new Color(204, 142, 53));

        btnQLS.setIcon(Util.loadImage("tksach.png"));
        btnQLPM.setIcon(Util.loadImage("tkphieumuon.png"));
        btnQLDG.setIcon(Util.loadImage("tkdocgia.png"));
        btnQLPT.setIcon(Util.loadImage("tkphieutra.png"));

        btnQLS.setBackground(new Color(51, 217, 178));
        btnQLPM.setBackground(new Color(37, 204, 247));
        btnQLPT.setBackground(new Color(237, 76, 103));
        btnQLDG.setBackground(new Color(204, 142, 53));

        pnHang1.add(pnThongKeSach);
        pnHang1.add(pnThongKeDocGia);
        pnHang2.add(pnThongKePhieuMuon);
        pnHang2.add(pnThongKePhieuTra);

        pnQuanLy.add(pnHang1);
        pnQuanLy.add(pnHang2);

        mainPanel.add(pnQuanLy, BorderLayout.EAST);


        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new BoxLayout(pnChucNang, BoxLayout.Y_AXIS));

        JPanel pnHinhAnh = new JPanel();
        pnHinhAnh.setLayout(new FlowLayout());
        JLabel lblHinhAnh = new JLabel();
        pnHinhAnh.setBackground(Color.WHITE);
        lblHinhAnh.setIcon(Util.loadImage("tkgd.png"));
        pnHinhAnh.add(lblHinhAnh);

        JPanel pnQuayLai = new JPanel();
        pnQuayLai.setLayout(new FlowLayout());
        btnQuayLai = new JButton("QUAY LẠI TRANG CHỦ");
        pnQuayLai.add(btnQuayLai);
        btnQuayLai.setPreferredSize(new Dimension(300, 40));
        pnQuayLai.setBackground(Color.WHITE);
        btnQuayLai.setBackground(new Color(255, 177, 66));
        btnQuayLai.setForeground(Color.WHITE);
        btnQuayLai.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        btnChiTietSach.setBackground(new Color(51, 217, 178));
        btnChiTietPM.setBackground(new Color(37, 204, 247));
        btnChiTietPT.setBackground(new Color(237, 76, 103));
        btnChiTietDG.setBackground(new Color(204, 142, 53));

        btnChiTietSach.setForeground(Color.WHITE);
        btnChiTietPM.setForeground(Color.WHITE);
        btnChiTietPT.setForeground(Color.WHITE);
        btnChiTietDG.setForeground(Color.WHITE);

        Font font3 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 19);
        lblTKSach.setFont(font3);
        lblTKPM.setFont(font3);
        lblTKPT.setFont(font3);
        lblTKDocGia.setFont(font3);
        lblTKSach.setForeground(Color.WHITE);
        lblTKDocGia.setForeground(Color.WHITE);
        lblTKPM.setForeground(Color.WHITE);
        lblTKPT.setForeground(Color.WHITE);

        Font font2 = Util.loadFontFromResource("SVN-Avo.ttf", Font.BOLD, 18);
        btnQuayLai.setFont(font2);

        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.CENTER_BASELINE, 18);
        btnChiTietDG.setFont(font4);
        btnChiTietSach.setFont(font4);
        btnChiTietPM.setFont(font4);
        btnChiTietPT.setFont(font4);

        pnChucNang.add(pnHinhAnh);
        pnChucNang.add(pnQuayLai);

        mainPanel.add(pnChucNang, BorderLayout.WEST);

        btnQLDG.setBorder(null);
        btnQLPM.setBorder(null);
        btnQLPT.setBorder(null);
        btnQLS.setBorder(null);

        btnChiTietDG.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnChiTietPM.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnChiTietPT.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnChiTietSach.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        btnChiTietSach.setPreferredSize(new Dimension(120, 38));
        btnChiTietDG.setPreferredSize(new Dimension(120, 38));
        btnChiTietPT.setPreferredSize(new Dimension(120, 38));
        btnChiTietPM.setPreferredSize(new Dimension(120, 38));

    }

    public void addEvents() {
        btnQuayLai.addActionListener(e -> {
            // TODO Auto-generated method stub
            AdminManager ql = new AdminManager("Trang Chủ Phần Mềm Quản Lý Thư Viện");
            ql.tentk = tenTk;
            ql.showWindow();
            dispose();
        });
        btnChiTietDG.addActionListener(e -> {
            // TODO Auto-generated method stub
            ReaderManager ql = new ReaderManager("Quản lý độc giả");
            ql.tentk = tenTk;
            ql.thongke = thongke;
            ql.showWindow();
            dispose();
        });
        btnChiTietSach.addActionListener(e -> {
            // TODO Auto-generated method stub
            BookManager ql = new BookManager("Quản lý sách");
            ql.tentk = tenTk;
            ql.thongke = thongke;
            ql.showWindow();
            dispose();
        });
        btnChiTietPM.addActionListener(e -> {
            // TODO Auto-generated method stub
            LoanManager ql = new LoanManager("Quản lý phiếu mượn");
            ql.tentk = tenTk;
            ql.thongke = thongke;
            ql.showWindow();
            dispose();
        });
        btnChiTietPT.addActionListener(e -> {
            // TODO Auto-generated method stub
            ReturnManager ql = new ReturnManager("Quản lý phiếu trả");
            ql.tentk = tenTk;
            ql.thongke = thongke;
            ql.showWindow();
            dispose();
        });
        btnQLDG.addActionListener(e -> {
            // TODO Auto-generated method stub
            try {
                String report = "jasper/DocGiaReport.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(report);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, ConnectMySQL.connect);
                JasperViewer jv = new JasperViewer(jp, false);
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
                ex.printStackTrace();
            }
        });

        btnQLPT.addActionListener(e -> {
            // TODO Auto-generated method stub
            try {
                String report = "jasper/PhieuTraReport.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(report);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, ConnectMySQL.connect);
                JasperViewer jv = new JasperViewer(jp, false);
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        btnQLS.addActionListener(e -> {
            // TODO Auto-generated method stub
            try {
                String report = "jasper/SachReport.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(report);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, ConnectMySQL.connect);
                JasperViewer jv = new JasperViewer(jp, false);
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        btnQLPM.addActionListener(e -> {
            // TODO Auto-generated method stub
            try {
                String report = "jasper/PhieuMuonReport.jrxml";
                JasperReport jr = JasperCompileManager.compileReport(report);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, ConnectMySQL.connect);
                JasperViewer jv = new JasperViewer(jp, false);
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
    }
}
