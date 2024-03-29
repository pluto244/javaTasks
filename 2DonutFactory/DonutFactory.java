import java.util.Random;

interface Donut {
    void eat();
}

enum DonutTypes {
    CHERRY, CHOCOLATE, ALMOND
}

class CherryDonut implements Donut {
    public void eat() {
        System.out.println("Eating a cherry donut.");
    }
}

class ChocolateDonut implements Donut {
    public void eat() {
        System.out.println("Eating a chocolate donut.");
    }
}

class AlmondDonut implements Donut {
    public void eat() {
        System.out.println("Eating an almond donut.");
    }
}

class DonutFactory {
    private int cherryCount = 0;
    private int chocolateCount = 0;
    private int almondCount = 0;

    public Donut getDonut(DonutTypes type) {
        switch (type) {
            case CHERRY:
                cherryCount++;
                return new CherryDonut();
            case CHOCOLATE:
                chocolateCount++;
                return new ChocolateDonut();
            case ALMOND:
                almondCount++;
                return new AlmondDonut();
            default:
                throw new IllegalArgumentException("Unknown Donut Type");
        }
    }

    public void eatRandomDonut() {
        Random random = new Random();
        DonutTypes[] values = DonutTypes.values();
        Donut randomDonut = getDonut(values[random.nextInt(values.length)]);
        randomDonut.eat();
    }

    public int getCherryCount() {
        return cherryCount;
    }

    public int getChocolateCount() {
        return chocolateCount;
    }

    public int getAlmondCount() {
        return almondCount;
    }

    public void resetCounts() {
        cherryCount = 0;
        chocolateCount = 0;
        almondCount = 0;
    }

    public void eat100RandomDonuts() {
        for (int i = 0; i < 100; i++) {
            eatRandomDonut();
        }
    }

}
