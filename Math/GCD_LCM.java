// Greatest Common Divisor (GCD) and Least Common Multiple (LCM)

package whiteboard;


public class Lab_whiteboard {

    public static void main(String[] args) {

        
        // Test gcd().
        System.out.println(gcd(12, 8)); // 4
        System.out.println(gcd(45, 60)); // 15
        
        // Test lcm(). 
        System.out.println(lcm(12, 8)); // 24
        System.out.println(lcm(45, 60)); // 180

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    // Get Greatest Common Divisor of two integers. 
    // Author: TopCoder Book p.415 + kei
    // Date  : December 15, 2016

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }


    // Get Least Common Multiple of two integers. 
    // Be aware that integer overflow could happen. 
    // Author: TopCoder Book p.413 + kei
    // Date  : December 15, 2016
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    
    
    
    
    
    
    
    
    
    
    
    
}

























