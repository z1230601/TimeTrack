package android.finite.com.timetrack;

import android.app.DialogFragment;
import android.content.Intent;
import android.finite.com.data.Country;
import android.finite.com.data.Customer;
import android.finite.com.data.Project;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.dialogs.PropertyInsertDialog;
import android.finite.com.timetrack.view.spinner.CountrySpinnerAdapter;
import android.finite.com.timetrack.view.spinner.CustomerSpinnerAdapter;
import android.finite.com.timetrack.view.PropertyListAdapter;
import android.finite.com.utility.Tuple;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Map;

public class ProjectDataView extends AppCompatActivity implements View.OnClickListener, PropertyInsertDialog.PropertyDialogListener {
    private Project exisitingProject = null;
    private CountrySpinnerAdapter countryAdapter = null;
    private CustomerSpinnerAdapter customerSpinnerAdapter;
    private RecyclerView propertyList = null;
    private LinearLayoutManager layoutManager;
    private PropertyListAdapter listAdapter;
    private Button saveBtn;
    private TextView codeName;
    private Spinner countrySpinner;
    private Spinner customerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_data_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.saveBtn = (Button) findViewById(R.id.saveBtn);
        this.saveBtn.setOnClickListener(this);

        this.codeName = (TextView) findViewById(R.id.codeName);
        this.propertyList = (RecyclerView) findViewById(R.id.propertiesList);
        this.layoutManager = new LinearLayoutManager(this);

        this.propertyList.setHasFixedSize(true);
        this.propertyList.setLayoutManager(this.layoutManager);

        this.countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        this.countryAdapter = new CountrySpinnerAdapter(this);
        this.countrySpinner.setAdapter(this.countryAdapter);

        this.customerSpinner = (Spinner) findViewById(R.id.customerSpinner);
        this.customerSpinnerAdapter = new CustomerSpinnerAdapter(this);
        this.customerSpinner.setAdapter(new CustomerSpinnerAdapter(this));

        if (getIntent().getExtras() != null &&
                getIntent().getExtras().containsKey(Project.PROJECT_KEY)) {
            initFromProject(getIntent().getExtras().getInt(Project.PROJECT_KEY), toolbar);
        } else {
            toolbar.setTitle( getResources().getString(R.string.PojectDataView_newProjectTitle));
            this.listAdapter = new PropertyListAdapter();
        }

        this.propertyList.setAdapter(this.listAdapter);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initFromProject(int projectId, Toolbar toolbar) {

        this.exisitingProject = DataManager.get().getProjectById(projectId);
        codeName.setText(this.exisitingProject.getCodeName());


        preslectIdInSpinner(countrySpinner, this.countryAdapter.getIndex(this.exisitingProject.getCountryId()));
        preslectIdInSpinner(customerSpinner, this.customerSpinnerAdapter.getIndex(this.exisitingProject.getCustomerId()));

        toolbar.setTitle(getResources().getString(R.string.ProjectDataView_prefixEditModeTitle) +
                " " + this.exisitingProject.getCodeName());

        this.listAdapter = new PropertyListAdapter();
        for(Map.Entry<String, String> entry :  this.exisitingProject.getProperties().entrySet()) {
            this.listAdapter.addItem(entry.getKey(), entry.getValue());
        }

        //TODO: add selection, add, delete of properties
        {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addPropertyFab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PropertyInsertDialog dialog = new PropertyInsertDialog();
                    dialog.show(getFragmentManager(), "PropertyInsertDialog");
                }
            });
        }

    }

    private void preslectIdInSpinner(Spinner countrySpinner, int itemindex) {
        countrySpinner.setSelection(itemindex);
    }

    @Override
    public void onClick(View v) {
        if(this.exisitingProject == null ) {
            this.exisitingProject = new Project(this.codeName.getText().toString(),
            -1, ((Country) this.countrySpinner.getSelectedItem()).getCountryId(),
                    ((Customer) this.customerSpinner.getSelectedItem()).getCustomerId());
            DataManager.get().saveNewProject(this.exisitingProject);
        } else {
            this.exisitingProject.setCodeName(this.codeName.getText().toString());
            this.exisitingProject.setCountry((Country) this.countrySpinner.getSelectedItem());
            this.exisitingProject.setCustomer((Customer) this.customerSpinner.getSelectedItem());
            DataManager.get().updateProject(this.exisitingProject);
        }
        finish();
    }

    @Override
    public void setProperty(Tuple<String, String> data) {
        if(this.exisitingProject != null) {
            this.exisitingProject.setAdditionalProperty(data.first, data.second);
            //TODO recreated list and/or add item
        } else {
            //TODO figure out a good way to save data if no existing project present
        }
    }
}
