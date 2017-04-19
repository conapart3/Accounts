package com.tndo.multiaccounts.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tndo.multiaccounts.R;
import com.tndo.multiaccounts.util.DatabaseHelper;

/**
 * Created by Conal on 18/04/2017.
 */

public class CreateAccountDialogFragment extends DialogFragment
{
    // Use this instance of the interface to deliver action events
    private CreateAccountDialogListener listener;
    private View v;

    /**
     * The system calls this to get the DialogFragment's layout, regardless
     * of whether it's being displayed as a dialog or an embedded fragment.
     */
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState )
    {
        //todo this could be the problem. dialog.getView() in MainActivity gets the view returned by this. This may not be the same. Do you need to make a class level variable?
        // Inflate the layout to use as dialog or embedded fragment
        return v;
    }

    /**
     * The system calls this only when creating the layout in a dialog.
     */
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState )
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        v = inflater.inflate(R.layout.account_create_dialog, null);

        builder.setView(v).setPositiveButton(R.string.done, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick( DialogInterface dialog, int id )
            {
                // Send positive button event back to the activity
                listener.onDialogPositiveClick(CreateAccountDialogFragment.this);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick( DialogInterface dialog, int id )
            {
                // Send negative button event back to the activity
                listener.onDialogNegativeClick(CreateAccountDialogFragment.this);
            }
        });
        return builder.create();
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        // verify that the host implements the callback interface
        try{
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (CreateAccountDialogListener) context;
        } catch ( ClassCastException e ){
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString() + " must implement NoticeDialogListener");
        }
    }

    /**
     * The activity that creates an instance of this dialog fragment should implement this interface
     * in order to receive event callbacks. Each method passes the DialogFragment in case the host
     * needs to query it.
     */
    public interface CreateAccountDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

}
