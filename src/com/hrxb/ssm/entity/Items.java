package com.hrxb.ssm.entity;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.hrxb.ssm.utils.Validator1;
import com.hrxb.ssm.utils.Validator2;

public class Items {
    private Integer id;

    @Size(min=1,max=10,message="{items.itemsname.message}",groups={Validator1.class})
    private String itemsname;

    @Max(value=999999999,message="{items.price.max.message}",groups={Validator2.class})
    @Min(value=1,message="{items.price.min.message}",groups={Validator2.class})
    private Float price;

    private String pic;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemsname() {
        return itemsname;
    }

    public void setItemsname(String itemsname) {
        this.itemsname = itemsname == null ? null : itemsname.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

	@Override
	public String toString() {
		return "Items [id=" + id + ", itemsname=" + itemsname + ", price=" + price + ", pic=" + pic + ", createtime="
				+ createtime + ", detail=" + detail + "]";
	}
    
    
}