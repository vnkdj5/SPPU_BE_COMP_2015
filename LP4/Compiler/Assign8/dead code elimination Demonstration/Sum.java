/* Demonstration of dead-code elimination in the JVM.
 * 
 * This class demonstrates some of the difficulties of benchmarking
 * code which may be subject to dead-code elimination by the JVM.
 * There are two loops, both calling the same function. But only the
 * second loop actually uses the result of the function.
 *
 * When I run this code, I get results like this:
 *
 * Dead code:
 * Computed 10000.0 in 108 msecs
 * Computed 10000.0 in 22 msecs
 * Computed 10000.0 in 0 msecs      // dead-code elimination
 * Computed 10000.0 in 0 msecs
 * Computed 10000.0 in 0 msecs
 * 
 * Live code:
 * Computed 4.9995E11 in 92 msecs
 * Computed 4.9995E11 in 92 msecs
 * Computed 4.9995E11 in 96 msecs
 * Computed 4.9995E11 in 93 msecs
 * Computed 4.9995E11 in 92 msecs
 *
 * However, if I swap the order of the two loops, they both run at the
 * same speed! Maybe because by that point the JVM has already
 * optimized the 'sum' function so it doesn't bother to eliminate it.
 */

package example;

public class Sum {
    public static double sum(double[] array) {
        double total = 0.0;
        for (int i = 0; i < array.length; i++) {
            total += array[i];
        }
        return total;
    }

    public static void main(String[] args) {
        double[] array = new double[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (double)i;
        }

        System.out.println("Dead code:");
        for (int i = 0; i < 5; i++) {
            double grand_total = 0.0;
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10000; j++) {
                sum(array);
                grand_total += 1.0;
            }
            long stop = System.currentTimeMillis();
            System.out.println("Computed " + grand_total +
                               " in " + (stop - start) + " msecs");
        }

        System.out.println("\nLive code:");
        for (int i = 0; i < 5; i++) {
            double grand_total = 0.0;
            long start = System.currentTimeMillis();
            for (int j = 0; j < 10000; j++) {
                grand_total += sum(array);
            }
            long stop = System.currentTimeMillis();
            System.out.println("Computed " + grand_total +
                               " in " + (stop - start) + " msecs");
        }
    }
}
