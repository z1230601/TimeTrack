package android.finite.com.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

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
public class Project {
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

    public Project() {}

    public Project(String codeName) {
        this.projectId = -1;
        this.codeName = codeName;
    }

    public void setAdditionalProperty(String name, String value) {
        this.properties.put(name, value);
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

    public List<String> getShortSummary() {
        List<String> lines = new ArrayList<String>();
        lines.add(this.codeName);
        for(Map.Entry<String, String> entry : this.properties.entrySet()) {
            lines.add(entry.getValue());
        }
        return lines;
    }
}
