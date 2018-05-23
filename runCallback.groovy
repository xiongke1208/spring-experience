//import com.counect.cube.daservice.entity.Receipt
//
//
//Receipt receipt = new Receipt()
//receipt.receipttype = "11"
//receipt.extend1 = "RM 11"
//receipt.extend4 = "0.01"
def text = "84 SUB TOTAL 2,275.80 Service Chrg 10% 227.58"
def fieldValue = ""





def amount_regex1 = java.util.regex.Pattern.compile("SUB\\s*TOTAL\\s*(\\d+,?\\d+\\.\\d+)");
def amount_regex2 = java.util.regex.Pattern.compile("Service\\s*Chrg\\s*\\d*%\\s*(\\d+\\.\\d+)");
def matches1 = text =~ amount_regex1;
def matches2 = text =~ amount_regex2;
def amount = new BigDecimal(0);
if(matches1.find()){
    amount =amount.add(new BigDecimal(String.valueOf(matches1[0][1]).replace(",","")));
    fieldValue = amount.toString();
}
if(matches2.find()){
    amount = amount.add(new BigDecimal(matches2[0][1]));
    fieldValue = amount.toString();
}



println fieldValue
//println receipt.extend4