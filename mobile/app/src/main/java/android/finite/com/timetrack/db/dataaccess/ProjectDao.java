package android.finite.com.timetrack.db.dataaccess;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.finite.com.timetrack.db.data.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Query("Select * FROM projects;")
    List<Project> getAllProjects();

    @Insert
    void saveProject(Project project);

    @Update
    void updateProjet(Project project);

    @Query("SELECT * FROM projects where projectId LIKE  :projectId ")
    Project getProjectById(int projectId);
}
