
package intersection;

import java.util.Scanner; 
import java.lang.Math;

public class Intersection {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter initial velocity and distance: ");
        
        int init_v = input.nextInt() * 1000 / 3600;
        int init_d = input.nextInt();
                        
        System.out.println("Please enter intersection duration and width: ");
        
        int duration = input.nextInt();
        int width = input.nextInt();
        
        Car car = new Car(init_v, init_d);
        Inter inter = new Inter(duration, width);

        double pos_acc = pos_acc(car, inter);
        double neg_acc = neg_acc(car, inter);
        
        String pass = Car.isAccAcceptable(pos_acc) ? "Pass" : "No pass";
        String stop = Car.isAccAcceptable(neg_acc) ? "Stop" : "No Stop"; 

        System.out.println(pass + " and " + stop);
    }
    
    public static double pos_acc (Car car, Inter inter) {      
        double pos_acc = 2 *(car.getInit_distance() + inter.getWidth() - car.getInit_velocity()*inter.getDuration()) / (Math.pow(inter.getDuration(), 2));
        return pos_acc;
    }
    
    public static double neg_acc (Car car, Inter inter) {
        double neg_acc = (car.getInit_distance() - car.getInit_velocity()
                *inter.getDuration()) / (0.5 * Math.pow(inter.getDuration(), 2));
        return neg_acc;
    } 
}
