public class Main {

    public static void main(String[] args) {
        System.out.println("This program will solve matricies with Seidel method.");

        //matrix of values
        double[][] matrix = new double[][]{
                new double[]{900,3,2,1,5,-3,5,9,7,3},
                new double[]{-3,122,1,5,7,4,-2,3,12,-2},
                new double[]{-6,4,840,10,11,8,7,7,10,7},

                new double[]{-4,3,15,400,9,10,4,5,17,8},
                new double[]{-3,5,-8,3,-540,5,4,7,8,-9},
                new double[]{-2,4,16,7,8,101,2,8,7,5},

                new double[]{2,32,-4,-3,2,-8,505,16,7,9},
                new double[]{-3,-5,4,1,3,5,2,302,5,15},
                new double[]{-5,8,9,-8,3,4,5,6,301,3},

                new double[]{-3,2,1,14,9,-2,14,7,18,503}
        };
        //Free elements
        double[] vec= new double[] {5,-3,-12,15,7,7,19,3,1,52};
        seidel(matrix, vec);
    }

    private static boolean done(double[] current, double[] previous, double eps) {
        double n=Math.abs(previous[0]-current[0]);
        for (int i = 1; i < current.length; i++) {
            double new_n=Math.abs(previous[i] - current[i]);
            if (new_n>n)
                n = new_n;
        }
        return n<eps;
    }

    private static void print(double[] x) {

        for (int i = 0; i < x.length; i++) {
            System.out.println("X" + (i+1) + ": " + x[i]);
        }
    }

    private static void seidel(double[][] matrix, double[] vec) {
        //Previous solution
        double[] xp=new double[matrix.length];
        //current solution
        double[] x = new double[matrix.length];
        double eps = Math.pow(10, -9);
        //number of iterations
        int jit=0;

        for (int i = 0; i < matrix.length; i++)
            x[i] = 0;

        do {
            for(int i=0; i<matrix.length; i++) {
                System.arraycopy(x,0,xp,0,matrix.length);
                x[i] = vec[i];
                for (int j = 0; j < matrix.length; j++)
                    if (j != i)
                        x[i]-=matrix[i][j] * x[j];
                x[i]/= matrix[i][i];
            }
            jit++;
        } while(!done(x,xp,eps));
        System.out.println("JIT "+jit);
        print(x);
    }
}