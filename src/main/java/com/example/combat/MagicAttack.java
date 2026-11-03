package com.example.combat;

public class MagicAttack implements AttackStrategy {
    private static final double MULTIPLIER = 0.8;

    @Override
    public int calculateDamage(GameCharacter attacker) {
        return (int) Math.round(attacker.getAttackDamage() * MULTIPLIER);
    }
}
