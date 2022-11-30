package somegame;

import java.util.Scanner;

public class _Game {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print(">>Хозяин арены: Добро пожаловать игрок! Я хозяин этой арены!\n" +
                "Перед тем, как ты начнёшь, введи желаемое имя своего персонажа.\nВаше имя: ");
        String namePlayer = input.nextLine();
        System.out.printf(">>Хозяин арены: Отлично %s, теперь всё готово, желаю удачи в прохождении!\n" +
                ">>Хозяин арены: Вот, взгляни на свои характеристики:\n", namePlayer);
        Player player = new Player(namePlayer, 15, 10, 15, 5);
        Bogeyman[] monsters = {
                new Bogeyman("Зомби", 5, 7, 10, 5),
                new Bogeyman("Оборотень", 15, 10, 10, 8),
                new Bogeyman("Дракон", 20, 10, 35, 15)
        };
        player.status();
        System.out.println("....\n........\nВы вошли на арену...\n\n");
        System.out.printf(">>Хозяин арены: Твоим первым противником будет %s. Он довольно слабый, я думаю ты с ним" +
                " легко разберёшься.\n", monsters[0]);
        System.out.println(">>Зомби: МммМмммМм....(Промычал зомби и направился в твою сторону)");
        mainMenu(player, monsters[0]);
        if(player.isAlive()) {
            System.out.printf("\n\n>>Хозяин таверны: Охохо, ты ещё жив! Тогда твой следующий противник - %s\n", monsters[1]);
            System.out.println(">>Оборотень: Ауууууу....(Провыл оборотень и кинулся в атаку)");
            mainMenu(player, monsters[1]);
        }
        if(player.isAlive()) {
            System.out.printf("\n\n>>Хозяин таверны: Ого, ты снова меня удивил! Твой последний соперник - %s\n", monsters[2]);
            System.out.println(">>Дракон: . . . \n>>Дракон: Злобно посмотрел на тебя и со всей силы дыхнул пламенем!!!");
            player.setCurrentHealth(0);
        }
        if(!player.isAlive()) System.out.println("Вы пали смертью храбрых :D(....и звуки и DarkSouls)");
        System.out.println(">>Хозяин арены: Ну вот, на сегодня арена закончена. Благодарю за участие!");
        System.out.println("\n\nЗавершение работы программы...");
    }

    /**
     * Меню выбора действия
     * @param p игрок
     * @param b монстр
     */
    public static void mainMenu(Player p, Bogeyman b) {
        System.out.println("---------------------------------\nВыберите действие.\n 1 - атаковать\n " +
                "2 - восстановить здоровье\n 3 - информация о себе\n 4 - осмотреть противника");
        System.out.print("Ваше действие: ");
        String choice = input.nextLine();
        System.out.println("---------------------------------");
        switch (choice) {
            case "1":
                fight(p, b);
                break;
            case "2":
                p.restoreHealth();
                mainMenu(p, b);
                break;
            case "3":
                p.status();
                mainMenu(p, b);
                break;
            case "4":
                b.status();
                mainMenu(p, b);
                break;
            default:
                System.out.println("Введено неверное значение, попробуй ещё раз.");
                mainMenu(p, b);
        }
    }

    /**
     * Битва между монстром и человеком
     * @param p игрок
     * @param b монстр
     */
    public static void fight(Player p, Bogeyman b) {
        if(p.isAlive()) p.assaultTo(b);
        if(b.isAlive()) b.assaultTo(p);
        if (p.isAlive() && b.isAlive()) {
            mainMenu(p, b);
        } else if(p.isAlive()){
            System.out.printf("<<<! %s %s победил в схватке! !>>>", p.getTypeCreature(), p.getName());
        } else System.out.printf("<<<! %s %s победил в схватке! !>>>", b.getTypeCreature(), b.getName());
    }
}
