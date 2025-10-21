import java.util.Scanner;

public class Main
{
    private static Folder root;

    public static void main(String[] args)
    {
        createInitialStructure();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do
        {
            printMenu();
            System.out.print("Ваш вибір: ");
            while (!scanner.hasNextInt())
            {
                System.out.println("Будь ласка, введіть число.");
                scanner.next();
                System.out.print("Ваш вибір: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println("\n--- Поточна структура каталогів ---");
                    root.display("");
                    break;
                case 2:
                    System.out.println("\nЗагальний розмір кореневої папки: " + root.getSize() + " bytes");
                    break;
                case 3:
                    renameComponent(scanner);
                    break;
                case 4:
                    System.out.println("\nПрограма завершила роботу.");
                    break;
                default:
                    System.out.println("\nНевірний вибір. Спробуйте ще раз.");
                    break;
            }
            System.out.println("------------------------------------");
        } while (choice != 4);

        scanner.close();
    }

    public static void printMenu()
    {
        System.out.println("\n===== МЕНЮ КЕРУВАННЯ ФАЙЛАМИ =====");
        System.out.println("1. Вивести структуру каталогів");
        System.out.println("2. Розрахувати загальний розмір");
        System.out.println("3. Перейменувати файл або папку");
        System.out.println("4. Вийти");
    }
    
    public static void createInitialStructure()
    {
        root = new Folder("root");
        root.addComponent(new File("photo.jpg", 150));
        root.addComponent(new File("document.docx", 300));

        Folder subfolder1 = new Folder("subfolder1");
        subfolder1.addComponent(new File("archive.zip", 1000));

        Folder subfolder2 = new Folder("subfolder2");
        subfolder2.addComponent(new File("notes.txt", 50));

        subfolder1.addComponent(subfolder2);
        root.addComponent(subfolder1);
    }

    private static void renameComponent(Scanner scanner)
    {
        System.out.print("Введіть шлях до елемента (напр., root/subfolder1/archive.zip): ");
        String path = scanner.nextLine();

        FileSystemComponent component = findComponentByPath(path);

        if (component == null) {
            System.out.println("Елемент за вказаним шляхом не знайдено.");
            return;
        }

        System.out.print("Введіть нове ім'я для '" + component.getName() + "': ");
        String newName = scanner.nextLine();
        component.rename(newName);
        System.out.println("Елемент успішно перейменовано!");
    }

    private static FileSystemComponent findComponentByPath(String path)
    {
        String[] parts = path.split("/");
        if (parts.length == 0 || !parts[0].equals(root.getName()))
        {
            return null;
        }

        FileSystemComponent current = root;

        for (int i = 1; i < parts.length; i++)
        {
            if (current instanceof Folder)
            {
                current = ((Folder) current).findChild(parts[i]);
                if (current == null)
                {
                    return null;
                }
            } else
            {
                return null;
            }
        }
        return current;
    }
}