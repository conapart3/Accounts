package com.tndo.multiaccounts.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.tndo.multiaccounts.R;
import com.tndo.multiaccounts.account.AccountArrayAdapter;
import com.tndo.multiaccounts.account.Subaccount;
import com.tndo.multiaccounts.util.DatabaseHelper;
import com.tndo.multiaccounts.util.ValidatorUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CreateAccountDialogFragment.CreateAccountDialogListener
{

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create db helper object
        dbHelper = new DatabaseHelper(getBaseContext());

        setupFloatingActionButton(dbHelper);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Inflate the account header view
//        final AccountHeader accountHeader = (AccountHeader) findViewById(R.id.accountHeader);
//        final AccountHeader accountHeader = new AccountHeader(getBaseContext());

//        TextView mainAccountNameView = (TextView) accountHeader.findViewById(R.id.mainAccountName);
//        mainAccountNameView.setText(getMainName());
//        TextView mainAccountBalanceView = (TextView) accountHeader.findViewById(R.id.mainAccountBalance);
//        mainAccountBalanceView.setText(getMainBalance());

        // Inflate the account list view
        final ListView listView = (ListView) findViewById(R.id.accountListView);
        List<Subaccount> subaccountList = getSubaccountList(dbHelper, "mainaccount");
        final ArrayAdapter adapter = new AccountArrayAdapter(this, R.layout.account_list_item, subaccountList);
        listView.setAdapter(adapter);
    }

    /**
     * Sets up the floating action button. Its action is to add a new sub account to the open main account.
     *
     * @param dbHelper the database helper object for the database implementation.
     */
    private void setupFloatingActionButton( final DatabaseHelper dbHelper )
    {
        // Floating action button for adding new sub accounts to the current viewed main account
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( final View view )
            {
                CreateAccountDialogFragment dialog = new CreateAccountDialogFragment();
                dialog.show(getFragmentManager(), "Create Account Dialog Fragment");
            }
        });
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if ( drawer.isDrawerOpen(GravityCompat.START) ) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings ) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected( MenuItem item )
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if ( id == R.id.nav_camera ) {
            // Handle the camera action
        } else if ( id == R.id.nav_gallery ) {

        } else if ( id == R.id.nav_slideshow ) {

        } else if ( id == R.id.nav_manage ) {

        } else if ( id == R.id.nav_share ) {

        } else if ( id == R.id.nav_send ) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Placeholder method, sets up a list of dummy data
     *
     * @return
     */
    private List<Subaccount> getSubaccountList( final DatabaseHelper dbHelper, final String mainaccount )
    {
        List<Subaccount> subaccountList = dbHelper.getSubAccounts(mainaccount);
        return subaccountList;
    }

    /**
     * Placeholder methods for retrieving the name and balance of the main account from the db.
     *
     * @return
     */
    private String getMainBalance()
    {
        return "" + 5123.35;
    }

    private String getMainName()
    {
        return "Danske Bank Current Subaccount";
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick( DialogFragment dialog )
    {
        final EditText nameET = (EditText) dialog.getView().findViewById(R.id.createAccountName);
        final EditText balanceET = (EditText) dialog.getView().findViewById(R.id.createAccountBalance);
        final String name = nameET.getText().toString();
        final String balance = balanceET.getText().toString();

        //todo - figure out why name and balance here are just ""
        dbHelper.addSubAccount(name, ValidatorUtil.validateMonetaryInput(balance), "mainaccount", "ic_food");
    }

    @Override
    public void onDialogNegativeClick( DialogFragment dialog )
    {

    }
}
