package com.shivam.RideHailingService.Services;

import com.shivam.RideHailingService.models.DiscountVoucher;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class DiscountVoucherService {
    public DiscountVoucherService() {
    }

    public DiscountVoucher generateDiscountVoucher(Double maxDiscount, Double percentageOff) {
        String voucherID = UUID.randomUUID().toString();
        DiscountVoucher discountVoucher = new DiscountVoucher(voucherID, maxDiscount, percentageOff);
        return discountVoucher;
    }
}
