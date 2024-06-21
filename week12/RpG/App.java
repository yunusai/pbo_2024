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
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rpg";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        long startTime = 0;
        Scanner scanner = new Scanner(System.in);

        // Database connection
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
            return;
        }

        // Initialize hero
        Hero hero = null;
        while (hero == null) {
            try {
                System.out.println("Welcome to RPG Game!");
                System.out.println("Choose an option:");
                System.out.println("1. Create New Hero");
                System.out.println("2. Show Leaderboard");
                System.out.print("Enter your choice: ");

                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (option) {
                    case 1:
                        System.out.println("Enter hero name: ");
                        String heroName = scanner.nextLine();
                        hero = new Hero(heroName);
                        startTime = System.currentTimeMillis();
                        break;
                    case 2:
                        showLeaderboard(connection);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        int stage = 1;
        boolean inBattle = false;

        while (stage <= 3 && hero.isAlive()) {
            System.out.println("Stage " + stage);
            Karakter enemy;

            if (stage == 3) {
                enemy = new Boss();
            } else {
                enemy = new Mob(stage);
            }

            System.out.println("Encountered " + enemy.getName());

            inBattle = true;

            while (enemy.isAlive() && hero.isAlive() && inBattle) {
                System.out.println("Choose action: (1) Attack (2) Block (3) Heal (4) Appraisal (5) Run");
                int action = 0;
                try {
                    action = scanner.nextInt();
                    if (action < 1 || action > 5) {
                        throw new InputMismatchException("Invalid action.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    scanner.next(); // Clear the invalid input
                    continue;
                }

                switch (action) {
                    case 1:
                        int heroDamage = hero.attack();
                        enemy.takeDamage(heroDamage);
                        System.out.println(hero.getName() + " attacks for " + heroDamage + " damage.");

                        if (enemy.isAlive()) {
                            int enemyDamage = enemy.attack();
                            hero.takeDamage(enemyDamage);
                            System.out.println(enemy.getName() + " attacks for " + enemyDamage + " damage.");
                        }
                        break;
                    case 2:
                        hero.block();
                        break;
                    case 3:
                        hero.heal();
                        if (enemy.isAlive()) {
                            int enemyDamage = enemy.attack();
                            hero.takeDamage(enemyDamage);
                            System.out.println(enemy.getName() + " attacks for " + enemyDamage + " damage.");
                        }
                        break;
                    case 4:
                        enemy.displayStats();
                        break;
                    case 5:
                        if (hero.run()) {
                            inBattle = false;
                        } else {
                            int enemyDamage = enemy.attack();
                            hero.takeDamage(enemyDamage);
                            System.out.println(enemy.getName() + " attacks for " + enemyDamage + " damage.");
                        }
                        break;
                }

                System.out.println(hero.getName() + " HP: " + hero.getHp() + "/" + hero.getMaxHp() + " | " + enemy.getName() + " HP: " + enemy.getHp());
            }

            if (!hero.isAlive()) {
                System.out.println("Game Over. " + hero.getName() + " was defeated.");
                break;
            } else if (!enemy.isAlive()) {
                System.out.println(hero.getName() + " defeated " + enemy.getName());
                int expGained = 0;
                if (enemy instanceof Boss) {
                    expGained = 2000;
                    stage++;
                } else if (enemy instanceof Mob) {
                    if (stage == 1) {
                        expGained = 20;
                    } else if (stage == 2) {
                        expGained = 100;
                    }
                }
                hero.gainExp(expGained);
                inBattle = false;
            }

            if (!inBattle && hero.isAlive() && stage <= 3) {
                boolean continueChoosing = true;
                while (continueChoosing) {
                    System.out.println("Choose next action: (1) Move (2) Continue to next stage (3) Rest (4) Check Hero Stats");
                    int nextAction = 0;
                    try {
                        nextAction = scanner.nextInt();
                        if (nextAction < 1 || nextAction > 4) {
                            throw new InputMismatchException("Invalid action.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 4.");
                        scanner.next(); // Clear the invalid input
                        continue;
                    }

                    switch (nextAction) {
                        case 1:
                            continueChoosing = false;
                            inBattle = true;
                            break;
                        case 2:
                            stage++;
                            continueChoosing = false;
                            inBattle = true;
                            break;
                        case 3:
                            hero.rest();
                            inBattle = false;
                            break;
                        case 4:
                            hero.displayStats();
                            break;
                    }
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long playTime = (endTime - startTime) / 1000;

        if (hero.isAlive() && stage > 3) {
            System.out.println("Congratulations! " + hero.getName() + " has completed the game.");
        }

        // Insert game results into the database
        if (hero.isAlive() && stage > 3) {
            try {
                // Convert playTime to HH:MM:SS format
                String formattedPlayTime = formatTime(playTime);

                String insertQuery = "INSERT INTO user (nama, skor, waktu) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, hero.getName());
                preparedStatement.setInt(2, hero.getScore());
                preparedStatement.setString(3, formattedPlayTime); // Insert formatted playTime
                preparedStatement.executeUpdate();
                System.out.println("Game results inserted into the database.");
            } catch (SQLException e) {
                System.out.println("Failed to insert game results into the database: " + e.getMessage());
            }
        }

        // Display game summary
        System.out.println("----------------------------------------------------------------");
        System.out.println("Hero Name: " + hero.getName());
        System.out.println("Hero Level: " + hero.getLevel());
        System.out.println("Score: " + hero.getScore());
        System.out.println("Playtime: " + formatTime(playTime));
        System.out.println("----------------------------------------------------------------");

        // Show leaderboard function
        showLeaderboard(connection);

        scanner.close();

        // Close database connection
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close database connection: " + e.getMessage());
            }
        }
    }

    // Method to format time in HH:MM:SS format
    private static String formatTime(long seconds) {
        Duration duration = Duration.ofSeconds(seconds);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long secs = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    // Method to show leaderboard
    private static void showLeaderboard(Connection connection) {
        System.out.println("Leaderboard (Top 3):");
        try {
            String query = "SELECT * FROM user ORDER BY skor DESC LIMIT 3";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if result set is empty
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Belum ada Hero yang bermain.");
            } else {
                int rank = 1;
                while (resultSet.next()) {
                    String nama = resultSet.getString("nama");
                    int skor = resultSet.getInt("skor");
                    String waktu = resultSet.getString("waktu"); // Retrieve waktu as String
                    System.out.println(rank + ". " + nama + " - Score: " + skor + " - Waktu: " + waktu);
                    rank++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch leaderboard: " + e.getMessage());
        }
    }
}
