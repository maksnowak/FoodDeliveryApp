package pap2023z.z09.gui;

import pap2023z.z09.accounts.*;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.baskets.BasketsDTO;
import pap2023z.z09.baskets.AddBasket;
import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDTO;
import pap2023z.z09.dishes.favourites.FavoritesDAO;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App extends JFrame implements Callback {
    public CardLayout cardLayout;

    private WelcomePanel welcomePanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private MainMenuPanel mainMenuPanel;
    private RestaurantChoicePanel restaurantChoicePanel;
    private OpinionRestaurantChoicePanel opinionRestaurantChoicePanel;
    private DishSelectionPanel dishSelectionPanel;
    private AccountInfoPanel accountInfoPanel;
    private EditAccountPanel editAccountPanel;
    private ModifyRestaurantsPanel modifyRestaurantsPanel;
    private ModifyRestaurantDetailsPanel modifyRestaurantDetailsPanel;
    private BasketPanel basketPanel;
    private OpinionPanel opinionPanel;
    private PaymentMethodsPanel paymentMethodsPanel;


    private JLabel clockLabel;
    public RestaurantsEntity selectedRestaurant;

    public AccountsEntity loggedAccount;

    public ModifyRestaurantDetailsPanel getModifyRestaurantDetailsPanel() {
        return modifyRestaurantDetailsPanel;
    }

    public App() {
        setTitle("PAP2023Z - Z09");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


        cardLayout = new CardLayout();
        setLayout(cardLayout);

        welcomePanel = new WelcomePanel(this);
        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        mainMenuPanel = new MainMenuPanel(this);
        restaurantChoicePanel = new RestaurantChoicePanel(this);
        opinionRestaurantChoicePanel = new OpinionRestaurantChoicePanel(this);
        dishSelectionPanel = new DishSelectionPanel(this);
        accountInfoPanel = new AccountInfoPanel(this);
        editAccountPanel = new EditAccountPanel(this);
        modifyRestaurantsPanel = new ModifyRestaurantsPanel(this);
        modifyRestaurantDetailsPanel = new ModifyRestaurantDetailsPanel(this);
        basketPanel = new BasketPanel(this);
        opinionPanel = new OpinionPanel(this);
        paymentMethodsPanel = new PaymentMethodsPanel(this);

        add(welcomePanel, "Welcome");
        add(loginPanel, "Login");
        add(registerPanel, "Register");
        add(mainMenuPanel, "MainMenu");
        add(restaurantChoicePanel, "RestaurantChoice");
        add(opinionRestaurantChoicePanel, "OpinionRestaurantChoice");
        add(dishSelectionPanel, "DishSelection");
        add(accountInfoPanel, "AccountInfo");
        add(editAccountPanel, "EditAccount");
        add(modifyRestaurantsPanel, "ModifyRestaurants");
        add(modifyRestaurantDetailsPanel, "ModifyRestaurantDetails");
        add(basketPanel, "Basket");
        add(opinionPanel, "Opinion");
        add(paymentMethodsPanel, "PaymentMethods");

        cardLayout.show(this.getContentPane(), "Welcome");

        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.RIGHT);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        getContentPane().add(clockLabel, BorderLayout.NORTH);
        timer.start();
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    @Override
    public void onRestaurantSelected(RestaurantsEntity restaurant) {
        selectedRestaurant = restaurant;
        dishSelectionPanel.enter(selectedRestaurant);
        cardLayout.show(getContentPane(), "DishSelection");
    }

    @Override
    public void onOpinionRestaurantSelected(RestaurantsEntity restaurant) {
        selectedRestaurant = restaurant;
        cardLayout.show(getContentPane(), "Opinion");
    }

    @Override
    public void onAccountLogged(AccountsEntity account) {
        loggedAccount = account;
        updateAccountInfo();
        if (loggedAccount.getType() == 2) {
            mainMenuPanel.showRestaurantsButton();
        }
        else {
            mainMenuPanel.hideRestaurantsButton();
        }
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
        AccountsDAO ADAO = new AccountsDAO();
        OrdersDAO ODAO = new OrdersDAO();
        PaymentMethodsDAO PMDAO = new PaymentMethodsDAO();
        FavoritesDAO FDAO = new FavoritesDAO();
        BasketsDAO BDAO = new BasketsDAO();
        DeleteService DS = new DeleteService(ADAO, ODAO, PMDAO, FDAO, BDAO);
        DS.deleteAccount(loggedAccount.getAccountId());
        loggedAccount = null;
        JOptionPane.showMessageDialog(this, "Konto zostało usunięte.");
        cardLayout.show(getContentPane(), "Welcome");
    }

    @Override
    public void onAccountLogout() {
        loggedAccount = null;
        cardLayout.show(getContentPane(), "Welcome");
    }

    @Override
    public void addToBasket(DishesDTO dish) {
        BasketsDTO basket = new BasketsDTO();
        AddBasket addBasket = new AddBasket(new BasketsDAO());
        basket.setCustomerId(loggedAccount.getAccountId());
        basket.setDishId(dish.getDishId());
        addBasket.addBasket(basket);
        JOptionPane.showMessageDialog(this, "Dodano " + dish.getName() + " do koszyka.");
    }

    @Override
    public void enterBasket() {
        basketPanel.enter(loggedAccount.getAccountId());
        cardLayout.show(getContentPane(), "Basket");
    }

    @Override
    public void enterPaymentMethods() {
        paymentMethodsPanel.enter(loggedAccount.getAccountId());
        cardLayout.show(getContentPane(), "PaymentMethods");
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
