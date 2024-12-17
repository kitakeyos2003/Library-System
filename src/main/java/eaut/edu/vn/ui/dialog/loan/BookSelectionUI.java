package eaut.edu.vn.ui.dialog.loan;

import eaut.edu.vn.model.Book;
import eaut.edu.vn.service.BookService;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookSelectionUI extends JDialog {
    private DefaultListModel<Book> listModel, selectedListModel;
    private JList<Book> bookJList, selectedBookJList;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;

    private List<Book> allBooks;
    @Getter
    private List<Book> selectedBooks;

    public BookSelectionUI(JDialog owner) {
        super(owner, "Chọn sách mượn", true);
        setSize(900, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(owner);
        this.allBooks = BookService.getInstance().getAll().stream().filter(book -> book.getQuantity() > 0).toList();
        this.selectedBooks = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        // Header
        JLabel headerLabel = new JLabel("CHỌN SÁCH MƯỢN", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);

        // Search and Filter Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Set<String> bookSet = new LinkedHashSet<>();
        bookSet.add("Tất cả");
        for (Book book : allBooks) {
            bookSet.add(book.getCategory());
        }

        searchField = new JTextField(20);
        categoryFilter = new JComboBox<>(bookSet.toArray(String[]::new));
        JButton searchButton = new JButton("🔍 Tìm kiếm");

        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Danh mục:"));
        searchPanel.add(categoryFilter);
        searchPanel.add(searchButton);

        // Book List
        listModel = new DefaultListModel<>();
        bookJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(bookJList);

        // Selected Books List
        selectedListModel = new DefaultListModel<>();
        selectedBookJList = new JList<>(selectedListModel);
        JScrollPane selectedScrollPane = new JScrollPane(selectedBookJList);
        selectedScrollPane.setPreferredSize(new Dimension(200, 0));

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(scrollPane);
        centerPanel.add(selectedScrollPane);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("➕ Thêm");
        JButton removeButton = new JButton("❌ Xóa");
        JButton confirmButton = new JButton("✅ Xác nhận");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(confirmButton);

        // Add Components
        add(searchPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        searchButton.addActionListener(e -> applyFilter());
        addButton.addActionListener(e -> addSelectedBooks());
        removeButton.addActionListener(e -> removeSelectedBooks());
        confirmButton.addActionListener(e -> confirmSelection());

        loadBookList(allBooks);
    }

    private void loadBookList(List<Book> books) {
        listModel.clear();
        books.forEach(book -> {
            if (!selectedBooks.contains(book)) listModel.addElement(book);
        });
    }

    private void applyFilter() {
        String searchText = searchField.getText().toLowerCase();
        String selectedCategory = (String) categoryFilter.getSelectedItem();

        List<Book> filteredBooks = allBooks.stream()
                .filter(book -> book.getName().toLowerCase().contains(searchText))
                .filter(book -> selectedCategory.equals("Tất cả") || book.getCategory().equalsIgnoreCase(selectedCategory))
                .collect(Collectors.toList());

        loadBookList(filteredBooks);
    }

    private void addSelectedBooks() {
        List<Book> selected = bookJList.getSelectedValuesList();
        for (Book book : selected) {
            if (!selectedBooks.contains(book) && selectedBooks.size() < 5) {
                selectedBooks.add(book);
                selectedListModel.addElement(book);
            }
        }
        loadBookList(allBooks);
    }

    private void removeSelectedBooks() {
        List<Book> toRemove = selectedBookJList.getSelectedValuesList();
        selectedBooks.removeAll(toRemove);
        selectedListModel.removeAllElements();
        selectedBooks.forEach(selectedListModel::addElement);
        loadBookList(allBooks);
    }

    private void confirmSelection() {
        AddLoan loan = (AddLoan) getOwner();
        loan.setSelectedBooks(selectedBooks);
        StringBuilder result = new StringBuilder("Sách đã chọn:\n");
        selectedBooks.forEach(book -> result.append("• ").append(book.getName()).append("\n"));

        int option = JOptionPane.showConfirmDialog(this, result.toString(), "Xác nhận", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            setVisible(false);
        }
    }
}
