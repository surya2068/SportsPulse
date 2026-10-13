package sportspulse.modules;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.math.BigInteger;
import sportspulse.Input;

public class Module6RandomizedParallel {
    public void menu(Scanner sc) {
        while(true) {
            System.out.println("\n--- MODULE 6: RANDOMIZED AND PARALLEL ALGORITHMS ---");
            System.out.println("1. Randomized QuickSort\n2. Reservoir sampling\n3. Miller-Rabin primality test\n4. Prefix scan\n5. Reduce: sum/average/max\n6. Brent's theorem bound\n0. Back");
            int c=Input.readInt(sc,"Choose: ");if(c==0)return;
            if(c==1) {
                int n=Input.readInt(sc,"Number of scores: ");if(n<1||n>10000){System.out.println("Use 1-10000.");continue;}
                double[] a=new double[n];for(int i=0;i<n;i++)a[i]=Input.readDouble(sc,"Score "+(i+1)+": ");
                quickSort(a,0,n-1,new Random());System.out.println("Sorted scores: "+Arrays.toString(a));
            } else if(c==2) {
                int n=Input.readInt(sc,"Number of events: "),k=Input.readInt(sc,"Sample size: ");if(n<1||k<1||k>n){System.out.println("Require 1 <= k <= n.");continue;}
                String[] r=new String[k];Random rand=new Random();
                for(int i=0;i<n;i++){String ev=Input.readText(sc,"Event "+(i+1)+": ");if(i<k)r[i]=ev;else{int j=rand.nextInt(i+1);if(j<k)r[j]=ev;}}
                System.out.println("Reservoir sample: "+Arrays.toString(r));
            } else if(c==3) {
                long n=Input.readInt(sc,"Integer to test (up to 2^31-1): ");System.out.println(n+" is "+(millerRabin(n)?"probably prime":"composite")+".");
            } else if(c==4) {
                int n=Input.readInt(sc,"Number of values: ");if(n<1||n>10000){System.out.println("Invalid size.");continue;}
                long sum=0;System.out.print("Prefix scan: [");for(int i=0;i<n;i++){sum+=Input.readInt(sc,"Value "+(i+1)+": ");System.out.print(sum+(i<n-1?", ":""));}System.out.println("]");
            } else if(c==5) {
                int n=Input.readInt(sc,"Number of values: ");if(n<1||n>10000){System.out.println("Invalid size.");continue;}
                long sum=0,max=Long.MIN_VALUE;for(int i=0;i<n;i++){long v=Input.readInt(sc,"Value "+(i+1)+": ");sum+=v;max=Math.max(max,v);}
                System.out.println("Reduce -> sum="+sum+", average="+((double)sum/n)+", max="+max);
                System.out.println("This demonstrates the aggregation result; the loop itself is sequential.");
            } else if(c==6) {
                long work=Input.readInt(sc,"Work T1: "),span=Input.readInt(sc,"Span T-infinity: "),p=Input.readInt(sc,"Processor count P: ");
                if(work<0||span<1||p<1){System.out.println("Invalid values.");continue;}
                System.out.println("Brent bound: T_P <= T1/P + T-infinity = "+(Math.ceil((double)work/p)+span));
            } else System.out.println("Invalid choice.");
        }
    }
    private void quickSort(double[] a,int lo,int hi,Random r) {
        if(lo>=hi)return;int pi=lo+r.nextInt(hi-lo+1);double tmp=a[pi];a[pi]=a[hi];a[hi]=tmp;
        double pivot=a[hi];int i=lo-1;for(int j=lo;j<hi;j++)if(a[j]<=pivot){i++;tmp=a[i];a[i]=a[j];a[j]=tmp;}
        tmp=a[i+1];a[i+1]=a[hi];a[hi]=tmp;int p=i+1;quickSort(a,lo,p-1,r);quickSort(a,p+1,hi,r);
    }
    private boolean millerRabin(long n) {
        if(n<2)return false;for(int p:new int[]{2,3,5,7,11,13,17,19,23,29,31,37})if(n%p==0)return n==p;
        long d=n-1;int s=0;while((d&1)==0){d>>=1;s++;}
        for(long a:new long[]{2,3,5,7,11}){if(a>=n)continue;long x=pow(a,d,n);if(x==1||x==n-1)continue;boolean witness=true;
            for(int i=1;i<s;i++){x=mul(x,x,n);if(x==n-1){witness=false;break;}}if(witness)return false;
        }return true;
    }
    private long pow(long a,long d,long mod){long r=1;while(d>0){if((d&1)==1)r=mul(r,a,mod);a=mul(a,a,mod);d>>=1;}return r;}
    private long mul(long a,long b,long mod){return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();}
}
