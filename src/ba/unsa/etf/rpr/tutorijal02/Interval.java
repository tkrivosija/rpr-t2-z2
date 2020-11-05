package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double prva, druga;
    private boolean prvaUnutra, drugaUnutra;

    public Interval(double prva, double druga, boolean prvaUnutra, boolean drugaUnutra) {
        if (prva > druga) throw new IllegalArgumentException("ne moze");
        this.prva = prva;
        this.druga = druga;
        this.prvaUnutra = prvaUnutra;
        this.drugaUnutra = drugaUnutra;
    }

    public Interval() {
        prva = 0;
        druga = 0;
        prvaUnutra = false;
        drugaUnutra = false;
    }

    public boolean isNull() {
        if (prva == 0 && druga == 0 && !prvaUnutra && !drugaUnutra) return true;
        return false;
    }

    public boolean isIn(double t) {
        if (prvaUnutra) {
            if (drugaUnutra) {
                if (t >= prva && t <= druga) return true;
            }
            if (t >= prva && t < druga) return true;
        } else {
            if (drugaUnutra) {
                if (t > prva && t <= druga) return true;
            }
            if (t > prva && t < druga) return true;
        }
        return false;
    }

    public Interval intersect(Interval i) {
        if (druga < i.prva || i.druga < prva) return null;
        if (isIn(i.prva)) {
            if (i.druga >= druga) {
                if (isIn(druga)) {
                    if (i.isIn(i.prva)) return new Interval(i.prva, druga, true, true);
                    else return new Interval(i.prva, druga, false, true);
                } else {
                    if (i.isIn(i.prva)) return new Interval(i.prva, druga, true, false);
                    else return new Interval(i.prva, druga, false, false);
                }
            } else {
                if (isIn(druga)) {
                    if (i.isIn(i.prva)) return new Interval(i.prva, i.druga, true, true);
                    else return new Interval(i.prva, druga, false, true);
                } else {
                    if (i.isIn(i.prva)) return new Interval(i.prva, i.druga, true, false);
                    else return new Interval(i.prva, druga, false, false);
                }
            }
        }
        if (i.isIn(prva)) {
            if (druga >= i.druga) {
                if (i.isIn(i.druga)) {
                    if (isIn(prva)) return new Interval(prva, i.druga, true, true);
                    else return new Interval(prva, i.druga, false, true);
                } else {
                    if (isIn(prva)) return new Interval(prva, i.druga, true, false);
                    else return new Interval(prva, i.druga, false, false);
                }
            } else {
                if (i.isIn(druga)) {
                    if (isIn(prva)) return new Interval(prva, druga, true, true);
                    else return new Interval(prva, i.druga, false, true);
                } else {
                    if (isIn(prva)) return new Interval(prva, druga, true, false);
                    else return new Interval(prva, i.druga, false, false);
                }
            }
        }
        return null;
    }

    public static Interval intersect(Interval i1, Interval i2) {
        if (i1.druga < i2.prva || i2.druga < i1.prva) return null;
        if (i1.isIn(i2.prva)) {
            if (i2.druga >= i1.druga) {
                if (i1.isIn(i1.druga)) {
                    if (i2.isIn(i2.prva)) return new Interval(i2.prva, i1.druga, true, true);
                    else return new Interval(i2.prva, i1.druga, false, true);
                } else {
                    if (i2.isIn(i2.prva)) return new Interval(i2.prva, i1.druga, true, false);
                    else return new Interval(i2.prva, i1.druga, false, false);
                }
            } else {
                if (i2.isIn(i2.druga)) {
                    if (i2.isIn(i2.prva)) return new Interval(i2.prva, i2.druga, true, true);
                    else return new Interval(i2.prva, i1.druga, false, true);
                } else {
                    if (i2.isIn(i2.prva)) return new Interval(i2.prva, i2.druga, true, false);
                    else return new Interval(i2.prva, i1.druga, false, false);
                }
            }
        }
        if (i2.isIn(i1.prva)) {
            if (i1.druga >= i2.druga) {
                if (i2.isIn(i2.druga)) {
                    if (i1.isIn(i1.prva)) return new Interval(i1.prva, i2.druga, true, true);
                    else return new Interval(i1.prva, i2.druga, false, true);
                } else {
                    if (i1.isIn(i1.prva)) return new Interval(i1.prva, i2.druga, true, false);
                    else return new Interval(i1.prva, i2.druga, false, false);
                }
            } else {
                if (i2.isIn(i1.druga)) {
                    if (i1.isIn(i1.prva)) return new Interval(i1.prva, i1.druga, true, true);
                    else return new Interval(i1.prva, i2.druga, false, true);
                } else {
                    if (i1.isIn(i1.prva)) return new Interval(i1.prva, i1.druga, true, false);
                    else return new Interval(i1.prva, i2.druga, false, false);
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.prva, prva) == 0 &&
                Double.compare(interval.druga, druga) == 0 &&
                prvaUnutra == interval.prvaUnutra &&
                drugaUnutra == interval.drugaUnutra;
    }

    @Override
    public String toString() {
        if (prva == druga) return "()";
        else if (isIn(prva)) {
            if (isIn(druga)) return "[" + prva + "," + druga + "]";
            else return "[" + prva + "," + druga + ")";
        } else {
            if (isIn(druga)) return "(" + prva + "," + druga + "]";
            return "(" + prva + "," + druga + ")";
        }
    }
}
