package gameEvents;

import entities.Ship;

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
            System.out.println("Cometa: distrutta una colonna!");
            
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
            System.out.println("Tempesta cosmica: danno a tutti i sistemi!");

        }
    };

    public abstract void execute(Ship ship);
}