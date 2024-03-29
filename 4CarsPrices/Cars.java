interface Lada {
    long getLadaPrice();
}

interface Ferrari {
    long getFerrariPrice();
}

interface Porshe {
    long getPorshePrice();
}

interface InteAbsFactory {
    Lada getLada();
    Ferrari getFerrari();
    Porshe getPorshe();
}


class ByLadaImpl implements Lada {
    public long getLadaPrice() { return 1000; }
}

class ByFerrariImpl implements Ferrari {
    public long getFerrariPrice() { return 3000; }
}

class ByPorsheImpl implements Porshe {
    public long getPorshePrice() { return 2000; }
}


class RuLadaImpl implements Lada {
    public long getLadaPrice() { return 10000; }
}

class RuFerrariImpl implements Ferrari {
    public long getFerrariPrice() { return 30000; }
}

class RuPorsheImpl implements Porshe {
    public long getPorshePrice() { return 20000; }
}


class ByCarPriceAbsFactory implements InteAbsFactory {
    public Lada getLada() { return new ByLadaImpl(); }
    public Ferrari getFerrari() { return new ByFerrariImpl(); }
    public Porshe getPorshe() { return new ByPorsheImpl(); }
}

class RuCarPriceAbsFactory implements InteAbsFactory {
    public Lada getLada() { return new RuLadaImpl(); }
    public Ferrari getFerrari() { return new RuFerrariImpl(); }
    public Porshe getPorshe() { return new RuPorsheImpl(); }
}
