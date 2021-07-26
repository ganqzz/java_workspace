package algorithms;

/**
 * Monte Carlo
 */
public class FindingPI {

    public static void main(String[] args) {
        // approximate pi using the values listed below for the numPoints
        System.out.println(getPi(10_000));
        System.out.println(getPi(100_000));
        System.out.println(getPi(1_000_000));
        System.out.println(getPi(10_000_000));
        System.out.println(getPi(100_000_000));
    }

    public static double getPi(int numPoints) {
        int inCircle = 0;
        for (int i = 0; i < numPoints; i++) {
            // a square with a side of length 2 centered at 0 has
            // x and y range of -1 to 1
            double randX = (Math.random() * 2) - 1; // range -1 to 1
            double randY = (Math.random() * 2) - 1; // range -1 to 1
            // distance from (0,0)
            double dist = Math.sqrt(randX * randX + randY * randY);
            if (dist < 1) {
                // circle with diameter of 2 has radius of 1
                // add one every time the point is inside the circle.
                inCircle++;
            }
        }
        return 4.0 * inCircle / numPoints;
    }
}
