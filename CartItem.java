/**
 * CartItem เป็น record class สำหรับเก็บข้อมูลสินค้าในตะกร้า
 * 
 * @param sku รหัสการซื้อ  "BOGO", "BULK"
 * @param name ชื่อสินค้า
 * @param price ราคาต่อชิ้น
 * @param quantity จำนวนที่ซื้อ
 */
public record CartItem(String sku, String name, double price, int quantity) {
    
    /**
     * Constructor สำหรับตรวจสอบความถูกต้องของข้อมูล
     */
    public CartItem {
        if (sku == null) {
            throw new IllegalArgumentException("SKU cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }
}