package somegame;

class Bogeyman extends Creatures {
    /**
     * Конструктор создания монстра.
     *
     * @param name        имя
     * @param attack      значение атаки (от 1 до 20)
     * @param protection  значение защиты (от 1 до 20)
     * @param totalHealth максимальное здоровье (от 1 до N)
     * @param damage      урон (от 1 до 15)
     */
    public Bogeyman(String name, int attack, int protection, int totalHealth, int damage) {
        super("Монстр", name, attack, protection, totalHealth, damage);
    }

    @Override
    public String toString() {
        return String.format("%s %s", getTypeCreature(), getName());
    }
}
