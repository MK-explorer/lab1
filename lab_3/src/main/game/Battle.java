package main.game;

import main.droids.*;
import java.util.*;

public class Battle {
    private StringBuilder log = new StringBuilder();

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    //  HP-БАР
    private void showHP(Droid d) {
        int hp = d.getHealth();
        int bar = hp / 5;

        bar = Math.max(0, bar);    // не нижче 0
        bar = Math.min(20, bar);   // не вище 20

        String hpBar = "█".repeat(bar) + "-".repeat(20 - bar);

        System.out.println(d.getName() + ": [" + hpBar + "] " + hp + " HP");
    }

    //  БІЙ 1 НА 1
    public void fightOneOnOne(Droid d1, Droid d2) {
        d1.restoreHealth();
        d2.restoreHealth();

        log.setLength(0);
        System.out.println("\n БІЙ 1 НА 1");
        log.append("БІЙ 1 НА 1 \n");

        while (d1.isAlive() && d2.isAlive()) {
            // Хід 1-го дроїда
            d1.attack(d2);
            log.append(d1.getName()).append(" атакує ").append(d2.getName()).append("\n");
            showHP(d2);
            sleep(800);
            System.out.println();

            if (!d2.isAlive()) break;

            // Хід 2-го дроїда
            d2.attack(d1);
            log.append(d2.getName()).append(" атакує ").append(d1.getName()).append("\n");
            showHP(d1);
            sleep(800);
            System.out.println();
        }

        Droid winner = d1.isAlive() ? d1 : d2;
        System.out.println("Переможець: " + winner.getName());
        log.append("Переможець: ").append(winner.getName()).append("\n");

        d1.restoreHealth();
        d2.restoreHealth();
    }

    //  КОМАНДНИЙ БІЙ
    public void fightTeamVsTeam(List<Droid> team1, List<Droid> team2) {
        log.setLength(0);
        System.out.println("\nБІЙ КОМАНДА НА КОМАНДУ");
        log.append("БІЙ КОМАНДА НА КОМАНДУ\n");

        Random rand = new Random();

        // Перевірка на дублікати дроїдів у командах
        Set<Droid> uniqueCheck = new HashSet<>();
        if (!uniqueCheck.addAll(team1) || !uniqueCheck.addAll(team2)) {
            System.out.println(" Увага! Один і той самий дроїд не може бути в обох командах!");
            return;
        }

        // Початок бою
        while (isTeamAlive(team1) && isTeamAlive(team2)) {

            //  Ходи команди 1
            for (Droid attacker : team1) {
                if (!attacker.isAlive()) continue;

                Droid target = getRandomAlive(team2, rand);
                if (target == null) break;

                attacker.attack(target);
                log.append(attacker.getName()).append(" атакує ").append(target.getName()).append("\n");
                showHP(target);
                sleep(500);
            }

            if (!isTeamAlive(team2)) break;

            //  Ходи команди 2
            for (Droid attacker : team2) {
                if (!attacker.isAlive()) continue;

                Droid target = getRandomAlive(team1, rand);
                if (target == null) break;

                attacker.attack(target);
                log.append(attacker.getName()).append(" атакує ").append(target.getName()).append("\n");
                showHP(target);
                sleep(500);
            }
        }

        String winner = isTeamAlive(team1) ? "Команда 1" : "Команда 2";
        System.out.println("Перемогла " + winner + "!");
        log.append("Перемогла ").append(winner).append("\n");
    }

    // Допоміжні методи
    private boolean isTeamAlive(List<Droid> team) {
        return team.stream().anyMatch(Droid::isAlive);
    }

    private Droid getRandomAlive(List<Droid> team, Random rand) {
        List<Droid> alive = team.stream().filter(Droid::isAlive).toList();
        if (alive.isEmpty()) return null;
        return alive.get(rand.nextInt(alive.size()));
    }

    public String getLog() {
        return log.toString();
    }
}

