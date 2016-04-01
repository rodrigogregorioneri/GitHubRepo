package net.paulacr.githubrepo.repositories;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import net.paulacr.githubrepo.R;
import net.paulacr.githubrepo.data.Item;
import net.paulacr.githubrepo.data.Repositories;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepositoriesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RepositoriesContract.View {

    //**************************************************************************
    // Bind Views
    //**************************************************************************
    @Bind(R.id.listRepo)
    RecyclerView repositoryList;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    private List<Item> itemList;
    private RepositoriesAdapter adapter;
    ProgressDialog progressBar;

    //**************************************************************************
    // LifeCycle
    //**************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repositories_activity);

        ButterKnife.bind(this);
        setupViews();
        navigationView.setNavigationItemSelectedListener(this);

        //create the presenter or start service for request
        RepositoriesPresenter presenter = new RepositoriesPresenter(this);
        showLoadingView(true);
        presenter.searchRepositories("", 1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //**************************************************************************
    // Setup Views
    //**************************************************************************

    private void setupViews() {
        setupToolBar();
        setupActionBar();
        createRepositoryList();

    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
    }

    private void setupActionBar() {
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_drawer);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void createRepositoryList() {
        itemList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);

        adapter = new RepositoriesAdapter(this, itemList);
        repositoryList.setLayoutManager(manager);
        repositoryList.setAdapter(adapter);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //**************************************************************************
    // Ui methods
    //**************************************************************************

    @Override
    public void showRepositories(List<Item> repositories) {
        //Create the adapter and pass the repositories list
        itemList.addAll(repositories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showLoadingView(boolean show) {
        if(progressBar == null) {
            progressBar = new ProgressDialog(this);
        }

        if(show) {
            progressBar.setTitle("Please Wait");
            progressBar.setMessage("Searching for repositories");
            progressBar.show();
        } else {
            progressBar.dismiss();
        }
    }

    @Override
    public void retry() {

    }
}