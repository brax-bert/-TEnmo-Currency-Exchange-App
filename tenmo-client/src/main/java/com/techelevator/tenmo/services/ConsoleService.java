package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    /**
     * Set the console output and background color to CONSOLE DEFAULT
     */
    public static final String ANSI_RESET = "\u001B[0m";
    /**
     * Set the console output color to BOLD
     */
    public static final String ANSI_BOLD = "\u001B[1m";
    /**
     * Set the console output color to UNDERLINE
     */
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    /**
     * Set the console output color to NO UNDERLINE
     */
    public static final String ANSI_NO_UNDERLINE = "\u001B[24m";
    /**
     * /**
     * Set the console output color to WHITE
     */
    public static final String ANSI_WHITE = "\u001B[97m";
    /**
     * Set the console background color to WHITE
     */
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[107m";
    /**
     * Set the console output color to BLACK
     */
    public static final String ANSI_BLACK = "\u001B[30m";
    /**
     * Set the console background color to BLACK
     */
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    /**
     * Set the console output color to RED
     */
    public static final String ANSI_RED = "\u001B[31m";
    /**
     * Set the console background color to RED
     */
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    /**
     * Set the console output color to GREEN
     */
    public static final String ANSI_GREEN = "\u001B[32m";
    /**
     * Set the console background color to GREEN
     */
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    /**
     * Set the console output color to YELLOW
     */
    public static final String ANSI_YELLOW = "\u001B[33m";
    /**
     * Set the console background color to YELLOW
     */
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    /**
     * Set the console output color to BLUE
     */
    public static final String ANSI_BLUE = "\u001B[34m";
    /**
     * Set the console background color to BLUE
     */
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    /**
     * Set the console output color to MAGENTA
     */
    public static final String ANSI_MAGENTA = "\u001B[35m";
    /**
     * Set the console background color to MAGENTA
     */
    public static final String ANSI_MAGENTA_BACKGROUND = "\u001B[45m";
    /**
     * Set the console output color to CYAN
     */
    public static final String ANSI_CYAN = "\u001B[36m";
    /**
     * Set the console background color to CYAN
     */
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    /**
     * Set the console output color to LIGHT GRAY
     */
    public static final String ANSI_LIGHT_GRAY = "\u001B[37m";
    /**
     * Set the console background color to LIGHT GRAY
     */
    public static final String ANSI_LIGHT_GRAY_BACKGROUND = "\u001B[47m";
    /**
     * Set the console output color to DARK GRAY
     */
    public static final String ANSI_DARK_GRAY = "\u001B[90m";
    /**
     * Set the console background color to DARK GRAY
     */
    public static final String ANSI_DARK_GRAY_BACKGROUND = "\u001B[100m";
    /**
     * Set the console output color to LIGHT RED
     */
    public static final String ANSI_LIGHT_RED = "\u001B[91m";
    /**
     * Set the console background color to LIGHT RED
     */
    public static final String ANSI_LIGHT_RED_BACKGROUND = "\u001B[101m";
    /**
     * Set the console output color to LIGHT GREEN
     */
    public static final String ANSI_LIGHT_GREEN = "\u001B[92m";
    /**
     * Set the console background color to LIGHT GREEN
     */
    public static final String ANSI_LIGHT_GREEN_BACKGROUND = "\u001B[102m";
    /**
     * Set the console output color to LIGHT YELLOW
     */
    public static final String ANSI_LIGHT_YELLOW = "\u001B[93m";
    /**
     * Set the console background color to LIGHT YELLOW
     */
    public static final String ANSI_LIGHT_YELLOW_BACKGROUND = "\u001B[103m";
    /**
     * Set the console output color to LIGHT BLUE
     */
    public static final String ANSI_LIGHT_BLUE = "\u001B[94m";
    /**
     * Set the console background color to LIGHT BLUE
     */
    public static final String ANSI_LIGHT_BLUE_BACKGROUND = "\u001B[104m";
    /**
     * Set the console output color to LIGHT MAGENTA
     */
    public static final String ANSI_LIGHT_MAGENTA = "\u001B[95m";
    /**
     * Set the console background color to LIGHT MAGENTA
     */
    public static final String ANSI_LIGH_MAGENTA_BACKGROUND = "\u001B[105m";
    /**
     * Set the console output color to LIGHT CYAN
     */
    public static final String ANSI_LIGHT_CYAN = "\u001B[96m";
    /**
     * Set the console background color to LIGHT CYAN
     */
    public static final String ANSI_LIGHT_CYAN_BACKGROUND = "\u001B[106m";

    private static final Scanner scanner = new Scanner(System.in);
    public int promptForToUserId;

    public void printUsers(List<User> userList) {
        System.out.println("List of registered users:");
        for (User user : userList) {
            System.out.println(user.getId() + ": " + user.getUsername());
        }
    }

    public static int promptForToUserId() {
        int toUserId = 0;
        boolean validInput = false;

        // Keep prompting until valid input is provided
        while (!validInput) {
            System.out.print("Enter the ID of the recipient user: ");
            String userInput = scanner.nextLine();

            try {
                // Attempt to parse the input as an integer
                toUserId = Integer.parseInt(userInput);
                validInput = true; // Set flag to exit loop if parsing successful
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid user ID.");
            }
        }

        return toUserId;
    }
    public BigDecimal promptForTransferAmount(double balance) {
        System.out.print("Enter the amount to transfer: ");
        while (true) {
            try {
                BigDecimal amount = new BigDecimal(scanner.nextLine());
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Transfer amount must be greater than zero.");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }


    public String fillText(String fillChar, int fillCount) {
        String result = "";

        for (int i = 0; i < fillCount; i++) {
            result += fillChar;
        }

        return result;
    }

    public void printError(String text) {
        System.out.print(ConsoleService.ANSI_RED);
        System.out.print(text);
        System.out.println(ConsoleService.ANSI_RESET);
    }

    public void printMessage(String text, String colorCode1, String colorCode2, boolean printReturn, boolean reset) {
        if (colorCode1 != null) {
            System.out.print(colorCode1);
        }
        if (colorCode2 != null) {
            System.out.print(colorCode2);
        }
        System.out.print(text);
        if (reset) {
            System.out.print(ANSI_RESET);
        }
        if (printReturn) {
            System.out.println();
        }
    }

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void outputPalette() {

        String[] styles = {
                ANSI_BOLD,
                ANSI_UNDERLINE
        };

        String[] styleWords = {
                "BOLD",
                "UNDERLINE"
        };

        String[] codes = {
                ANSI_WHITE,
                ANSI_WHITE_BACKGROUND,
                ANSI_BLACK,
                ANSI_RED,
                ANSI_RED_BACKGROUND,
                ANSI_GREEN,
                ANSI_GREEN_BACKGROUND,
                ANSI_YELLOW,
                ANSI_YELLOW_BACKGROUND,
                ANSI_BLUE,
                ANSI_BLUE_BACKGROUND,
                ANSI_MAGENTA,
                ANSI_MAGENTA_BACKGROUND,
                ANSI_CYAN,
                ANSI_CYAN_BACKGROUND,
                ANSI_LIGHT_GRAY,
                ANSI_LIGHT_GRAY_BACKGROUND,
                ANSI_DARK_GRAY,
                ANSI_DARK_GRAY_BACKGROUND,
                ANSI_LIGHT_RED,
                ANSI_LIGHT_RED_BACKGROUND,
                ANSI_LIGHT_GREEN,
                ANSI_LIGHT_GREEN_BACKGROUND,
                ANSI_LIGHT_YELLOW,
                ANSI_LIGHT_YELLOW_BACKGROUND,
                ANSI_LIGHT_BLUE,
                ANSI_LIGHT_BLUE_BACKGROUND,
                ANSI_LIGHT_MAGENTA,
                ANSI_LIGH_MAGENTA_BACKGROUND,
                ANSI_LIGHT_CYAN,
                ANSI_LIGHT_CYAN_BACKGROUND
        };

        String[] codeWords = {
                "ANSI_WHITE",
                "ANSI_WHITE_BACKGROUND",
                "ANSI_BLACK",
                "ANSI_RED",
                "ANSI_RED_BACKGROUND",
                "ANSI_GREEN",
                "ANSI_GREEN_BACKGROUND",
                "ANSI_YELLOW",
                "ANSI_YELLOW_BACKGROUN",
                "ANSI_BLUE",
                "ANSI_BLUE_BACKGROUND",
                "ANSI_MAGENTA",
                "ANSI_MAGENTA_BACKGROUND",
                "ANSI_CYAN",
                "ANSI_CYAN_BACKGROUND",
                "ANSI_LIGHT_GRAY",
                "ANSI_LIGHT_GRAY_BACKGROUND",
                "ANSI_DARK_GRAY",
                "ANSI_DARK_GRAY_BACKGROUND",
                "ANSI_LIGHT_RED",
                "ANSI_LIGHT_RED_BACKGROUND",
                "ANSI_LIGHT_GREEN",
                "ANSI_LIGHT_GREEN_BACKGROUND",
                "ANSI_LIGHT_YELLOW",
                "ANSI_LIGHT_YELLOW_BACKGROUND",
                "ANSI_LIGHT_BLUE",
                "ANSI_LIGHT_BLUE_BACKGROUND",
                "ANSI_LIGHT_MAGENTA",
                "ANSI_LIGH_MAGENTA_BACKGROUND",
                "ANSI_LIGHT_CYAN",
                "ANSI_LIGHT_CYAN_BACKGROUND"
        };


        for (int i = 0; i < styles.length; i++) {
            System.out.print(ConsoleService.ANSI_WHITE_BACKGROUND);
            System.out.print(ConsoleService.ANSI_BLACK);
            System.out.print(styles[i]);
            System.out.print(fillText(styleWords[i] + " ", 25));
            System.out.println(ANSI_RESET);
        }

        for (int i = 0; i < codes.length; i++) {
            System.out.print(codes[i]);
            if (codes[i] == ANSI_BLACK) {
                System.out.print(ANSI_WHITE_BACKGROUND);
            } else if (codes[i] == ANSI_WHITE_BACKGROUND) {
                System.out.print(ConsoleService.ANSI_BLACK);
            }
            System.out.print(fillText(codeWords[i] + " ", 25));
            System.out.println(ANSI_RESET);

        }
    }

    public void demoAll() {
        outputPalette();
        printError("An error occurred");
        printMessage("Test Message", ANSI_BLACK, ANSI_LIGHT_GRAY_BACKGROUND, true, true);
        System.out.println("All done");

    }



}
