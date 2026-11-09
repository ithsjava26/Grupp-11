package com.example;

import com.example.combat.*;

public class Main {

    private enum Outcome { WON, LOST, FLED }

    private static final String START_MENU = """
            1) Start!
            q) Quit
            """;

    private static final int HEAL_COOLDOWN_TURNS = 4;

    static void main() {
        startMenu();
    }

    public static void startMenu() {
        while (true) {
            IO.print(START_MENU);
            int choice = startQuitInput();
            if (choice == 0) {
                return;
            }
            runGame();
        }
    }

    private static void runGame() {
        Hero hero = new Hero("Hero", 1, 100, 10);
 
        while (true) {
            Enemy enemy = spawnEnemy();
            Outcome outcome = fight(hero, enemy);

            switch (outcome) {
                case WON -> {
                    IO.println();
                    IO.println("  ★ You have killed " + enemy.getName() + "! Going to the next enemy...");
                    Printer.printDivider();
                }
                case LOST -> {
                    IO.println();
                    IO.println("  ✖ You have died!");
                    IO.println();
                    return;
                }
                case FLED -> {
                    IO.println();
                    IO.println("  You fled from the battle.");
                    IO.println();
                    return;
                }
            }
        }
    }


    // --- Combat ---
    private static Enemy spawnEnemy() {
        // TODO: Add random enemy spawn
        return new Enemy("Goblin", 1, EnemyType.GOBLIN);
    }

    private static Outcome fight(Hero hero, Enemy enemy) {
        Printer.printEnterCombatMessage(hero, enemy);
 
        boolean heroTurn = true;
        int round = 1;
 
        while (true) {
            if (heroTurn) {
                IO.println();
                IO.println("  ── Round " + round + " ──────────────────────────");
                Printer.printTurn(hero);
                boolean fled = !heroAction(hero, enemy);
                if (fled) {
                    return Outcome.FLED;
                }
            } else {
                Printer.printTurn(enemy);
                attack(enemy, hero);
            }
 
            IO.println();
            Printer.printHealth(enemy);
            IO.println();
            Printer.printHealth(hero);
 
            if (!enemy.isAlive()) {
                hero.onTurnEnd();
                return Outcome.WON;
            }
            if (!hero.isAlive()) {
                hero.onTurnEnd();
                return Outcome.LOST;
            }

            if (heroTurn) {
                Printer.printDivider();
            }
            if (!heroTurn) {
                hero.onTurnEnd();
                round++;
            }
            heroTurn = !heroTurn;
        }
    }


    // --- Action ---
    private static boolean heroAction(Hero hero, GameCharacter enemy) {
        while (true) {
            IO.println();
            IO.println(Printer.getMenuOptions(hero));
            int choice = getInput(1, 5);

            switch (choice) {
                case 1 -> attackOption(hero, new MeleeAttack(), enemy);
                case 2 -> attackOption(hero, new RangedAttack(), enemy);
                case 3 -> attackOption(hero, new MagicAttack(), enemy);
                case 4 -> {
                    int amount = (int) Math.round(hero.getMaxHealth() * 0.2);
                    if (!hero.heal(amount)) {
                        IO.println("Could not use heal, " + hero.getHealBlockedTurns() + " turns left until available");
                        continue;
                    }
                    hero.blockHealing(HEAL_COOLDOWN_TURNS);
                    IO.println();
                    IO.println("  » %s heals for up to %d health.".formatted(hero.getName(), amount));
                }
                case 5 -> {
                    return false;
                }
            }
            return true;
        }
    }

    private static void attackOption(Hero hero, AttackStrategy attackStrategy, GameCharacter enemy) {
        hero.setAttackStrategy(attackStrategy);
        attack(hero, enemy);
    }

    private static void attack(GameCharacter attacker, GameCharacter target) {
        int damage = attacker.getAttackStrategy().calculateDamage(attacker);
        target.takeDamage(damage);
        IO.println();
        IO.println("  » %s attacks %s for %d damage!".formatted(attacker.getName(), target.getName(), damage));
    }


    // --- Input ---
    private static int startQuitInput() {
        while (true) {
            IO.print("> ");
            String line = readLine();
            if (line.equalsIgnoreCase("q")) {
                return 0;
            }
            try {
                int input = Integer.parseInt(line);
                if (input == 1) return input;
            } catch (NumberFormatException _) {
            }
            IO.println("Invalid menu input, try again.");
        }
    }

    private static int getInput(int min, int max) {
        while (true) {
            IO.print("> ");
            String line = readLine();
            try {
                int input = Integer.parseInt(line);
                if (input >= min && input <= max) {
                    return input;
                }
            } catch (NumberFormatException _) {
            }
            IO.println("Invalid menu input, try again.");
        }
    }

    private static String readLine() {
        String line = IO.readln();
        return line == null ? "q" : line.trim();
    }
}
