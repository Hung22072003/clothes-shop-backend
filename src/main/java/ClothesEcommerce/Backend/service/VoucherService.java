package ClothesEcommerce.Backend.service;

import ClothesEcommerce.Backend.dto.VoucherDTO;
import ClothesEcommerce.Backend.entity.Voucher;
import ClothesEcommerce.Backend.exception.AppException;
import ClothesEcommerce.Backend.exception.ErrorCode;
import ClothesEcommerce.Backend.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;
    public List<VoucherDTO> getAllVoucher() {
        return voucherRepository.findAll().stream().map(VoucherDTO::new).toList();
    }

    public VoucherDTO createVoucher(VoucherDTO voucherDTO) {
        if(voucherRepository.existsById(voucherDTO.getId())) throw new AppException(ErrorCode.VOUCHER_EXIST);
        voucherRepository.save(new Voucher(voucherDTO));
        return voucherDTO;
    }

    public void deleteVoucher(String id) {
        voucherRepository.deleteById(id);
    }
}
