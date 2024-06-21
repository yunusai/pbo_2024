package RpG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestDB {
    private static final String URL = "jdbc:mysql://localhost:3306/perpustakaan";
    private static final String USER = "root"; // ganti dengan user database Anda
    private static final String PASSWORD = ""; // ganti dengan password database Anda

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
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
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        tambahBuku(connection, scanner);
                        break;
                    case 2:
                        lihatBuku(connection);
                        break;
                    case 3:
                        updateBuku(connection, scanner);
                        break;
                    case 4:
                        hapusBuku(connection, scanner);
                        break;
                    case 5:
                        tambahPenulis(connection, scanner);
                        break;
                    case 6:
                        lihatPenulis(connection);
                        break;
                    case 7:
                        updatePenulis(connection, scanner);
                        break;
                    case 8:
                        hapusPenulis(connection, scanner);
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

    private static void tambahBuku(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Judul Buku: ");
        String judul = scanner.nextLine();
        System.out.print("Tahun Terbit: ");
        int tahun = scanner.nextInt();
        System.out.print("Stok: ");
        int stok = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("ID Penulis: ");
        int penulisId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "INSERT INTO buku (judul_buku, tahun_terbit, stok, penulis_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, judul);
            statement.setInt(2, tahun);
            statement.setInt(3, stok);
            statement.setInt(4, penulisId);
            statement.executeUpdate();
            System.out.println("Buku berhasil ditambahkan.");
        }
    }

    private static void lihatBuku(Connection connection) throws SQLException {
        String query = "SELECT buku.id, judul_buku, tahun_terbit, stok, penulis_id FROM buku";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("=== Daftar Buku ===");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul_buku");
                int tahun = resultSet.getInt("tahun_terbit");
                int stok = resultSet.getInt("stok");
                int penulisId = resultSet.getInt("penulis_id");

                System.out.println("ID: " + id);
                System.out.println("Judul: " + judul);
                System.out.println("Tahun Terbit: " + tahun);
                System.out.println("Stok: " + stok);
                System.out.println("ID Penulis: " + penulisId);
                System.out.println();
            }
        }
    }

    private static void updateBuku(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("ID Buku yang akan diupdate: ");
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
        int penulisId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "UPDATE buku SET judul_buku = ?, tahun_terbit = ?, stok = ?, penulis_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, judul);
            statement.setInt(2, tahun);
            statement.setInt(3, stok);
            statement.setInt(4, penulisId);
            statement.setInt(5, id);
            statement.executeUpdate();
            System.out.println("Buku berhasil diupdate.");
        }
    }

    private static void hapusBuku(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("ID Buku yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM buku WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Buku berhasil dihapus.");
        }
    }

    private static void tambahPenulis(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Nama Penulis: ");
        String nama = scanner.nextLine();

        String query = "INSERT INTO penulis (nama_penulis) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nama);
            statement.executeUpdate();
            System.out.println("Penulis berhasil ditambahkan.");
        }
    }

    private static void lihatPenulis(Connection connection) throws SQLException {
        String query = "SELECT id, nama_penulis FROM penulis";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("=== Daftar Penulis ===");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama_penulis");

                System.out.println("ID: " + id);
                System.out.println("Nama Penulis: " + nama);
                System.out.println();
            }
        }
    }

    private static void updatePenulis(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("ID Penulis yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Nama Penulis baru: ");
        String nama = scanner.nextLine();

        String query = "UPDATE penulis SET nama_penulis = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nama);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Penulis berhasil diupdate.");
        }
    }

    private static void hapusPenulis(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("ID Penulis yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM penulis WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Penulis berhasil dihapus.");
        }
    }
}
