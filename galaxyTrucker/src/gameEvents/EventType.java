package gameEvents;

import java.util.Random;

import components.enums.Side;
import entities.Ship;
import gameEvents.Actions.ProjectileType;
import gameEvents.Actions.Shoot;

public enum EventType {
    ASTEROID_FIELD {
        @Override
        public void execute(Ship ship) {
            System.out.println("La nave subisce 2 danni da asteroidi!");
        }
    },
    PIRATE_RAID {
        @Override
        public void execute(Ship ship) {
            System.out.println("Attacco pirata: 3 proiettili da destra!");
           
        }
    },
    ABANDONED_STATION {
        @Override
        public void execute(Ship ship) {
            if(ship.getAliensCounter() > 0) {
                System.out.println("Vendi alieni: +4 crediti!");
            }
        }
    },
    SLAVER_SHIP {
        @Override
        public void execute(Ship ship) {
            System.out.println("Rapimento: perdi 1 equipaggio!");
           
        }
    },
    COMET_STRIKE {
        @Override
    public void execute(Ship ship) {
        System.out.println("Sciame di comete! 3 colpi casuali!");
        for (int i = 0; i < 3; i++) {
            Side randomSide = Side.values()[new Random().nextInt(Side.values().length)];
            int randomTile = new Random().nextInt(
                (randomSide == Side.UP || randomSide == Side.DOWN) ? 
                ship.getGameLevel().getBoardX() : ship.getGameLevel().getBoardY()
            );
            new Shoot(randomSide, ProjectileType.BIG_ASTEROID, randomTile).getHit(ship); 
        }
    }
    },
    MERCHANT_OUTPOST {
        @Override
        public void execute(Ship ship) {
            System.out.println("Mercanti: vendi merci per crediti!");
            int profit = ship.getWaresValue() * 2;
            
        }
    },
    SABOTAGE {
        @Override
        public void execute(Ship ship) {
            System.out.println("Sabotaggio: perdi 1 componente!");
           
        }
    },
    COSMIC_STORM {
      @Override
    public void execute(Ship ship) {
        int exposedConnectors = ship.getVoidConnectors();
        if (exposedConnectors > 0) {
            int componentsToLose = exposedConnectors; 
            System.out.println("Tempesta cosmica. Perse " + componentsToLose + " componenti.");
            for (int i = 0; i < componentsToLose; i++) {
                //servirebbe un metodo in ship in teoria che spacca un componente randomico non protetto
            }
        } else {
            System.out.println("Scudo attivo: nessun danno!");
        }
    }
    };

    public abstract void execute(Ship ship);
}