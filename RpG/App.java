package RpG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static final String URL = "jdbc:mysql://127.0.0.1/rpg";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        long waktuMulai = 0;
        long waktuAkhir = 0;
        long waktuMain = 0;
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
            return;
        }

        
        Hero hero = null;
        while (hero == null) {
            System.out.println("Selamat Datang!");
            System.out.println("Menu:");
            System.out.println("1. Buat Hero Baru");
            System.out.println("2. Lihat Leaderboard");
            System.out.print("Pilihan mu: ");
            
            int option = scanner.nextInt();
            try {
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Masukkan pilihan yang tersedia");
                scanner.nextLine();  
            }
                switch (option) {
                    case 1:
                        System.out.println("Masukkan nama Hero mu: ");
                        String namaHero = scanner.nextLine();
                        hero = new Hero(namaHero);
                        waktuMulai = System.currentTimeMillis();
                        break;
                    case 2:
                        tampilLeaderboard(connection);
                        break;
                }
        }

        int stage = 1;
        boolean battle = false;

        while (stage <= 3 && hero.hidup()) {
            System.out.println("Stage " + stage);
            Karakter musuh;

            if (stage == 3) {
                musuh = new Boss();
            } else {
                musuh = new Mob(stage);
            }

            System.out.println("Kamu bertemu dengan " + musuh.getNama());

            battle = true;

            while (musuh.hidup() && hero.hidup() && battle) {
                System.out.println("Pilih aksi: (1) serang (2) Block (3) Heal (4) Lihat status musuh (5) Run");
                int aksi = 0;
                try {
                    aksi = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Pilihan hanya 1 atau 2.");
                        scanner.next();
                        continue;
                }

                switch (aksi) {
                    case 1:
                        int heroDamage = hero.serang();
                        musuh.kenaDamage(heroDamage);
                        System.out.println(hero.getNama() + " menyerang dengan " + heroDamage + " damage.");

                        if (musuh.hidup()) {
                            int musuhDamage = musuh.serang();
                            hero.kenaDamage(musuhDamage);
                            System.out.println(musuh.getNama() + " menyerang dengan " + musuhDamage + " damage.");
                        }
                        break;
                    case 2:
                        hero.block();
                        break;
                    case 3:
                        hero.heal();
                        if (musuh.hidup()) {
                            int musuhDamage = musuh.serang();
                            hero.kenaDamage(musuhDamage);
                            System.out.println(musuh.getNama() + " menyerang dengan" + musuhDamage + " damage.");
                        }
                        break;
                    case 4:
                        musuh.status();
                        break;
                    case 5:
                        if (hero.run()) {
                            battle = false;
                        } else {
                            int musuhDamage = musuh.serang();
                            hero.kenaDamage(musuhDamage);
                            System.out.println(musuh.getNama() + " menyerang dengan " + musuhDamage + " damage.");
                        }
                        break;
                }

                System.out.println(hero.getNama() + " HP: " + hero.getHP() + "/" + hero.getMaxHp() + " | "
                        + musuh.getNama() + " HP: " + musuh.getHP());
            }

            if (!hero.hidup()) {
                    System.out.println("Game Over. Hero " + hero.getNama() + " telah dikalahkan.");
                    waktuAkhir = System.currentTimeMillis();
                    waktuMain = (waktuAkhir - waktuMulai) / 1000;
                    save(waktuMain, connection, hero);
                break;
            } else if (!musuh.hidup()) {
                System.out.println(hero.getNama() + " mengalahkan " + musuh.getNama());
                int expDidapat = 0;
                if (musuh instanceof Boss) {
                    expDidapat = 2000;
                    stage++;
                } else if (musuh instanceof Mob) {
                    if (stage == 1) {
                        expDidapat = 20;
                    } else if (stage == 2) {
                        expDidapat = 100;
                    }
                }
                hero.dapatExp(expDidapat);
                battle = false;
            }

            if (!battle && hero.hidup() && stage <= 3) {
                boolean lanjut = true;
                     while (lanjut) {
                        System.out.println("Apa yang ingin dilakukan: (1) Maju (2) Menuju stage berikutnya (3) Istirahat (4) Lihat status Hero");
                    int aksiNext = 0;
                    try {
                        aksiNext = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Pilihan hanya 1 sampai 4.");
                        scanner.next();
                        continue;
                    }

                    switch (aksiNext) {
                        case 1:
                            lanjut = false;
                            battle = true;
                            break;
                        case 2:
                            if (stage < 3) {
                                stage++;
                                lanjut = false;
                                battle = true;
                                break;
                            } else {
                                lanjut = false;
                                battle = true;
                                break;
                            }
                            
                        case 3:
                            hero.rest();
                            battle = false;
                            break;
                        case 4:
                            hero.status();
                            break;
                    }
                }
            }
        }

        if (hero.hidup() && stage > 3) {
            waktuAkhir = System.currentTimeMillis();
            waktuMain = (waktuAkhir - waktuMulai) / 1000;
            System.out.println("Selamat! Hero " + hero.getNama() + " telah menyelesaikan game.");
            save(waktuMain, connection, hero);
        }

        System.out.println("----------------------------------------------------------------");
        System.out.println("Nama Hero: " + hero.getNama());
        System.out.println("Hero Level: " + hero.getLevel());
        System.out.println("Skor: " + hero.getSkor());
        System.out.println("Waktu Main: " + formatTime(waktuMain));
        System.out.println("----------------------------------------------------------------");

        
        scanner.close();
            {
            try {
                connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
    }

    private static String formatTime(long seconds) {
            Duration duration = Duration.ofSeconds(seconds);
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long secs = duration.toSecondsPart();
            return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    private static void tampilLeaderboard(Connection connection) {
        System.out.println("Leaderboard (Top 5):");
        try {
            String query = "SELECT * FROM user ORDER BY skor DESC limit 5";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("Belum ada Hero yang bermain.");
            } else {
                int rank = 1;
                while (rs.next()) {
                    String nama = rs.getString("nama");
                    int skor = rs.getInt("skor");
                    String waktu = rs.getString("waktu");
                    System.out.println(rank + ". " + nama + " - Score: " + skor + " - Waktu: " + waktu);
                    rank++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch leaderboard: " + e.getMessage());
        }
    }

    private static void save(long waktuMain, Connection connection, Hero hero) {
        try {
                String formattedWaktuMain = formatTime(waktuMain);

                String  query  = "INSERT INTO user(nama, skor, waktu) VALUES (?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement( query);
                stmt.setString(1, hero.getNama());
                stmt.setInt(2, hero.getSkor());
                stmt.setString(3, formattedWaktuMain);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }



