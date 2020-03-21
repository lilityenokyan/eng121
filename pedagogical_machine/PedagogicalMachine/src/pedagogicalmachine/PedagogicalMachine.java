
package pedagogicalmachine;

import java.util.Scanner; 
import java.lang.Math;
/**
 *
 * @author Lilit
 */
public class PedagogicalMachine {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        System.out.println("Enter M1 M2 M3 n1 n2 n3 x1 x2 x3H x3V:");
        
        int M1 = input.nextInt();
        int M2 = input.nextInt();
        int M3 = input.nextInt();
        double n1 = input.nextDouble();
        double n2 = input.nextDouble();
        double n3 = input.nextDouble();
        int x01 = input.nextInt();
        int x02 = input.nextInt();
        int x03h = input.nextInt();
        int x03v = input.nextInt();
        
        System.out.println("Enter number of time moments (including t=0):");

        int n=input.nextInt();

        int times[]=new int[n];
        int forces[]=new int[n];

        System.out.println("Enter time moment and the force,i.e. t0, f(t0), t1, f(t1)...");

        for (int i=0; i<n; i++) {
            times[i]=input.nextInt();
            forces[i]=input.nextInt();
        }
        
        int M = M1 + M2 + M3;
        int g = -10;
        int M23 = M2 * M3;
        double M22 = Math.pow(M2, 2);
        double M33 = Math.pow(M3, 2);
        
        // Start calculations
        double acc[][]=new double[n-1][3];

        double position1[]=new double[n];
        double position2[]=new double[n];
        double position3h[]=new double[n];
        double position3v[]=new double[n];
        position1[0] = x01;
        position2[0] = x02;
        position3h[0] = x03h;
        position3v[0] = x03v;
        
        double a1 = 0;
        double a2 = 0;
        double a3v = 0;
        double a1Nom = 0;
//        System.out.println(M33);
        double a1Denom = -2*M23 - M33*(1-n1*n3) + M23*(n1+n3) - M1*(M2+M3);
//        System.out.println(a1Denom);
        for (int i=1; i<n; i++) {
            
            //calculate accelarations
            a1Nom = M23*g*(1-n1*n2) + g*(n1*M33-n2*M22) - (M2+M3)*(forces[i]-n1*g*M);
//            System.out.println(a1Nom);
            a1 = a1Nom / a1Denom;
            a2 = a1*M3*(1-n3) - n2*M2*g - M3*g;
            a3v = a1 - a2;
            acc[i-1][0] = a1;
            acc[i-1][1] = a2;
            acc[i-1][2] = a3v;
            
            //calculate positions
            int dt = times[i] - times[i-1];
            position1[i] = calc_position(a1, position1[i-1], dt);
            position2[i] = calc_position(a2, position2[i-1], dt);
            position3h[i] = calc_position(a1, position3h[i-1], dt);
            position3v[i] = calc_position(a3v, position3v[i-1], dt);
            
            System.out.println("accs 1: " + a1 + " 2: " + a2 + " 3v: " + a3v);
        }
        
        System.out.println("Positions of the first body: ");
        for(double i: position1){
            System.out.print(i + " ");
        }
        
        System.out.print("\r\n");
        System.out.println("Positions of the second body: ");
        for(double i: position2){
            System.out.print(i + " ");
        }
        
        System.out.print("\r\n");
        System.out.println("Horiz. positions of the third body: ");
        for(double i: position3h){
            System.out.print(i + " ");
        }
        
        System.out.print("\r\n");
        System.out.println("Vertical positions of the third body: ");
        for(double i: position3v){
            System.out.print(i + " ");
        }
    }
    
    public static double calc_position (double a, double x0, int t) {
        double x = 0;
        x = x0 + a * Math.pow(t, 2) / 2;
        return Math.round(x*100) / 100;
    } 
}
