/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntersectionTwoCars;

import intersection.Car;
import intersection.Inter;
import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author Lilit
 */
public class IntercectionTwoCars {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String stop1 = "";
        String stop2 = "";
        String pass1 = "";
        String pass2 = "";
        
        System.out.println("Enter the FIRST car's initial velocity and distance: ");
        
        int init_v1 = input.nextInt() * 1000 / 3600;
        int init_d1 = input.nextInt();
        
         System.out.println("Enter the Second car's initial velocity and distance from the first one: ");
        
        int init_v2 = input.nextInt() * 1000 / 3600;
        int init_d2 = input.nextInt() + init_d1;
                
        System.out.println("Enter intersection duration and width: ");
        
        int duration = input.nextInt();
        int width = input.nextInt();
        
        Car car1 = new Car(init_v1, init_d1);
        Car car2 = new Car(init_v2, init_d2);
        Inter inter = new Inter(duration, width);

        double pos_acc1 = pos_acc(car1, inter);
        double neg_acc1 = neg_acc(car1, inter);
        double pos_acc2 = pos_acc(car2, inter);
        double neg_acc2 = neg_acc(car2, inter);
        
        //Case when car 1 PASSES
        if (Car.isAccAcceptable(pos_acc1)) {
            pass1 = "Pass";
            
            //Check the case when the first car passes but second might or might pass
            pass2 = Car.isAccAcceptable(pos_acc2) ? "Pass" : "No pass";
            
            //Check the case when the first car passes but second might or might not have a chance to stop
            //SO it is possible for the first car to pass but second pass and stop.
            pass2 = Car.isAccAcceptable(neg_acc2) ? pass2 + "Stop" : pass2 + "No Stop"; 
            
        } else {
            pass1 = "No pass";
            pass2 = "No pass";
        }
        
        //Case when car 1 STOPS
        if (Car.isAccAcceptable(neg_acc1)) {
            stop1 = "Stop";
            stop2 = Car.isAccAcceptable(neg_acc2) ? "Stop" : "No Stop"; 
        } else {
            stop1 = "No Stop";
            stop2 = "No Stop";
        }
        
        System.out.println("Car 1: " + pass1 + " and " + stop1);
        System.out.println("Car 2: " + pass2 + " and " + stop2);
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
