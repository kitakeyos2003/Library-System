package eaut.edu.vn.ui;

import eaut.edu.vn.database.DbManager;
import eaut.edu.vn.main.Application;
import eaut.edu.vn.model.Book;
import eaut.edu.vn.model.Loan;
import eaut.edu.vn.model.LoanDetail;
import eaut.edu.vn.model.Reader;
import eaut.edu.vn.service.BookService;
import eaut.edu.vn.service.LoanDetailService;
import eaut.edu.vn.service.LoanService;
import eaut.edu.vn.service.ReaderService;
import eaut.edu.vn.ui.controls.CustomFrame;
import eaut.edu.vn.ui.controls.Footer;
import eaut.edu.vn.ui.controls.Header;
import eaut.edu.vn.util.Util;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatisticAnalyzer extends CustomFrame {

    JButton btnBookDetail, btnReaderDetail, btnLoanDetail, btnReturnDetail, btnReturn;
    JasperReport bookReport, readerReport, loanReport, returnReport;

    public StatisticAnalyzer(String title) {
        super(title);
        this.setSize(1025, 580);
        setHeader(new Header("THỐNG KÊ"));
        setFooter(new Footer());
        new Thread(this::compileReport).start();
    }

    private void compileReport() {
        try {
            bookReport = JasperCompileManager.compileReport("report/SachReport.jrxml");
            readerReport = JasperCompileManager.compileReport("report/DocGiaReport.jrxml");
            loanReport = JasperCompileManager.compileReport("report/DocGiaReport.jrxml");
            returnReport = JasperCompileManager.compileReport("report/PhieuMuonReport.jrxml");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public int countBook() {
        List<Book> ds = BookService.getInstance().getAll();
        return ds.size();
    }

    public int countLoan() {
        List<Loan> ds = LoanService.getInstance().getAll();
        return ds.size();
    }

    public int countReturnReceipt() {
        List<LoanDetail> ds = LoanDetailService.getInstance().getAll();
        return ds.size();
    }

    public int countReader() {
        List<Reader> ds = ReaderService.getInstance().getAll();
        return ds.size();
    }

    public void initComponents() {
        JPanel pnQuanLy = new JPanel();
        pnQuanLy.setLayout(new BoxLayout(pnQuanLy, BoxLayout.Y_AXIS));

        JPanel pnHang1 = new JPanel();
        pnHang1.setLayout(new FlowLayout());
        pnHang1.setBackground(Color.WHITE);

        int kqs = countBook();
        int kqpm = countLoan();
        int kqpt = countReturnReceipt();
        int kqdg = countReader();

        JPanel pnChiTietSach = new JPanel();
        pnChiTietSach.setLayout(new BorderLayout());
        btnBookDetail = new JButton("Chi tiết");
        pnChiTietSach.add(btnBookDetail, BorderLayout.EAST);
        pnChiTietSach.setBackground(new Color(99, 211, 165));

        JPanel pnThongKeSach = new JPanel();
        pnThongKeSach.setLayout(new BoxLayout(pnThongKeSach, BoxLayout.Y_AXIS));
        JLabel lbBookManager = new JLabel();
        JLabel lblTKSach = new JLabel("Có " + kqs + " đầu sách");
        pnThongKeSach.add(lbBookManager);
        pnThongKeSach.add(lblTKSach);
        pnThongKeSach.add(pnChiTietSach);

        JPanel pnChiTietDocGia = new JPanel();
        pnChiTietDocGia.setLayout(new BorderLayout());
        btnReaderDetail = new JButton("Chi tiết");
        pnChiTietDocGia.add(btnReaderDetail, BorderLayout.EAST);
        pnChiTietDocGia.setBackground(new Color(82, 78, 79));

        JPanel pnThongKeDocGia = new JPanel();
        pnThongKeDocGia.setLayout(new BoxLayout(pnThongKeDocGia, BoxLayout.Y_AXIS));
        JLabel lbReaderManager = new JLabel();
        JLabel lblTKDocGia = new JLabel("Có " + kqdg + " độc giả");
        pnThongKeDocGia.add(lbReaderManager);
        pnThongKeDocGia.add(lblTKDocGia);
        pnThongKeDocGia.add(pnChiTietDocGia);

        JPanel pnHang2 = new JPanel();
        pnHang2.setLayout(new FlowLayout());
        pnHang2.setBackground(Color.WHITE);


        JPanel pnChiTietPhieuMuon = new JPanel();
        pnChiTietPhieuMuon.setLayout(new BorderLayout());
        btnLoanDetail = new JButton("Chi tiết");
        pnChiTietPhieuMuon.add(btnLoanDetail, BorderLayout.EAST);
        pnChiTietPhieuMuon.setBackground(new Color(122, 161, 94));

        JPanel pnThongKePhieuMuon = new JPanel();
        pnThongKePhieuMuon.setLayout(new BoxLayout(pnThongKePhieuMuon, BoxLayout.Y_AXIS));
        JLabel lbLoanManager = new JLabel();
        JLabel lblTKPM = new JLabel("Có " + kqpm + " phiếu mượn");
        pnThongKePhieuMuon.add(lbLoanManager);
        pnThongKePhieuMuon.add(lblTKPM);
        pnThongKePhieuMuon.add(pnChiTietPhieuMuon);

        JPanel pnChiTietPhieuTra = new JPanel();
        pnChiTietPhieuTra.setLayout(new BorderLayout());
        btnReturnDetail = new JButton("Chi tiết");
        pnChiTietPhieuTra.add(btnReturnDetail, BorderLayout.EAST);
        pnChiTietPhieuTra.setBackground(new Color(120, 191, 51));

        JPanel pnThongKePhieuTra = new JPanel();
        pnThongKePhieuTra.setLayout(new BoxLayout(pnThongKePhieuTra, BoxLayout.Y_AXIS));
        JLabel lbReturnManager = new JLabel();
        JLabel lblTKPT = new JLabel("Có " + kqpt + " phiếu trả");
        pnThongKePhieuTra.add(lbReturnManager);
        pnThongKePhieuTra.add(lblTKPT);
        pnThongKePhieuTra.add(pnChiTietPhieuTra);


        lbBookManager.setPreferredSize(new Dimension(270, 150));
        lbLoanManager.setPreferredSize(lbBookManager.getPreferredSize());
        lbReaderManager.setPreferredSize(lbBookManager.getPreferredSize());
        lbReturnManager.setPreferredSize(lbBookManager.getPreferredSize());

        pnThongKeSach.setBackground(new Color(99, 211, 165));
        pnThongKePhieuMuon.setBackground(new Color(122, 161, 94));
        pnThongKePhieuTra.setBackground(new Color(120, 191, 51));
        pnThongKeDocGia.setBackground(new Color(82, 78, 79));

        lbBookManager.setIcon(Util.loadImage("tksach.png"));
        lbLoanManager.setIcon(Util.loadImage("tkphieumuon.png"));
        lbReaderManager.setIcon(Util.loadImage("tkdocgia.png"));
        lbReturnManager.setIcon(Util.loadImage("tkphieutra.png"));

        lbBookManager.setBackground(new Color(99, 211, 165));
        lbLoanManager.setBackground(new Color(122, 161, 94));
        lbReturnManager.setBackground(new Color(120, 191, 51));
        lbReaderManager.setBackground(new Color(82, 78, 79));

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
        btnReturn = new JButton("QUAY LẠI TRANG CHỦ");
        btnReturn.setFocusPainted(false);
        pnQuayLai.add(btnReturn);
        btnReturn.setPreferredSize(new Dimension(300, 40));
        pnQuayLai.setBackground(Color.WHITE);
        btnReturn.setBackground(new Color(4, 191, 138));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        btnBookDetail.setBackground(new Color(99, 211, 165));
        btnLoanDetail.setBackground(new Color(122, 161, 94));
        btnReturnDetail.setBackground(new Color(120, 191, 51));
        btnReaderDetail.setBackground(new Color(82, 78, 79));

        btnBookDetail.setForeground(Color.WHITE);
        btnLoanDetail.setForeground(Color.WHITE);
        btnReturnDetail.setForeground(Color.WHITE);
        btnReaderDetail.setForeground(Color.WHITE);

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
        btnReturn.setFont(font2);

        Font font4 = Util.loadFontFromResource("SVN-Avo.ttf", Font.CENTER_BASELINE, 18);
        btnReaderDetail.setFont(font4);
        btnBookDetail.setFont(font4);
        btnLoanDetail.setFont(font4);
        btnReturnDetail.setFont(font4);

        pnChucNang.add(pnHinhAnh);
        pnChucNang.add(pnQuayLai);

        mainPanel.add(pnChucNang, BorderLayout.WEST);

        lbReaderManager.setBorder(BorderFactory.createEmptyBorder());
        lbLoanManager.setBorder(BorderFactory.createEmptyBorder());
        lbReturnManager.setBorder(BorderFactory.createEmptyBorder());
        lbBookManager.setBorder(BorderFactory.createEmptyBorder());

        btnReaderDetail.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnLoanDetail.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnReturnDetail.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnBookDetail.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        btnBookDetail.setPreferredSize(new Dimension(120, 38));
        btnReaderDetail.setPreferredSize(new Dimension(120, 38));
        btnReturnDetail.setPreferredSize(new Dimension(120, 38));
        btnLoanDetail.setPreferredSize(new Dimension(120, 38));

    }

    public void addEvents() {
        btnReturn.addActionListener(e -> {
            if (Application.account.getRole() == 1) {
                AdminManager ql = Application.SINGLETON.ADMIN_MANAGER;
                ql.showWindow();
            } else if (Application.account.getRole() == 2) {
                LibrarianManager ql = Application.SINGLETON.LIBRARIAN_MANAGER;
                ql.showWindow();
            }
            dispose();
        });
        btnReaderDetail.addActionListener(e -> {
            try {
                JasperPrint jp = JasperFillManager.fillReport(readerReport, null, DbManager.getInstance().getConnection());
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });

        btnReturnDetail.addActionListener(e -> {
            try {
                JasperPrint jp = JasperFillManager.fillReport(returnReport, null, DbManager.getInstance().getConnection());
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        btnBookDetail.addActionListener(e -> {
            try {
                JasperPrint jp = JasperFillManager.fillReport(bookReport, null, DbManager.getInstance().getConnection());
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        btnLoanDetail.addActionListener(e -> {
            try {
                JasperPrint jp = JasperFillManager.fillReport(loanReport, null, DbManager.getInstance().getConnection());
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
    }
}
