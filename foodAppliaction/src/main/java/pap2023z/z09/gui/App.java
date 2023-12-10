package pap2023z.z09.gui;

import pap2023z.z09.accounts.*;
import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.RestaurantsEntity;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame implements Callback {
    public CardLayout cardLayout;

    private WelcomePanel welcomePanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private MainMenuPanel mainMenuPanel;
    private RestaurantChoicePanel restaurantChoicePanel;
    private DishSelectionPanel dishSelectionPanel;
    private AccountInfoPanel accountInfoPanel;
    private EditAccountPanel editAccountPanel;


    public RestaurantsEntity selectedRestaurant;
    public AccountsEntity loggedAccount;

    public App() {
        setTitle("PAP2023Z - Z09");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        welcomePanel = new WelcomePanel(this);
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        mainMenuPanel = new MainMenuPanel(this);
        restaurantChoicePanel = new RestaurantChoicePanel(this);
        dishSelectionPanel = new DishSelectionPanel(this);
        accountInfoPanel = new AccountInfoPanel(this);
        editAccountPanel = new EditAccountPanel(this);

        add(welcomePanel, "Welcome");
        add(loginPanel, "Login");
        add(registerPanel, "Register");
        add(mainMenuPanel, "MainMenu");
        add(restaurantChoicePanel, "RestaurantChoice");
        add(dishSelectionPanel, "DishSelection");
        add(accountInfoPanel, "AccountInfo");
        add(editAccountPanel, "EditAccount");

        cardLayout.show(this.getContentPane(), "Welcome");
    }

    @Override
    public void onRestaurantSelected(RestaurantsEntity restaurant) {
        selectedRestaurant = restaurant;
        dishSelectionPanel.updateRestaurantLabel(selectedRestaurant);
        cardLayout.show(getContentPane(), "DishSelection");
    }

    @Override
    public void onAccountLogged(AccountsEntity account) {
        loggedAccount = account;
        updateAccountInfo();
        cardLayout.show(getContentPane(), "MainMenu");
    }

    @Override
    public void onEditAccount(String firstName, String lastName, String email, String password) throws EmailAlreadyExistsException {
        AccountsDTO DTO = new AccountsDTO();
        AccountsDAO DAO = new AccountsDAO();
        InputValidationService IVS = new InputValidationService();
        VerifyIfEmailAlreadyExistsService VIAES = new VerifyIfEmailAlreadyExistsService(DAO);

        DTO.setAccountId(loggedAccount.getAccountId());
        DTO.setName(firstName);
        DTO.setSurname(lastName);
        DTO.setEmail(email);
        DTO.setPassword(password);
        DTO.setType(loggedAccount.getType());
        System.out.println(DTO.getName());
        System.out.println(DTO.getSurname());
        System.out.println(DTO.getEmail());
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        UpdateService US = new UpdateService(DAO, IVS, VIAES);
        US.updateAccount(DTO);
        loggedAccount = DAO.getAccountById(loggedAccount.getAccountId());
        updateAccountInfo();
        cardLayout.show(getContentPane(), "AccountInfo");
    }

    @Override
    public void onDeleteAccount() {
        AccountsDAO DAO = new AccountsDAO();
        DeleteService DS = new DeleteService(DAO);
        DS.deleteAccount(loggedAccount.getAccountId());
        loggedAccount = null;
        JOptionPane.showMessageDialog(this, "Konto zostało usunięte.");
        cardLayout.show(getContentPane(), "Welcome");
    }

    public void updateAccountInfo() {
        mainMenuPanel.updateAccountLabel(loggedAccount.getEmail());
        accountInfoPanel.updateAccountInfo("Imię: " + loggedAccount.getName() + "\nNazwisko: " + loggedAccount.getSurname() + "\nEmail: " + loggedAccount.getEmail() + "\nTyp: " + loggedAccount.getType());
        editAccountPanel.setFields(loggedAccount.getName(), loggedAccount.getSurname(), loggedAccount.getEmail(), loggedAccount.getPassword());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().setVisible(true);
            }
        });
    }
}
