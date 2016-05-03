package lab3_1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.application.api.handler.AddProductCommandHandler;
import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import pl.com.bottega.ecommerce.sales.domain.equivalent.SuggestionService;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import pl.com.bottega.ecommerce.sales.domain.reservation.Reservation;
import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import pl.com.bottega.ecommerce.system.application.SystemContext;

public class AddProductCommandHandlerTest 
{
	@Mock
	ReservationRepository 		reservationRepository;
	
	@Mock
	ProductRepository 			productRepository;
	
	@Mock
	SuggestionService 			suggestionService;
	
	@Mock
	ClientRepository 			clientRepository;
	
	@Mock
	SystemContext 				systemContext;

	@InjectMocks
	AddProductCommandHandler 	addProductCommandHandler = new AddProductCommandHandler();
	
	Id 							orderID;
	Product 					product;
	Reservation 				reservation;
	AddProductCommand 			addProductCommand;

	@Test
	public void test() 
	{
		fail("Not yet implemented");
	}
}
