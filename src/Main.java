public class Main {

    public static void main(String[] args) {
        System.out.println("This program will solve matricies with Seidel method.");

        double[][] matrix = new double[][]{
                new double[]{2, 3, -4, 1},
                new double[]{1, -2, -5, 1},
                new double[]{5, -3, 1, -4},
                new double[]{10, 2, -1, 2},
        };

        double[] vec= new double[] {3,2,1,4};

        seidel(matrix, vec);

    }

    private static boolean done(double[] current, double[] previous, double eps) {
        double n = 0.0;
        for (int i = 0; i < current.length; i++)
            n += Math.abs(current[i] - previous[i]);
        System.out.println(n+ " ::HERE");
        return n < eps;
    }

    private static void print(double[] x) {

        for (int i = 0; i < x.length; i++) {
            System.out.println("X" + (i+1) + ": " + x[i]);
        }
    }

    private static void seidel(double[][] matrix, double[] vec) {
        double[] prev=new double[matrix.length];
        double eps = Math.pow(10, -4);
        int jit=0;
        for (int i = 0; i < matrix.length; i++)
            prev[i] = 0.0;
        while(jit<=5) {
            double[] current = new double[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                System.out.println("Iter: " + i);
                current[i] = vec[i];
                for (int j = 0; j < matrix.length; j++)
                    if(i!=j)
                        current[i]+=matrix[i][j]*current[j];
                current[i] /= matrix[i][i];
                System.out.println(current[i]);
            }

            if(done(current,prev,eps))
                break;
            prev = current;
            jit++;
        }
        print(prev);
    }
}