package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.finite.com.timetrack.data.DataManager;
import android.finite.com.timetrack.view.cards.LinesCardDataProvider;
import android.finite.com.utility.TextLayout;
import android.finite.com.utility.Tuple;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "projects", foreignKeys = {@ForeignKey(entity = Country.class,
        parentColumns = "countryId",
        childColumns = "countryId",
        onDelete = CASCADE), @ForeignKey(entity = Customer.class,
        parentColumns = "customerId",
        childColumns = "customerId",
        onDelete = CASCADE)},
    indices = {@Index("projectId"),  @Index("countryId"), @Index("customerId")})
public class Project extends LinesCardDataProvider {
    @Ignore
    public static final String PROJECT_KEY = "PROJECT";

    @PrimaryKey
    private int projectId;
    @ColumnInfo(name="codeName")
    private String codeName;
    @ColumnInfo(name="countryId")
    private int countryId;
    @ColumnInfo(name="customerId")
    private int customerId;
    @Ignore
    private Map<String, String> properties = new LinkedHashMap<String, String>();
    @Ignore
    private Country associatedCountry;
    @Ignore
    private Customer associatedCustomer;

    public void init() {
        this.associatedCountry = DataManager.get().getCountryById(countryId);
        this.associatedCustomer = DataManager.get().getCustomerById(customerId);
    }

    public Project() {

    }

    @Ignore
    public Project(String codeName) {
        this(codeName, -1);

    }

    @Ignore
    public Project(String codeName, int id, int countryId, int customerId) {
        this.projectId = id;
        this.codeName = codeName;
        this.countryId = countryId;
        this.customerId = customerId;
        init();
    }

    @Ignore
    public Project(String codeName, int id) {
        this.projectId = id;
        this.codeName = codeName;
    }

    public void setAdditionalProperty(String name, String value) {
        this.properties.put(name, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return this.codeName;
    }

    public void setCountry(Country country) {
        this.countryId = country.getCountryId();
        this.associatedCountry = country;
    }

    public void setCustomer(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.associatedCustomer = customer;
    }

    @Override
    public Tuple<TextLayout, String> getHeadLine() {
        return new Tuple<TextLayout, String>(TextLayout.LARGE, this.codeName);
    }

    @Override
    public Tuple<TextLayout, List<String>> getContentLines() {
        List<String> lines = new ArrayList<String>();
        for(Map.Entry<String, String> entry : this.properties.entrySet()) {
            lines.add(entry.getValue());
        }
        return new Tuple<TextLayout, List<String>>(TextLayout.SMALL, lines);
    }

    @Override
    public Tuple<TextLayout, String> getFootNote() {
        if(this.associatedCountry == null) {
            init();
        }
        return new Tuple<TextLayout, String>(TextLayout.MEDIUM, this.associatedCountry.getName());
    }

    @Override
    public boolean equals(Object ob) {
        if(ob instanceof Project){
            return this.projectId == ((Project) ob).getProjectId();
        }

        return false;
    }
}
