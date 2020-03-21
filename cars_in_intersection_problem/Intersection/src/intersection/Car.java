
package intersection;

public class Car {
    
    int init_velocity;
    int init_distance;

    public Car(int v, int d)
    {
        init_velocity = v;
        init_distance = d;

    }

    public void setInit_velocity(int init_velocity)
    {
        this.init_velocity = init_velocity;
    }

    public void setInit_distance(int init_distance)
    {
        this.init_distance = init_distance;
    }

    public int getInit_velocity()
    {
        return init_velocity;
    }

    public int getInit_distance()
    {
        return init_distance;
    }

    public static Boolean isAccAcceptable (double acc)
    {
        return (1.0 < Math. abs(acc) && Math. abs(acc) < 3.0);
    }
    
    public static Boolean isVelAcceptable (double vel)
    {
        return (50/3.6 < Math. abs(vel) && Math. abs(vel) < 100/3.6);
    }
}
