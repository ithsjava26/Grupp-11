package com.example.combat;

@FunctionalInterface
public interface AttackStrategy {
    int calculateDamage(GameCharacter attacker);
}
