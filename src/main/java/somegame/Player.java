package somegame;

class Player extends Creatures {
    private int chanceToHeal = 3;

    /**
     * Конструктор создания игрока
     * @param name имя
     * @param attack значение атаки (от 1 до 20)
     * @param protection значение защиты (от 1 до 20)
     * @param totalHealth максимальное здоровье (от 1 до N)
     * @param damage урон (от 1 до 15)
     */
    public Player(String name, int attack, int protection, int totalHealth, int damage) {
        super("Игрок", name, attack, protection, totalHealth, damage);
    }

    /**
     * Метод исцеления игрока, 3 раза за всё время он может восстановиться на 50% от максимального здоровья.
     */
    public void restoreHealth() {
        if (chanceToHeal > 0) {
            if (getCurrentHealth() == getTotalHealth())
                System.out.printf("Лечение не требуется. Здоровье игрока %s уже достигло максимального значения!\n", getName());
            else {
                chanceToHeal--;
                setCurrentHealth(getCurrentHealth() + (getTotalHealth() / 2));
                System.out.printf("Игрок %s восстановил %d ед. здоровья. Текущее здоровье: %d Возможностей подлечиться осталось: %d\n",
                        getName(), (getTotalHealth() / 2), getCurrentHealth(), chanceToHeal);
            }
        } else System.out.println("Лечение невозможно. Вы истратили все возможности восстановить здоровье!");
    }
}
