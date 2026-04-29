package me.yourname.anticheat;

import java.util.HashMap;
import java.util.UUID;

public class ViolationManager {
    private final HashMap<UUID, Double> violations = new HashMap<>();

    public void addViolation(UUID uuid, double amount) {
        violations.put(uuid, violations.getOrDefault(uuid, 0.0) + amount);
    }

    public double getVL(UUID uuid) {
        return violations.getOrDefault(uuid, 0.0);
    }

    public void reset(UUID uuid) {
        violations.remove(uuid);
    }
}
