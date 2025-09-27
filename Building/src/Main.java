public class Main {
    public static void main(String[] args) throws Exception {
        BuildingRepository repo = new CsvBuildingRepository("data/buildings.csv");
        BuildingManager manager = new BuildingManager(repo);
        manager.run();
    }
}
