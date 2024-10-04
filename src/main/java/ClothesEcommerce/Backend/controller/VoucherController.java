package ClothesEcommerce.Backend.controller;

import ClothesEcommerce.Backend.dto.ProductDTO;
import ClothesEcommerce.Backend.dto.VoucherDTO;
import ClothesEcommerce.Backend.entity.Voucher;
import ClothesEcommerce.Backend.model.ApiResponse;
import ClothesEcommerce.Backend.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vouchers")
@RequiredArgsConstructor
@CrossOrigin
public class VoucherController {

    private final VoucherService voucherService;
    @GetMapping("/all")
    public List<VoucherDTO> getAllVoucher() {
        return voucherService.getAllVoucher();
    }

    @PostMapping("/create")
    public ApiResponse<VoucherDTO> createVoucher(
            @RequestBody VoucherDTO voucherDTO
    ) {
        ApiResponse<VoucherDTO> apiResponse = new ApiResponse<>();
        apiResponse.setResult(voucherService.createVoucher(voucherDTO));
        return apiResponse;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteVoucher(@PathVariable String id) {
        voucherService.deleteVoucher(id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResult("Delete voucher successfully");
        return apiResponse;
    }
}
