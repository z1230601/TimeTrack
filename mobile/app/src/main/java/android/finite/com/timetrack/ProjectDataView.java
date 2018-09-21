package android.finite.com.timetrack;

import android.finite.com.data.Country;
import android.finite.com.data.Customer;
import android.finite.com.data.Project;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.CountrySpinnerAdapter;
import android.finite.com.timetrack.view.CustomerSpinnerAdapter;
import android.finite.com.timetrack.view.PropertyListAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Map;

public class ProjectDataView extends AppCompatActivity implements View.OnClickListener {
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

        this.propertyList = (RecyclerView) findViewById(R.id.propertiesList);
        this.layoutManager = new LinearLayoutManager(this);

        this.propertyList.setHasFixedSize(true);
        this.propertyList.setLayoutManager(this.layoutManager);

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
        this.codeName = (TextView) findViewById(R.id.codeName);
        codeName.setText(this.exisitingProject.getCodeName());

        this.countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        this.countryAdapter = new CountrySpinnerAdapter(this);
        countrySpinner.setAdapter(this.countryAdapter);
        preslectIdInSpinner(countrySpinner, this.countryAdapter.getIndex(this.exisitingProject.getCountryId()));

        this.customerSpinner = (Spinner) findViewById(R.id.customerSpinner);
        this.customerSpinnerAdapter = new CustomerSpinnerAdapter(this);
        customerSpinner.setAdapter(new CustomerSpinnerAdapter(this));
        preslectIdInSpinner(customerSpinner, this.customerSpinnerAdapter.getIndex(this.exisitingProject.getCustomerId()));

        toolbar.setTitle(getResources().getString(R.string.ProjectDataView_prefixEditModeTitle) +
                " " + this.exisitingProject.getCodeName());

        this.listAdapter = new PropertyListAdapter();
        for(Map.Entry<String, String> entry :  this.exisitingProject.getProperties().entrySet()) {
            this.listAdapter.addItem(entry.getKey(), entry.getValue());
        }

        //TODO: add selction, add, delete of properties
        //TODO: add Save

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
}
