// 
// Decompiled by Procyon v0.5.36
// 

public class RationalCalculator
{
    private int writeMax;
    private int length;
    private double num;
    public static char sqrtSymbol;
    private String primeFactors;
    private String originalRational;
    private String finalRational;
    private String realNumber;
    private int counter;
    double[] largeArray;
    
    public RationalCalculator(final int a) {
        this.writeMax = 80;
        this.counter = -1;
        this.largeArray = new double[100];
        this.num = a;
        this.originalRational = "" + (int)this.num;
        this.run(this.num);
        this.primeFactors = this.primeFactorization();
    }
    
    public String[] displayData() {
        final String[] displayer = new String[3];
        if (trueInt(Math.sqrt(this.num))) {
            displayer[0] = "" + (int)Math.sqrt(this.num);
        }
        else {
            displayer[0] = "" + Math.sqrt(this.num);
        }
        displayer[1] = "" + this.primeFactors;
        displayer[2] = "" + this.finalRational;
        return displayer;
    }
    
    public void run(final double number) {
        final double ab = this.getFirstFac(number);
        if (isPrime(ab) && ab != 0.0) {
            ++this.counter;
            this.largeArray[this.counter] = ab;
        }
        else {
            this.run(ab);
        }
        final double bc = this.getSecondFac(number);
        if (isPrime(bc) && bc != 0.0) {
            ++this.counter;
            this.largeArray[this.counter] = bc;
        }
        else {
            this.run(bc);
        }
    }
    
    private String primeFactorization() {
        final int[] ofFactors = new int[this.counter + 1];
        String factors = "";
        for (int x = 0; x <= this.counter; ++x) {
            ofFactors[x] = (int)this.largeArray[x];
            if (x == this.counter) {
                factors += ofFactors[x];
            }
            else if (x == this.counter - 1) {
                factors = factors + ofFactors[x] + ", and ";
            }
            else {
                factors = factors + ofFactors[x] + ", ";
            }
        }
        final int[][] fullArray = this.findMatches(ofFactors);
        final int numCounter = fullArray[0][0];
        final int sqrtCounter = fullArray[1][0];
        int number = fullArray[0][1];
        int square = fullArray[1][1];
        for (int x2 = 2; x2 <= numCounter + 1; ++x2) {
            number *= fullArray[0][x2];
        }
        for (int x2 = 2; x2 <= sqrtCounter + 1; ++x2) {
            square *= fullArray[1][x2];
        }
        if (number == 0) {
            this.finalRational = "" + RationalCalculator.sqrtSymbol + square;
        }
        else if (square == 0) {
            this.finalRational = "" + number;
        }
        else {
            this.finalRational = "" + number + RationalCalculator.sqrtSymbol + square;
        }
        return factors;
    }
    
    private int[][] findMatches(final int[] array) {
        final int[] arrayOfNums = new int[100];
        final int[] arrayOfSqrts = new int[100];
        int counterOfNums = -1;
        int counterOfSqrts = -1;
        final int[] arrayOfTrash = new int[this.counter + 1];
        int y = 0;
        for (int x = 0; x <= this.counter; ++x) {
            --y;
            arrayOfTrash[x] = y;
        }
        y = 0;
        int z = 0;
        for (int x2 = 0; x2 < (this.counter + 1) * (this.counter + 1); ++x2) {
            if (z != y && array[z] == array[y]) {
                ++counterOfNums;
                arrayOfNums[counterOfNums] = array[z];
                array[z] = arrayOfTrash[z];
                array[y] = arrayOfTrash[y];
            }
            if (z == this.counter && z != 0) {
                z = -1;
                ++y;
            }
            ++z;
        }
        for (int x2 = 0; x2 <= this.counter; ++x2) {
            if (array[x2] > 0) {
                ++counterOfSqrts;
                arrayOfSqrts[counterOfSqrts] = array[x2];
            }
        }
        final int[][] finalFactors = new int[2][100];
        finalFactors[0][0] = counterOfNums;
        finalFactors[1][0] = counterOfSqrts;
        for (int x3 = 1; x3 <= counterOfNums + 1; ++x3) {
            finalFactors[0][x3] = arrayOfNums[x3 - 1];
        }
        for (int x3 = 1; x3 <= counterOfSqrts + 1; ++x3) {
            finalFactors[1][x3] = arrayOfSqrts[x3 - 1];
        }
        return finalFactors;
    }
    
    private double getFirstFac(final double ab) {
        double[] array1 = new double[2];
        array1 = this.findFactors(ab);
        return array1[0];
    }
    
    private double getSecondFac(final double ab) {
        double[] array1 = new double[2];
        array1 = this.findFactors(ab);
        return array1[1];
    }
    
    static boolean isPrime(final double x) {
        double test = x - 1.0;
        if (x == 2.0) {
            return true;
        }
        boolean end;
        boolean prime;
        do {
            if (trueInt(x / test)) {
                end = true;
                prime = false;
            }
            else {
                end = false;
                prime = false;
                --test;
                if (test > 1.0) {
                    continue;
                }
                prime = true;
                end = true;
            }
        } while (!end);
        return prime;
    }
    
    private double[] findFactors(final double n) {
        double test = n - 1.0;
        boolean end = false;
        final double[] factors = new double[2];
        do {
            if (trueInt(n / test)) {
                factors[1] = n / (factors[0] = test);
                end = true;
            }
            else {
                end = false;
                --test;
            }
        } while (!end);
        return factors;
    }
    
    static boolean trueInt(final double nt) {
        final int nint = (int)nt;
        return nint == nt;
    }
    
    public static char getSymbol() {
        return RationalCalculator.sqrtSymbol;
    }
    
    public int getNum() {
        return (int)this.num;
    }
    
    static {
        RationalCalculator.sqrtSymbol = '\u221a';
    }
}