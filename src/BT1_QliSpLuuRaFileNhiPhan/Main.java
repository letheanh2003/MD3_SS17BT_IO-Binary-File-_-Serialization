package BT1_QliSpLuuRaFileNhiPhan;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Product> products;

    public static void main(String[] args) {
        products =readProduct();
        System.out.println("nhập số sản phẩm muốn thêm:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Product product = createProduct();
            products.add(product);
        }
        writeProduct();
        showProduct();
        System.out.println("bạn muốn tìm kiếm sản phẩm gì?");
        String searchProduct = scanner.nextLine();
        searchProduct(searchProduct);
    }

    public static void writeProduct() {
        try {
            File file = new File("product.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(products);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Product> readProduct(){
        File file = new File("product.txt");
        try {
            if (file.exists()){
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                List<Product> list = (List<Product>) ois.readObject();
                return list;
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    public static Product createProduct() {
        Product product = new Product();
        if (products.isEmpty()) {
            product.setProductId(1);
        } else {
            int newId = products.get(products.size() - 1).getProductId() + 1;
            product.setProductId(newId);
        }
        System.out.println("nhập tên sản phẩm: ");
        product.setProductName(scanner.nextLine());
        System.out.println("nhập hãng sản xuất: ");
        product.setManufacturer(scanner.nextLine());
        System.out.println("nhập giá: ");
        product.setPrice(Float.parseFloat(scanner.nextLine()));
        return product;
    }
    public static void showProduct(){
        for (Product product:products) {
            System.out.println(product);
        }
    }
    public static void searchProduct(String name){
        for (Product product:products) {
            if (product.getProductName().equalsIgnoreCase(name)){
                System.out.println(product);
            }
        }
    }
}
