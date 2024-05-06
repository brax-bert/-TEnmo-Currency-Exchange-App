package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.UserService;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class App {
    private UserService userService;
    private AuthenticatedUser currentUser;
    private RestTemplate restTemplate;

    public App() {
        this.restTemplate = new RestTemplate();
        this.userService = new UserService();}
    public static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);


    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }

    //Endpoint is wrong
    private List<User> getUsersFromServer() {
        List<User> users = userService.getAllUsers();
        return users;
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                List<User> userList = getUsersFromServer();
                sendBucks(userList);
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
		int userId = currentUser.getUser().getId();
        double balance = authenticationService.getCurrentBalance(userId);

        System.out.println("You're current balance is: $" + balance);
	}

	private void viewTransferHistory() {
		// TODO Auto-generated method stub
		
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

    private void sendBucks(List<User> userList) {
        consoleService.printUsers(userList);
        int toUserId = consoleService.promptForToUserId();
        if (toUserId == currentUser.getUser().getId()) {
            System.out.println("Sorry, you can't send yourself money!");
            return;
        }
        double balance = authenticationService.getCurrentBalance(currentUser.getUser().getId());
        System.out.println("Your current balance is $" + balance);
        BigDecimal amount = consoleService.promptForTransferAmount(balance);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid transfer amount :(");
            return;
        }
        boolean transferSuccess = authenticationService.sendTransfer(currentUser.getUser().getId(), toUserId, amount.doubleValue());
        if (transferSuccess) {
            System.out.println("Transfer Successful!");
        } else {
            System.out.println("Transfer Failed :(");
        }
    }


    private void requestBucks() {
		// TODO Auto-generated method stub
		
	}


}
