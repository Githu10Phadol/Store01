package lib;
import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));  // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: BOGO ซื้อ 2 จ่าย 1
        ArrayList<CartItem> bogoCart1 = new ArrayList<>();
        bogoCart1.add(new CartItem("BOGO", "Shampoo", 100.0, 2)); // ซื้อ 2 จ่าย 1 = 100
        double total4 = ShoppingCartCalculator.calculateTotalPrice(bogoCart1);
        if (total4 == 100.0) {
            System.out.println("PASSED: BOGO cart (2 items) total is correct (100.0)");
            passedCount++;
        } else { 
            System.out.println("FAILED: BOGO cart (2 items) expected 100.0 but got " + total4);
            failedCount++;
        }

        // Test 5: BOGO - ซื้อ 3 จ่าย 2
        ArrayList<CartItem> bogoCart2 = new ArrayList<>();
        bogoCart2.add(new CartItem("BOGO", "Soap", 50.0, 3)); // ซื้อ 3 จ่าย 2 = 100
        double total5 = ShoppingCartCalculator.calculateTotalPrice(bogoCart2);
        if (total5 == 100.0) {
            System.out.println("PASSED: BOGO cart (3 items) total is correct (100.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart (3 items) expected 100.0 but got " + total5);
            failedCount++;
        }

        // Test 6: BOGO - ซื้อ 5 จ่าย 3
        ArrayList<CartItem> bogoCart3 = new ArrayList<>();
        bogoCart3.add(new CartItem("BOGO", "Toothpaste", 30.0, 5)); // ซื้อ 5 จ่าย 3 = 90
        double total6 = ShoppingCartCalculator.calculateTotalPrice(bogoCart3);
        if (total6 == 90.0) {
            System.out.println("PASSED: BOGO cart (5 items) total is correct (90.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO cart (5 items) expected 90.0 but got " + total6);
            failedCount++;
        }

        // Test 7: BULK - ซื้อน้อยกว่า 6 ชิ้น (ไม่มีส่วนลด)
        ArrayList<CartItem> bulkCart1 = new ArrayList<>();
        bulkCart1.add(new CartItem("BULK", "Rice", 40.0, 5)); // 40 * 5 = 200
        double total7 = ShoppingCartCalculator.calculateTotalPrice(bulkCart1);
        if (total7 == 200.0) {
            System.out.println("PASSED: BULK cart (5 items, no discount) total is correct (200.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart (5 items, no discount) expected 200.0 but got " + total7);
            failedCount++;
        }

        // Test 8: BULK - ซื้อ 6 ชิ้น (ได้ส่วนลด 10%)
        ArrayList<CartItem> bulkCart2 = new ArrayList<>();
        bulkCart2.add(new CartItem("BULK", "Sugar", 50.0, 6)); // 50 * 6 * 0.9 = 270
        double total8 = ShoppingCartCalculator.calculateTotalPrice(bulkCart2);
        if (total8 == 270.0) {
            System.out.println("PASSED: BULK cart (6 items, 10% discount) total is correct (270.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart (6 items, 10% discount) expected 270.0 but got " + total8);
            failedCount++;
        }

        // Test 9: BULK - ซื้อ 10 ชิ้น (ได้ส่วนลด 10%)
        ArrayList<CartItem> bulkCart3 = new ArrayList<>();
        bulkCart3.add(new CartItem("BULK", "Oil", 25.0, 10)); // 25 * 10 * 0.9 = 225
        double total9 = ShoppingCartCalculator.calculateTotalPrice(bulkCart3);
        if (total9 == 225.0) {
            System.out.println("PASSED: BULK cart (10 items, 10% discount) total is correct (225.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK cart (10 items, 10% discount) expected 225.0 but got " + total9);
            failedCount++;
        }

        // Test 10: การผสมสินค้าหลายประเภท
        ArrayList<CartItem> mixedCart = new ArrayList<>();
        mixedCart.add(new CartItem("NORMAL", "Water", 10.0, 1));    // 10
        mixedCart.add(new CartItem("BOGO", "Shampoo", 100.0, 2));  // 100 (ซื้อ 2 จ่าย 1)
        mixedCart.add(new CartItem("BULK", "Rice", 40.0, 6));      // 216 (40*6*0.9)
        double total10 = ShoppingCartCalculator.calculateTotalPrice(mixedCart);
        if (total10 == 326.0) {
            System.out.println("PASSED: Mixed cart total is correct (326.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Mixed cart expected 326.0 but got " + total10);
            failedCount++;
        }

        // Test 11: สินค้าที่มีราคาติดลบ
        ArrayList<CartItem> negativeCart = new ArrayList<>();
        negativeCart.add(new CartItem("NORMAL", "Invalid", -10.0, 2)); // ควรข้ามไม่เอ่าไม่นับ
        negativeCart.add(new CartItem("NORMAL", "Valid", 20.0, 1));    // 20
        double total11 = ShoppingCartCalculator.calculateTotalPrice(negativeCart);
        if (total11 == 20.0) {
            System.out.println("PASSED: Negative price handled correctly (20.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Negative price expected 20.0 but got " + total11);
            failedCount++;
        }

        // Test 12: สินค้าที่มีจำนวนติดลบ
        ArrayList<CartItem> negativeQuantityCart = new ArrayList<>();
        negativeQuantityCart.add(new CartItem("NORMAL", "Invalid", 10.0, -2)); // ควรข้าม
        negativeQuantityCart.add(new CartItem("NORMAL", "Valid", 15.0, 3));    // 45
        double total12 = ShoppingCartCalculator.calculateTotalPrice(negativeQuantityCart);
        if (total12 == 45.0) {
            System.out.println("PASSED: Negative quantity handled correctly (45.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Negative quantity expected 45.0 but got " + total12);
            failedCount++;
        }

        // Test 13: CartItem เป็น null
        ArrayList<CartItem> nullItemCart = new ArrayList<>();
        nullItemCart.add(null); // ควรข้าม
        nullItemCart.add(new CartItem("NORMAL", "Valid", 30.0, 2)); // 60
        double total13 = ShoppingCartCalculator.calculateTotalPrice(nullItemCart);
        if (total13 == 60.0) {
            System.out.println("PASSED: Null item handled correctly (60.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Null item expected 60.0 but got " + total13);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed. Please check your implementation.");
        }
    }
      public static void main(String[] args) {
        run();
     }

}