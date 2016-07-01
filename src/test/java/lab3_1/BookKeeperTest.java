package lab3_1;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;
import org.mockito.Mockito;

import builder.InvoiceBuilder;
import builder.InvoiceRequestBuilder;
import builder.RequestItemBuilder;
import builder.TaxBuilder;
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

import org.junit.Before;

public class BookKeeperTest 
{
	InvoiceFactory invoiceFactory;
	BookKeeper bookKeeper;
	InvoiceRequest invoiceRequest;
	TaxPolicy taxPolicy;

	@Before
	public void setUp() throws Exception 
	{
		invoiceRequest = new InvoiceRequestBuilder().build();
		
		Invoice invoice = new InvoiceBuilder().withClient(invoiceRequest.getClient()).build();

		Tax tax = new TaxBuilder().build();

		// given, always same
		invoiceFactory = Mockito.mock(InvoiceFactory.class);
		bookKeeper = new BookKeeper(invoiceFactory);
		taxPolicy = Mockito.mock(TaxPolicy.class);

		Mockito.when(invoiceFactory.create(invoiceRequest.getClientData())).thenReturn(invoice);

		Mockito.when(taxPolicy.calculateTax(Mockito.any(ProductType.class), Mockito.any(Money.class))).thenReturn(tax);
	}

	@Test
	public final void test_setOne_getOne()  
	{
		// when
		RequestItem requestItem = new RequestItemBuilder().build();
		invoiceRequest.add(requestItem);
		Invoice newInvoice = bookKeeper.issuance(invoiceRequest, taxPolicy);

		// then
		assertThat(newInvoice.getItems().size(), is(1));
	}

	@Test
	public final void test_setTwo_getCountTax() 
	{
		// when
		RequestItem firstItem = new RequestItemBuilder().build();
		RequestItem secondItem = new RequestItemBuilder().build();
		invoiceRequest.add(firstItem);
		invoiceRequest.add(secondItem);
		bookKeeper.issuance(invoiceRequest, taxPolicy);

		// then
		Mockito.verify(taxPolicy, Mockito.times(2)).calculateTax(Mockito.any(ProductType.class),Mockito.any(Money.class));
	}
}
