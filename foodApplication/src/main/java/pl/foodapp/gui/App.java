package pl.foodapp.gui;

import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.baskets.BasketsDTO;
import pl.foodapp.baskets.AddBasket;
import pl.foodapp.database.AccountsEntity;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.DishesDTO;
import pl.foodapp.dishes.favourites.AddFavoriteService;
import pl.foodapp.dishes.favourites.FavoritesDAO;
import pl.foodapp.dishes.favourites.FavoritesDTO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.paymentMethods.PaymentMethodsDAO;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.reviews.ReviewsDAO;
import pl.foodapp.workers.WorkersDAO;
import pl.foodapp.accounts.*;

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
    ModifyRestaurantsPanel modifyRestaurantsPanel;
    private ModifyRestaurantDetailsPanel modifyRestaurantDetailsPanel;
    private BasketPanel basketPanel;
    private OpinionPanel opinionPanel;
    private PaymentMethodsPanel paymentMethodsPanel;
    private AddPaymentMethodPanel addPaymentMethodPanel;
    private PaymentPanel paymentPanel;
    private ModifyPaymentMethodPanel modifyPaymentMethodPanel;
    private HistoryPanel historyPanel;
    private OrderDetailsPanel orderDetailsPanel;
    private ComplaintPanel complaintPanel;
    private AddDiscountPanel addDiscountPanel;
    private FavouritesSelectionPanel favouriteSelectionPanel;
    private RestaurantStatisticsPanel restaurantStatisticsPanel;
    private FavouritesRestaurantChoicePanel favouritesRestaurantChoicePanel;

    private JLabel clockLabel;
    public RestaurantsEntity selectedRestaurant;

    public AccountsEntity loggedAccount;
    private AccountsDAO accountsDAO = new AccountsDAO();

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
        addPaymentMethodPanel = new AddPaymentMethodPanel(this);
        paymentPanel = new PaymentPanel(this);
        modifyPaymentMethodPanel = new ModifyPaymentMethodPanel(this);
        historyPanel = new HistoryPanel(this);
        orderDetailsPanel = new OrderDetailsPanel(this);
        complaintPanel = new ComplaintPanel(this);
        addDiscountPanel = new AddDiscountPanel(this);
        favouriteSelectionPanel = new FavouritesSelectionPanel(this);
        restaurantStatisticsPanel = new RestaurantStatisticsPanel(this);
        favouritesRestaurantChoicePanel = new FavouritesRestaurantChoicePanel(this);


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
        add(addPaymentMethodPanel, "AddPaymentMethod");
        add(paymentPanel, "PaymentPanel");
        add(modifyPaymentMethodPanel, "ModifyPaymentMethod");
        add(historyPanel, "History");
        add(orderDetailsPanel, "OrderDetails");
        add(complaintPanel, "Complaint");
        add(addDiscountPanel, "AddDiscount");
        add(restaurantStatisticsPanel, "RestaurantStatistics");
        add(favouriteSelectionPanel, "favouriteSelectionPanel");
        add(favouritesRestaurantChoicePanel, "favouritesRestaurantChoicePanel");

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
    public void onFavouritesRestaurantSelected(RestaurantsEntity restaurant) {
        selectedRestaurant = restaurant;
        favouriteSelectionPanel.enter(selectedRestaurant);
        cardLayout.show(getContentPane(), "favouriteSelectionPanel");
    }

    @Override
    public void onOpinionRestaurantSelected(RestaurantsEntity restaurant) {
        selectedRestaurant = restaurant;
        opinionPanel.enter(loggedAccount.getAccountId(), selectedRestaurant.getRestaurantId());
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
        WorkersDAO WDAO = new WorkersDAO();
        RestaurantsDAO RDAO = new RestaurantsDAO();
        DishesDAO DDAO = new DishesDAO();
        OrderedDishesDAO ODDAO = new OrderedDishesDAO();
        ReviewsDAO RvDAO = new ReviewsDAO();

        DeleteService DS = new DeleteService(ADAO, ODAO, PMDAO, FDAO, BDAO, WDAO, RDAO, DDAO, ODDAO, RvDAO);
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
    public void addToFavourites(DishesDTO dish) {
        FavoritesDTO favourite = new FavoritesDTO();
        AddFavoriteService addFav = new AddFavoriteService(new FavoritesDAO());
        favourite.setCustomer(loggedAccount.getAccountId());
        favourite.setDishId(dish.getDishId());
        addFav.addFavorite(favourite);
        JOptionPane.showMessageDialog(this, "Dodano " + dish.getName() + " do ulubionych.");
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

    @Override
    public void enterPayment() {
        paymentPanel.enter(loggedAccount.getAccountId());
        cardLayout.show(getContentPane(), "PaymentPanel");
    }

    @Override
    public void enterModifyPaymentMethod(int id) {
        modifyPaymentMethodPanel.enter(id);
        cardLayout.show(getContentPane(), "ModifyPaymentMethod");
    }

    @Override
    public void enterHistoryPanel() {
        historyPanel.enter(loggedAccount.getAccountId());
        cardLayout.show(getContentPane(), "History");
    }

    @Override
    public void enterOrderDetailsPanel(int orderId) {
        orderDetailsPanel.enter(loggedAccount.getAccountId(), orderId);
        cardLayout.show(getContentPane(), "OrderDetails");
    }

    @Override

    public void enterComplaintPanel(int orderId) {
        complaintPanel.enter(loggedAccount.getAccountId(), orderId);
        cardLayout.show(getContentPane(), "Complaint");
    }

    @Override
    public void enterRestaurantStats(int restaurantId) {
        restaurantStatisticsPanel.enter(restaurantId);
        cardLayout.show(getContentPane(), "RestaurantStatistics");
    }

    @Override
    public void enterRestaurantChoicePanel() {
        restaurantChoicePanel.enter();
        cardLayout.show(getContentPane(), "RestaurantChoice");
    }

    public void updateAccountInfo() {
        AccountTypesDAO ATDAO = new AccountTypesDAO();
        ViewAccountTypeNameService VATNS = new ViewAccountTypeNameService(ATDAO);
        String type = VATNS.getAccountTypeName(loggedAccount.getType());
        mainMenuPanel.updateAccountLabel(loggedAccount.getEmail());
        accountInfoPanel.updateAccountInfo("Imię: " + loggedAccount.getName() + "\nNazwisko: " + loggedAccount.getSurname() + "\nEmail: " + loggedAccount.getEmail() + "\nTyp: " + type);
        editAccountPanel.setFields(loggedAccount.getName(), loggedAccount.getSurname(), loggedAccount.getEmail(), loggedAccount.getPassword());
    }


    @Override
    public void enterModifyRestaurantPanel() {
        modifyRestaurantsPanel.enter(loggedAccount.getAccountId());
        cardLayout.show(getContentPane(), "ModifyRestaurants");
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
