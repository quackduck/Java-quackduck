import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
public class PiCalc {
	static boolean showProgress = false;
	static long numOfCycles = 900000000;
	static String actualPi = "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651 3282306647 0938446095 5058223172 5359408128 4811174502 8410270193 8521105559 6446229489 5493038196";
	static MathContext mc = new MathContext(300, RoundingMode.HALF_UP);
	
	public static void main(String[] args) {
		String result = calculatepi(numOfCycles);
		System.out.println(result);
		System.out.println(Math.PI);
		//System.out.println("Accuracy:" + (Math.abs(Math.PI-result)));
		//String result2 = bdpi((int)numOfCycles);
		//System.out.println(result2);
		//System.out.println(actualPi);
	}
	
	public static String calculatepi(long n) {
		BigDecimal result = new BigDecimal(0);
//		double result = 0.0;
		for (long i = 1; i <= n; i++) {
			result = result.add(BigDecimal.ONE.divide(BigDecimal.valueOf(i*i), mc));
			if (showProgress && n>=100000000 && i % (Math.round((double)n/100)) == 0) {
				System.out.println(i/(Math.round((double)(n/100))));
			}
		}	
		result = result.multiply(BigDecimal.valueOf(6));
		result = result.sqrt(mc);

		return result.toPlainString();
	}
	
	public static double calculateRecursive( long k ) {
        if ( k == 0 ) { return doCalc( k ); }

        return doCalc( k ) + calculateRecursive( k - 1 );
    }

    public static double doCalc( long k ) {
        double numerator = Math.pow( -1, k ) * factorial( 6 * k ) * ( 545140134 * k + 13591409 );
        double denominator = factorial( 3 * k ) * Math.pow( factorial( k ), 3 ) * Math.pow( 640320, 3 * k + 3.0 / 2.0 );
        return 12.0 * numerator / denominator;
    }

    public static double factorial( long n ) {
        if ( n == 0 ) {
            return 1;
        } else {
            return n * factorial( n - 1 );
        }
    }
    
    public static String bdpi (int k) {
    	BigDecimal bdk = bdcalculateRecursive(k);
    	bdk = BigDecimal.ONE.divide(bdk, mc);
    	return bdk.toPlainString();
    }
    
    public static BigDecimal bdcalculateRecursive( int k ) {
        if ( k == 0 ) { return bddoCalc( k ); }

        return bddoCalc(k).add(bdcalculateRecursive( k - 1 ));
    }

    public static BigDecimal bddoCalc( int k ) {
        BigDecimal numerator = BigDecimal.valueOf(-1).pow( k ).multiply(bdfactorial( 6 * k )).multiply(BigDecimal.valueOf((545140134 * k + 13591409)));
        BigDecimal denominator = bdfactorial( 3 * k ).multiply(bdfactorial(k).pow(3)).multiply(BigDecimal.valueOf(Math.pow(640320, 3 * k + 3.0 / 2.0)));
        return numerator.multiply(BigDecimal.valueOf(12)).divide(denominator, mc);
    }

    public static BigDecimal bdfactorial( int n ) {
        BigDecimal bdn = new BigDecimal(n);
    	if (bdn.equals(BigDecimal.ZERO)) {
            return BigDecimal.ONE;
        } else {
            return bdn.multiply(bdfactorial(n - 1));
        }
    }
    
    

}
