package lib;

public record CartItem(String sku, String name, double price, int quantity) {
    
    /**
     * Constructor สำหรับตรวจสอบความถูกต้องของข้อมูล
     */
}