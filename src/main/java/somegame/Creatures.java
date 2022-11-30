package somegame;

import java.util.Random;

/**
 * Класс создания существ. Он абстрактный, но при этом тут прописаны почти все поля и методы, которые присущи существам
 */
abstract class Creatures {
    private String name;
    private int attack;
    private int protection;
    private int totalHealth;
    private int currentHealth;
    private int damage;
    private final String typeCreature;

    /**
     * Конструктор существ. Если неверно ввести параметры существа, будет выброшено исключение.
     *
     * @param typeCreature тип
     * @param name         имя
     * @param attack       значение атаки (от 1 до 20)
     * @param protection   значение защиты (от 1 до 20)
     * @param totalHealth  максимальное здоровье (от 1 до N)
     * @param damage       урон (от 1 до 6)
     */
    public Creatures(String typeCreature, String name, int attack, int protection, int totalHealth, int damage) {
        this.typeCreature = typeCreature;
        setName(name);
        try {
        setAttack(attack);
        setProtection(protection);
        setTotalHealth(totalHealth);
        setDamage(damage);
        } catch (CreationException e) {
            System.out.println(e.getNameException() + e.getMessage());
            System.exit(0);
        }

    }

    public String getTypeCreature() {
        return typeCreature;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) throws CreationException {
        if (attack > 0 && attack < 21)
            this.attack = attack;
        else throw new CreationException(String.format(" Атака существа \"%s\" должна быть в диапазоне от 1 " +
                "до 20. Ваше значение: %d", getTypeCreature(), attack));
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) throws CreationException {
        if (attack > 0 && attack < 21)
            this.protection = protection;
        else throw new CreationException(String.format(" Защита существа \"%s\" должна быть в диапазоне от 1" +
                " до 20. Ваше значение: %d", getTypeCreature(), protection));
    }

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int totalHealth) throws CreationException {
        if (totalHealth > 0) {
            this.totalHealth = totalHealth;
            this.currentHealth = totalHealth;
        } else throw new CreationException(String.format(" Здоровье нового существа \"%s\" не может быть меньше" +
                " или равно 0. Ваше значение: %d", getTypeCreature(), totalHealth));
    }

    public int getCurrentHealth() {
        return Math.max(currentHealth, 0);
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = Math.min(currentHealth, totalHealth);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) throws CreationException {
        if (damage > 0 && damage < 16)
            this.damage = damage;
        else throw new CreationException(String.format(" Урон существа \"%s\" должен быть в диапазоне от 1 до 15. " +
                        "Ваше значение: %d",
                getTypeCreature(), damage));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void status() {
        if (getCurrentHealth() == 0) System.out.printf("%s %s мёртв!\n", typeCreature, name);
        else {
            System.out.printf(">> Окно статуса для существа \"%s\"\n", typeCreature);
            System.out.println("╔════════════════════════════════════╗");
            System.out.printf("   Имя: %s\n   Текущее здоровье: %d из %d\n   Атака: %d | Защита: %d | Урон %d\n",
                    name, getCurrentHealth(), totalHealth, attack, protection, damage);
            System.out.println("╚════════════════════════════════════╝\n");
        }
    }

    /**
     * Метод нанесения удара.
     *
     * @param defending защищающееся существо
     * @param <T>       универсальная переменная, указывающая, что атаковать можно только существ(даже самого себя) :D
     */
    public <T extends Creatures> void assaultTo(T defending) {
        int attackModifier = this.attack - defending.getProtection() + 1;
        if (attackModifier <= 0) attackModifier = 1;
        Random rnd = new Random();
        boolean success = false;
        int cubeValue;
        for (int i = 0; i < attackModifier; i++) {
            cubeValue = rnd.nextInt(6) + 1;
            if (cubeValue > 4) {
                success = true;
                break;
            }
        }
        if (success) {
            int term = defending.getCurrentHealth();
            int damageTaken = (rnd.nextInt(getDamage()) + 1);
            defending.setCurrentHealth(defending.getCurrentHealth() - damageTaken);
            if (defending.getCurrentHealth() == 0) {
                System.out.printf("%s %s попал ударом на %d ед. урона! %s потерял все %d ед. здоровья. %s мёртв...\n", this.typeCreature,
                        this.name, damageTaken, defending.getName(), defending.getTotalHealth(), defending.getName());
            } else System.out.printf("%s %s попал ударом! %s потерял %d из %d ед. здоровья...\n", this.typeCreature,
                    this.name, defending.getName(), damageTaken, term);
        } else System.out.printf("Атака %s не прошла...\n", this.typeCreature + "а");
    }

    public boolean isAlive() {
        return getCurrentHealth() != 0;
    }
}
