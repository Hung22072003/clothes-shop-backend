package ClothesEcommerce.Backend.dto;

import ClothesEcommerce.Backend.entity.Voucher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {
    private String id;
    private String start_date;
    private String end_date;
    private int quantity;
    private Long min_total;
    private Long discount_price;

    public VoucherDTO(Voucher voucher) {
        this.id = voucher.getId_voucher();
        this.start_date = voucher.getStart_date().toString();
        this.end_date = voucher.getEnd_date().toString();
        this.quantity = voucher.getQuantity();
        this.min_total = voucher.getMin_total();
        this.discount_price = voucher.getDiscount_price();
    }
}
