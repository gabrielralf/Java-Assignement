
public class Main {
    public static void main(String[] args) {
        
        ResidentialBuilding AltaVista = new ResidentialBuilding("Av. Santos Dumont 7785", 12, 127.0, true, 36, true, true);
        CommercialBuilding EdificioComercial = new CommercialBuilding("Av. Brasil 1234", 54, 300.0, true, "Investment Banking", 540 , true, 30.0);

        System.out.println("=== Residential Building Info ===");
        AltaVista.printInfo();

        System.out.println("\n=== Commercial Building Info ===");
        EdificioComercial.printInfo();
        
    }
}
