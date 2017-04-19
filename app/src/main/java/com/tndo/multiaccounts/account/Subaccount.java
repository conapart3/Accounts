package com.tndo.multiaccounts.account;

/**
 * Created by Conal on 15/04/2017.
 */

public class Subaccount
{
    public int id;

    public String name;

    public double balance;

    public String mainAccountName;

    public String icon;

    public Subaccount( int id, String name, double balance, String icon, String mainAccountName )
    {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.icon = icon;
        this.mainAccountName = mainAccountName;
    }
}
