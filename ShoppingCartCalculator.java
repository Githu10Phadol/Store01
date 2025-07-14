import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * คำนวณราคาสุทธิของสินค้าในตะกร้า
     * 
     * กฎการคำนวณ:
     * - ราคาพื้นฐาน: price × quantity
     * - กฎส่วนลดที่ 1 (BOGO): สำหรับสินค้าที่มี sku = "BOGO"
     *   ใช้โปรโมชันซื้อ 1 แถม 1 (ซื้อ 2 จ่าย 1, ซื้อ 3 จ่าย 2, ซื้อ 4 จ่าย 2)
     *   วิธีคำนวณ: จ่าย Math.ceil(quantity / 2.0) ชิ้น
     * - กฎส่วนลดที่ 2 (BULK): สำหรับสินค้าที่มี sku = "BULK"
     *   หากซื้อตั้งแต่ 6 ชิ้นขึ้นไป จะได้รับส่วนลด 10% จากราคารวมของสินค้านั้น
     * 
     * การจัดการกรณีพิเศษ:
     * - หาก items เป็น null หรือ empty  คืนค่า 0.0
     * - หาก CartItem มี price หรือ quantity ติดลบ  นับเป็น 0 (ไม่คิดราคา)
     * - หาก CartItem เป็น null  ข้ามไปสินค้าตัวต่อไป
     * 
     * @param items รายการสินค้าในตะกร้า
     * @return ราคารวมของสินค้าทั้งหมด
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        
        double totalPrice =0.0;
        
        for (CartItem item : items) {
            // ข้ามสินค้าที่เป็น null
            if (item == null) {
                continue;
            }
            
            // กรณีข้ามสินค้าที่มีราคาหรือจำนวนติดลบ
            if (item.price() < 0 || item.quantity() < 0) {
                continue;
            }
            
            double itemTotal = 0.0;
            
            // คำนวณตามกฎส่วนลด
            if ("BOGO".equals(item.sku())) {
                // กฎส่วนลด BOGO: ซื้อ 1 แถม 1
                int payableQuantity = (int)Math.ceil(item.quantity() / 2.0);
                itemTotal = item.price() * payableQuantity;
            } else if ("BULK".equals(item.sku())) {
                // กฎส่วนลด BULK: ซื้อ >= 6 ชิ้น ลด 10%
                itemTotal = item.price()*item.quantity();
                if (item.quantity() >= 6) {
                    itemTotal = itemTotal * 0.9; // ลด 10%
                }
            } else {
               
                itemTotal = item.price() * item.quantity(); // ราคาปกติ
            }
            
            totalPrice += itemTotal;
        }
        
        return totalPrice;
    }
}