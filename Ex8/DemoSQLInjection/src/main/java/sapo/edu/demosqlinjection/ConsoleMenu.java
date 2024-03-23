package sapo.edu.demosqlinjection;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sapo.edu.demosqlinjection.service.CategoryService;

@Component
public class ConsoleMenu implements CommandLineRunner {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----- MENU -----");
            System.out.println("1. Tìm kiếm an toàn");
            System.out.println("2. Tìm kiếm không an toàn");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc kí tự '\n'

            switch (choice) {
                case 0:
                    System.out.println("Kết thúc chương trình.");
                    return;
                case 1:
                    searchCategorySafe(scanner);
                    break;
                case 2:
                    searchCategoryUnsafe(scanner);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }

    private void searchCategorySafe(Scanner scanner) {
        System.out.print("Nhập giá trị category an toàn: ");
        String categoryName = scanner.nextLine();
        categoryService.searchCategorySafe(categoryName).forEach(System.out::println);
    }

    private void searchCategoryUnsafe(Scanner scanner) {
        System.out.print("Nhập giá trị category không an toàn: ");
        String categoryName = scanner.nextLine();
        categoryService.searchCategoryUnsafe(categoryName).forEach(System.out::println);
    }
}
