package sportspulse;

import java.util.Scanner;
import sportspulse.data.SportsData;
import sportspulse.modules.Module1StringAlgorithms;
import sportspulse.modules.Module2SuffixStructures;
import sportspulse.modules.Module3DynamicProgramming;
import sportspulse.modules.Module4NetworkFlow;
import sportspulse.modules.Module5NPCompleteness;
import sportspulse.modules.Module6RandomizedParallel;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SportsData data = new SportsData();
        while (true) {
            System.out.println("\n========== SPORTSPULSE ==========");
            System.out.println("1. Players, teams and records");
            System.out.println("2. Module 1 - String Algorithms");
            System.out.println("3. Module 2 - Suffix Structures");
            System.out.println("4. Module 3 - Dynamic Programming");
            System.out.println("5. Module 4 - Network Flow");
            System.out.println("6. Module 5 - NP-Completeness and Approximation");
            System.out.println("7. Module 6 - Randomized and Parallel Algorithms");
            System.out.println("0. Exit");
            int choice = Input.readInt(sc, "Choose module: ");
            switch (choice) {
                case 1: data.menu(sc); break;
                case 2: new Module1StringAlgorithms().menu(sc); break;
                case 3: new Module2SuffixStructures().menu(sc); break;
                case 4: new Module3DynamicProgramming().menu(sc, data); break;
                case 5: new Module4NetworkFlow().menu(sc); break;
                case 6: new Module5NPCompleteness().menu(sc); break;
                case 7: new Module6RandomizedParallel().menu(sc); break;
                case 0: System.out.println("SportsPulse closed."); sc.close(); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
