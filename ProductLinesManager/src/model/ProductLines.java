package model;

public class ProductLines {
    private String productLine;
    private String textDescription;
    private String htmlDescription;

    public ProductLines() {
    }

    public ProductLines(String productLine) {
        this.productLine = productLine;
    }

    public ProductLines(String productLine, String textDescription, String htmlDescription) {
        this.productLine = productLine;
        this.textDescription = textDescription;
        this.htmlDescription = htmlDescription;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDesciption) {
        this.textDescription = textDesciption;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDesciption) {
        this.htmlDescription = htmlDesciption;
    }

    @Override
    public String toString() {
        return
                "productLine: " + productLine +".\n"
               +"textDescription: " + textDescription + ".\n"
               +"htmlDescription: " + htmlDescription + ".\n";
    }

    public void show() {
        System.out.println("Product Line: " + getProductLine());
        System.out.println("Text Description: " + getTextDescription());
        System.out.println("HTML Description: " + getHtmlDescription());
    }
}
