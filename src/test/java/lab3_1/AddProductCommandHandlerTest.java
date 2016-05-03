package lab3_1;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.application.api.handler.AddProductCommandHandler;
import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import pl.com.bottega.ecommerce.sales.domain.equivalent.SuggestionService;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sales.domain.reservation.Reservation;
import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import pl.com.bottega.ecommerce.sharedkernel.Money;
import pl.com.bottega.ecommerce.sales.domain.reservation.Reservation.ReservationStatus;
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
	Id							productID;
	int 						quantity;
	ReservationStatus 			reservationStatus;
	ClientData 					clientData; 
	Date 						createDate;
	
	Product 					product;
	Reservation 				reservation;
	AddProductCommand 			addProductCommand;

	@Test
	public final void test_hasReservationBeedSaved() 
	{
		orderID 			= Id.generate();
		productID			= Id.generate();
		quantity 			= 1;
		reservationStatus	= ReservationStatus.OPENED;
		clientData 			= new ClientData(Id.generate(), "nieistotne");
		createDate			= new Date();
		
		product 			= new Product(Id.generate(),new Money(10d),"nieistotne",ProductType.STANDARD);
		reservation 		= new Reservation(Id.generate(),reservationStatus,clientData,createDate);
		addProductCommand 	= new AddProductCommand(orderID, productID, quantity);

		MockitoAnnotations.initMocks(addProductCommandHandler);
		
		Mockito.when(reservationRepository.load(Mockito.any(Id.class))).thenReturn(reservation);
		Mockito.when(productRepository.load(Mockito.any(Id.class))).thenReturn(product);
		
		addProductCommandHandler.handle(addProductCommand);

		Mockito.verify(reservationRepository, Mockito.times(1)).save(reservation);
	}
}
