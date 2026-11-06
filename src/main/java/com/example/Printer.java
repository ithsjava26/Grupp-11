package com.example;

import com.example.combat.*;

public class Printer {

    private static final String COMBAT_START_MESSAGE = """
            ══════════════════════════════════════════
                       ⚔  COMBAT START  ⚔          \s
            ══════════════════════════════════════════
            """;

    private static String healthBar(GameCharacter c, int length) {
        int filled = c.getMaxHealth() > 0 ? c.getCurrentHealth() * length / c.getMaxHealth() : 0;
        filled = Math.max(0, Math.min(filled, length));
        return "█".repeat(filled) + "░".repeat(length - filled);
    }

    public static void printHealth(GameCharacter c) {
        IO.println("  %-10s | Lvl: %-3d | HP: %s  %d / %d".formatted(
                c.getName(), c.getLevel(), healthBar(c, 16),
                Math.max(c.getCurrentHealth(), 0), c.getMaxHealth()));
    }

    public static void printStatus(GameCharacter c) {
        String line = "═".repeat(38);

        IO.println();
        IO.println("╔" + line + "╗");
        IO.println("║  %-34s  ║".formatted(c.getName()));
        IO.println("║  Level %-30d║".formatted(c.getLevel()));
        IO.println("╚" + line + "╝");
        IO.println();
        IO.println("  HP       %s  %d / %d".formatted(
                healthBar(c, 16), Math.max(c.getCurrentHealth(), 0), c.getMaxHealth()));
        IO.println();
        IO.println("  ─── Stats ────────────────────────────");
        IO.println("  Attack          %d".formatted(c.getAttackDamage()));
        IO.println();
    }

    public static void printTurn(GameCharacter c) {
        IO.println();
        IO.println("  «|»   " + c.getName() + "'s turn   «|»  ");
        IO.println();
    }

    public static void printDivider() {
        IO.println();
        IO.println("─".repeat(62));
    }

    public static void printEnterCombatMessage(GameCharacter hero, GameCharacter enemy) {
        Printer.printStatus(enemy);
        Printer.printStatus(hero);
        IO.println("");
        IO.println(COMBAT_START_MESSAGE);
        Printer.printHealth(enemy);
        IO.println("");
        Printer.printHealth(hero);
    }
}
