package com.rentalcam.model;


import java.util.ArrayList;
import java.util.List;

public class User  {
    String username;
    String password;
    double walletBalance;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.walletBalance = 0;
     
    }

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }
	}

	

