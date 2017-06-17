package com.thinkgem.jeesite.modules.ftc.dto.product;

import com.thinkgem.jeesite.modules.ftc.entity.product.Image;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.entity.product.ProductSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bingbing on 2017/6/14.
 */
public class ProductDto {
    private String  id;//id
    private String did;
    private String name;//名称
    private String desc;//描述
    private double  price;//价格
    private int  count;//库存
    private boolean open;
    List<ImageDto> textures;//图片
    List<ProductSpecDto> clothing;//规格

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<ImageDto> getTextures() {
        return textures;
    }

    public void setTextures(List<ImageDto> textures) {
        this.textures = textures;
    }

    public List<ProductSpecDto> getClothing() {
        return clothing;
    }

    public void setClothing(List<ProductSpecDto> clothing) {
        this.clothing = clothing;
    }
    public void convert(Product product){
        this.id=product.getId();
        this.name=product.getName();
        this.desc=product.getIntroduce();
        this.price=product.getShowPrice();
        List<ImageDto> images=new ArrayList<>();
        for(Image image:product.getImages()){
            ImageDto imageDto=new ImageDto();
            imageDto.convert(image);
        }
        this.textures=images;
        List<ProductSpecDto> specDtos=new ArrayList<>();
        for(ProductSpec productSpec:product.getSpecs()){
            ProductSpecDto dto=new ProductSpecDto();
            dto.convert(productSpec);
            specDtos.add(dto);
        }
        this.setClothing(specDtos);


    }
}
