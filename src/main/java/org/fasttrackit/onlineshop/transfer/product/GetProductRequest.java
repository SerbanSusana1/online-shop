package org.fasttrackit.onlineshop.transfer.product;

public class GetProductRequest {
    private  String  partialName;
    //wraper calsses accept null values as well
    private  Integer minimumQantity;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public Integer getMinimumQantity() {
        return minimumQantity;
    }

    @Override
    public String toString() {
        return "GetProductRequest{" +
                "partialName='" + partialName + '\'' +
                ", minimumQantity=" + minimumQantity +
                '}';
    }

    public void setMinimumQantity(Integer minimumQantity) {
        this.minimumQantity = minimumQantity;
    }
}
