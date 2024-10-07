package ClothesEcommerce.Backend.service;

import ClothesEcommerce.Backend.dto.ColorDTO;
import ClothesEcommerce.Backend.dto.ProductDTO;
import ClothesEcommerce.Backend.dto.SizeColorDTO;
import ClothesEcommerce.Backend.dto.SizeDTO;
import ClothesEcommerce.Backend.entity.*;
import ClothesEcommerce.Backend.entity.embeddedID.ProductCategoryId;
import ClothesEcommerce.Backend.entity.embeddedID.ProductColorId;
import ClothesEcommerce.Backend.entity.embeddedID.ProductSizeColorId;
import ClothesEcommerce.Backend.exception.AppException;
import ClothesEcommerce.Backend.exception.ErrorCode;
import ClothesEcommerce.Backend.model.Pagination;
import ClothesEcommerce.Backend.model.PaginationObjectResponse;
import ClothesEcommerce.Backend.model.ProductRequest;
import ClothesEcommerce.Backend.repository.*;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductColorRepository productColorRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ProductSizeColorRepository productSizeColorRepository;
    private final ProductDescriptionRepository productDescriptionRepository;
    @Override
    public PaginationObjectResponse getProductsByCategory(int id, int page, int size) {
        Page<Product> productPage = productRepository.findProductsByCategory(id, PageRequest.of(page, size, Sort.by("id").descending()));
        return PaginationObjectResponse.builder().data(productPage.get().map(ProductDTO::new).toList()).pagination(new Pagination(productPage.getTotalElements(), productPage.getTotalPages(), productPage.getNumber())).build();
    }

    @Override
    public ProductDTO addProduct(ProductRequest productRequest, List<MultipartFile> images) {
        if(productRepository.existsByName(productRequest.getName())) throw new AppException(ErrorCode.PRODUCT_EXIST);
        Product product = new Product(productRequest.getName(), productRequest.getOriginal_price(), productRequest.getSell_price(), productRequest.getDiscount(), productRequest.getMaterial(), productRequest.getFit(), productRequest.getQuantity(), productRequest.getType());
        Product saveProduct = productRepository.save(product);
        if(productRequest.getCategories() != null) {
            productRequest.getCategories().forEach((category) -> {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryId(new ProductCategoryId(saveProduct.getId(),category));
                productCategory.setProduct(saveProduct);
                productCategory.setCategory(categoryRepository.findById(category).orElse(null));
                productCategoryRepository.save(productCategory);
            });
        }

        if(productRequest.getDescriptions() != null) {
            productRequest.getDescriptions().forEach((description) -> {
                ProductDescription productDescription = new ProductDescription(description);
                productDescription.setProduct(saveProduct);
                productDescriptionRepository.save(productDescription);
            });
        }

        if(productRequest.getColor_sizes() != null) {
            productRequest.getColor_sizes().forEach((colorSize) -> {
                Color color = colorRepository.findById(colorSize.getColor()).orElse(null);
                ProductColor productColor = new ProductColor();
                productColor.setProductColorId(new ProductColorId(saveProduct.getId(), colorSize.getColor()));
                productColor.setProduct(saveProduct);
                productColor.setColor(color);
                productColorRepository.save(productColor);

                colorSize.getValues().forEach((value) -> {
                    ProductSizeColor productSizeColor = new ProductSizeColor();
                    productSizeColor.setProductSizeColorId(new ProductSizeColorId(saveProduct.getId(), value.getType(), colorSize.getColor()));
                    productSizeColor.setProduct(saveProduct);
                    productSizeColor.setColor(color);
                    productSizeColor.setSize(sizeRepository.findById(value.getType()).orElse(null));
                    productSizeColor.setQuantity(value.getQuantity());
                    productSizeColorRepository.save(productSizeColor);
                });
            });
        }


        List<String> productImages = new ArrayList<String>();
        if(images != null) {
            List<CompletableFuture<String>> futures = images.stream()
                    .map(image -> CompletableFuture.supplyAsync(() -> {
                        try {
                            String url = cloudinary.getInstance().uploader().upload(image.getBytes(), ObjectUtils.asMap("folder", "clothes_shop/product",
                                    "resource_type", "image")).values().toArray()[3].toString();
                            productImageRepository.save(new ProductImage(url, saveProduct));
                            return url;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }))
                    .collect(Collectors.toList());

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            productImages.addAll(futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList()));
        }
        ProductDTO productDTO = new ProductDTO(saveProduct);
        productDTO.setImages(productImages);
        productDTO.setDescriptions(productRequest.getDescriptions());
        return productDTO;
    }

    @Override
    public ProductDTO   getProductById(int id) {
            Product foundProduct = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            List<SizeColorDTO> sizeColorDTOS = new ArrayList<SizeColorDTO>();
            List<ColorDTO> colorDTOS = foundProduct.getProductColors().stream().map((color) -> {
                sizeColorDTOS.add(new SizeColorDTO(color.getColor().getId()));
                return new ColorDTO(color);
            }).toList();

            colorDTOS.forEach((colorDTO) -> {
                List<ProductSizeColor> productSizeColors = productSizeColorRepository.findAllByProductAndColor(foundProduct, colorRepository.findById(colorDTO.getId()).orElse(null)).stream().sorted(Comparator.comparing((productSizeColor -> productSizeColor.getSize().getId()))).toList();
                sizeColorDTOS.get(sizeColorDTOS.indexOf(new SizeColorDTO(colorDTO.getId()))).setValues(productSizeColors.stream().map(SizeDTO::new).collect(Collectors.toList()));
            });
            ProductDTO productDTO = new ProductDTO(foundProduct);
            productDTO.setColors(colorDTOS);
            productDTO.setSizes(sizeColorDTOS);
            return productDTO;
    }

    @Override
    public PaginationObjectResponse searchProducts(String query, int page, int size) {
        if(query.isEmpty()) return PaginationObjectResponse.builder().build();
        Page<Product> productPage =  productRepository.findByNameContaining(query, PageRequest.of(page, size, Sort.by("id").descending()));
        return PaginationObjectResponse.builder().data(productPage.get().map(ProductDTO::new).toList()).pagination(new Pagination(productPage.getTotalElements(), productPage.getTotalPages(), productPage.getNumber())).build();
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        Product foundProduct = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        List<ProductImage> productImages = foundProduct.getProductImageList();
        deleteProductImagesOnCloud(productImages);
        productRepository.delete(foundProduct);
    }

    @Override
    public void deleteProductImagesOnCloud(List<ProductImage> productImages) {
        List<String> nameImages = getNameImages(productImages);
        List<CompletableFuture<Void>> deletionFutures = nameImages.stream()
                .map(name -> CompletableFuture.runAsync(() -> {
                    try {
                        cloudinary.getInstance().api().deleteResources(Arrays.asList(name),
                                ObjectUtils.asMap("type", "upload", "resource_type", "image"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }))
                .collect(Collectors.toList());

        CompletableFuture<Void> allDeletions = CompletableFuture.allOf(
                deletionFutures.toArray(new CompletableFuture[0]));

        try {
            allDeletions.get(); // Wait for all deletions to complete
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getNameImages(List<ProductImage> productImages) {
        List<String> nameImages = new ArrayList<String>();
        for(ProductImage productImage: productImages) {
            nameImages.add(getNameOneImage(productImage));
        }
        return nameImages;
    }

    @Override
    public String getNameOneImage(ProductImage productImage) {
        int lastSlashIndex = productImage.getName().lastIndexOf("/");

        // Cắt chuỗi từ vị trí sau dấu "/" cho đến hết chuỗi
        String fileNameWithExtension = productImage.getName().substring(lastSlashIndex + 1);

        // Tách phần tên file và phần mở rộng
        String[] parts = fileNameWithExtension.split("\\.");
        return "clothes_shop/product/" + parts[0];
    }
}
