package sportspulse.modules;

import java.util.Scanner;
import java.util.Arrays;
import sportspulse.Input;

public class Module4NetworkFlow {
    public void menu(Scanner sc) {
        while(true) {
            System.out.println("\n--- MODULE 4: NETWORK FLOW ---");
            System.out.println("1. Edmonds-Karp maximum flow\n2. Bipartite player-position matching\n3. Vertex-cover 2-approximation concept\n4. Max-flow/min-cut concept\n0. Back");
            int c=Input.readInt(sc,"Choose: ");if(c==0)return;
            if(c==1) {
                int n=Input.readInt(sc,"Number of nodes (2-20): ");if(n<2||n>20){System.out.println("Invalid size.");continue;}
                int[][] cap=new int[n][n];int m=Input.readInt(sc,"Number of directed edges: ");
                for(int i=0;i<m;i++){int u=Input.readInt(sc,"From node: "),v=Input.readInt(sc,"To node: "),w=Input.readInt(sc,"Capacity: ");if(u>=0&&u<n&&v>=0&&v<n&&w>=0)cap[u][v]+=w;else System.out.println("Invalid edge skipped.");}
                int s=Input.readInt(sc,"Source: "),t=Input.readInt(sc,"Sink: ");
                if(s<0||s>=n||t<0||t>=n||s==t){System.out.println("Invalid source/sink.");continue;}
                System.out.println("Maximum flow = "+maxFlow(cap,s,t));
            } else if(c==2) {
                int n=Input.readInt(sc,"Players count: "),m=Input.readInt(sc,"Positions count: ");
                if(n<1||m<1||n>20||m>20){System.out.println("Use 1-20.");continue;}
                boolean[][] e=new boolean[n][m];
                for(int i=0;i<n;i++){int k=Input.readInt(sc,"Number of eligible positions for player "+i+": ");for(int z=0;z<k;z++){int p=Input.readInt(sc,"Position index (0-"+(m-1)+"): ");if(p>=0&&p<m)e[i][p]=true;}}
                int[] match=new int[m];Arrays.fill(match,-1);int count=0;for(int i=0;i<n;i++)if(augment(i,e,new boolean[m],match))count++;
                System.out.println("Maximum assignments = "+count);for(int j=0;j<m;j++)if(match[j]>=0)System.out.println("Player "+match[j]+" -> Position "+j);
                System.out.println("König's theorem: maximum matching size equals minimum vertex-cover size in bipartite graphs.");
            } else if(c==3)System.out.println("For every uncovered conflict edge (u,v), select both endpoints. This gives a 2-approximation for minimum vertex cover.");
            else if(c==4)System.out.println("Max-flow/min-cut theorem: maximum feasible flow equals minimum cut capacity.");
            else System.out.println("Invalid choice.");
        }
    }
    private int maxFlow(int[][] c,int s,int t) {
        int n=c.length,flow=0;int[][] r=new int[n][n];for(int i=0;i<n;i++)r[i]=Arrays.copyOf(c[i],n);
        while(true){int[] par=new int[n];Arrays.fill(par,-1);par[s]=s;int[] q=new int[n];int h=0,z=0;q[z++]=s;
            while(h<z&&par[t]<0){int u=q[h++];for(int v=0;v<n;v++)if(par[v]<0&&r[u][v]>0){par[v]=u;q[z++]=v;}}
            if(par[t]<0)break;int add=Integer.MAX_VALUE;for(int v=t;v!=s;v=par[v])add=Math.min(add,r[par[v]][v]);
            for(int v=t;v!=s;v=par[v]){r[par[v]][v]-=add;r[v][par[v]]+=add;}flow+=add;
        }return flow;
    }
    private boolean augment(int u,boolean[][] e,boolean[] seen,int[] match) {
        for(int v=0;v<match.length;v++)if(e[u][v]&&!seen[v]){seen[v]=true;if(match[v]<0||augment(match[v],e,seen,match)){match[v]=u;return true;}}return false;
    }
}
