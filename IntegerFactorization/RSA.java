import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Write a description of class RSA here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RSA
{
    public static void main(String[] args){
        BigInteger n = new BigInteger("712446816787");
        BigInteger y = new BigInteger("273095689186");
        BigInteger e = new BigInteger("6551");
        
        ArrayList<BigInteger> factors = naiveFactor(n);
        BigInteger p = factors.get(0);
        BigInteger q = factors.get(1);
        BigInteger thetaN = ((p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))));
        BigInteger d = e.modInverse(thetaN);
        
        BigInteger x = y.modPow(d, n); 
        
        System.out.println("The plaintext is: " + x);
    }
    
    public static ArrayList<BigInteger> naiveFactor(BigInteger n)
    {
        BigInteger x = n; 
        BigInteger two = new BigInteger("2");
        BigInteger f = new BigInteger("3");
        ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
        int counter = 0;

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
        return factors;
    }
}
