package sportspulse.modules;

import java.util.Scanner;
import java.util.Arrays;
import sportspulse.Input;
import sportspulse.data.SportsData;
import sportspulse.model.Player;

public class Module3DynamicProgramming {
    public void menu(Scanner sc, SportsData data) {
        while(true) {
            System.out.println("\n--- MODULE 3: DYNAMIC PROGRAMMING ---");
            System.out.println("1. Levenshtein edit distance\n2. Damerau edit distance\n3. Bitmask DP team selection\n4. Matrix-chain multiplication\n5. Subset sum\n0. Back");
            int c=Input.readInt(sc,"Choose: ");if(c==0)return;
            if(c==1||c==2){String a=Input.readText(sc,"First name/string: "),b=Input.readText(sc,"Second name/string: ");System.out.println("Distance = "+editDistance(a.toLowerCase(),b.toLowerCase(),c==2));}
            else if(c==3) {
                Player[] p=data.getPlayers();int n=Input.readInt(sc,"Consider first how many players (1-"+p.length+"): ");
                if(n<1||n>p.length){System.out.println("Invalid n.");continue;}int k=Input.readInt(sc,"Squad size: ");
                if(k<1||k>n){System.out.println("Invalid squad size.");continue;}
                int states=1<<n;double[] dp=new double[states];Arrays.fill(dp,-1e9);dp[0]=0;
                for(int mask=0;mask<states;mask++)if(Integer.bitCount(mask)<k)for(int i=0;i<n;i++)if((mask&(1<<i))==0)dp[mask|(1<<i)]=Math.max(dp[mask|(1<<i)],dp[mask]+p[i].getPerformance());
                double best=-1;int bm=0;for(int m=0;m<states;m++)if(Integer.bitCount(m)==k&&dp[m]>best){best=dp[m];bm=m;}
                System.out.println("Best combined performance score: "+best);for(int i=0;i<n;i++)if((bm&(1<<i))!=0)System.out.println(p[i]);
                System.out.println("Bitmask DP uses O(n*2^n) time in this simplified selection demo.");
            } else if(c==4) {
                int n=Input.readInt(sc,"Number of matrices (2-10): ");if(n<2||n>10){System.out.println("Enter 2-10.");continue;}
                int[] d=new int[n+1];for(int i=0;i<=n;i++)d[i]=Input.readInt(sc,"Dimension d["+i+"]: ");
                long[][] dp=new long[n][n];
                for(int len=2;len<=n;len++)for(int i=0;i<=n-len;i++){int j=i+len-1;dp[i][j]=Long.MAX_VALUE/4;for(int k=i;k<j;k++)dp[i][j]=Math.min(dp[i][j],dp[i][k]+dp[k+1][j]+(long)d[i]*d[k+1]*d[j+1]);}
                System.out.println("Minimum scalar multiplications = "+dp[0][n-1]);
            } else if(c==5) {
                int n=Input.readInt(sc,"Number of weights (max 20): ");if(n<1||n>20){System.out.println("Use 1-20.");continue;}
                int[] a=new int[n];for(int i=0;i<n;i++)a[i]=Input.readInt(sc,"Weight "+(i+1)+": ");int target=Input.readInt(sc,"Target sum: ");
                if(target<0||target>100000){System.out.println("Target must be 0-100000.");continue;}
                boolean[] dp=new boolean[target+1];dp[0]=true;
                for(int x:a)if(x>=0)for(int j=target;j>=x;j--)dp[j]|=dp[j-x];
                System.out.println("Subset with target sum exists: "+dp[target]);
            } else System.out.println("Invalid choice.");
        }
    }
    private int editDistance(String a,String b,boolean damerau) {
        int[][] d=new int[a.length()+1][b.length()+1];for(int i=0;i<=a.length();i++)d[i][0]=i;for(int j=0;j<=b.length();j++)d[0][j]=j;
        for(int i=1;i<=a.length();i++)for(int j=1;j<=b.length();j++){int cost=a.charAt(i-1)==b.charAt(j-1)?0:1;d[i][j]=Math.min(Math.min(d[i-1][j]+1,d[i][j-1]+1),d[i-1][j-1]+cost);if(damerau&&i>1&&j>1&&a.charAt(i-1)==b.charAt(j-2)&&a.charAt(i-2)==b.charAt(j-1))d[i][j]=Math.min(d[i][j],d[i-2][j-2]+1);}
        return d[a.length()][b.length()];
    }
}
