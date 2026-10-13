package sportspulse.modules;

import java.util.Scanner;
import java.util.Arrays;
import sportspulse.Input;

public class Module2SuffixStructures {
    public void menu(Scanner sc) {
        while(true) {
            System.out.println("\n--- MODULE 2: SUFFIX STRUCTURES ---");
            System.out.println("1. Suffix array + Kasai LCP\n2. Compare reports for longest common substring\n3. Suffix tree/automaton/SA-IS concepts\n0. Back");
            int c=Input.readInt(sc,"Choose: ");if(c==0)return;
            if(c==1) {
                String s=Input.readText(sc,"Report text: ");
                int[] sa=suffixArray(s),lcp=kasai(s,sa);
                System.out.println("Suffix array: "+Arrays.toString(sa));
                System.out.println("LCP array: "+Arrays.toString(lcp));
                for(int i:sa)System.out.println(i+": "+s.substring(i));
                System.out.println("This demonstration sorts suffixes directly; SA-IS is the linear-time advanced construction.");
            } else if(c==2) {
                String a=Input.readText(sc,"First report: ").toLowerCase(),b=Input.readText(sc,"Second report: ").toLowerCase();
                String best="";
                for(int i=0;i<a.length();i++)for(int j=i+1;j<=a.length();j++){String sub=a.substring(i,j);if(sub.length()>best.length()&&b.contains(sub))best=sub;}
                System.out.println("Longest common substring: "+(best.isEmpty()?"(none)":best));
            } else if(c==3) {
                System.out.println("Suffix array: sorted starting positions of every suffix.");
                System.out.println("Kasai: builds LCP values in O(n) once suffix array is known.");
                System.out.println("SA-IS: linear-time suffix array construction via induced sorting.");
                System.out.println("Suffix tree: compressed suffix trie. Suffix automaton: compact substring-recognition automaton.");
            } else System.out.println("Invalid choice.");
        }
    }
    private int[] suffixArray(String s) {
        Integer[] x=new Integer[s.length()];for(int i=0;i<x.length;i++)x[i]=i;
        Arrays.sort(x,(a,b)->s.substring(a).compareTo(s.substring(b)));
        int[] sa=new int[x.length];for(int i=0;i<sa.length;i++)sa[i]=x[i];return sa;
    }
    private int[] kasai(String s,int[] sa) {
        int n=s.length();int[] rank=new int[n],lcp=new int[n];for(int i=0;i<n;i++)rank[sa[i]]=i;
        for(int i=0,k=0;i<n;i++){if(rank[i]==n-1){k=0;continue;}int j=sa[rank[i]+1];while(i+k<n&&j+k<n&&s.charAt(i+k)==s.charAt(j+k))k++;lcp[rank[i]]=k;if(k>0)k--;}
        return lcp;
    }
}
