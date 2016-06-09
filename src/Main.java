public class Main {

    public static void main(String[] args) {
        System.out.println("This program will solve matricies with Seidel method.");
        //matrix of values
        double[][] matrix = new double[][]{
                new double[]{22,7,-4,3},
                new double[]{1,27,2,1},
                new double[]{3,4,41,-3},
                new double[]{2,8,9,22},
        };
        //Free elements
        double[] vec = new double[]{3,9,1,4};
        for (int i = -1; i > -16; i--) {
            seidel(matrix, vec, Math.pow(10, i));
        }
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

    private static void seidel(double[][] matrix, double[] vec, double eps) {
        //Previous solution
        double[] xp=new double[matrix.length];
        //current solution
        double[] x = new double[matrix.length];
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
        print(x);
        System.out.println("Eps: "+eps+"   Iter: "+jit+"\n************************\n");
    }
}