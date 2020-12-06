package com.gmail.dvdguedes.conversorunidade;

public class Conversor
{
    public enum Unit
    {
        CENTIMETER,
        METER,
        KILOMETER;

        public static Unit fromString(String text)
        {
            if(text!=null)
            {
                for(Unit unit: Unit.values())
                {
                    if(text.equalsIgnoreCase(unit.toString()))
                    {
                        return unit;
                    }
                }

            }

            throw new IllegalArgumentException("Cannot find a value for "+text);
        }

    }

    private final double multiplier;

    public Conversor(Unit from, Unit to) {

        multiplier = getRate(from,to);
    }

    public double getRate(Unit from,Unit to){
        double constant = 1;

        switch(from){
    case CENTIMETER:
        constant = (1/getRate(Unit.KILOMETER, Unit.CENTIMETER))*getRate(Unit.KILOMETER,to);
        break;
    case METER:
        constant = (1/getRate(Unit.KILOMETER, Unit.METER))*getRate(Unit.KILOMETER,to);
        break;
    case KILOMETER:
                if (to == Unit.CENTIMETER) {
                    constant = 100000;
                }else if (to == Unit.METER) {
                    constant = 1000;
                }
                break;
    }

    return constant;
}

    public double convert(double input) {
        return input * multiplier;
    }
}
