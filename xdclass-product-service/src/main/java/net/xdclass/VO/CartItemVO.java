package net.xdclass.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author WJH
 * @date 2021/7/18 下午6:49
 * @QQ 1151777592
 * 购物项
 */
public class CartItemVO {

    /**
     * 商品ID
     */
    @JsonProperty("product_id")
    private Long productId;

    /**
     * 购买数量
     */
    @JsonProperty("buy_num")
    private Integer buyNum;

    /**
     * 商品标题
     */
    @JsonProperty("product_title")
    private String productTitle;

    /**
     * 商品图片
     */
    @JsonProperty("product_img")
    private String productImg;

    /**
     * 商品单价
     */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
     * 商品总价
     */
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 总价=单价*数量
     * @return
     */
    public BigDecimal getTotalAmount() {
        return this.amount.multiply(new BigDecimal(this.buyNum));
    }


}
