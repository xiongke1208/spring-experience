import com.counect.cube.daservice.entity.Receipt


Receipt receipt = new Receipt()
receipt.receipttype = "11"
receipt.extend1 = "RM 11"
receipt.extend4 = "0.01"
def text = "TotaIxcIuding6%GS1.1 6%GST1.2"
def fieldValue = ""


def regex = /TotaIxcIuding6%GS([0-9]*\.[0-9]*)/;
def regex2 = /6%GST([0-9]*\.[0-9]*)/;
def matches = text =~ regex;
def matches2 = text =~ regex2;
def amount = new BigDecimal(0);
if(matches.find()) {
    amount = amount.add(new BigDecimal(matches.group(1)));
}
if(matches2.find()) {
    amount = amount.add(new BigDecimal(matches2.group(1)));
}
fieldValue = amount.toString();
//return receipt



println fieldValue
println receipt.extend4