import java.util.List;

public interface BuildingRepository {
    List<Building> loadBuildings() throws Exception;
}
