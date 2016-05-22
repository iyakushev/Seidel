public class Main {

    public static void main(String[] args) {
        System.out.println("This program will solve matricies with Seidel method.");

        //matrix of values
        /*double[][] matrix = new double[][]{
                new double[]{-0.1, 0.2, -0.1},
                new double[]{0.1, -0.2, -0.2},
                new double[]{-0.2, 0.1, -0.1},
        };*/

        double[][] matrix=new double[][] {
                new double[]{2,1},
                new double[]{1,2}
        };
        //Free elements
//        double[] vec= new double[] {1.6,-2.3,1.5};
            double[] vec=new double[] {1,-1};
        seidel(matrix, vec);
    }

    private static boolean done(double[] current, double[] previous, double eps) {
        double n=0.0;
        for (int i = 0; i < current.length; i++)
            if(Math.abs(current[i]-previous[i])<eps)
                n=Math.abs(current[i]-previous[i]);
        return n>eps;
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
        double eps = Math.pow(10, -4);
        int jit=0;
        //Sum of elements
        double inty;

        for (int i = 0; i < matrix.length; i++)
            x[i] = 0;

        do {
            for(int i=0; i<matrix.length; i++) {
                xp=x;
                inty=0.0;
//                System.out.println("ITER: "+i);
                for(int j=0; j<matrix.length; j++) {
                    if(i>j) {
                        inty+=matrix[i][j]*xp[j];
                        System.out.println(inty+" inty:XP  "+xp[j]+"  matrix|  "+matrix[i][i]);
                    }
                    if(i<j)
                        inty+=matrix[i][j]*x[i];
                }
                x[i]=-(inty-vec[i])/matrix[i][i];
//                System.out.println("X["+i+"]  "+x[i]+"   vec: "+vec[i]+"   inty: "+inty+"  diag: "+matrix[i][i]);
            }
            jit++;
        } while(jit<5 && !done(x,xp,eps));
        System.out.println("JIT "+jit);
        print(x);
    }
}