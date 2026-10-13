package sportspulse.data;

import java.util.Arrays;
import java.util.Scanner;
import sportspulse.Input;
import sportspulse.model.Player;

public class SportsData {
    private Player[] players = {
        new Player(101,"Virat Kohli","India","Batter",91.5,94),
        new Player(102,"Rohit Sharma","India","Batter",88.2,89),
        new Player(103,"Jasprit Bumrah","India","Bowler",93.1,92),
        new Player(104,"Hardik Pandya","India","All-rounder",84.7,86),
        new Player(105,"Ravindra Jadeja","India","All-rounder",87.6,91),
        new Player(106,"Shubman Gill","India","Batter",85.9,90),
        new Player(201,"Babar Azam","Pakistan","Batter",89.3,88),
        new Player(202,"Shaheen Afridi","Pakistan","Bowler",86.8,87)
    };

    public Player[] getPlayers() { return Arrays.copyOf(players, players.length); }

    public void menu(Scanner sc) {
        while(true) {
            System.out.println("\n--- PLAYERS, TEAMS AND RECORDS ---");
            System.out.println("1. List players\n2. Search player by name\n3. Search by ID\n4. Rank by performance\n5. Add player\n0. Back");
            int c=Input.readInt(sc,"Choose: ");
            if(c==0)return;
            if(c==1) for(Player p:players) System.out.println(p);
            else if(c==2) {
                String q=Input.readText(sc,"Name or part of name: ").toLowerCase();
                boolean found=false;
                for(Player p:players) if(ModuleSearch.kmp(p.getName().toLowerCase(),q)>=0){System.out.println(p);found=true;}
                if(!found)System.out.println("No player found.");
            } else if(c==3) {
                String q=Input.readText(sc,"ID or ID fragment: ");
                boolean found=false;
                for(Player p:players) if(String.valueOf(p.getId()).contains(q)){System.out.println(p);found=true;}
                if(!found)System.out.println("No ID found.");
            } else if(c==4) {
                Player[] a=getPlayers();
                for(int i=0;i<a.length;i++)for(int j=0;j<a.length-1-i;j++)
                    if(a[j].getPerformance()<a[j+1].getPerformance()){Player t=a[j];a[j]=a[j+1];a[j+1]=t;}
                for(int i=0;i<a.length;i++)System.out.println((i+1)+". "+a[i]);
            } else if(c==5) {
                int id=Input.readInt(sc,"ID: ");
                String name=Input.readText(sc,"Name: "), team=Input.readText(sc,"Team: "), pos=Input.readText(sc,"Position: ");
                double perf=Input.readDouble(sc,"Performance score: "); int fit=Input.readInt(sc,"Fitness 0-100: ");
                players=Arrays.copyOf(players,players.length+1);
                players[players.length-1]=new Player(id,name,team,pos,perf,fit);
                System.out.println("Player added.");
            } else System.out.println("Invalid choice.");
        }
    }
}
