package week11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class crud {
    private static final String URL = "jdbc:mysql://localhost:3306/perpustakaan";
    private static final String USER = "root"; // ganti dengan user database Anda
    private static final String PASSWORD = ""; // ganti dengan password database Anda

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("=== Perpustakaan ===");
                System.out.println("1. Tambah Buku");
                System.out.println("2. Lihat Buku");
                System.out.println("3. Update Buku");
                System.out.println("4. Hapus Buku");
                System.out.println("5. Tambah Penulis");
                System.out.println("6. Lihat Penulis");
                System.out.println("7. Update Penulis");
                System.out.println("8. Hapus Penulis");
                System.out.println("9. Keluar");
                System.out.print("Pilih opsi: ");
                int inp = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (inp) {
                    case 1:
                        addBuku(conn, scanner);
                        break;
                    case 2:
                        showBuku(conn);
                        break;
                    case 3:
                        upBuku(conn, scanner);
                        break;
                    case 4:
                        deleteBuku(conn, scanner);
                        break;
                    case 5:
                        addPenulis(conn, scanner);
                        break;
                    case 6:
                        showPenulis(conn);
                        break;
                    case 7:
                        upPenulis(conn, scanner);
                        break;
                    case 8:
                        deletePenulis(conn, scanner);
                        break;
                    case 9:
                        System.out.println("Keluar...");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addBuku(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Judul Buku: ");
        String judul = scanner.nextLine();
        System.out.print("Tahun Terbit: ");
        int tahun = scanner.nextInt();
        System.out.print("Stok: ");
        int stok = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("ID Penulis: ");
        int id_penulis = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "INSERT INTO buku (judul_buku, tahun_terbit, stok, penulis) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, judul);
            stmt.setInt(2, tahun);
            stmt.setInt(3, stok);
            stmt.setInt(4, id_penulis);
            stmt.executeUpdate();
            System.out.println("Buku berhasil ditambahkan!");
        }
    }

    private static void showBuku(Connection conn) throws SQLException {
        String query = "SELECT buku.id, judul_buku, tahun_terbit, stok, penulis FROM buku";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("=== Daftar Buku ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String judul = rs.getString("judul_buku");
                int tahun = rs.getInt("tahun_terbit");
                int stok = rs.getInt("stok");
                int id_penulis = rs.getInt("penulis");

                System.out.println("ID: " + id);
                System.out.println("Judul: " + judul);
                System.out.println("Tahun Terbit: " + tahun);
                System.out.println("Stok: " + stok);
                System.out.println("ID Penulis: " + id_penulis);
                System.out.println();
            }
        }
    }

    private static void upBuku(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("ID Buku yang akan diperbaharui: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Judul Buku baru: ");
        String judul = scanner.nextLine();
        System.out.print("Tahun Terbit baru: ");
        int tahun = scanner.nextInt();
        System.out.print("Stok baru: ");
        int stok = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("ID Penulis baru: ");
        int id_penulis = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "UPDATE buku SET judul_buku = ?, tahun_terbit = ?, stok = ?, penulis = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, judul);
            stmt.setInt(2, tahun);
            stmt.setInt(3, stok);
            stmt.setInt(4, id_penulis);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Data buku berhasil diperbaharui!");
        }
    }

    private static void deleteBuku(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("ID Buku yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM buku WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Buku berhasil dihapus!");
        }
    }

    private static void addPenulis(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Nama Penulis: ");
        String nama = scanner.nextLine();

        String query = "INSERT INTO penulis (nama_penulis) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.executeUpdate();
            System.out.println("Penulis berhasil ditambahkan!");
        }
    }

    private static void showPenulis(Connection conn) throws SQLException {
        String query = "SELECT id, nama_penulis FROM penulis";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("=== Daftar Penulis ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama_penulis");

                System.out.println("ID: " + id);
                System.out.println("Nama Penulis: " + nama);
                System.out.println();
            }
        }
    }

    private static void upPenulis(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("ID Penulis yang akan diperbaharui: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Nama Penulis baru: ");
        String nama = scanner.nextLine();

        String query = "UPDATE penulis SET nama_penulis = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Data penulis berhasil diperbaharui!");
        }
    }

    private static void deletePenulis(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("ID Penulis yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM penulis WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Penulis berhasil dihapus!");
        }
    }
}
