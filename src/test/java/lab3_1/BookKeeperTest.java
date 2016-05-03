package lab3_1;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.sales.domain.invoicing.BookKeeper;
import pl.com.bottega.ecommerce.sales.domain.invoicing.Invoice;
import pl.com.bottega.ecommerce.sales.domain.invoicing.InvoiceFactory;
import pl.com.bottega.ecommerce.sales.domain.invoicing.InvoiceRequest;
import pl.com.bottega.ecommerce.sales.domain.invoicing.TaxPolicy;
import pl.com.bottega.ecommerce.sales.domain.invoicing.RequestItem;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class BookKeeperTest 
{
	BookKeeper bookKeeper;
	
	ClientData 		clientData;
	Invoice 		invoice;
	Invoice 		newInvoice;
	InvoiceRequest 	invoiceRequest;
	Money 			price;
	ProductData 	productData;
	RequestItem 	item;
	InvoiceFactory 	invoiceFactory;
	TaxPolicy 		taxPolicy;
	
	@Test
	public final void test_setOne_getOne() 
	{

	}
}
