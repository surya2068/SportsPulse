package sportspulse.modules;

import java.util.Scanner;
import sportspulse.Input;

public class Module5NPCompleteness {
    public void menu(Scanner sc) {
        while(true) {
            System.out.println("\n--- MODULE 5: NP-COMPLETENESS AND APPROXIMATION ---");
            System.out.println("1. Evaluate sample 3-SAT formula\n2. Show reduction chain\n3. Vertex-cover approximation explanation\n4. Complexity classes\n0. Back");
            int c=Input.readInt(sc,"Choose: ");if(c==0)return;
            if(c==1) {
                boolean a=Input.readText(sc,"A (true/false): ").trim().equalsIgnoreCase("true");
                boolean b=Input.readText(sc,"B (true/false): ").trim().equalsIgnoreCase("true");
                boolean d=Input.readText(sc,"C (true/false): ").trim().equalsIgnoreCase("true");
                boolean result=(a||!b||d)&&(!a||b||d);
                System.out.println("(A OR NOT B OR C) AND (NOT A OR B OR C) = "+result);
            } else if(c==2) {
                System.out.println("3-SAT -> CLIQUE: vertices represent literal occurrences; compatible literals across clauses are connected.");
                System.out.println("CLIQUE -> INDEPENDENT-SET: complement graph transforms clique into independent set.");
                System.out.println("INDEPENDENT-SET -> VERTEX-COVER: a set is independent iff its complement is a vertex cover.");
                System.out.println("These are polynomial-time reductions preserving yes/no answers.");
            } else if(c==3) {
                System.out.println("Take any uncovered edge and add both endpoints to the cover; repeat until no edges remain.");
                System.out.println("Every selected edge contributes two endpoints, while any cover needs at least one endpoint: output <= 2 x optimum.");
            } else if(c==4) {
                System.out.println("P: solvable in polynomial time. NP: candidate solutions verifiable in polynomial time.");
                System.out.println("NP-complete: in NP and NP-hard. NP-hard: at least as hard as every problem in NP.");
                System.out.println("PTAS: approximation ratio 1+epsilon for fixed epsilon; FPTAS is polynomial in input size and 1/epsilon.");
                System.out.println("FPT: f(k)*poly(n) time for parameter k. These menus demonstrate concepts, not a full SAT solver.");
            } else System.out.println("Invalid choice.");
        }
    }
}
