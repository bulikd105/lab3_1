package lab3_1;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;
import org.mockito.Mockito;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.invoicing.BookKeeper;
import pl.com.bottega.ecommerce.sales.domain.invoicing.Invoice;
import pl.com.bottega.ecommerce.sales.domain.invoicing.InvoiceFactory;
import pl.com.bottega.ecommerce.sales.domain.invoicing.InvoiceRequest;
import pl.com.bottega.ecommerce.sales.domain.invoicing.TaxPolicy;
import pl.com.bottega.ecommerce.sales.domain.invoicing.RequestItem;
import pl.com.bottega.ecommerce.sales.domain.invoicing.Tax;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
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
		clientData 		= new ClientData(Id.generate(), "nieistotne");
		invoice 		= new Invoice(Id.generate(), clientData);
		invoiceRequest 	= new InvoiceRequest(clientData);
		price 			= new Money(10);
		productData 	= new ProductData(Id.generate(), price, "nieistotne", ProductType.DRUG, new Date());
		item 			= new RequestItem(productData, 0, new Money(10));
		
		invoiceRequest.add(item);

		invoiceFactory 	= Mockito.mock(InvoiceFactory.class);
		taxPolicy 		= Mockito.mock(TaxPolicy.class);
		
		Mockito.when(invoiceFactory.create(invoiceRequest.getClientData())).thenReturn(invoice);
		Mockito.when(taxPolicy.calculateTax(ProductType.DRUG, price)).thenReturn(new Tax(new Money(1d), "nieistotne"));
		
		bookKeeper 		= new BookKeeper(invoiceFactory);
		newInvoice 		= bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		assertThat(newInvoice.getItems().size(), is(1));
	}
	
	@Test
	public final void test_setTwo_getCountTax() {
		clientData 		= new ClientData(Id.generate(), "nieistotne");
		invoice 		= new Invoice(Id.generate(), clientData);
		invoiceRequest 	= new InvoiceRequest(clientData);
		price 			= new Money(10);
		productData 	= new ProductData(Id.generate(), price, "nieistotne", ProductType.DRUG, new Date());
		item 			= new RequestItem(productData, 0, new Money(10));
		
		invoiceRequest.add(item);
		invoiceRequest.add(item);

		invoiceFactory 	= Mockito.mock(InvoiceFactory.class);
		taxPolicy 		= Mockito.mock(TaxPolicy.class);
		
		Mockito.when(invoiceFactory.create(invoiceRequest.getClientData())).thenReturn(invoice);
		Mockito.when(taxPolicy.calculateTax(ProductType.DRUG, price)).thenReturn(new Tax(new Money(1d), "nieistotne"));
		
		bookKeeper 		= new BookKeeper(invoiceFactory);
		newInvoice 		= bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		Mockito.verify(taxPolicy, Mockito.times(2)).calculateTax(Mockito.any(ProductType.class), Mockito.any(Money.class));
	}
}