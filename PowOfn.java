/**
 * Created by achoudhary on 11/02/2016.
 */
public class PowOfn {


    public double power(double x , int n){
        if(n == 0)
            return 1;
        //just multiple only half of the value and
        //simply multiply it twice to get
        double value = power(x,n/2);


        if(n % 2 == 0){
            //means it the pair of 2 values
            return value * value;
        }else{
            //its odd pair so multiply again
            return  value * value * x;
        }
    }

    public double myPow(double x, int n) {
        if(n < 0){
            return 1/power(x , -n);
        }else{
            return power(x,n);
        }

    }

    public static void main(String[] args) {
        double v = new PowOfn().myPow(6, 11);
        System.out.println(v);
    }
}
