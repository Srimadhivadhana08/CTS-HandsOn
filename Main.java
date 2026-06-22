import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Product {
    String productName;
    String category;
    int productId;

    List<Product> listForLinearSearch;
    List<Product> listForBinarySearch;

    public Product() {
        listForLinearSearch = new ArrayList<>();
        listForBinarySearch = new ArrayList<>();
    }

    public Product(String productName, String category, int productId) {
        this.productName = productName;
        this.category = category;
        this.productId = productId;
    }

    public void addProduct(Product product) {
        listForLinearSearch.add(product);
        listForBinarySearch.add(product);
    }

    // Linear Search
    public Product searchProductUsingLinearSearch(int id) {
        for (Product product : listForLinearSearch) {
            if (product.productId == id) {
                return product;
            }
        }
        return null;
    }

    // Binary Search
    public Product searchProductUsingBinarySearch(int id) {
        Collections.sort(
            listForBinarySearch,
            Comparator.comparingInt(p -> p.productId)
        );

        int start = 0;
        int end = listForBinarySearch.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midProductId = listForBinarySearch.get(mid).productId;

            if (midProductId == id) {
                return listForBinarySearch.get(mid);
            } else if (midProductId < id) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return null;
    }

    public static void printProduct(Product product) {
        if (product == null) {
            System.out.println("Product not found");
            return;
        }

        System.out.println("Product Id : " + product.productId);
        System.out.println("Product Name : " + product.productName);
        System.out.println("Product Category : " + product.category);
    }
}

public class Main {
    public static void main(String[] args) {

        Product store = new Product();

        Product product1 = new Product("Laptop", "Electronics", 1);
        Product product2 = new Product("Mobile Phone", "Electronics", 2);
        Product product3 = new Product("Charger", "Electronics", 3);
        Product product4 = new Product("Shoe", "Fashion", 10);

        store.addProduct(product1);
        store.addProduct(product2);
        store.addProduct(product3);
        store.addProduct(product4);

        System.out.println("Product Search Using Linear Search O(n):");
        Product result1 = store.searchProductUsingLinearSearch(1);
        Product.printProduct(result1);

        System.out.println();

        System.out.println("Product Search Using Binary Search O(log n):");
        Product result2 = store.searchProductUsingBinarySearch(3);
        Product.printProduct(result2);
    }
}