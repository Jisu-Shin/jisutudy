package com.jisutudy.service;

import com.jisutudy.domain.Booking;
import com.jisutudy.domain.BookingStatus;
import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.performance.Concert;
import com.jisutudy.domain.performance.Item;
import com.jisutudy.exception.NotEnoughStockException;
import com.jisutudy.repository.BookingSearch;
import com.jisutudy.repository.JpaBookingRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class BookingServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    BookingService bookingService;
    
    @Autowired
    JpaBookingRepository repository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Cust cust = createCust("고객1", "01012345678");

        Item item = createItem("콘서트", 150000, 20);

        int bookingCount = 2;

        //when
        Long bookingId = bookingService.book(cust.getId(), item.getId(), bookingCount);

        //then
        Booking getBooking = repository.findById(bookingId)
                .orElseThrow(()-> new IllegalArgumentException());

        assertEquals(BookingStatus.BOOK, getBooking.getStatus());
        assertEquals(bookingCount*150000, getBooking.getTotalPrice());
        assertEquals(18, item.getStockQuantity());
    }

    private Item createItem(String name, int price, int stockQuantity) {
        Item item = new Concert();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        em.persist(item);
        return item;
    }

    private Cust createCust(String name, String phoneNumber) {
        Cust cust = Cust.createCust(name, phoneNumber, CustSmsConsentType.ALL_ALLOW);
        em.persist(cust);
        return cust;
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Cust cust = createCust("고객1", "01012345678");
        Item item = createItem("제니 콘서트",220000,50);

        int bookingCount = 4;

        Long bookingId = bookingService.book(cust.getId(), item.getId(), bookingCount);

        //when
        bookingService.cancelBooking(bookingId);

        //then
        Booking getBooking = repository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException());

        assertEquals(BookingStatus.CANCEL, getBooking.getStatus());
        assertEquals(50, item.getStockQuantity());


    }

    @Test
    public void 예약주문_수량초과() throws Exception {
        //given
        Cust cust = createCust("고객1", "01012345678");
        Item item = createItem("콘서트", 150000, 1);

        int bookingCount = 4;

        //when

        //then
        assertThrows(NotEnoughStockException.class, () -> bookingService.book(cust.getId(), item.getId(), bookingCount));
    }


    @Test
    public void 예약검색() throws Exception {
        //given
        Cust cust1 = createCust("고객1", "01012345678");
        Cust cust2 = createCust("고객2", "01098745612");
        Item item = createItem("지디콘서트",10000,20);
        bookingService.book(cust1.getId(), item.getId(), 1);
        bookingService.book(cust2.getId(), item.getId(), 4);

        //when
        BookingSearch bookingSearch = new BookingSearch();
        bookingSearch.setCustName("고");
        List<Booking> bookings = repository.findAll(bookingSearch);
        bookings.stream().forEach(b-> System.out.println("b = " + b));
        assertEquals(2, bookings.size());

        bookingSearch = new BookingSearch();
        bookingSearch.setCustName("1");
        bookings = repository.findAll(bookingSearch);

        //then
        assertEquals(1, bookings.size());
    }

    @Test
    public void 검색조건없을경우() throws Exception {
        //given
        Cust cust1 = createCust("고객1", "01012345678");
        Cust cust2 = createCust("고객2", "01098745612");
        Item item = createItem("지디콘서트",10000,20);
        bookingService.book(cust1.getId(), item.getId(), 1);
        bookingService.book(cust2.getId(), item.getId(), 4);
        bookingService.book(cust1.getId(), item.getId(), 2);

        //when
        BookingSearch bookingSearch = new BookingSearch();
        List<Booking> bookings = bookingService.findBooking(bookingSearch);

        //then
        assertEquals(3,bookings.size());
    }

    @Test
    public void 예약중_검색() throws Exception {
        //given
        Cust cust1 = createCust("고객1", "01012345678");
        Cust cust2 = createCust("고객2", "01098745612");
        Item item = createItem("지디콘서트",10000,20);
        bookingService.book(cust1.getId(), item.getId(), 1);
        bookingService.book(cust2.getId(), item.getId(), 4);
        Long bookId = bookingService.book(cust1.getId(), item.getId(), 2);

        bookingService.cancelBooking(bookId);

        //when
        BookingSearch bookingSearch = new BookingSearch();
        bookingSearch.setBookingStatus(BookingStatus.BOOK);
        List<Booking> bookings = bookingService.findBooking(bookingSearch);
        System.out.println(bookings.size());

        //then
        assertEquals(2,bookings.size());
    }

    @Test
    public void 예약취소_검색() throws Exception {
        //given
        Cust cust1 = createCust("고객1", "01012345678");
        Cust cust2 = createCust("고객2", "01098745612");
        Item item = createItem("지디콘서트",10000,20);
        Long bookId1 = bookingService.book(cust1.getId(), item.getId(), 1);
        Long bookId2 = bookingService.book(cust2.getId(), item.getId(), 4);
        Long bookId3 = bookingService.book(cust1.getId(), item.getId(), 2);

        bookingService.cancelBooking(bookId1);
        bookingService.cancelBooking(bookId2);
        bookingService.cancelBooking(bookId3);

        //when
        BookingSearch bookingSearch = new BookingSearch();
        bookingSearch.setBookingStatus(BookingStatus.BOOK);
        List<Booking> bookings = bookingService.findBooking(bookingSearch);
        System.out.println(bookings.size());

        bookingSearch = new BookingSearch();
        bookingSearch.setBookingStatus(BookingStatus.CANCEL);
        List<Booking> cancelBookings = bookingService.findBooking(bookingSearch);

        //then
        assertEquals(3,cancelBookings.size());
    }



}