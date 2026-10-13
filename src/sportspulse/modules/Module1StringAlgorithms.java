package sportspulse.modules;

import java.util.Scanner;
import java.util.Arrays;
import sportspulse.Input;
import sportspulse.data.ModuleSearch;

public class Module1StringAlgorithms {
    public void menu(Scanner sc) {
        while(true) {
            System.out.println("\n--- MODULE 1: STRING ALGORITHMS ---");
            System.out.println("1. KMP pattern search\n2. Z-function\n3. Rabin-Karp search\n4. Match event keywords\n0. Back");
            int c=Input.readInt(sc,"Choose: "); if(c==0)return;
            String text=Input.readText(sc,"Enter commentary/text: ");
            if(c==1) {
                String p=Input.readText(sc,"Pattern: "); int i=ModuleSearch.kmp(text,p);
                System.out.println(i>=0?"Found at index "+i:"Pattern not found.");
            } else if(c==2) System.out.println("Z-array: "+Arrays.toString(zFunction(text)));
            else if(c==3) {
                String p=Input.readText(sc,"Pattern: "); int i=rabinKarp(text,p);
                System.out.println(i>=0?"Hash match at index "+i:"Pattern not found.");
            } else if(c==4) {
                String lower=text.toLowerCase(); String[] words={"goal","wicket","foul","yellow card","red card","six","boundary","penalty"};
                boolean found=false;
                for(String w:words)if(ModuleSearch.kmp(lower,w)>=0){System.out.println("Event keyword: "+w);found=true;}
                if(!found)System.out.println("No configured event keywords found.");
                System.out.println("Note: this is a simple multi-keyword scan; full Aho-Corasick uses a trie and failure links.");
            } else System.out.println("Invalid choice.");
        }
    }
    private int[] zFunction(String s) {
        int n=s.length(),l=0,r=0;int[] z=new int[n];
        for(int i=1;i<n;i++){if(i<=r)z[i]=Math.min(r-i+1,z[i-l]);while(i+z[i]<n&&s.charAt(z[i])==s.charAt(i+z[i]))z[i]++;if(i+z[i]-1>r){l=i;r=i+z[i]-1;}}
        if(n>0)z[0]=n;return z;
    }
    private int rabinKarp(String t,String p) {
        if(p.isEmpty())return 0;if(p.length()>t.length())return -1;
        long mod=1000000007L,base=257,ph=0,th=0,pow=1;
        for(int i=0;i<p.length();i++){ph=(ph*base+p.charAt(i))%mod;th=(th*base+t.charAt(i))%mod;if(i<p.length()-1)pow=pow*base%mod;}
        for(int i=0;i<=t.length()-p.length();i++){if(ph==th&&t.regionMatches(i,p,0,p.length()))return i;if(i<t.length()-p.length()){th=(th-t.charAt(i)*pow%mod+mod)%mod;th=(th*base+t.charAt(i+p.length()))%mod;}}
        return -1;
    }
}
