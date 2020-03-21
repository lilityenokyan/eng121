/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntersectionVelocity;

import intersection.Car;
import intersection.Inter;
import java.util.Scanner; 
import java.lang.Math;

/**
 *
 * @author Lilit
 */
public class IntersectionVelocity {
    

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
        
        String pass = (Car.isAccAcceptable(pos_acc) && Car.isVelAcceptable(velocity(car, inter,pos_acc))) ? "Pass" : "No pass";
        String stop = (Car.isAccAcceptable(neg_acc) && Car.isVelAcceptable(velocity(car, inter,neg_acc))) ? "Stop" : "No Stop";
        
        System.out.println("Pos vel: " + velocity(car, inter,pos_acc) + " Neg vel: " + velocity(car, inter,neg_acc));
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
    
    public static double velocity (Car car, Inter inter, double acc) {      
        double vel = car.getInit_velocity() + acc * inter.getDuration();
        return vel;
    }
}
