package com.solvd;

import com.solvd.controller.application.Navigator;
import com.solvd.exception.GraphCreationException;
import com.solvd.exception.InvalidGraphException;

public class NavigatorCLI {
    public static void main(String[] args) {
        Navigator navigator = new Navigator();
        try {
            navigator.run();
        } catch (InvalidGraphException ige) {
            ige.getMessage();
        } catch (GraphCreationException gce) {
            gce.getMessage();
        }
    }
}
