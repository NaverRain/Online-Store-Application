package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.ProductDto;
import com.naverrain.persistence.enteties.Product;
import com.naverrain.persistence.enteties.impl.DefaultProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDtoToProductConverter {

    private CategoryDtoToCategoryConverter categoryConverter;
    {
        categoryConverter = new CategoryDtoToCategoryConverter();
    }

    public List<Product> convertProductDtosToProducts(List<ProductDto> productDtos){
        List<Product> products = new ArrayList<>();

        for (ProductDto productDto : productDtos){
            products.add(convertProductDtoToProduct(productDto));
        }
        return products;
    }

    public Product convertProductDtoToProduct(ProductDto productDto){
        Product product = new DefaultProduct();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice().doubleValue());

        if (productDto.getCategoryDto() != null){
            product.setCategoryName(productDto.getCategoryDto().getCategoryName());
        }

        return product;
    }

    public List<ProductDto> convertProductsToProductDtos(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products){
            productDtos.add(convertProductToProductDto(product));
        }

        return productDtos;
    }

    private ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(BigDecimal.valueOf(product.getPrice()));
        productDto.setProductName(product.getProductName());
        productDto.setCategoryDto(categoryConverter.convertCategoryNameToCategoryDtoWithOnlyName(product.getCategoryName()));
        return  productDto;
    }
}
