package com.tndo.multiaccounts.util;

/**
 * Created by Conal on 19/04/2017.
 */

public class ValidatorUtil
{
    public static double validateMonetaryInput( String input )
    {
        if ( input == null )
            return 0.00;

        if ( input.equals("") )
            return 0.00;

        try {
            return Double.parseDouble(input);
        } catch ( NumberFormatException e ) {
            return 0.00;
        }
    }
}
