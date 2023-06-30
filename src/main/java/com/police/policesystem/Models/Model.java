package com.police.policesystem.Models;

import com.police.policesystem.Controllers.LoginController;
import com.police.policesystem.Views.AccountType;
import com.police.policesystem.Views.ViewFactory;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final LoginController loginController;
    private AccountType loginAccountType = AccountType.USER;

    private Model(){
        this.loginController = new LoginController();
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance(){

        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewsFactory() {
        return viewFactory;
    }
    public LoginController getLoginController(){return loginController;}
    public AccountType getLoginAccountType(){return loginAccountType;}

    public void setLoginAccountType(AccountType loginAccountType) {this.loginAccountType = loginAccountType;}

}

