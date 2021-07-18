package net.xdclass.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author WJH
 * @date 2021/7/18 下午6:48
 * @QQ 1151777592
 * 购物车
 */
public class CartVO {

    /**
     * 购物项
     */
    @JsonProperty("cart_items")
    private List<CartItemVO> cartItems;

    /**
     *购买总件数
     */
    @JsonProperty("total_num")
    private Integer totalNum;

    /**
     * 购物车总价
     */
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    /**
     * 购物车实际支付价格
     */
    @JsonProperty("real_pay_amount")
    private BigDecimal realPayAmount;

    /**
     * 总件数
     * @return
     */
    public Integer getTotalNum() {
        if (this.cartItems!=null){
            int total = cartItems.stream().mapToInt(CartItemVO::getBuyNum).sum();
            return total;
        }
        return 0;
    }

    /**
     * 总价格
     * @return
     */
    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal(0);
        if (this.cartItems!=null){
            for (CartItemVO cartItemVO : cartItems){
                BigDecimal voTotalAmount = cartItemVO.getTotalAmount();
                amount = amount.add(voTotalAmount);
            }
        }
        return amount;
    }

    /**
     * 购物车里实际支付的价格
     * @return
     */
    public BigDecimal getRealPayAmount() {
        BigDecimal amount = new BigDecimal(0);
        if (this.cartItems!=null){
            for (CartItemVO cartItemVO : cartItems){
                BigDecimal voTotalAmount = cartItemVO.getTotalAmount();
                amount = amount.add(voTotalAmount);
            }
        }
        return amount;
    }

    public List<CartItemVO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemVO> cartItems) {
        this.cartItems = cartItems;
    }
}
