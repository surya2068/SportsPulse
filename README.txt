SPORTSPULSE - MODULAR ECLIPSE PROJECT
====================================

This project is separated into packages and classes so each syllabus module can be explained independently.

Project structure:
src/
  sportspulse/
    Main.java
    Input.java
    model/Player.java
    data/SportsData.java
    data/ModuleSearch.java
    modules/Module1StringAlgorithms.java
    modules/Module2SuffixStructures.java
    modules/Module3DynamicProgramming.java
    modules/Module4NetworkFlow.java
    modules/Module5NPCompleteness.java
    modules/Module6RandomizedParallel.java

Import:
1. Extract the ZIP.
2. In Eclipse choose File > Import > Existing Projects into Workspace.
3. Select the extracted SportsPulse_Modular_Eclipse directory.
4. Open src/sportspulse/Main.java and choose Run As > Java Application.

All interactive inputs use java.util.Scanner, passed to the module methods from Main.

Implementation honesty:
- Module 1 implements KMP, Z-function and Rabin-Karp. Event-keyword scanning is a simple educational multi-keyword scan, not a full Aho-Corasick trie.
- Module 2 implements a direct suffix sort and Kasai LCP. SA-IS, suffix tree and suffix automaton are explained conceptually; the direct suffix sort is not SA-IS.
- Module 3 implements Levenshtein / restricted adjacent-transposition edit distance, bitmask squad selection, matrix-chain DP and subset sum.
- Module 4 implements Edmonds-Karp max flow and bipartite matching. Ford-Fulkerson/Dinic/min-cost flow are not implemented as separate algorithms in this version.
- Module 5 demonstrates a sample 3-SAT formula, reduction chain and vertex-cover approximation concept; it is not a general SAT solver.
- Module 6 implements randomized quicksort, reservoir sampling and Miller-Rabin. Prefix scan/reduce are sequential demonstrations, not parallel runtime implementations.
- java.util.Scanner is used for console input. Standard library arrays/random utilities are used in the application. If the instructor bans java.util.* throughout the engine, replace prohibited utilities with custom structures as required.
