import java.math.BigInteger;
import java.util.Random;
import java.util.ArrayList;

/**
 * Write a description of class FactorPrime here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FactorPrime
{
    public static void main(String[] args)
    {
        BigInteger p;
        BigInteger q;
        BigInteger n;
        int bitLength;
        Random rnd = new Random(); 
        for(int i = 10; i <= 32; i++)
        {
            bitLength = i;
            p = BigInteger.probablePrime(bitLength,rnd);
            q = BigInteger.probablePrime(bitLength,rnd);
            n = p.multiply(q);
            naiveFactor(n, bitLength);

        }

    }

    public static void naiveFactor(BigInteger n, int bitLength)
    {
        BigInteger x = n; 
        BigInteger two = new BigInteger("2");
        BigInteger f = new BigInteger("3");
        ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
        int counter = 0;
        long start = System.currentTimeMillis();

        if(x.mod(two) == BigInteger.ZERO){
            factors.add(two);
            x = x.divide(two);
            while(x.mod(two) == BigInteger.ZERO)
            {
                factors.add(two);
                x = x.divide(two); 
            }
        }

        if(f.compareTo(x) != 1){
            while((f.compareTo(x.sqrt()) != 1)){
                while(x.mod(f) == BigInteger.ZERO){
                    factors.add(f);
                    x = x.divide(f);
                }
                f = f.add(two); 
            }
            factors.add(x);
        }

        BigInteger p = factors.get(0);
        BigInteger q = factors.get(1);
        double end = (double)(System.currentTimeMillis() - start) / 1000;
        System.out.println(bitLength + "bit primes. " + "Duration = " 
            + end + "s => " + "n = " + n + ", " + "p = " + p  
            + ", q = " + q);  
    }
}