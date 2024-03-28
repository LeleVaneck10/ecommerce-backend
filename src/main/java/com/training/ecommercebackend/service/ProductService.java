package com.training.ecommercebackend.service;

import com.training.ecommercebackend.controller.ProductController.ResponseProduct;
import com.training.ecommercebackend.exceptions.ProductNotFoundExeption;
import com.training.ecommercebackend.model.Category;
import com.training.ecommercebackend.model.Product;
import com.training.ecommercebackend.repository.CategoryRepository;
import com.training.ecommercebackend.repository.ProductRepository;
import com.training.ecommercebackend.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository , CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

//    public Product saveProduct(Product product){
//
//        return productRepository.save(product);
//    }



    public void saveProduct(String name , String description , Long categoryId , BigDecimal price , MultipartFile imageFile) throws IOException {

        String imagePath = null;
        Product product = new Product();

        Optional<Category> theCategory =  categoryRepository.findById(categoryId);


        if(theCategory.isPresent()){

            if ((imageFile != null) && (!imageFile.isEmpty())) {

                imagePath = ImageUtil.saveImage(imageFile);
            }

            product.setName(name);
            product.setDescription(description);
            product.setCategory(theCategory.get());
            product.setPrice(price);
            product.setImagePath(imagePath);

            theCategory.get().addProduct(product);

            productRepository.save(product);

        }else{
            throw new ProductNotFoundExeption("saved fall category with "+categoryId+" not found !");
        }


    }




    public ResponseProduct findById(long id) {

        ResponseProduct theProduct = new ResponseProduct();
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            theProduct.setName(product.get().getName());
            theProduct.setDescription(product.get().getDescription());
            theProduct.setPrice(product.get().getPrice());
            theProduct.setImagePath(product.get().getImagePath());
            theProduct.setCategory(product.get().getCategory().getName());
        }
        else {
            throw new ProductNotFoundExeption(" PRODUCT witch the "+id+" NOT FOUND !");
        }

        return theProduct;
    }

    public  void deleteProductById( Long id) throws IOException , ProductNotFoundExeption{

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()){

            ImageUtil.deleteFile(product.get().getImagePath());

            productRepository.delete(product.get());
        }

        else {

            throw new ProductNotFoundExeption("product with the id: "+id+" not found !");
        }

    }

    public void updateProduct() {
        
    }

    public List<ResponseProduct> getAllProduct() throws ProductNotFoundExeption{

       List<Product> products = productRepository.findAll();

       ResponseProduct responseProduct  = new ResponseProduct();

       List<ResponseProduct> theProducts = null ;

       if (!products.isEmpty()){

           for (Product temp : products){

               responseProduct.setName(temp.getName());
               responseProduct.setDescription(temp.getDescription());
               responseProduct.setPrice(temp.getPrice());
               responseProduct.setCategory(temp.getCategory().getName());
               responseProduct.setImagePath(temp.getImagePath());

               theProducts.add(responseProduct);
           }

       }else{
           throw new ProductNotFoundExeption("product not found !");

       }

       return  theProducts;
    }
}
