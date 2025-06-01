package eventCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Player;
import entities.ship.Ship;
import entities.ship.ShipTile;
import entities.board.Board;
import ui.Graphic;
import entities.Position;

public class EpidemicCard extends Card {
    private static final int INFECTION_RATE = 30; 
    private static final int MEDICAL_COST = 3;
    
    public EpidemicCard(Graphic graphic) {
        super(graphic, "Epidemia", 
             "Malattia contagiosa", 
             null,  
             2);    
    }

    @Override
    public void execute(Board board) {
        List<Player> infectedPlayers = new ArrayList<>();
        List<Player> allPlayers = board.getPlayers();
        
      
        Player patientZero = allPlayers.get(new Random().nextInt(allPlayers.size()));
        infectedPlayers.add(patientZero);
        graphic.printAlert("Malattia rilevata su " + patientZero.getName());

    
        propagateInfection(allPlayers, infectedPlayers);


        handleOutbreak(infectedPlayers);
    }

    private void propagateInfection(List<Player> allPlayers, List<Player> infectedPlayers) {
        for (Player player : allPlayers) {
            if (!infectedPlayers.contains(player)) {
                for (Player infected : infectedPlayers) {
                    if (isContagious(infected) && new Random().nextInt(100) < INFECTION_RATE) {
                        infectedPlayers.add(player);
                        graphic.printMessage(player.getName() + " contagiato!");
                        break;
                    }
                }
            }
        }
    }

    private boolean isContagious(Player player) {
        return true;
    }

    private void handleOutbreak(List<Player> infectedPlayers) {
        for (Player player : infectedPlayers) {
            Ship ship = player.getShip();
            
            
            if (hasMedicalFacilities(ship) && 
                graphic.askUser(player.getName() + ": Usare risorse mediche (" + MEDICAL_COST + ")?")) {
                applyTreatment(ship);
                continue;
            }
            
            
            applySymptoms(ship, player);
        }
    }

    private boolean hasMedicalFacilities(Ship ship) {
        return true; //servirebbe un getter per il numero di umani
    }

    private void applyTreatment(Ship ship) {
        graphic.printMessage("Paziente guarito");
    }

    private void applySymptoms(Ship ship, Player player) {
        int casualties = new Random().nextInt(2) + 1;
        /*servirebbe un metodo che restituisce il numero degli umani e che lo setta tipo sethumancounter e il getter*/
        

        if (new Random().nextBoolean()) {
            damageRandomSystem(ship);
        }
        
        graphic.printAlert(player.getName() + " perde " + casualties + " membri equipaggio!");
    }

    private void damageRandomSystem(Ship ship) {
        List<Position> validTargets = new ArrayList<>();
        ShipTile[][] grid = ship.getShipComponets();
        
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x].getComponent() != null) {
                    validTargets.add(new Position(x, y));
                }
            }
        }
        
        if (!validTargets.isEmpty()) {
            Position target = validTargets.get(new Random().nextInt(validTargets.size()));
            grid[target.getY()][target.getX()].setComponent(null);
            graphic.printAlert("Sistema danneggiato a (" + target.getX() + "," + target.getY() + ")");
        }
    }
}
