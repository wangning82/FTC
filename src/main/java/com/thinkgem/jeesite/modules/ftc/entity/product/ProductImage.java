/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.product;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 图片Entity
 * @author wangqingxiang
 * @version 2017-06-01
 */
public class ProductImage extends DataEntity<ProductImage> {

	private static final long serialVersionUID = 1L;
	private ProductSpec productSpec;		// 商品规格

	private Position position;		// 位置id


	private String imgUrl;//大图地址
	private String imgNailUrl;//小图地址

	public ProductSpec getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(ProductSpec productSpec) {
		this.productSpec = productSpec;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getImgNailUrl() {
		return imgNailUrl;
	}

	public void setImgNailUrl(String imgNailUrl) {
		this.imgNailUrl = imgNailUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}