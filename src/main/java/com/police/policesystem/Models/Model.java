package com.police.policesystem.Models;

import com.police.policesystem.Views.ViewsFactory;

public class Model {

    private static Model model;
    private final ViewsFactory viewsFactory;

    private Model(){
            this.viewsFactory = new ViewsFactory();
    }

    public static synchronized Model getInstance(){

        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewsFactory getViewsFactory() {
        return viewsFactory;
    }
}
